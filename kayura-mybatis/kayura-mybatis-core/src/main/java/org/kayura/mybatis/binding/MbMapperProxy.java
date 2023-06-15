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

package org.kayura.mybatis.binding;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.Serial;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public class MbMapperProxy<T> implements InvocationHandler, Serializable {

  @Serial
  private static final long serialVersionUID = -1535601464562403032L;
  private final SqlSession sqlSession;
  private final Class<T> mapperInterface;
  private final Map<Method, MbMapperMethod> methodCache;

  public MbMapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MbMapperMethod> methodCache) {
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
    this.methodCache = methodCache;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      if (Object.class.equals(method.getDeclaringClass())) {
        return method.invoke(this, args);
      } else if (isDefaultMethod(method)) { // } else if (method.isDefault()) {
        return invokeDefaultMethod(proxy, method, args);
      }
    } catch (Throwable t) {
      throw ExceptionUtil.unwrapThrowable(t);
    }
    // 获取此接口方法对应的Mapper处理方法
    final MbMapperMethod mapperMethod = cachedMapperMethod(method);
    return mapperMethod.execute(sqlSession, args);
  }

  private MbMapperMethod cachedMapperMethod(Method method) {
    // 第一次调用时先创建并添加至缓存
    return methodCache.computeIfAbsent(method, k -> new MbMapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
  }

  private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
    final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.class);
    if (!constructor.canAccess(proxy)) {
      constructor.setAccessible(true);
    }
    final Class<?> declaringClass = method.getDeclaringClass();
    return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
      .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
  }

  private boolean isDefaultMethod(Method method) {
    return (method.getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC && method.getDeclaringClass().isInterface();
  }

}
