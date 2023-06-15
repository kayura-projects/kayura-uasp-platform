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

package org.kayura.vaildation;

import org.kayura.type.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

  private List<String> values;

  @Override
  public void initialize(EnumValid constraintAnnotation) {
    this.values = Arrays.asList(constraintAnnotation.values());
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (value != null) {
      if (value instanceof Collection<?>) {
        Collection<?> list = (Collection<?>) value;
        for (Object v : list) {
          if (!values.contains(v)) {
            return false;
          }
        }
      } else if (value instanceof String) {
        return values.contains(value);
      } else if (value instanceof EnumValue) {
        return values.contains(value.toString());
      } else {
        return false;
      }
    }

    return true;
  }

}
