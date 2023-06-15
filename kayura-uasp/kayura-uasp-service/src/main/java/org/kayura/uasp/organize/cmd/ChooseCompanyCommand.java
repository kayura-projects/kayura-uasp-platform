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
