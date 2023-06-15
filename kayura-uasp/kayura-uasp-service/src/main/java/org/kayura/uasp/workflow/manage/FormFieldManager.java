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
