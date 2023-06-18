/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
