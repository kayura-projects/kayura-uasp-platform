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

package org.kayura.uasp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "kayura.upload")
public class UploadSettings {

  private String alive;
  private List<String> imageTypes = Arrays.asList("jpg", "jpeg", "png", "gif");
  private List<String> editableTypes = Arrays.asList("doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt");
  private String storePath;
  private String tempPath;

  public String getAlive() {
    return alive;
  }

  public UploadSettings setAlive(String alive) {
    this.alive = alive;
    return this;
  }

  public List<String> getImageTypes() {
    return imageTypes;
  }

  public UploadSettings setImageTypes(List<String> imageTypes) {
    this.imageTypes = imageTypes;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public UploadSettings setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getTempPath() {
    return tempPath;
  }

  public UploadSettings setTempPath(String tempPath) {
    this.tempPath = tempPath;
    return this;
  }

  public List<String> getEditableTypes() {
    return editableTypes;
  }

  public UploadSettings setEditableTypes(List<String> editableTypes) {
    this.editableTypes = editableTypes;
    return this;
  }
}
