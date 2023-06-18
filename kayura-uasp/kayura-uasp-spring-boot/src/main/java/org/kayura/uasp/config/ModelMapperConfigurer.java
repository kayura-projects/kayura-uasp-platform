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
