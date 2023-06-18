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
