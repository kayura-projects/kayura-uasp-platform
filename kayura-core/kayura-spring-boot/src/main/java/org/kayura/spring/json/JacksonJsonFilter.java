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
