/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.security.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class IpWhitelistTest {

  @Test
  @DisplayName("测试 128.21.*.*")
  void test1() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.21.*.*"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.21.1.100"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.21.2.100"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.20.2.100"));
  }

  @Test
  @DisplayName("测试 128.22.50.*")
  void test2() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.22.50.*"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.22.50.100"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.22.50.110"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.22.51.100"));
  }

  @Test
  @DisplayName("测试 128.23.[51-58].*")
  void test3() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.23.[51-58].*"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.23.51.100"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.23.52.100"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.23.50.100"));
  }

  @Test
  @DisplayName("测试 128.24.[51,52,53].*")
  void test4() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.24.[51,52,53,58].*"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.24.51.52"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.24.58.68"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.24.50.100"));
  }

  @Test
  @DisplayName("测试 128.25.50.[51-58]")
  void test5() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.25.50.[51-58]"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.25.50.52"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.25.50.53"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.25.50.50"));
  }

  @Test
  @DisplayName("测试 128.26.50.[51,52,53]")
  void test6() {
    IpWhitelist ipWhitelist = new DefaultIpWhitelist(Set.of("128.26.50.[51,52,53]"));

    Assertions.assertTrue(ipWhitelist.isAllowed("128.26.50.51"));
    Assertions.assertTrue(ipWhitelist.isAllowed("128.26.50.52"));
    Assertions.assertFalse(ipWhitelist.isAllowed("128.26.50.50"));
  }
}