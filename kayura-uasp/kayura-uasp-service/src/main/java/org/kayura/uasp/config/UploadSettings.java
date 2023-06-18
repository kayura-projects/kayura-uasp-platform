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
