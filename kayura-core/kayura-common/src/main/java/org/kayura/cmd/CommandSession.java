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

package org.kayura.cmd;

import java.util.HashMap;
import java.util.Map;

public class CommandSession {

  private Command command;
  private Map<String, Object> data = new HashMap<>();
  private Object result;

  public static CommandSession create(Command command) {
    CommandSession session = new CommandSession();
    session.command = command;
    return session;
  }

  public Command getCommand() {
    return command;
  }

  public CommandSession setCommand(Command command) {
    this.command = command;
    return this;
  }

  public Object get(String key) {
    return this.data.getOrDefault(key, null);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String key, Class<T> type) {
    Object value = this.data.getOrDefault(key, null);
    if (value != null && type != null && !type.isInstance(value)) {
      throw new IllegalStateException("value is not of required type [" + type.getName() + "]: " + value);
    }
    return (T) value;
  }

  public CommandSession set(String key, Object value) {
    this.data.put(key, value);
    return this;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public CommandSession setData(Map<String, Object> data) {
    this.data = data;
    return this;
  }

  public Object getResult() {
    return result;
  }

  public CommandSession setResult(Object result) {
    this.result = result;
    return this;
  }
}
