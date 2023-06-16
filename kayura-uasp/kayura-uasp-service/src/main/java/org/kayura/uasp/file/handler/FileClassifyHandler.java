/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
