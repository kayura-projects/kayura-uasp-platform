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
