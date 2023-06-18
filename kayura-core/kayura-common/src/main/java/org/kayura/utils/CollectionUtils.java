/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

package org.kayura.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liangxia@live.com
 */
public class CollectionUtils {

  public static final List<String> EMPTY_STRING = new ArrayList<>();

  public static boolean isEmpty(Object[] array) {
    return (array == null || array.length == 0);
  }

  public static boolean isNotEmpty(Object[] array) {
    return !isEmpty(array);
  }

  /**
   * 判断集合是否为空
   *
   * @param collection 集合对象
   * @return 为空 true 否则false
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> coll) {
    return !isEmpty(coll);
  }

  public static boolean isEmpty(Map<?, ?> map) {
    return (map == null || map.isEmpty());
  }

  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }

  /**
   * 判断一个集合中是否存在指定元素
   *
   * @param collection 集合对象
   * @param value      集合元素
   * @return true:存在 否则不存在
   */
  public static <T> boolean contains(Collection<T> collection, T value) {
    return !isEmpty(collection) && collection.contains(value);
  }

  /**
   * 根据比较器比较两个collection中哪些是新增的对象以及删除的对象和没有改变的对象
   *
   * @param newList    新list
   * @param oldList    旧list
   * @param comparator 集合对象比较器
   * @return 返回比较的结果集。
   */
  public static <T> CompareResult<T> compare(Collection<T> newList, Collection<T> oldList, Comparator<T> comparator) {
    Collection<T> unmodifiedValue = new ArrayList<>();

    Iterator<T> newIte = newList.iterator();
    while (newIte.hasNext()) {
      T newObj = newIte.next();
      //遍历旧数组
      Iterator<T> oldIte = oldList.iterator();
      while (oldIte.hasNext()) {
        //如果新旧数组中的对象相同，则为没有改变的对象
        T oldObj = oldIte.next();
        if (comparator.compare(newObj, oldObj) == 0) {
          unmodifiedValue.add(oldObj);
          oldIte.remove();
          newIte.remove();
        }
      }
    }

    return new CompareResult<T>(newList, oldList, unmodifiedValue);
  }

  public static <T> List<Collection<T>> splitList(Collection<T> collection, int splitSize) {
    Assert.notEmpty(collection, "分割集合不允许为空集合。");
    int maxSize = collection.size() / splitSize + 1;
    return Stream.iterate(0, f -> f + 1)
      .limit(maxSize)
      .parallel()
      .map(a -> collection.parallelStream().skip((long) a * splitSize).limit(splitSize).collect(Collectors.toList()))
      .filter(List::isEmpty)
      .collect(Collectors.toList());
  }

  /**
   * 列表比较结果对象
   *
   * @param <T>
   */
  public static class CompareResult<T> {
    /**
     * 新增的对象列表
     */
    private final Collection<T> addValue;
    /**
     * 删除的对象列表
     */
    private final Collection<T> delValue;
    /**
     * 没有改变的对象列表
     */
    private final Collection<T> unmodifiedValue;

    public CompareResult(Collection<T> addValue, Collection<T> delValue, Collection<T> unmodifiedValue) {
      this.addValue = addValue;
      this.delValue = delValue;
      this.unmodifiedValue = unmodifiedValue;
    }

    public Collection<T> getDelValue() {
      return delValue;
    }

    public Collection<T> getAddValue() {
      return addValue;
    }

    public Collection<T> getUnmodifiedValue() {
      return unmodifiedValue;
    }
  }
}
