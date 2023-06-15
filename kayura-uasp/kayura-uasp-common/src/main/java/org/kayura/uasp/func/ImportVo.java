package org.kayura.uasp.func;

import java.util.List;

/**
 * 模块导入数据结构。
 */
public class ImportVo {

  private String appId;
  private String parentId;
  private List<String> moduleIds;

  public String getAppId() {
    return appId;
  }

  public ImportVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ImportVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public List<String> getModuleIds() {
    return moduleIds;
  }

  public ImportVo setModuleIds(List<String> moduleIds) {
    this.moduleIds = moduleIds;
    return this;
  }
}
