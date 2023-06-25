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
