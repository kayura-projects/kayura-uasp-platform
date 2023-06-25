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
