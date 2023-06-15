/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
