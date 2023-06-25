/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
