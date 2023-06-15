/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
