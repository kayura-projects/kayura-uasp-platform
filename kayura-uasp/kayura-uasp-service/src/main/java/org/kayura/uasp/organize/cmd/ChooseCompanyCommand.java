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

package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseCompanyCommand extends Command {

  private OutputTypes output;
  private CompanyTypes type;
  private String tenantId;
  private String parentId;
  private String categoryId;
  private OrderByClause orderByClause;

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseCompanyCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseCompanyCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public ChooseCompanyCommand setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ChooseCompanyCommand setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public ChooseCompanyCommand setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public ChooseCompanyCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }
}
