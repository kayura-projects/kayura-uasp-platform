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

package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseApplicCommand extends Command {

  private OutputTypes output;
  private ApplicTypes type;
  private boolean notUasp;
  private String tenantId;
  private String companyId;
  private String userId;
  private Integer level;

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseApplicCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ChooseApplicCommand setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseApplicCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public ChooseApplicCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ChooseApplicCommand setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public boolean isNotUasp() {
    return notUasp;
  }

  public ChooseApplicCommand setNotUasp(boolean notUasp) {
    this.notUasp = notUasp;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public ChooseApplicCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
