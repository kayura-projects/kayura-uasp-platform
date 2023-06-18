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
