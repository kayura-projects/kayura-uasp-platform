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
package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applibrary.UpdateModes;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.ops.cmd.DownloadAppStoreCommand;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.HttpUtils;
import org.kayura.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@Component
public class DownloadAppStoreCommandHandler implements CommandHandler<DownloadAppStoreCommand, Void> {

  private final AppStoreManager appStoreManager;
  private final FileLinkManager fileLinkManager;
  private final UploadHandlerDelegate uploadHandler;
  private final ObjectMapper objectMapper;

  public DownloadAppStoreCommandHandler(AppStoreManager appStoreManager,
                                        FileLinkManager fileLinkManager,
                                        UploadHandlerDelegate uploadHandler,
                                        ObjectMapper objectMapper) {
    this.appStoreManager = appStoreManager;
    this.fileLinkManager = fileLinkManager;
    this.uploadHandler = uploadHandler;
    this.objectMapper = objectMapper;
  }

  @Transactional
  public Void execute(DownloadAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String appCode = command.getAppCode();
    String releaseId = command.getReleaseId();
    HttpServletRequest request = command.getRequest();
    HttpServletResponse response = command.getResponse();

    AppStoreEntity entity = appStoreManager.selectOne(w -> {
      w.select(AppStoreEntity::getResourceId);
      if (StringUtils.hasText(releaseId)) {
        w.eq(AppStoreEntity::getReleased, releaseId);
      } else {
        w.eq(AppStoreEntity::getAppCode, appCode);
        w.eq(AppStoreEntity::getReleased, Boolean.TRUE);
        w.eq(AppStoreEntity::getMode, UpdateModes.FULL);
        w.orderByDesc(AppStoreEntity::getVersion);
      }
    });
    if (entity == null) {
      String outContent = "指定的发布记录不存在。";
      this.outHttpResult(response, outContent);
      return null;
    }

    DownloadFile downloadFile = fileLinkManager.downloadFile(entity.getResourceId());
    if (downloadFile == null) {
      String outContent = "期望下载的文件不存在。";
      this.outHttpResult(response, outContent);
      return null;
    }

    this.downloadFile(request, response, downloadFile);
    return null;
  }

  private void outHttpResult(HttpServletResponse response, String outContent) {

    try {
      String json = objectMapper.writeValueAsString(HttpResult.error(outContent));
      HttpUtils.outText(response, json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public void downloadFile(HttpServletRequest request, HttpServletResponse response, DownloadFile downloadFile) {

    try {
      UploadHandler provider = this.uploadHandler.getProvider(downloadFile.getStoreType());
      File downFile = provider.readSingleFile(downloadFile);
      String fileName = HttpUtils.encoderFileName(request, downloadFile.getDisplayName());
      response.setContentType(downloadFile.getContentType());
      response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
      InputStream is = Files.newInputStream(downFile.toPath());
      OutputStream out = response.getOutputStream();
      StreamUtils.copy(is, out);
      is.close();
    } catch (Exception ex) {
      HttpUtils.outText(response, ex.getMessage());
    }
  }
}
