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

package org.kayura.utils;

import org.kayura.except.ExceptUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 断言实用工具类，它用于帮助验证参数。
 *
 * @author liangxia @live.com
 */
public abstract class Assert {

  /**
   * 假设一个字符串包含了有效文本内容，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.hasText(name, "name 值不允许为空。");
   * </pre>
   *
   * @param text    用于检查的文本字符。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果对象为 {@code null} 或为空字符时。
   */
  public static void hasText(String text, String message) throws RuntimeException {
    if (!StringUtils.hasText(text)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Not empty.
   *
   * @param string  the string
   * @param message the message
   */
  public static void notEmpty(String string, String message) throws RuntimeException {
    if (StringUtils.isEmpty(string)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Not empty.
   *
   * @param string          the string
   * @param messageSupplier the message supplier
   */
  public static void notEmpty(String string, Supplier<String> messageSupplier) throws RuntimeException {
    notEmpty(string, messageSupplier.get());
  }

  /**
   * 假设一个字符串即不为{@code null}也不为空字符串，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.hasLength(name, "name 值不允许为空。");
   * </pre>
   *
   * @param text    用于检查的文本字符。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果对象为 {@code null} 或为空字符时。
   */
  public static void hasLength(String text, String message) throws RuntimeException {
    if (!StringUtils.hasLength(text)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * 假设一个文本字符不包含指定的字符串，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.doesNotContain(name, "in", "name 值不允许包含 in 字符。");
   * </pre>
   *
   * @param textToSearch 用于搜索的文本字符。
   * @param substring    不包含的字符串。
   * @param message      断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果文本包含指定的字符时。
   */
  public static void doesNotContain(String textToSearch, String substring, String message) throws RuntimeException {
    if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
      && textToSearch.contains(substring)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * 假设一个对象为 {@code null}，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.isNull(value, "value 值必须为 null。");
   * </pre>
   *
   * @param object  对于检查的一个对象。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果对象不为 {@code null} 时。
   */
  public static void isNull(Object object, String message) throws RuntimeException {
    if (object != null) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * 假设一个对象不为 {@code null}，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.isNull(value, "value 值不允许为 null。");
   * </pre>
   *
   * @param object  对于检查的一个对象。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果对象为 {@code null} 时。
   */
  public static void notNull(Object object, String message) throws RuntimeException {
    if (object == null) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Not null.
   *
   * @param object  the object
   * @param message the message
   * @param params  the params
   */
  public static void notNull(Object object, String message, Object... params) throws RuntimeException {
    notNull(object, StringUtils.format(message, params));
  }

  /**
   * Not null.
   *
   * @param object          the object
   * @param messageSupplier the message supplier
   */
  public static void notNull(Object object, Supplier<String> messageSupplier) throws RuntimeException {
    notNull(object, messageSupplier.get());
  }

  /**
   * 假设一个数组元素均不为{@code null}，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.notEmpty(array, "array 数组不允许包含{@code null}元素。");
   * </pre>
   *
   * @param array   用于检查的数组。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果数组包含{@code null}元素时。
   */
  public static void noNullElements(Object[] array, String message) throws RuntimeException {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          ExceptUtils.argument(message);
        }
      }
    }
  }

  /**
   * 假设一个 Map 包含了条目，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.notEmpty(map, "map 必须包含一个条目。");
   * </pre>
   *
   * @param map     用于检查的 Map。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果 Map 不包含条目时。
   */
  public static void notEmpty(Map<?, ?> map, String message) throws RuntimeException {
    if (CollectionUtils.isEmpty(map)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Not empty.
   *
   * @param map     the map
   * @param message the message
   * @param params  the params
   */
  public static void notEmpty(Map<?, ?> map, String message, Object... params) throws RuntimeException {
    isTrue(CollectionUtils.isNotEmpty(map), message, params);
  }

  /**
   * 假设一个数组不为空，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.notEmpty(array, "array 数组不允许为空。");
   * </pre>
   *
   * @param <T>     the type parameter
   * @param array   用于检查的数组。
   * @param message 断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果数组为{@code null}或长度为 0 时。
   */
  public static <T> void notEmpty(T[] array, String message) throws RuntimeException {
    if (array == null || array.length == 0) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Not empty.
   *
   * @param <T>             the type parameter
   * @param array           the array
   * @param messageSupplier the message supplier
   */
  public static <T> void notEmpty(T[] array, Supplier<String> messageSupplier) throws RuntimeException {
    notEmpty(array, messageSupplier.get());
  }

  /**
   * 假设一个 Collection 不为空，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.notEmpty(collection, "collection 集合不允许为空。");
   * </pre>
   *
   * @param collection 用于检查的集合。
   * @param message    断言失败时抛出的异常消息。
   * @throws IllegalArgumentException 如果集合为{@code null}或长度为 0 时。
   */
  public static void notEmpty(Collection<?> collection, String message) throws RuntimeException {
    if (CollectionUtils.isEmpty(collection)) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * 假设一个布尔表达式的结果为 {@code true}，否则将会抛出一个 {@code IllegalArgumentException} 异常。
   * <pre class="entity">
   * Assert.isTrue(i &gt; 0, "i 值必需大于零。");
   * </pre>
   *
   * @param condition 一个布尔表达式。
   * @param message   当表达式结果为 {@code false} 时，抛出的异常消息。
   * @throws IllegalArgumentException 如果表达式结果为 {@code false} 时。
   */
  public static void isTrue(boolean condition, String message) throws RuntimeException {
    if (!condition) {
      ExceptUtils.argument(message);
    }
  }

  /**
   * Is true.
   *
   * @param expression the expression
   * @param message    the message
   * @param params     the params
   */
  public static void isTrue(boolean expression, String message, Object... params) throws RuntimeException {
    if (!expression) {
      ExceptUtils.argument(message, params);
    }
  }
}
