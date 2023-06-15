package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.uasp.file.cmd.VideoDownloadCommand;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.kayura.utils.HttpUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Component
public class VideoDownloadCommandHandler implements CommandHandler<VideoDownloadCommand, Void> {

  private static final Log logger = LogFactory.getLog(VideoDownloadCommandHandler.class);

  private final FileLinkManager linkManager;
  private final UploadHandlerDelegate uploadHandler;

  public VideoDownloadCommandHandler(FileLinkManager linkManager,
                                     UploadHandlerDelegate uploadHandler) {
    this.linkManager = linkManager;
    this.uploadHandler = uploadHandler;
  }

  @Transactional
  public Void execute(VideoDownloadCommand command) {

    HttpServletRequest request = command.getRequest();
    HttpServletResponse response = command.getResponse();
    String fileLinkId = command.getFileLinkId();

    try {

      // 查询出此 linkId 的物理文件路径
      DownloadFile downloadFile = linkManager.downloadFile(fileLinkId);
      if (downloadFile == null) {
        this.outErrorVideo(response);
        return null;
      }

      UploadHandler provider = this.uploadHandler.getProvider(downloadFile.getStoreType());
      File localFile = provider.readSingleFile(downloadFile);
      if (!localFile.exists()) {
        this.outErrorVideo(response);
        return null;
      }

      long contentLength = localFile.length(); // 字节总长度
      long startBytes = 0; // 下载开始字节
      long endBytes = contentLength; // 下载结束字节

      String range = request.getHeader("Range");
      if (StringUtils.hasText(range)) {

        // 响应头 contentRange
        if (logger.isDebugEnabled()) {
          logger.debug("Header('Range') => " + range);
        }

        if (range.startsWith("bytes=")) {

          String[] rangeBytes = range.substring("bytes=".length()).split("-");
          startBytes = Long.parseLong(rangeBytes[0]);
          if (rangeBytes.length > 1 && StringUtils.hasText(rangeBytes[1])) {
            endBytes = Long.parseLong(rangeBytes[1]);
          }

          // 处理响应头
          response.setHeader("Content-Range", "bytes " + startBytes + "-" + (endBytes - 1) + "/" + contentLength);
          response.setHeader("Accept-Ranges", "bytes");
          response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        }
      }

      String fileName = HttpUtils.encoderFileName(request, downloadFile.getDisplayName());

      response.setCharacterEncoding("UTF-8");
      response.setContentType(downloadFile.getContentType());
      response.setContentLengthLong(contentLength - startBytes);
      response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

      InputStream input = Files.newInputStream(localFile.toPath());
      StreamUtils.copyRange(input, response.getOutputStream(), startBytes, endBytes);
      input.close();

    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }
    return null;
  }

  private void outErrorVideo(HttpServletResponse response) {

    InputStream input = this.getClass().getResourceAsStream("/video/error.mp4");
    response.setContentType("video/mp4;charset=UTF-8");
    try {
      assert input != null;
      response.setContentLengthLong(StreamUtils.copy(input, response.getOutputStream()));
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
