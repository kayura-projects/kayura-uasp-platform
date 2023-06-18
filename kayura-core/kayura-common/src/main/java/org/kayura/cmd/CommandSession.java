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
