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
