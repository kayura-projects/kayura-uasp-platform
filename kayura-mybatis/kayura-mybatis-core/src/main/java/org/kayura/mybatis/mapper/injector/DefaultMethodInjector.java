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

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.mapper.injector.methods.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultMethodInjector extends AbstractMethodInjector {

  @Override
  public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
    return Stream.of(
      new DeleteById(),
      new DeleteByIds(),
      new DeleteByWhere(),
      new InsertOne(),
      new SelectById(),
      new SelectCount(),
      new SelectList(),
      new SelectMaps(),
      new SelectMapsPage(),
      new SelectOne(),
      new SelectPage(),
      new UpdateById(),
      new UpdateByWhere()
    ).collect(Collectors.toList());
  }

}
