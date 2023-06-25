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
