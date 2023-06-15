/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
