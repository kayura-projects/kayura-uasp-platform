/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
