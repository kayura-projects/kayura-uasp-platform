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

package org.kayura.utils;

import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

  private static final String FOLDER_SEPARATOR = "/";
  private static final char EXTENSION_SEPARATOR = '.';

  public static byte[] getBytesUtf8(String text) {
    return isBlank(text) ? null : text.getBytes(StandardCharsets.UTF_8);
  }

  public static String format(String target, Object... params) {
    if (target.contains("%s") && CollectionUtils.isNotEmpty(params)) {
      return String.format(target, params);
    }
    return target;
  }

  public static boolean isEmpty(@Nullable Object str) {
    return (str == null || "".equals(str));
  }

  public static boolean hasLength(@Nullable CharSequence str) {
    return (str != null && str.length() > 0);
  }

  public static boolean hasLength(@Nullable String str) {
    return (str != null && !str.isEmpty());
  }

  public static boolean hasText(@Nullable CharSequence str) {
    return (str != null && str.length() > 0 && containsText(str));
  }

  public static boolean hasText(@Nullable String str) {
    return (str != null && !str.isEmpty() && containsText(str));
  }

  private static boolean containsText(CharSequence str) {
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsWhitespace(@Nullable CharSequence str) {
    if (!hasLength(str)) {
      return false;
    }

    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsWhitespace(@Nullable String str) {
    return containsWhitespace((CharSequence) str);
  }

  public static String trimWhitespace(String str) {
    if (!hasLength(str)) {
      return str;
    }

    int beginIndex = 0;
    int endIndex = str.length() - 1;

    while (beginIndex <= endIndex && Character.isWhitespace(str.charAt(beginIndex))) {
      beginIndex++;
    }

    while (endIndex > beginIndex && Character.isWhitespace(str.charAt(endIndex))) {
      endIndex--;
    }

    return str.substring(beginIndex, endIndex + 1);
  }

  public static String trimAllWhitespace(String str) {
    if (!hasLength(str)) {
      return str;
    }

    int len = str.length();
    StringBuilder sb = new StringBuilder(str.length());
    for (int i = 0; i < len; i++) {
      char c = str.charAt(i);
      if (!Character.isWhitespace(c)) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String trimLeadingWhitespace(String str) {
    if (!hasLength(str)) {
      return str;
    }

    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }

  public static String trimTrailingWhitespace(String str) {
    if (!hasLength(str)) {
      return str;
    }

    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public static String trimLeadingCharacter(String str, char leadingCharacter) {
    if (!hasLength(str)) {
      return str;
    }

    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }

  public static String trimTrailingCharacter(String str, char trailingCharacter) {
    if (!hasLength(str)) {
      return str;
    }

    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public static boolean startsWithIgnoreCase(@Nullable String str, @Nullable String prefix) {
    return (str != null && prefix != null && str.length() >= prefix.length() &&
      str.regionMatches(true, 0, prefix, 0, prefix.length()));
  }

  public static boolean endsWithIgnoreCase(@Nullable String str, @Nullable String suffix) {
    return (str != null && suffix != null && str.length() >= suffix.length() &&
      str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length()));
  }

  public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
    if (index + substring.length() > str.length()) {
      return false;
    }
    for (int i = 0; i < substring.length(); i++) {
      if (str.charAt(index + i) != substring.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public static int countOccurrencesOf(String str, String sub) {
    if (!hasLength(str) || !hasLength(sub)) {
      return 0;
    }

    int count = 0;
    int pos = 0;
    int idx;
    while ((idx = str.indexOf(sub, pos)) != -1) {
      ++count;
      pos = idx + sub.length();
    }
    return count;
  }

  public static String replace(String inString, String oldPattern, @Nullable String newPattern) {
    if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
      return inString;
    }
    int index = inString.indexOf(oldPattern);
    if (index == -1) {
      // no occurrence -> can return input as-is
      return inString;
    }

    int capacity = inString.length();
    if (newPattern.length() > oldPattern.length()) {
      capacity += 16;
    }
    StringBuilder sb = new StringBuilder(capacity);

    int pos = 0;  // our position in the old string
    int patLen = oldPattern.length();
    while (index >= 0) {
      sb.append(inString, pos, index);
      sb.append(newPattern);
      pos = index + patLen;
      index = inString.indexOf(oldPattern, pos);
    }

    // append any characters to the right of a match
    sb.append(inString.substring(pos));
    return sb.toString();
  }

  public static String delete(String inString, String pattern) {
    return replace(inString, pattern, "");
  }

  public static String deleteAny(String inString, @Nullable String charsToDelete) {
    if (!hasLength(inString) || !hasLength(charsToDelete)) {
      return inString;
    }

    StringBuilder sb = new StringBuilder(inString.length());
    for (int i = 0; i < inString.length(); i++) {
      char c = inString.charAt(i);
      if (charsToDelete.indexOf(c) == -1) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  //---------------------------------------------------------------------
  // Convenience methods for working with formatted Strings
  //---------------------------------------------------------------------

  @Nullable
  public static String quote(@Nullable String str) {
    return (str != null ? "'" + str + "'" : null);
  }

  @Nullable
  public static Object quoteIfString(@Nullable Object obj) {
    return (obj instanceof String ? quote((String) obj) : obj);
  }

  public static String unqualify(String qualifiedName) {
    return unqualify(qualifiedName, '.');
  }

  public static String unqualify(String qualifiedName, char separator) {
    return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
  }

  public static String capitalize(String str) {
    return changeFirstCharacterCase(str, true);
  }

  public static String uncapitalize(String str) {
    return changeFirstCharacterCase(str, false);
  }

  private static String changeFirstCharacterCase(String str, boolean capitalize) {
    if (!hasLength(str)) {
      return str;
    }

    char baseChar = str.charAt(0);
    char updatedChar;
    if (capitalize) {
      updatedChar = Character.toUpperCase(baseChar);
    } else {
      updatedChar = Character.toLowerCase(baseChar);
    }
    if (baseChar == updatedChar) {
      return str;
    }

    char[] chars = str.toCharArray();
    chars[0] = updatedChar;
    return new String(chars, 0, chars.length);
  }

  @Nullable
  public static String getFilename(@Nullable String path) {
    if (path == null) {
      return null;
    }

    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
  }

  @Nullable
  public static String getFilenameExtension(@Nullable String path) {
    if (path == null) {
      return null;
    }

    int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    if (extIndex == -1) {
      return null;
    }

    int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (folderIndex > extIndex) {
      return null;
    }

    return path.substring(extIndex + 1);
  }

  public static String stripFilenameExtension(String path) {
    int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    if (extIndex == -1) {
      return path;
    }

    int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (folderIndex > extIndex) {
      return path;
    }

    return path.substring(0, extIndex);
  }

  public static String applyRelativePath(String path, String relativePath) {
    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    if (separatorIndex != -1) {
      String newPath = path.substring(0, separatorIndex);
      if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
        newPath += FOLDER_SEPARATOR;
      }
      return newPath + relativePath;
    } else {
      return relativePath;
    }
  }

  @Nullable
  public static Locale parseLocale(String localeValue) {
    String[] tokens = tokenizeLocaleSource(localeValue);
    if (tokens.length == 1) {
      return Locale.forLanguageTag(localeValue);
    }
    return parseLocaleTokens(localeValue, tokens);
  }

  @Nullable
  public static Locale parseLocaleString(String localeString) {
    return parseLocaleTokens(localeString, tokenizeLocaleSource(localeString));
  }

  private static String[] tokenizeLocaleSource(String localeSource) {
    return tokenizeToStringArray(localeSource, "_ ", false, false);
  }

  @Nullable
  private static Locale parseLocaleTokens(String localeString, String[] tokens) {
    String language = (tokens.length > 0 ? tokens[0] : "");
    String country = (tokens.length > 1 ? tokens[1] : "");
    validateLocalePart(language);
    validateLocalePart(country);

    String variant = "";
    if (tokens.length > 2) {
      // There is definitely a variant, and it is everything after the country
      // code sans the separator between the country code and the variant.
      int endIndexOfCountryCode = localeString.indexOf(country, language.length()) + country.length();
      // Strip off any leading '_' and whitespace, what's left is the variant.
      variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
      if (variant.startsWith("_")) {
        variant = trimLeadingCharacter(variant, '_');
      }
    }
    return (language.length() > 0 ? new Locale(language, country, variant) : null);
  }

  private static void validateLocalePart(String localePart) {
    for (int i = 0; i < localePart.length(); i++) {
      char ch = localePart.charAt(i);
      if (ch != ' ' && ch != '_' && ch != '#' && !Character.isLetterOrDigit(ch)) {
        throw new IllegalArgumentException(
          "Locale part \"" + localePart + "\" contains invalid characters");
      }
    }
  }

  @Deprecated
  public static String toLanguageTag(Locale locale) {
    return locale.getLanguage() + (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
  }

  public static TimeZone parseTimeZoneString(String timeZoneString) {
    TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
    if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
      // We don't want that GMT fallback...
      throw new IllegalArgumentException("Invalid time zone specification '" + timeZoneString + "'");
    }
    return timeZone;
  }

  //---------------------------------------------------------------------
  // Convenience methods for working with String arrays
  //---------------------------------------------------------------------

  public static String[] addStringToArray(@Nullable String[] array, String str) {
    if (isEmpty(array)) {
      return new String[]{str};
    }

    String[] newArr = new String[array.length + 1];
    System.arraycopy(array, 0, newArr, 0, array.length);
    newArr[array.length] = str;
    return newArr;
  }


  public static String[] toStringArray(Collection<String> collection) {
    return collection.toArray(new String[0]);
  }

  public static String[] toStringArray(Enumeration<String> enumeration) {
    return toStringArray(Collections.list(enumeration));
  }

  @Nullable
  public static String[] split(@Nullable String toSplit, @Nullable String delimiter) {
    if (!hasLength(toSplit) || !hasLength(delimiter)) {
      return null;
    }
    int offset = toSplit.indexOf(delimiter);
    if (offset < 0) {
      return null;
    }

    String beforeDelimiter = toSplit.substring(0, offset);
    String afterDelimiter = toSplit.substring(offset + delimiter.length());
    return new String[]{beforeDelimiter, afterDelimiter};
  }

  public static String[] tokenizeToStringArray(@Nullable String str, String delimiters) {
    return tokenizeToStringArray(str, delimiters, true, true);
  }

  public static String[] tokenizeToStringArray(
    @Nullable String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

    if (str == null) {
      return new String[0];
    }

    StringTokenizer st = new StringTokenizer(str, delimiters);
    List<String> tokens = new ArrayList<>();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if (!ignoreEmptyTokens || token.length() > 0) {
        tokens.add(token);
      }
    }
    return toStringArray(tokens);
  }

  public static String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter) {
    return delimitedListToStringArray(str, delimiter, null);
  }

  public static String[] delimitedListToStringArray(
    @Nullable String str, @Nullable String delimiter, @Nullable String charsToDelete) {

    if (str == null) {
      return new String[0];
    }
    if (delimiter == null) {
      return new String[]{str};
    }

    List<String> result = new ArrayList<>();
    if ("".equals(delimiter)) {
      for (int i = 0; i < str.length(); i++) {
        result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
      }
    } else {
      int pos = 0;
      int delPos;
      while ((delPos = str.indexOf(delimiter, pos)) != -1) {
        result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
        pos = delPos + delimiter.length();
      }
      if (str.length() > 0 && pos <= str.length()) {
        // Add rest of String, but not in case of empty input.
        result.add(deleteAny(str.substring(pos), charsToDelete));
      }
    }
    return toStringArray(result);
  }

  public static String[] commaDelimitedListToStringArray(@Nullable String str) {
    return delimitedListToStringArray(str, ",");
  }

  public static String toString(Object value) {
    return value != null ? value.toString() : null;
  }

  public static String concatCapitalize(String concatStr, final String str) {
    if (isEmpty(concatStr)) {
      concatStr = EMPTY;
    }
    if (str == null || str.length() == 0) {
      return str;
    }
    final char firstChar = str.charAt(0);
    if (Character.isTitleCase(firstChar)) {
      return str;
    }
    return concatStr + Character.toTitleCase(firstChar) + str.substring(1);
  }

  public static boolean checkValNotNull(Object object) {
    if (object instanceof CharSequence) {
      return isNotEmpty((CharSequence) object);
    }
    return object != null;
  }

  public static boolean checkValNull(Object object) {
    return !checkValNotNull(object);
  }
}
