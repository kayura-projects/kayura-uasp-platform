/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
