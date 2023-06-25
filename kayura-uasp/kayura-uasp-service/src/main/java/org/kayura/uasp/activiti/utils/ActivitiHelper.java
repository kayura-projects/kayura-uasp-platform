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

package org.kayura.uasp.activiti.utils;

import org.kayura.type.Properties;
import org.kayura.utils.StringUtils;
import org.activiti.bpmn.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static org.kayura.uasp.activiti.utils.UaspBpmnConstants.*;

public class ActivitiHelper {


  public static String readValue(BaseElement element, String propName) {
    return readValue(element, propName, null);
  }

  public static String readValue(BaseElement element, String propName, String defaultValue) {

    Map<String, List<ExtensionElement>> extensionElements = element.getExtensionElements();
    if (extensionElements != null && extensionElements.containsKey(propName)) {
      List<ExtensionElement> elements = extensionElements.get(propName);
      for (ExtensionElement el : elements) {
        String prefix = el.getNamespacePrefix();
        if ((StringUtils.isBlank(prefix) || kayura_PREFIX.equals(prefix)) && propName.equals(el.getName())) {
          return el.getElementText();
        } else if (defaultValue == null && kayura_DEFAULT_VALUES.containsKey(el.getName())) {
          return kayura_DEFAULT_VALUES.get(el.getName());
        }
      }
    }
    return defaultValue;
  }

  public static String readValue(BpmnModel bpmnModel, String elementId, String propName) {
    return readValue(bpmnModel, elementId, propName, null);
  }

  public static String readValue(BpmnModel bpmnModel, String elementId, String propName, String defaultValue) {
    FlowElement flowElement = bpmnModel.getFlowElement(elementId);
    return readValue(flowElement, propName, defaultValue);
  }

  public static Properties readProperties(BpmnModel bpmnModel, String elementId) {

    Properties properties = new Properties();
    FlowElement element = bpmnModel.getFlowElement(elementId);
    if (element != null) {
      Map<String, List<ExtensionElement>> extensionElements = element.getExtensionElements();
      for (Map.Entry<String, List<ExtensionElement>> map : extensionElements.entrySet()) {
        for (ExtensionElement el : map.getValue()) {
          String prefix = el.getNamespacePrefix();
          if ((StringUtils.isBlank(prefix) || kayura_PREFIX.equals(prefix))) {
            properties.put(map.getKey(), el.getElementText());
          }
        }
      }
    }
    for (Map.Entry<String, String> e : kayura_DEFAULT_VALUES.entrySet()) {
      if (!properties.containsKey(e.getKey())) {
        properties.put(e.getKey(), e.getValue());
      }
    }
    return properties;
  }

  @NotNull
  public static String nextTaskContains(String nodeKey) {
    return "${" + NEXT_TASKS + ".contains(\"" + nodeKey + "\")}";
  }

  @NotNull
  public static String nextTaskUserIds(String nodeKey) {
    return "${" + NEXT_TASK_USER_IDS + nodeKey + "}";
  }

  @NotNull
  public static String nextTaskGroupIds(String nodeKey) {
    return "${" + NEXT_TASK_GROUP_IDS + nodeKey + "}";
  }
}
