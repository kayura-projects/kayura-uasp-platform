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

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.mapper.metadata.EntityHelper;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.session.MbConfiguration;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

public abstract class AbstractMethodInjector implements IMethodInjector {

  private static final Log logger = LogFactory.getLog(AbstractMethodInjector.class);

  @Override
  public void inject(MapperBuilderAssistant assistant, Class<?> mapperClass) {
    Class<?> entityClass = extractEntity(mapperClass);
    if (entityClass != null) {
      String mapperName = mapperClass.toString();
      MbConfiguration configuration = (MbConfiguration) assistant.getConfiguration();
      if (!configuration.hasMapperRegistryCache(mapperName)) {
        List<AbstractMethod> methodList = this.getMethodList(mapperClass);
        EntityInfo tableInfo = EntityHelper.resolve(configuration, assistant.getCurrentNamespace(), entityClass);
        methodList.forEach(e -> e.inject(assistant, mapperClass, tableInfo));
      } else {
        if (logger.isTraceEnabled()) {
          logger.trace(mapperClass + ", 该接口已注入方法。");
        }
      }
      configuration.addMapperRegistryCache(mapperName);
    }
  }

  public abstract List<AbstractMethod> getMethodList(Class<?> mapperClass);

  protected Class<?> extractEntity(Class<?> mapperClass) {

    Type[] types = mapperClass.getGenericInterfaces();
    ParameterizedType target = null;
    for (Type type : types) {
      if (type instanceof ParameterizedType) {
        Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypes != null && actualTypes.length > 0) {
          for (Type t : actualTypes) {
            if (!(t instanceof TypeVariable) && !(t instanceof WildcardType)) {
              target = (ParameterizedType) type;
            }
            break;
          }
        }
        break;
      }
    }
    return target != null ? (Class<?>) target.getActualTypeArguments()[0] : null;
  }

}
