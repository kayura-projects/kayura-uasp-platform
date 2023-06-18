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
