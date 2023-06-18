/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
