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

package org.kayura.type;

import org.jetbrains.annotations.NotNull;

/**
 * 键值类型
 *
 * @author 夏亮（liangxia@live.com）
 */
public class KeyValue extends KeyValueT<String> {

  public static KeyValue builder() {
    return new KeyValue();
  }

  public KeyValue() {
  }

  public KeyValue(@NotNull String key, @NotNull String value) {
    this.setKey(key);
    this.setValue(value);
  }

}
