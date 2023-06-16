/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
