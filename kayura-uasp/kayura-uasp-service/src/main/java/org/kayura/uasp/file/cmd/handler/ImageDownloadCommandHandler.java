/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.uasp.file.cmd.ImageDownloadCommand;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.ThumbnailQry;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.kayura.utils.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Component
public class ImageDownloadCommandHandler implements CommandHandler<ImageDownloadCommand, Void> {

  private final FileLinkManager linkManager;
  private final UploadHandlerDelegate uploadHandler;

  public ImageDownloadCommandHandler(FileLinkManager linkManager,
                                     UploadHandlerDelegate uploadHandler) {
    this.linkManager = linkManager;
    this.uploadHandler = uploadHandler;
  }

  @Transactional
  public Void execute(ImageDownloadCommand command) {

    String formula = command.getFormula();
    HttpServletResponse response = command.getResponse();
    List<String> imageTypes = command.getImageTypes();

    ThumbnailQry query = resolver(formula);

    if (query.getScale() != null && (query.getScale() < 1 || query.getScale() > 99)) {
      this.outErrorImage(response);
      return null;
    }

    DownloadFile download = linkManager.downloadFile(query.getLinkId());
    if (download == null) {
      this.outNoneImage(response);
      return null;
    }

    if (imageTypes.contains(download.getPostfix()) &&
      download.getContentType().contains("image")) {

      try {

        UploadHandler provider = uploadHandler.getProvider(download.getStoreType());
        File originalFile = provider.readSingleFile(download);

        File imageFile;
        if (query.isThumbnail()) {
          String thumbnailName = this.genThumbnailName(download, query);
          imageFile = new File(provider.getTempPath(), thumbnailName);
          Thumbnails.Builder<File> builder = Thumbnails.of(originalFile);
          // 调整图片尺寸
          if (query.isAdjustSize()) {
            int width = query.getWidth() != null ? query.getWidth() : query.getHeight();
            int height = query.getHeight() != null ? query.getHeight() : query.getWidth();
            builder.size(width, height);
            // 保持比例
            builder.keepAspectRatio(query.getRatio() != null && query.getRatio());
          } else if (query.getScale() != null) {
            // 按比例缩放
            builder.scale(query.getScale() * 0.01f);
          }
          builder.toFile(imageFile);
        } else {
          imageFile = originalFile;
        }

        if (imageFile.exists()) {
          try {
            response.setContentType(download.getContentType());
            response.setContentLengthLong(imageFile.length());
            response.setHeader("Cache-Control", "max-age=864000");
            InputStream input = Files.newInputStream(imageFile.toPath());
            StreamUtils.copy(input, response.getOutputStream());
            input.close();
          } catch (Exception ex) {
            outErrorImage(response);
          }
          return null;
        }

      } catch (Exception ex) {
        this.outErrorImage(response);
      }
    }

    this.outNoneImage(response);
    return null;
  }

  protected String genThumbnailName(DownloadFile download, ThumbnailQry thumbnail) {

    StringBuilder sb = new StringBuilder();

    sb.append(download.getFileId());
    if (thumbnail.isAdjustSize()) {
      int width = thumbnail.getWidth() != null ? thumbnail.getWidth() : thumbnail.getHeight();
      int height = thumbnail.getHeight() != null ? thumbnail.getHeight() : thumbnail.getWidth();
      sb.append("_").append(width).append("x").append(height);
      if (thumbnail.getRatio() != null && thumbnail.getRatio()) {
        sb.append("_Ratio");
      }
    } else if (thumbnail.getScale() != null) {
      sb.append("_Scale").append(thumbnail.getScale());
    }
    sb.append(".").append(download.getPostfix());

    return sb.toString();
  }

  protected ThumbnailQry resolver(String formula) {

    ThumbnailQry query = ThumbnailQry.create();

    String linkId;
    if (formula.contains(".")) {
      formula = formula.substring(0, formula.lastIndexOf('.'));
    }
    if (formula.endsWith("_R")) {
      query.setRatio(true);
      formula = formula.substring(0, formula.lastIndexOf("_R"));
    }
    if (formula.contains("_")) {
      String[] split = StringUtils.split(formula, "_");
      linkId = split[0];
      if (split[1].contains("x")) {
        String[] size = StringUtils.split(split[1], "x");
        query.setWidth(Integer.parseInt(size[0]));
        query.setHeight(Integer.parseInt(size[1]));
      } else {
        query.setWidth(Integer.parseInt(split[1]));
      }
    } else {
      linkId = formula;
    }
    query.setLinkId(linkId);
    return query;
  }

  protected void outErrorImage(HttpServletResponse response) {
    this.outImage(response, "/images/img_error.jpg");
  }

  protected void outNoneImage(HttpServletResponse response) {
    this.outImage(response, "/images/img_none.jpg");
  }

  protected void outImage(HttpServletResponse response, String imageUrl) {
    try {
      InputStream input = this.getClass().getResourceAsStream(imageUrl);
      if (input != null) {
        response.setContentType("image/jpeg;charset=UTF-8");
        StreamUtils.copy(input, response.getOutputStream());
        input.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
