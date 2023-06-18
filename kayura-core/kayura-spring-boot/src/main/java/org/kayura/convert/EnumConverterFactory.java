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

package org.kayura.convert;

import org.kayura.type.EnumValue;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class EnumConverterFactory implements ConverterFactory<String, EnumValue> {

  @Override
  public <T extends EnumValue> @NotNull Converter<String, T> getConverter(@NotNull Class<T> targetType) {
    return new StringToEnum<>(targetType);
  }

  public static class StringToEnum<T extends EnumValue> implements Converter<String, T> {

    private final Class<T> targetType;

    public StringToEnum(Class<T> targetType) {
      this.targetType = targetType;
    }

    @Override
    public T convert(@NotNull String source) {
      if (!StringUtils.hasText(source)) {
        return null;
      }
      return (T) EnumConverterFactory.getEnum(this.targetType, source);
    }
  }

  public static <T extends EnumValue> T getEnum(Class<T> targetType, String source) {
    for (T constant : targetType.getEnumConstants()) {
      if (source.equals(String.valueOf(constant.getValue()))) {
        return constant;
      }
    }
    return null;
  }
}
