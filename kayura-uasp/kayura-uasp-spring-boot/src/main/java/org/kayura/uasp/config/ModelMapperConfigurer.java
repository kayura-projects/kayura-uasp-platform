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

package org.kayura.uasp.config;

import org.kayura.autoconfigure.bean.ModelMapperCustomizer;
import org.kayura.type.ExtendField;
import org.kayura.type.ExtendFields;
import org.kayura.uasp.dict.DictFieldList;
import org.kayura.uasp.workflow.FlowLabel;
import org.kayura.uasp.workflow.FlowLabels;
import org.kayura.uasp.workflow.FormulaGroups;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigurer implements ModelMapperCustomizer {

  @Override
  public void customize(ModelMapper modelMapper) {

    // DictFieldList
    modelMapper.addConverter(new AbstractConverter<DictFieldList, DictFieldList>() {
      @Override
      protected DictFieldList convert(DictFieldList source) {
        DictFieldList list = null;
        if (source != null) {
          list = new DictFieldList();
          if (!source.isEmpty()) {
            list.addAll(source);
          }
        }
        return list;
      }
    });
    // FormulaGroups
    modelMapper.addConverter(new AbstractConverter<FormulaGroups, FormulaGroups>() {
      @Override
      protected FormulaGroups convert(FormulaGroups source) {
        FormulaGroups list = null;
        if (source != null) {
          list = new FormulaGroups();
          if (!source.isEmpty()) {
            list.addAll(source);
          }
        }
        return list;
      }
    });
    // ExtendFieldList
    modelMapper.addConverter(new AbstractConverter<ExtendFields, ExtendFields>() {
      @Override
      protected ExtendFields convert(ExtendFields source) {
        ExtendFields list = null;
        if (source != null) {
          list = new ExtendFields();
          for (ExtendField row : source) {
            list.add(ExtendField.clone(row));
          }
        }
        return list;
      }
    });
    // FlowLabels
    modelMapper.addConverter(new AbstractConverter<FlowLabels, FlowLabels>() {
      @Override
      protected FlowLabels convert(FlowLabels source) {
        FlowLabels list = null;
        if (source != null) {
          list = new FlowLabels();
          for (FlowLabel row : source) {
            list.add(FlowLabel.clone(row));
          }
        }
        return list;
      }
    });

  }
}
