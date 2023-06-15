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
