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

import java.io.Serial;

/**
 * 由于系统的业务逻辑问题，引发的异常类型。
 */
public class LogicException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public LogicException() {
    super();
  }

  public LogicException(String message, Throwable cause) {
    super(message, cause);
  }

  public LogicException(String message) {
    super(message);
  }

}
