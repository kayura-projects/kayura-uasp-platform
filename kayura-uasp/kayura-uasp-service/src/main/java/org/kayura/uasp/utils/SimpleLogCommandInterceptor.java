/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
