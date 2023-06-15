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

package org.kayura.mybatis.mapper.wrapper.support;

import java.io.Serializable;

public interface Join<Children> extends Serializable {

  Children or(boolean condition);

  default Children or() {
    return or(true);
  }

  Children apply(boolean condition, String applySql, Object... value);

  default Children apply(String applySql, Object... value) {
    return apply(true, applySql, value);
  }


  Children exists(boolean condition, String existsSql);

  default Children exists(String existsSql) {
    return exists(true, existsSql);
  }


  Children notExists(boolean condition, String notExistsSql);

  default Children notExists(String notExistsSql) {
    return notExists(true, notExistsSql);
  }

}
