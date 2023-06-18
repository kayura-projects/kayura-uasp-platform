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

package org.kayura.except;

import org.kayura.utils.StringUtils;

import java.io.IOException;

public abstract class ExceptUtils {

  public static void argument(String message) {
    throw new IllegalArgumentException(message);
  }

  public static void argument(String message, Object... params) {
    argument(StringUtils.format(message, params));
  }

  public static void business(String message) {
    throw new BusinessException(message);
  }

  public static void business(String message, Object... params) {
    business(StringUtils.format(message, params));
  }

  public static void config(String message) {
    throw new ConfigException(message);
  }

  public static void config(String message, Object... params) {
    config(StringUtils.format(message, params));
  }

  public static void io(String message) throws IOException {
    throw new IOException(message);
  }

  public static void io(String message, Object... params) throws IOException {
    io(StringUtils.format(message, params));
  }

  public static void security(String message) {
    throw new SecurityException(message);
  }

  public static void security(String message, Object... params) {
    security(StringUtils.format(message, params));
  }

}
