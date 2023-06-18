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

package org.kayura.security.utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DefaultIpWhitelist implements IpWhitelist {

  private final Set<String> whitelist;

  public DefaultIpWhitelist() {
    this.whitelist = new HashSet<>();
  }

  public DefaultIpWhitelist(Set<String> whitelist) {
    this.whitelist = whitelist;
  }

  public void addToWhitelist(String ipAddress) {
    whitelist.add(ipAddress);
  }

  @Override
  public boolean isAllowed(String clientIp) {
    for (String ipWhite : whitelist) {
      if (isIpMatching(clientIp, ipWhite)) {
        return true;
      }
    }
    return false;
  }

  private boolean isIpMatching(String clientIp, String ipWhite) {

    if (Objects.equals(clientIp, ipWhite)) {
      return true;
    }

    String[] ipAddressParts = clientIp.split("\\.");
    String[] whitelistEntryParts = ipWhite.split("\\.");

    if (ipAddressParts.length != whitelistEntryParts.length) {
      return false;
    }

    for (int i = 0; i < ipAddressParts.length; i++) {
      String ipAddressPart = ipAddressParts[i];
      String whitelistEntryPart = whitelistEntryParts[i];
      if (whitelistEntryPart.equals("*")) {
        continue;
      }
      if (whitelistEntryPart.startsWith("[") && whitelistEntryPart.endsWith("]")) {
        String whitelistContent = whitelistEntryPart.substring(1, whitelistEntryPart.length() - 1);
        String[] whitelistValues = whitelistContent.split(",");
        boolean matchFound = false;
        for (String value : whitelistValues) {
          value = value.trim();
          if (value.contains("-")) {
            String[] rangeBounds = value.split("-");
            if (rangeBounds.length != 2) {
              continue;
            }
            int startRange = Integer.parseInt(rangeBounds[0].trim());
            int endRange = Integer.parseInt(rangeBounds[1].trim());
            int ipPart = Integer.parseInt(ipAddressPart.trim());
            if (ipPart >= startRange && ipPart <= endRange) {
              matchFound = true;
              break;
            }
          } else {
            int whitelistValue = Integer.parseInt(value.trim());
            int ipPart = Integer.parseInt(ipAddressPart.trim());
            if (ipPart == whitelistValue) {
              matchFound = true;
              break;
            }
          }
        }
        if (!matchFound) {
          return false;
        }
      } else {
        int whitelistValue = Integer.parseInt(whitelistEntryPart.trim());
        int ipPart = Integer.parseInt(ipAddressPart.trim());
        if (ipPart != whitelistValue) {
          return false;
        }
      }
    }

    return true;
  }

}
