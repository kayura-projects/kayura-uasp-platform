/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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

  public void subscribe(Class<? extends ICommand> commandType, CommandHandlerWrapper handlerWrapper) {

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

  public void unsubscribe(Class<? extends ICommand> commandClass) {
    handlerWrappers.remove(commandClass);
  }

  @Override
  public CommandHandlerWrapper getHandlerWrapper(Class<? extends ICommand> commandType) {
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