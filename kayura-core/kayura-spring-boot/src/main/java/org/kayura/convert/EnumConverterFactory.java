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
