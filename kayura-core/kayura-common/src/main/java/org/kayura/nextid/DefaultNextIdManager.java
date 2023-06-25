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
