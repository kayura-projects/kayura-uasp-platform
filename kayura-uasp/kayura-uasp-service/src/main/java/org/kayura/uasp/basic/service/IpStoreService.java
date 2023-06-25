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

package org.kayura.uasp.basic.service;

import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.entity.IpStoreEntity;
import org.kayura.uasp.basic.manage.IpStoreManager;
import org.kayura.uasp.handler.IPAddrResolver;
import org.kayura.uasp.handler.IPResult;
import org.kayura.uasp.ip.IpStoreBody;
import org.kayura.uasp.ip.IpStoreQuery;
import org.kayura.uasp.ip.IpStoreVo;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
public class IpStoreService {

  private final ModelMapper modelMapper;
  private final IpStoreManager ipStoreManager;
  private final IPAddrResolver ipAddrResolver;

  public IpStoreService(ModelMapper modelMapper,
                        IpStoreManager ipStoreManager,
                        IPAddrResolver ipAddrResolver) {
    this.modelMapper = modelMapper;
    this.ipStoreManager = ipStoreManager;
    this.ipAddrResolver = ipAddrResolver;
  }

  public String resolveIpAddr(String ipAddr) {

    IpStoreEntity entity = ipStoreManager.selectById(ipAddr);
    if (entity == null) {
      IPResult result = ipAddrResolver.handle(ipAddr);
      entity = IpStoreEntity.create()
        .setIpAddr(ipAddr)
        .setIpDesc(result.getDesc())
        .setType(result.getType())
        .setUpdateTime(LocalDateTime.now());
      ipStoreManager.insertOne(entity);
    }
    return entity.getIpDesc();
  }

  public PageList<IpStoreVo> selectIpStorePage(IpStoreQuery query, PageClause pageClause) {

    PageList<IpStoreVo> pageList = ipStoreManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, IpStoreVo.class));
    return pageList;
  }

  public IpStoreVo selectIpStoreById(String ipAddr) {

    IpStoreEntity entity = ipStoreManager.selectById(ipAddr);
    return entity != null ? modelMapper.map(entity, IpStoreVo.class) : null;
  }

  public void createIpStore(@Validated(Create.class) IpStoreBody form) {

    IpStoreEntity entity = ipStoreManager.selectById(form.getIpAddr());
    if (entity == null) {
      IPResult result = ipAddrResolver.handle(form.getIpAddr());
      entity = IpStoreEntity.create()
        .setIpAddr(form.getIpAddr())
        .setIpDesc(result.getDesc())
        .setType(result.getType())
        .setUpdateTime(LocalDateTime.now());
      ipStoreManager.insertOne(entity);
    }
  }

  public void updateIpStore(@Validated(Update.class) IpStoreBody form) {

    ipStoreManager.updateByWhere(w -> {
      w.set(IpStoreEntity::getIpDesc, form.getIpDesc());
      w.set(IpStoreEntity::getType, form.getType());
      w.set(IpStoreEntity::getUpdateTime, LocalDateTime.now());
      w.eq(IpStoreEntity::getIpAddr, form.getIpAddr());
    });
  }

  public void deleteIpStore(String ipAddr) {

    ipStoreManager.deleteById(ipAddr);
  }

}
