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

package org.kayura.autoconfigure.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "kayura.cache")
public class CacheSettings {

  public static final String JWT_TOKEN = "JWT_TOKEN";
  public static final List<String> CACHE_NAMES = new ArrayList<>();

  public CacheSettings() {
    this.names = new HashMap<>();
  }

  private Map<String, Long> names;

  public Map<String, Long> getNames() {
    return names;
  }

  public CacheSettings setNames(Map<String, Long> names) {
    this.names = names;
    return this;
  }
}
