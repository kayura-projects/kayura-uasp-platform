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
package org.kayura.uasp.utils.cmd;

import org.kayura.cmd.Command;

public class BuildQRCodeCommand extends Command {

  private String content;
  private int width = 400;
  private int height = 400;
  private String format = "jpg";

  public String getContent() {
    return content;
  }

  public BuildQRCodeCommand setContent(String content) {
    this.content = content;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public BuildQRCodeCommand setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public BuildQRCodeCommand setHeight(int height) {
    this.height = height;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public BuildQRCodeCommand setFormat(String format) {
    this.format = format;
    return this;
  }
}
