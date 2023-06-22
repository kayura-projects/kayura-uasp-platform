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

package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.config.UploadSettings;
import org.kayura.uasp.file.cmd.FileUploadCommand;
import org.kayura.uasp.file.UploadFile;
import org.kayura.uasp.file.UploadPayload;
import org.kayura.uasp.file.UploadResult;
import org.kayura.uasp.file.manage.UploadManager;
import org.kayura.uasp.handler.StoreFileArgs;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.kayura.utils.AesUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Component
public class FileUploadCommandHandler implements CommandHandler<FileUploadCommand, HttpResult> {

  private static final long MAX_MD5_LEN = 104857600L;
  private final UploadHandlerDelegate uploadHandler;
  private final UploadManager uploadManager;
  private final UploadSettings uploadSettings;

  public FileUploadCommandHandler(UploadHandlerDelegate uploadHandler,
                                  UploadManager uploadManager,
                                  UploadSettings uploadSettings) {
    this.uploadHandler = uploadHandler;
    this.uploadManager = uploadManager;
    this.uploadSettings = uploadSettings;
  }

  @Transactional
  public HttpResult execute(FileUploadCommand command) {

    LoginUser loginUser = command.getLoginUser();
    HttpServletRequest request = command.getRequest();
    MultipartFile file = command.getFile();
    UploadPayload payload = command.getPayload();
    List<String> editableTypes = Optional.ofNullable(command.getEditableTypes())
      .orElse(uploadSettings.getEditableTypes());

    if (file == null) {
      return HttpResult.error("未传入 file 文件内容。");
    }
    try {
      UploadFile uploadFile = UploadFile.of(payload);
      uploadFile.setUploaderId(loginUser.getUserId());
      uploadFile.setUploaderName(loginUser.getDisplayName());
      uploadFile.setFileName(Optional.ofNullable(uploadFile.getFileName()).orElse(file.getOriginalFilename()));
      uploadFile.setPostfix(FilenameUtils.getExtension(file.getOriginalFilename()));
      uploadFile.setFileSize(file.getSize());
      uploadFile.setContentType(file.getContentType());

      // 加密文件内容
      byte[] fileContent = file.getBytes();
      if (Boolean.TRUE.equals(uploadFile.getEncrypt())) {
        uploadFile.setSalt(RandomStringUtils.randomAscii(16));
        try {
          fileContent = AesUtils.aesEncrypt(file.getBytes(), uploadFile.getSalt());
        } catch (Exception ex) {
          return HttpResult.error("加密文件时发生异常。");
        }
      }

      // 记录只读文件hashcode
      if (uploadFile.getReadonly() == null) {
        uploadFile.setReadonly(!editableTypes.contains(uploadFile.getPostfix()));
      }
      if (Boolean.TRUE.equals(uploadFile.getReadonly())) {
        byte[] bytes;
        if (uploadFile.getFileSize() < MAX_MD5_LEN) {
          bytes = fileContent;
        } else {
          String rawValue = uploadFile.getContentType() + uploadFile.getFileSize() + uploadFile.getEncrypt();
          bytes = rawValue.getBytes();
        }
        uploadFile.setHashCode(DigestUtils.md5DigestAsHex(bytes));
      }

      // 保存上传文件信息
      UploadHandler uploadHandler = this.uploadHandler.getAlive();
      uploadFile.setStoreType(uploadHandler.getStoreType());
      uploadFile.setLogicPath(uploadHandler.getLogicPath());
      UploadResult uploadResult = uploadManager.storeUploadFile(uploadFile);
      if (uploadResult.isNew()) {
        try {
          StoreFileArgs args = StoreFileArgs.create()
            .setLoginUser(loginUser)
            .setFile(file)
            .setFileContent(fileContent)
            .setFileId(uploadResult.getFileId())
            .setLogicPath(uploadFile.getLogicPath())
            .setEncrypted(uploadResult.getEncrypted())
            .setUploadResult(uploadResult);
          uploadHandler.storeFile(args);
        } catch (Exception ex) {
          return HttpResult.error("写入储存文件时异常。", ex);
        }
      }
      return HttpResult.okBody(uploadResult.clearSensitive());
    } catch (Exception ex) {
      return HttpResult.error("上传发生异常。" + ex.getMessage());
    }
  }

}
