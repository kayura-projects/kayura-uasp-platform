/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
