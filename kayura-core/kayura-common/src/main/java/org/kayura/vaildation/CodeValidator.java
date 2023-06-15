package org.kayura.vaildation;

import org.kayura.utils.PatternUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodeValidator implements ConstraintValidator<CodeValid, String> {

  @Override
  public void initialize(CodeValid constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return value == null || PatternUtils.isValidCode(value);
  }

}
