package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.except.ExceptUtils;
import org.kayura.uasp.file.cmd.FileDownloadCommand;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.DownloadQuery;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.kayura.utils.DateUtils;
import org.kayura.utils.HttpUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileDownloadCommandHandler implements CommandHandler<FileDownloadCommand, Void> {

  private final FileLinkManager linkManager;
  private final ModelMapper modelMapper;
  private final UploadHandlerDelegate uploadHandler;

  public FileDownloadCommandHandler(FileLinkManager linkManager,
                                    ModelMapper modelMapper,
                                    UploadHandlerDelegate uploadHandler) {
    this.linkManager = linkManager;
    this.modelMapper = modelMapper;
    this.uploadHandler = uploadHandler;
  }

  @Transactional
  public Void execute(FileDownloadCommand command) {

    HttpServletRequest request = command.getRequest();
    HttpServletResponse response = command.getResponse();
    DownloadQuery query = command.getQuery();

    List<FileLinkEntity> links = linkManager.selectList(w -> w.of(query));
    linkManager.updateDownloads(links);
    List<DownloadFile> downloads = links.stream().map(m -> modelMapper.map(m, DownloadFile.class))
      .collect(Collectors.toList());
    try {
      File downFile = null;
      String fileName = null;
      if (downloads.size() > 0) {
        UploadHandler provider = this.uploadHandler.getProvider(downloads.get(0).getStoreType());
        if (downloads.size() > 1) {
          downFile = provider.readMultiFile(downloads);
          fileName = "合并下载" + downloads.size() + "个文件_" + DateUtils.formatNow("yyyyMMddHHmmss") + ".zip";
          response.setContentType("application/octet-stream");
        } else if (downloads.size() == 1) {
          DownloadFile download = downloads.get(0);
          downFile = provider.readSingleFile(download);
          fileName = HttpUtils.encoderFileName(request, download.getDisplayName());
          response.setContentType(download.getContentType());
        } else {
          ExceptUtils.business("没有查询到需下载的文件列表。");
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream is = Files.newInputStream(downFile.toPath());
        OutputStream out = response.getOutputStream();
        StreamUtils.copy(is, out);
        is.close();
      }
    } catch (Exception ex) {
      HttpUtils.outText(response, ex.getMessage());
    }

    return null;
  }

}
