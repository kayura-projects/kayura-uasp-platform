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

package org.kayura.uasp.config;

import org.kayura.mybatis.autoconfigure.MbConfigurationCustomizer;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.typehandler.JsonTypeHandler;
import org.kayura.uasp.dict.DictFieldList;
import org.kayura.uasp.workflow.FlowLabels;
import org.kayura.uasp.workflow.FormulaGroups;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfigurer implements MbConfigurationCustomizer {

  @Override
  public void customize(MbConfiguration configuration) {
    TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
    registry.register(DictFieldList.class, JsonTypeHandler.class);
    registry.register(FormulaGroups.class, JsonTypeHandler.class);
    registry.register(FlowLabels.class, JsonTypeHandler.class);
  }
}
