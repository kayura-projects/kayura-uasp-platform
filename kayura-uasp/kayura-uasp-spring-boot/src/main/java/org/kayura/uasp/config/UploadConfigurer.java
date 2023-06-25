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
