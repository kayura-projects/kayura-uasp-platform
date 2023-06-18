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
