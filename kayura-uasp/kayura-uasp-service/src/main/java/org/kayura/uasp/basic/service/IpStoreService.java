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
