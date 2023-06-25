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

package org.kayura.uasp.workflow.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.form.FormFieldFrm;
import org.kayura.uasp.workflow.entity.FormFieldEntity;
import org.kayura.uasp.workflow.mapper.FormFieldMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormFieldManager extends CrudManagerImpl<FormFieldMapper, FormFieldEntity> {

  protected FormFieldManager(FormFieldMapper baseMapper) {
    super(baseMapper);
  }

  public void batchInsert(String formId, List<FormFieldFrm> fields) {

    for (FormFieldFrm field : fields) {
      FormFieldEntity fieldEntity = FormFieldEntity.create()
        .setFieldId(this.nextId())
        .setFormId(formId)
        .setFieldName(field.getFieldName())
        .setPropName(field.getPropName())
        .setDisplayName(field.getDisplayName())
        .setSort(field.getSort())
        .setDataType(field.getDataType())
        .setUsage(field.getUsage());
      this.insertOne(fieldEntity);
    }
  }
}
