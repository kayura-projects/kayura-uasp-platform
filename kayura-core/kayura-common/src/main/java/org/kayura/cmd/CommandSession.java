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
