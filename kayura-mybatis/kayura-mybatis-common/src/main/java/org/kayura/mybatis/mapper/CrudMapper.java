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

package org.kayura.mybatis.mapper;

import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.mapper.wrapper.Wrapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface CrudMapper<ENTITY> extends SelectMapper<ENTITY> {

  ENTITY selectById(@Param(Constants.ROW_ID) Serializable id);

  int countById(@Param(Constants.ROW_ID) Serializable id);

  List<ENTITY> selectByIds(@Param(Constants.ROW_IDS) Collection<? extends Serializable> ids);

  int insertOne(ENTITY entity);

  int updateById(@Param(Constants.ENTITY) ENTITY entity);

  int updateByWhere(@Param(Constants.WRAPPER) Wrapper wrapper);

  int deleteById(@Param(Constants.ROW_ID) Serializable id);

  int deleteByIds(@Param(Constants.ROW_IDS) Collection<? extends Serializable> ids);

  int deleteByWhere(@Param(Constants.WRAPPER) Wrapper wrapper);

}
