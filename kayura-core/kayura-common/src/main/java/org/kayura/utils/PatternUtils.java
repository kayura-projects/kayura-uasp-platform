package org.kayura.utils;

import java.util.regex.Pattern;

public abstract class PatternUtils implements Constant {

  public static boolean isValidCode(String value) {

    return Pattern.compile(CODE).matcher(value).find();
  }
}
