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
