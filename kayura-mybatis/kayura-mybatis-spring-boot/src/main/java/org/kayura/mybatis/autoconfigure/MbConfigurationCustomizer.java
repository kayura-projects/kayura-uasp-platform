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

package org.kayura.mybatis.autoconfigure;

import org.kayura.mybatis.session.MbConfiguration;

/**
 * Callback interface that can be customized a {@link MbConfiguration} object generated on auto-configuration.
 *
 * @author Kazuki Shimizu
 * @since 1.2.1
 */
@FunctionalInterface
public interface MbConfigurationCustomizer {

  /**
   * Customize the given a {@link MbConfiguration} object.
   *
   * @param configuration the configuration object to customize
   */
  void customize(MbConfiguration configuration);

}
