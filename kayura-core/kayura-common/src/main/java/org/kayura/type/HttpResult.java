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

package org.kayura.type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


/**
 * WEB 请求结果对象。
 *
 * @author liangxia@live.com
 */
public class HttpResult implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private static final Log LOGGER = LogFactory.getLog(HttpResult.class);

  public static final Integer SC_SUCCEED = 200;        // 执行成功
  public static final Integer SC_SERVER_ERROR = 500;   // 服务器执行异常
  public static final Integer SC_ARGUMENT = 501;       // 请求的参数错误
  public static final Integer SC_UNAUTHORIZED = 401;   // 禁用访问的资料
  public static final Integer SC_FORBIDDEN = 403;      // 缺少访问的权限
  public static final Integer SC_NOT_FOUND = 400;      // 访问的资源不存在

  private Integer code = SC_SUCCEED;
  private String message;
  private Object data;

  public static HttpResult create() {
    return new HttpResult();
  }

  public static HttpResult of(Result result) {
    return create()
      .setCode(result.isOk() ? SC_SUCCEED : SC_SERVER_ERROR)
      .setMessage(result.getMessage())
      .setData(result.getBody());
  }

  public static HttpResult ok() {
    return ok(null, null);
  }

  public static HttpResult okBody(Object data) {
    return ok(null, data);
  }

  public static HttpResult ok(String message) {
    return ok(message, null);
  }

  public static HttpResult ok(String message, Object data) {
    return ok(SC_SUCCEED, message, data);
  }

  public static HttpResult ok(Integer code, String message, Object body) {
    return create().setCode(code).setMessage(message).setData(body);
  }

  public static HttpResult error(String message) {
    return error(SC_SERVER_ERROR, message, null);
  }

  public static HttpResult error(String message, Object ex) {
    return error(SC_SERVER_ERROR, message, ex);
  }

  public static HttpResult error(Integer code, String message) {
    return error(code, message, null);
  }

  public static HttpResult error(Integer code, String message, Object ex) {
    if (ex instanceof Throwable) {
      LOGGER.error(message, (Throwable) ex);
    }
    return create().setCode(code).setMessage(message);
  }

  public boolean isSuccess() {
    return Objects.equals(this.code, SC_SUCCEED);
  }

  public Integer getCode() {
    return SC_SUCCEED.equals(code) ? null : code;
  }

  public HttpResult setCode(Integer code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public HttpResult setMessage(String message) {
    this.message = message;
    return this;
  }

  public Object getData() {
    return data;
  }

  public HttpResult setData(Object data) {
    this.data = data;
    return this;
  }
}
