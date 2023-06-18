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

package org.kayura.type;

import java.util.HashMap;
import java.util.Map;

public class Result {

  private boolean ok;
  private String message;
  private Object body;
  private Map<String, Object> attr;

  public static Result create() {
    return new Result();
  }

  public static Result ok() {
    return create().setOk(true);
  }

  public static Result ok(String message) {
    return ok().setMessage(message);
  }

  public static Result okBody(Object body) {
    return ok().setBody(body);
  }

  public static Result fail(String message) {
    return create().setOk(false).setMessage(message);
  }

  public boolean isOk() {
    return ok;
  }

  public boolean isFail() {
    return !ok;
  }

  public Result setOk(boolean ok) {
    this.ok = ok;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public Result setMessage(String message) {
    this.message = message;
    return this;
  }

  @SuppressWarnings("unchecked")
  public <T> T get(Class<T> type) {
    Object value = this.body;
    if (value != null && type != null && !type.isInstance(value)) {
      throw new IllegalStateException("value is not of required type [" + type.getName() + "]: " + value);
    }
    return (T) value;
  }

  public Object getBody() {
    return body;
  }

  public Result setBody(Object body) {
    this.body = body;
    return this;
  }

  public Map<String, Object> getAttr() {
    return attr;
  }

  public Result setAttr(Map<String, Object> attr) {
    this.attr = attr;
    return this;
  }

  public Object finAttr(String key) {
    assert key != null;
    return attr.get(key);
  }

  public Result putAttr(String key, Object value) {
    assert key != null;
    if (this.attr == null) {
      this.attr = new HashMap<>();
    }
    this.attr.put(key, value);
    return this;
  }
}