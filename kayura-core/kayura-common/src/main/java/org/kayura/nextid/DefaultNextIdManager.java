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
