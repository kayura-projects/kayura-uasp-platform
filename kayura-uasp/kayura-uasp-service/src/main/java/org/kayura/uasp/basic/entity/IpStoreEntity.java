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