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
package org.kayura.uasp.utils;

import org.kayura.cmd.CommandInterceptor;
import org.kayura.cmd.CommandSession;
import org.springframework.stereotype.Component;

@Component
public class SimpleLogCommandInterceptor implements CommandInterceptor {

  @Override
  public void preHandle(CommandSession session) {
    session.set("START_TIME", System.currentTimeMillis());
    System.out.println("--> Interceptor: " + session.getCommand().getClass().getSimpleName() + " -> preHandle");
  }

  @Override
  public void postHandle(CommandSession session) {
    Long startTime = session.get("START_TIME", Long.class);
    long duration = System.currentTimeMillis() - startTime;
    System.out.println("<-- Interceptor: " + session.getCommand().getClass().getSimpleName() + " -> postHandle, duration: " + duration);
  }
}
