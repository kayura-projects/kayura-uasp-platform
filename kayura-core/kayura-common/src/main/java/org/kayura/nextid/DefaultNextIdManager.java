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

package org.kayura.nextid;

import org.kayura.except.ExceptUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultNextIdManager implements NextIdManager {

  private static final Map<Class<?>, IdGenerator> suppers1 = new ConcurrentHashMap<>();
  private static final Map<NextIdTypes, IdGenerator> suppers2 = new ConcurrentHashMap<>();

  public void addGenerator(Class<?> dataType, IdGenerator idGenerator) {
    suppers1.put(dataType, idGenerator);
  }

  public void addGenerator(NextIdTypes idTypes, IdGenerator idGenerator) {
    suppers2.put(idTypes, idGenerator);
  }

  @Override
  public String nextId(Class<?> dataType) {
    IdGenerator idGenerator = suppers1.get(dataType);
    if (idGenerator == null) {
      ExceptUtils.config("不支持 " + dataType.getSimpleName() + " 类型的Id生成器。");
    }
    String nextId = idGenerator.nextId();
    return nextId;
  }

  @Override
  public String nextId(NextIdTypes idType) {
    IdGenerator idGenerator = suppers2.get(idType);
    if (idGenerator == null) {
      ExceptUtils.config("不支持 " + idType.name() + " 类型的Id生成器。");
    }
    String nextId = idGenerator.nextId();
    return nextId;
  }

  @Override
  public IdGenerator get(Class<?> dataType) {
    IdGenerator idGenerator = suppers1.get(dataType);
    if (idGenerator == null) {
      ExceptUtils.config("不支持 " + dataType.getSimpleName() + " 类型的Id生成器。");
    }
    return idGenerator;
  }

  @Override
  public IdGenerator get(NextIdTypes idType) {
    IdGenerator idGenerator = suppers2.get(idType);
    if (idGenerator == null) {
      ExceptUtils.config("不支持 " + idType.name() + " 类型的Id生成器。");
    }
    return idGenerator;
  }

}
