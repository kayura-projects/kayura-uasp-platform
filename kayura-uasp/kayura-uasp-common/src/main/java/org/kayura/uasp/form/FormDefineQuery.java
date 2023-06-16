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

package org.kayura.uasp.form;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

import javax.validation.constraints.NotBlank;

public class FormDefineQuery {

  @NotBlank
  private String appId;
  private DataStatus status;
  @Like("code,name")
  private String searchText;
  @Ignore
  private Boolean needField;

  public String getAppId() {
    return appId;
  }

  public FormDefineQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormDefineQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public FormDefineQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getNeedField() {
    return needField;
  }

  public FormDefineQuery setNeedField(Boolean needField) {
    this.needField = needField;
    return this;
  }
}
