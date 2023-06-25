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
