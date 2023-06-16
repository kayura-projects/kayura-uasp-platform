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
