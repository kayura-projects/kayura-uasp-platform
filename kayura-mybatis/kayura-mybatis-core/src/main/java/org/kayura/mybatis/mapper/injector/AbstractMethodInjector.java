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
