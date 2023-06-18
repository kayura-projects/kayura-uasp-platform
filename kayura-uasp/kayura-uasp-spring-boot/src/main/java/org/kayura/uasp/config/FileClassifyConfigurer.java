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

import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.handler.FileClassifyHandler;
import org.kayura.uasp.file.handler.FileClassifyResolver;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileClassifyConfigurer {

  static final String[] DOC_TYPE = {"pdf", "officedocument", "msword", "ms-excel", "visio"};
  static final String[] ZIP_POSTFIX = {"rar", "zip", "7z"};

  @Bean
  public FileClassifyHandler fileClassifyHandler(
    ObjectProvider<FileClassifyResolver[]> classifyProvider
  ) {

    FileClassifyHandler handler = new FileClassifyHandler();
    FileClassifyResolver[] classifyResolvers = classifyProvider.getIfAvailable();
    if (CollectionUtils.isNotEmpty(classifyResolvers)) {
      for (FileClassifyResolver resolver : classifyResolvers) {
        handler.addResolver(resolver);
      }
    }
    handler.addResolver(defaultClassifyResolver());
    return handler;
  }

  private FileClassifyResolver defaultClassifyResolver() {
    return (uploadFile -> {

      String contentType = uploadFile.getContentType();
      if (contentType.startsWith("image")) {
        return FileClassify.IMAGE;
      } else if (contentType.startsWith("video")) {
        return FileClassify.VIDEO;
      } else if (contentType.equalsIgnoreCase("application/pdf")) {
        return FileClassify.PDF;
      } else if (contentType.startsWith("text")) {
        return FileClassify.DOC;
      } else if (contentType.startsWith("application")) {
        if (contentType.contains("compressed")) {
          return FileClassify.ZIP;
        } else if (StringUtils.containsAnyIgnoreCase(contentType, DOC_TYPE)) {
          return FileClassify.DOC;
        }
      }

      String postfix = uploadFile.getPostfix();
      if (StringUtils.equalsAnyIgnoreCase(postfix, ZIP_POSTFIX)) {
        return FileClassify.ZIP;
      }
      return FileClassify.OTHER;
    });
  }
}
