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