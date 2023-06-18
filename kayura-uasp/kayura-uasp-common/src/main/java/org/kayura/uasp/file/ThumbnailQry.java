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
