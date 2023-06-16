/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
