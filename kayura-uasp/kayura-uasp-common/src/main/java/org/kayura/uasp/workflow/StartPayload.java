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

package org.kayura.uasp.workflow;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public class StartPayload {

  @NotBlank
  private String businessKey;
  private String tenantId;
  private String title;
  private String initiator;
  private String comment;
  @NotBlank
  private String processKey;
  private List<NextTaskFrm> nextTasks;
  private Map<String, Object> variables;

  public static StartPayload create() {
    return new StartPayload();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public StartPayload setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public StartPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public StartPayload setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getInitiator() {
    return initiator;
  }

  public StartPayload setInitiator(String initiator) {
    this.initiator = initiator;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public StartPayload setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public StartPayload setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

  public StartPayload setVariables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public List<NextTaskFrm> getNextTasks() {
    return nextTasks;
  }

  public StartPayload setNextTasks(List<NextTaskFrm> nextTasks) {
    this.nextTasks = nextTasks;
    return this;
  }
}
