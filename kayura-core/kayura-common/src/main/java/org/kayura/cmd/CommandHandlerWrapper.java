/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.cmd;

@SuppressWarnings({"ClassCanBeRecord", "rawtypes"})
public final class CommandHandlerWrapper implements Comparable<CommandHandlerWrapper> {

  private final CommandHandler handler;
  private final int priority;

  public CommandHandlerWrapper(CommandHandler handler, int priority) {
    this.handler = handler;
    this.priority = priority;
  }

  @SuppressWarnings("unchecked")
  public <R> R invoke(Command command) {
    return (R) handler.execute(command);
  }

  @Override
  public int compareTo(CommandHandlerWrapper other) {
    return Integer.compare(priority, other.priority);
  }

  public CommandHandler getHandler() {
    return this.handler;
  }

  public int getPriority() {
    return this.priority;
  }
}