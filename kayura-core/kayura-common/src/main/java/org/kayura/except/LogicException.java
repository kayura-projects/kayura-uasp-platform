/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.except;

import java.io.Serial;

/**
 * 由于系统的业务逻辑问题，引发的异常类型。
 */
public class LogicException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public LogicException() {
    super();
  }

  public LogicException(String message, Throwable cause) {
    super(message, cause);
  }

  public LogicException(String message) {
    super(message);
  }

}
