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
package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.BpmnXmlPayload;

public class SaveBpmnModelCommand extends Command {

  private String modelId;
  private String body;
  private BpmnXmlPayload payload;

  public String getModelId() {
    return modelId;
  }

  public SaveBpmnModelCommand setModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }

  public String getBody() {
    return body;
  }

  public SaveBpmnModelCommand setBody(String body) {
    this.body = body;
    return this;
  }

  public BpmnXmlPayload getPayload() {
    return payload;
  }

  public SaveBpmnModelCommand setPayload(BpmnXmlPayload payload) {
    this.payload = payload;
    return this;
  }
}
