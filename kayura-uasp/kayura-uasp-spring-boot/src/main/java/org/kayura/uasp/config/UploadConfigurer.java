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

package org.kayura.uasp.config;

import org.kayura.except.ConfigException;
import org.kayura.except.ExceptUtils;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.uasp.file.handler.UploadHandlerByLocalDisk;
import org.kayura.uasp.file.handler.UploadHandlerDelegate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(UploadSettings.class)
public class UploadConfigurer {

  private final UploadSettings uploadSettings;

  public UploadConfigurer(UploadSettings uploadSettings) {
    this.uploadSettings = uploadSettings;
  }

  @Bean
  public UploadHandlerDelegate uploadProviderDelegate(
    ObjectProvider<UploadHandler[]> uploadHandlerProvider
  ) {

    UploadHandler[] handlers = uploadHandlerProvider.getIfAvailable();
    if (handlers == null || handlers.length == 0) {
      ExceptUtils.config("缺少 UploadHandler 实例。");
    }

    List<UploadHandler> providers = List.of(handlers);

    UploadHandler alive;
    if (providers.size() > 1) {
      alive = Arrays.stream(handlers)
        .filter(a -> a.getStoreType().getValue().equalsIgnoreCase(uploadSettings.getAlive()))
        .findFirst().orElseThrow(ConfigException::new);
    } else {
      alive = providers.get(0);
    }

    UploadHandlerDelegate delegate = new UploadHandlerDelegate(providers, alive);
    return delegate;
  }

  @Bean
  public UploadHandler uploadProviderByLocalDisk() {
    return new UploadHandlerByLocalDisk(uploadSettings.getStorePath(), uploadSettings.getTempPath());
  }

}
