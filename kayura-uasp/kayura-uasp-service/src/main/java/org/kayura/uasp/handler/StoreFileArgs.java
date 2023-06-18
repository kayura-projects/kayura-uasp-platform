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

package org.kayura.uasp.handler;

import org.kayura.security.LoginUser;
import org.kayura.uasp.file.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public class StoreFileArgs {

  private LoginUser loginUser;
  private MultipartFile file;
  private byte[] fileContent;
  private String logicPath;
  private String fileId;
  private boolean encrypted;
  private UploadResult uploadResult;

  public static StoreFileArgs create() {
    return new StoreFileArgs();
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }

  public StoreFileArgs setLoginUser(LoginUser loginUser) {
    this.loginUser = loginUser;
    return this;
  }

  public MultipartFile getFile() {
    return file;
  }

  public StoreFileArgs setFile(MultipartFile file) {
    this.file = file;
    return this;
  }

  public byte[] getFileContent() {
    return fileContent;
  }

  public StoreFileArgs setFileContent(byte[] fileContent) {
    this.fileContent = fileContent;
    return this;
  }

  public UploadResult getUploadResult() {
    return uploadResult;
  }

  public StoreFileArgs setUploadResult(UploadResult uploadResult) {
    this.uploadResult = uploadResult;
    return this;
  }

  public String getLogicPath() {
    return logicPath;
  }

  public StoreFileArgs setLogicPath(String logicPath) {
    this.logicPath = logicPath;
    return this;
  }

  public String getFileId() {
    return fileId;
  }

  public StoreFileArgs setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public boolean getEncrypted() {
    return encrypted;
  }

  public StoreFileArgs setEncrypted(boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }
}
