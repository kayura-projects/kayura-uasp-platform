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
