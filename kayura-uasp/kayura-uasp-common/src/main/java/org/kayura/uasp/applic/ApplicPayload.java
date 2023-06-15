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
