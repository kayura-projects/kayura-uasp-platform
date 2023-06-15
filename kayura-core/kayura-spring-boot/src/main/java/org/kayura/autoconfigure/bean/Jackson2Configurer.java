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
