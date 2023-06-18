/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
