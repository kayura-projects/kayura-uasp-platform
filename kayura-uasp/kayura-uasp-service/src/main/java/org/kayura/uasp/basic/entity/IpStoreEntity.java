package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.ip.IPTypes;

import java.time.LocalDateTime;

/**
 * IP地址库(uasp_ip_store) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_ip_store")
public class IpStoreEntity {

  /** IP地址 */
  @Id
  private String ipAddr;
  /** 显示地 */
  private String ipDesc;
  /** IP类型:LOCAL本机,LAN局域网,WAN广域网 */
  private IPTypes type;
  /** 更新时间 */
  private LocalDateTime updateTime;

  public static IpStoreEntity create() {
    return new IpStoreEntity();
  }

  public String getIpAddr() {
    return ipAddr;
  }

  public IpStoreEntity setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
    return this;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public IpStoreEntity setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public IpStoreEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public IPTypes getType() {
    return type;
  }

  public IpStoreEntity setType(IPTypes type) {
    this.type = type;
    return this;
  }
}