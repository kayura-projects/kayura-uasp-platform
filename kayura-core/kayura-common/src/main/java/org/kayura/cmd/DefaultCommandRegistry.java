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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultCommandRegistry implements CommandRegistry {

  private static final Log LOG = LogFactory.getLog(DefaultCommandRegistry.class);
  private final Map<Class<?>, CommandHandlerWrapper> handlerWrappers = new ConcurrentHashMap<>();
  private final List<CommandInterceptorWrapper> interceptorWrappers = new CopyOnWriteArrayList<>();
  private final List<CommandPluginWrapper> pluginWrappers = new CopyOnWriteArrayList<>();

  @Override
  public void addPlugin(CommandPluginWrapper pluginWrapper) {
    this.pluginWrappers.add(pluginWrapper);
  }

  @Override
  public void addInterceptor(CommandInterceptorWrapper interceptorWrapper) {
    this.interceptorWrappers.add(interceptorWrapper);
  }

  public void subscribe(Class<? extends Command> commandType, CommandHandlerWrapper handlerWrapper) {

    CommandHandlerWrapper oldValue = handlerWrappers.put(commandType, handlerWrapper);
    if (oldValue != null) {
      if (LOG.isInfoEnabled()) {
        LOG.info(oldValue.getHandler().getClass().getName() + " Command is replaced with " + handlerWrapper.getHandler().getClass().getName());
      }
    } else {
      if (LOG.isTraceEnabled()) {
        LOG.trace("New command -> " + handlerWrapper.getHandler().getClass().getName());
      }
    }
  }

  public void unsubscribe(Class<? extends Command> commandClass) {
    handlerWrappers.remove(commandClass);
  }

  @Override
  public CommandHandlerWrapper getHandlerWrapper(Class<? extends Command> commandType) {
    return this.handlerWrappers.getOrDefault(commandType, null);
  }

  @Override
  public List<CommandInterceptorWrapper> getInterceptorWrappers() {
    return this.interceptorWrappers;
  }

  @Override
  public List<CommandPluginWrapper> getPluginWrappers() {
    return this.pluginWrappers;
  }

}