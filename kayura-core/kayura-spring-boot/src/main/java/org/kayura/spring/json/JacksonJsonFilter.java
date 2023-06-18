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

package org.kayura.spring.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.kayura.utils.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonFilter("JacksonFilter")
public class JacksonJsonFilter extends FilterProvider {

  private final Map<Class<?>, Collection<String>> includes = new HashMap<>();
  private final Map<Class<?>, Collection<String>> excludes = new HashMap<>();

  public void include(Class<?> classType, String fields) {
    include(classType, Arrays.asList(fields.split(",")));
  }

  public void include(Class<?> classType, Collection<String> fields) {
    includes.put(classType, fields);
  }

  public void exclude(Class<?> classType, String fields) {
    exclude(classType, Arrays.asList(fields.split(",")));
  }

  public void exclude(Class<?> classType, Collection<String> fields) {
    excludes.put(classType, fields);
  }

  public void filter(Class<?> classType, String includes, String excludes) {

    if (StringUtils.hasText(includes)) {
      include(classType, includes);
    }
    if (StringUtils.hasText(excludes)) {
      exclude(classType, excludes);
    }
  }

  public void filter(DataFilter dataFilter) {
    filter(dataFilter.type(), dataFilter.includes(), dataFilter.excludes());
  }

  protected boolean isInclude(Class<?> classType, String name) {

    if (excludes.containsKey(classType)) {
      Collection<String> collection = excludes.get(classType);
      if (collection.contains(name)) {
        return false;
      }
    }

    if (includes.containsKey(classType)) {
      Collection<String> collection = includes.get(classType);
      return collection.contains(name);
    }

    return true;
  }

  @SuppressWarnings("deprecation")
  @Override
  public BeanPropertyFilter findFilter(Object filterId) {
    throw new UnsupportedOperationException("Access to deprecated filters not supported");
  }

  @Override
  public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {

    return new SimpleBeanPropertyFilter() {

      @Override
      public void serializeAsField(Object pojo, JsonGenerator jsonGenerator, SerializerProvider provider,
                                   PropertyWriter writer) throws Exception {
        if (isInclude(pojo.getClass(), writer.getName())) {
          writer.serializeAsField(pojo, jsonGenerator, provider);
        } else if (!jsonGenerator.canOmitFields()) {
          writer.serializeAsOmittedField(pojo, jsonGenerator, provider);
        }
      }

    };
  }

}
