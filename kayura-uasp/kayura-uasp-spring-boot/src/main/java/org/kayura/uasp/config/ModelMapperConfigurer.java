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
