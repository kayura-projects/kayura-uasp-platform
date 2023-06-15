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
