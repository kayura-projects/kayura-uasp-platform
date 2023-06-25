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

package org.kayura.mybatis.autoconfigure;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Hans Westerbeek
 * @author Eddú Meléndez
 * @author Kazuki Shimizu
 */
public class MbSpringBootVFS extends VFS {

  private final ResourcePatternResolver resourceResolver;

  public MbSpringBootVFS() {
    this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  protected List<String> list(URL url, String path) throws IOException {
    String urlString = url.toString();
    String baseUrlString = urlString.endsWith("/") ? urlString : urlString.concat("/");
    Resource[] resources = resourceResolver.getResources(baseUrlString + "**/*.class");
    return Stream.of(resources).map(resource -> preserveSubpackageName(baseUrlString, resource, path))
      .collect(Collectors.toList());
  }

  private static String preserveSubpackageName(final String baseUrlString, final Resource resource,
                                               final String rootPath) {
    try {
      return rootPath + (rootPath.endsWith("/") ? "" : "/")
        + resource.getURL().toString().substring(baseUrlString.length());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
