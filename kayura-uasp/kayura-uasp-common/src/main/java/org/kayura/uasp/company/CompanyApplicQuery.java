package org.kayura.uasp.company;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class CompanyApplicQuery {

  private CompanyTypes companyType;
  private DataStatus status;
  @Like("companyCode,companyName")
  private String searchCompany;
  @Like("appCode,appName")
  private String searchApp;

  public static CompanyApplicQuery create() {
    return new CompanyApplicQuery();
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyApplicQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchCompany() {
    return searchCompany;
  }

  public CompanyApplicQuery setSearchCompany(String searchCompany) {
    this.searchCompany = searchCompany;
    return this;
  }

  public String getSearchApp() {
    return searchApp;
  }

  public CompanyApplicQuery setSearchApp(String searchApp) {
    this.searchApp = searchApp;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public CompanyApplicQuery setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }
}
