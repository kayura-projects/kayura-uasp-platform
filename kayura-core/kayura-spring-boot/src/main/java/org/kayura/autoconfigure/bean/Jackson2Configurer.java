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

package org.kayura.autoconfigure.bean;

import org.kayura.json.SmartEnumDeserializer;
import org.kayura.type.EnumValue;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StdZhDateFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class Jackson2Configurer {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {

    return builder -> {

      builder.timeZone(TimeZone.getDefault());
      builder.dateFormat(new StdZhDateFormat());
      builder.failOnEmptyBeans(false);
      builder.failOnUnknownProperties(false);
      builder.serializationInclusion(JsonInclude.Include.NON_NULL);

      SimpleModule simpleModule = new SimpleModule();
      simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
      simpleModule.addSerializer(EnumValue.class, ToStringSerializer.instance);
      simpleModule.addDeserializer(Enum.class, new SmartEnumDeserializer());

      JavaTimeModule dateModule = new JavaTimeModule();
      LocalDateDeserializer dateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtils.YYYYMMDD));
      LocalDateSerializer dateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtils.YYYYMMDD));
      dateModule.addDeserializer(LocalDate.class, dateDeserializer);
      dateModule.addSerializer(LocalDate.class, dateSerializer);

      JavaTimeModule timeModule = new JavaTimeModule();
      LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.YYYYMMDDHHMMSS));
      LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.YYYYMMDDHHMMSS));
      timeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
      timeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

      builder.modules(simpleModule, dateModule, timeModule);
    };
  }

}
