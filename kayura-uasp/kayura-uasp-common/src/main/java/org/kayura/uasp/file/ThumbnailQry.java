package org.kayura.uasp.file;

public class ThumbnailQry {

  private String linkId;
  private Integer width;
  private Integer height;
  private Integer scale;
  private Boolean ratio;

  public boolean isAdjustSize() {
    return width != null || height != null;
  }

  public boolean isThumbnail() {
    return isAdjustSize() || (ratio != null && isAdjustSize()) || scale != null;
  }

  public static ThumbnailQry create() {
    return new ThumbnailQry();
  }

  public String getLinkId() {
    return linkId;
  }

  public ThumbnailQry setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public Integer getWidth() {
    return width;
  }

  public ThumbnailQry setWidth(Integer width) {
    this.width = width;
    return this;
  }

  public Integer getHeight() {
    return height;
  }

  public ThumbnailQry setHeight(Integer height) {
    this.height = height;
    return this;
  }

  public Integer getScale() {
    return scale;
  }

  public ThumbnailQry setScale(Integer scale) {
    this.scale = scale;
    return this;
  }

  public Boolean getRatio() {
    return ratio;
  }

  public ThumbnailQry setRatio(Boolean ratio) {
    this.ratio = ratio;
    return this;
  }
}
