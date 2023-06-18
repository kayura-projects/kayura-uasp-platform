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

import org.kayura.except.BusinessException;
import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.DataStatus;
import org.kayura.uasp.activiti.model.BizTableInfo;
import org.kayura.uasp.form.FieldUsage;
import org.kayura.uasp.workflow.*;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.entity.FormFieldEntity;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.handler.InnerExprManager;
import org.kayura.uasp.workflow.mapper.FormDefineMapper;
import org.kayura.uasp.workflow.mapper.FormFlowMapper;
import org.kayura.utils.Assert;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FormFlowManager extends CrudManagerImpl<FormFlowMapper, FormFlowEntity> {

  private final ModelMapper modelMapper;
  private final InnerExprManager innerExprManager;
  private final FormDefineMapper formDefineMapper;
  private final FormFieldManager formFieldManager;
  private final FlowLabelManager flowLabelManager;
  private final JdbcTemplate jdbcTemplate;

  protected FormFlowManager(FormFlowMapper baseMapper,
                            ModelMapper modelMapper,
                            InnerExprManager innerExprManager,
                            FormDefineMapper formDefineMapper,
                            FormFieldManager formFieldManager,
                            FlowLabelManager flowLabelManager,
                            JdbcTemplate jdbcTemplate) {
    super(baseMapper);
    this.modelMapper = modelMapper;
    this.innerExprManager = innerExprManager;
    this.formDefineMapper = formDefineMapper;
    this.formFieldManager = formFieldManager;
    this.flowLabelManager = flowLabelManager;
    this.jdbcTemplate = jdbcTemplate;
  }

  public String readFlowLabel(String processKey, String businessKey) {

    BizTableInfo info = this.findTableInfo(processKey);
    if (StringUtils.isNotBlank(info.getFlowField())) {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT ").append(info.getTableName());
      sql.append(" ").append(info.getFlowField()).append(" ");
      sql.append("WHERE ").append(info.getIdField()).append(" = ? ");
      String flowLabel = jdbcTemplate.queryForObject(sql.toString(), String.class);
      return flowLabel;
    } else {
      return flowLabelManager.readFlowLabel(businessKey);
    }
  }

  public BizTableInfo findTableInfo(String processKey) {

    FormFlowEntity flow = this.selectOne(w ->
      w.eq(FormFlowEntity::getProcessKey, processKey)
    );
    Assert.notNull(flow, "运行的流程KEY：" + processKey + "，找不到。");

    FormDefineEntity define = formDefineMapper.selectById(flow.getFormId());
    Assert.notNull(define, "定义的业务表单：" + flow.getFormCode() + "，找不到。");

    List<FormFieldEntity> fields = formFieldManager.selectList(w -> {
      w.eq(FormFieldEntity::getFormId, flow.getFormId());
    });

    BizTableInfo info = BizTableInfo.create();
    info.setTableName(Optional.ofNullable(define.getTable())
      .orElseThrow(() -> new BusinessException("业务表单定义中未设置表名。")));
    info.setIdField(fields.stream().filter(x -> FieldUsage.PrimaryKey.equals(x.getUsage()))
      .map(FormFieldEntity::getFieldName).findFirst()
      .orElseThrow(() -> new BusinessException("业务表单定义中未设置主键字段。")));
    info.setStatusField(fields.stream().filter(x -> FieldUsage.Status.equals(x.getUsage()))
      .map(FormFieldEntity::getFieldName).findFirst().orElse(StringUtils.EMPTY));
    info.setFlowField(fields.stream().filter(x -> FieldUsage.FlowCode.equals(x.getUsage()))
      .map(FormFieldEntity::getFieldName).findFirst().orElse(StringUtils.EMPTY));
    return info;
  }

  public void updateAuditStatus(String processKey, String businessKey, String status) {

    BizTableInfo info = this.findTableInfo(processKey);
    Assert.hasText(info.getStatusField(), "业务表单定义中未设置审批状态字段。");

    // 更新审批状态
    StringBuilder sql = new StringBuilder();
    sql.append("UPDATE ").append(info.getTableName());
    sql.append(" SET ").append(info.getStatusField()).append(" = ? ");
    sql.append("WHERE ").append(info.getIdField()).append(" = ? ");
    jdbcTemplate.update(sql.toString(), status, businessKey);
  }

  public void updateFlowLabel(String processKey, String businessKey, String flowLabel) {
    BizTableInfo info = this.findTableInfo(processKey);
    if (StringUtils.isNotBlank(info.getFlowField())) {
      // 更新审批状态
      StringBuilder sql = new StringBuilder();
      sql.append("UPDATE ").append(info.getTableName());
      sql.append(" SET ").append(info.getFlowField()).append(" = ? ");
      sql.append("WHERE ").append(info.getIdField()).append(" = ? ");
      jdbcTemplate.update(sql.toString(), flowLabel, businessKey);
    } else {
      flowLabelManager.insertOrUpdate(businessKey, flowLabel);
    }
  }

  public List<ChooseFlowVo> chooseBizFlow(ChooseFlowQry query) {

    List<FormFlowEntity> entities = this.selectList(w -> {
      w.select(FormFlowEntity::getProcessKey);
      w.select(FormFlowEntity::getDisplayName);
      w.select(FormFlowEntity::getDescription);
      w.select(FormFlowEntity::getPrimary);
      w.select(FormFlowEntity::getExprType);
      w.select(FormFlowEntity::getInnerExpr);
      w.select(FormFlowEntity::getCustomExpr);
      w.eq(FormFlowEntity::getTenantId, query.getTenantId());
      w.eq(FormFlowEntity::getFormCode, query.getFormCode());
      w.eq(FormFlowEntity::getStatus, DataStatus.Valid);
    });

    Assert.notEmpty(entities, "没有可用的流程图。");

    List<FormFlowEntity> selected;
    if (entities.size() == 1) {
      selected = entities;
    } else {
      selected = new ArrayList<>();
      for (FormFlowEntity entity : entities) {
        if (ExprTypes.NONE.equals(entity.getExprType())) {
          selected.add(entity);
        } else if (ExprTypes.INNER.equals(entity.getExprType())) {
          InnerExprArgs args = InnerExprArgs.create()
            .setTenantId(query.getTenantId())
            .setFormCode(query.getFormCode())
            .setVariables(query.getVariables());
          if (innerExprManager.execute(entity.getInnerExpr(), args)) {
            selected.add(entity);
          }
        } else if (ExprTypes.CUSTOM.equals(entity.getExprType())) {
          if (FormulaGroups.hasTrue(entity.getCustomExpr(), query.getVariables())) {
            selected.add(entity);
          }
        }
      }
      if (selected.isEmpty()) {
        selected = entities.stream().filter(FormFlowEntity::getPrimary).collect(Collectors.toList());
      }
    }

    return selected.stream().map(m -> modelMapper.map(m, ChooseFlowVo.class)).collect(Collectors.toList());
  }

  public void autoPatchPrimary(boolean primary, String formId) {

    if (primary) {
      this.updateByWhere(w -> {
        w.set(FormFlowEntity::getPrimary, Boolean.FALSE);
        w.eq(FormFlowEntity::getFormId, formId);
        w.eq(FormFlowEntity::getPrimary, Boolean.TRUE);
      });
    }
  }

  public FlowLabels sortedFlowLabels(FlowLabels saveLabels) {

    FlowLabels result = new FlowLabels();
    if (saveLabels != null) {
      List<FlowLabel> labels = saveLabels.stream()
        .sorted(Comparator.comparingInt(FlowLabel::getSort))
        .toList();
      for (int i = 0; i < labels.size(); i++) {
        FlowLabel m = labels.get(i);
        result.add(FlowLabel.create()
          .setCode(m.getCode())
          .setName(m.getName())
          .setSort(i));
      }
    }
    return result;
  }
}
