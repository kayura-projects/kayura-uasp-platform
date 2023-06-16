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

package org.kayura.uasp.activiti.manage;

import org.kayura.mybatis.manager.impl.SelectManagerImpl;
import org.kayura.uasp.activiti.entity.HistoryProcInstEntity;
import org.kayura.uasp.activiti.mapper.HistoryProcInstMapper;
import org.kayura.uasp.activiti.model.ProcInstCount;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryProcInstManager extends SelectManagerImpl<HistoryProcInstMapper, HistoryProcInstEntity> {

  private final HistoryProcInstMapper thisMapper;

  protected HistoryProcInstManager(HistoryProcInstMapper baseMapper) {
    super(baseMapper);
    this.thisMapper = baseMapper;
  }

  public List<ProcInstCount> collectProcInst(List<String> processKeys) {
    return thisMapper.collectProcInst(processKeys);
  }

}
