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
