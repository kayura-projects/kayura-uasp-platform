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
