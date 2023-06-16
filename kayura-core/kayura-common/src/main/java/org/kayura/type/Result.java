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