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
