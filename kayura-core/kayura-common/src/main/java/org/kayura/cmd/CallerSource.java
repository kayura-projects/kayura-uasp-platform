package org.kayura.cmd;

import java.lang.reflect.Method;

public class CallerSource {

  private Class<?> clazz;
  private Method method;

  public static CallerSource create() {
    return new CallerSource();
  }

  public Class<?> getClazz() {
    return clazz;
  }

  public CallerSource setClazz(Class<?> clazz) {
    this.clazz = clazz;
    return this;
  }

  public Method getMethod() {
    return method;
  }

  public CallerSource setMethod(Method method) {
    this.method = method;
    return this;
  }
}
