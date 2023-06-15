package org.kayura.uasp.file.handler;

import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.UploadFile;
import org.kayura.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FileClassifyHandler {

  private List<FileClassifyResolver> resolvers;

  public FileClassify resolve(UploadFile contentType) {

    if (CollectionUtils.isNotEmpty(this.resolvers)) {
      for (FileClassifyResolver resolver : this.resolvers) {
        FileClassify classify = resolver.resolve(contentType);
        if (classify != null) {
          return classify;
        }
      }
    }
    return FileClassify.OTHER;
  }

  public List<FileClassifyResolver> getResolvers() {
    return resolvers;
  }

  public FileClassifyHandler setResolvers(List<FileClassifyResolver> resolvers) {
    this.resolvers = resolvers;
    return this;
  }

  public FileClassifyHandler addResolver(FileClassifyResolver resolver) {
    if (this.resolvers == null) {
      this.resolvers = new ArrayList<>();
    }
    this.resolvers.add(resolver);
    return this;
  }

}
