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

package org.kayura.mybatis.binding;

import org.kayura.mybatis.annotation.MbMapperAnnotationBuilder;
import org.kayura.mybatis.session.MbConfiguration;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展自定义的Mapper注册器
 *
 * @author LiangXia
 */
public class MbMapperRegistry extends MapperRegistry {

  private final Map<Class<?>, MbMapperProxyFactory<?>> knownMappers = new HashMap<>();
  private final MbConfiguration config;

  public MbMapperRegistry(MbConfiguration config) {
    super(config);
    this.config = config;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    final MbMapperProxyFactory<T> mapperProxyFactory = (MbMapperProxyFactory<T>) knownMappers.get(type);
    if (mapperProxyFactory == null) {
      throw new BindingException("类型 " + type + " 未在 MapperRegistry 中注册。");
    }
    try {
      return mapperProxyFactory.newInstance(sqlSession);
    } catch (Exception e) {
      throw new BindingException("错误获取 mapper 实例。原因：" + e, e);
    }
  }

  @Override
  public <T> void addMapper(Class<T> mapperType) {
    if (mapperType.isInterface()) {
      if (hasMapper(mapperType)) {
        throw new BindingException("Type " + mapperType + " is already known to the MapperRegistry.");
      }
      boolean loadCompleted = false;
      try {
        knownMappers.put(mapperType, new MbMapperProxyFactory<>(mapperType));
        // It's important that the mapperType is added before the parser is run
        // otherwise the binding may automatically be attempted by the
        // mapper parser. If the mapperType is already known, it won't try.
        MbMapperAnnotationBuilder parser = new MbMapperAnnotationBuilder(config, mapperType);
        parser.parse();
        loadCompleted = true;
      } finally {
        if (!loadCompleted) {
          knownMappers.remove(mapperType);
        }
      }
    }
  }

  @Override
  public Collection<Class<?>> getMappers() {
    return Collections.unmodifiableCollection(knownMappers.keySet());
  }

}
