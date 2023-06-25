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

package org.kayura.uasp.applic;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ApplicPayload {

  @NotBlank(groups = Update.class)
  private String appId;
  @NotBlank
  private String code;
  @NotBlank
  private String name;
  private Integer level;
  @NotNull
  private ApplicTypes type;
  private String url;
  private Integer sort;
  @NotNull
  private Boolean needRelease;
  private DataStatus status;
  private String remark;

  public static ApplicPayload create() {
    return new ApplicPayload();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String appId;
    private String code;
    private String name;
    private Integer level;
    private ApplicTypes type;
    private String url;
    private Integer sort;
    private Boolean needRelease;
    private DataStatus status;
    private String remark;

    public Builder withAppId(String appId) {
      this.appId = appId;
      return this;
    }

    public Builder withCode(String code) {
      this.code = code;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withLevel(Integer level) {
      this.level = level;
      return this;
    }

    public Builder withType(ApplicTypes type) {
      this.type = type;
      return this;
    }

    public Builder withUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder withSort(Integer sort) {
      this.sort = sort;
      return this;
    }

    public Builder withNeedRelease(Boolean needRelease) {
      this.needRelease = needRelease;
      return this;
    }

    public Builder withStatus(DataStatus status) {
      this.status = status;
      return this;
    }

    public Builder withRemark(String remark) {
      this.remark = remark;
      return this;
    }

    public ApplicPayload build() {
      ApplicPayload applicPayload = new ApplicPayload();
      applicPayload.type = this.type;
      applicPayload.remark = this.remark;
      applicPayload.appId = this.appId;
      applicPayload.code = this.code;
      applicPayload.url = this.url;
      applicPayload.status = this.status;
      applicPayload.sort = this.sort;
      applicPayload.needRelease = this.needRelease;
      applicPayload.name = this.name;
      applicPayload.level = this.level;
      return applicPayload;
    }
  }

  public String getAppId() {
    return appId;
  }

  public ApplicPayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ApplicPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ApplicPayload setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ApplicPayload setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ApplicPayload setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ApplicPayload setUrl(String url) {
    this.url = url;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ApplicPayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public Boolean getNeedRelease() {
    return needRelease;
  }

  public ApplicPayload setNeedRelease(Boolean needRelease) {
    this.needRelease = needRelease;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public ApplicPayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ApplicPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
