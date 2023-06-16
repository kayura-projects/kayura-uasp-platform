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
package org.kayura.uasp.rest.basic;

import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.service.IpStoreService;
import org.kayura.uasp.ip.IpStoreBody;
import org.kayura.uasp.ip.IpStoreQuery;
import org.kayura.uasp.ip.IpStoreVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_IP_STORE)
public class IpStoreWebApi {

  private final IpStoreService ipStoreService;

  public IpStoreWebApi(IpStoreService ipStoreService) {
    this.ipStoreService = ipStoreService;
  }

  @GetMapping("/ip-store/resolve")
  @Secured(actions = QUERY)
  public HttpResult resolveIpAddr(String ip) {

    String ipDesc = ipStoreService.resolveIpAddr(ip);
    return HttpResult.okBody(ipDesc);
  }

  @PostMapping("/ip-store/page")
  @Secured(actions = QUERY)
  public HttpResult queryIpStorePage(@RequestBody @Validated IpStoreQuery query, PageClause pageClause) {

    PageList<IpStoreVo> pageList = ipStoreService.selectIpStorePage(query, pageClause);
    return HttpResult.okBody(pageList);
  }

  @GetMapping("/ip-store/get")
  @Secured(actions = QUERY)
  public HttpResult findAutoNoById(String id) {

    IpStoreVo model = ipStoreService.selectIpStoreById(id);
    return HttpResult.okBody(model);
  }

  @PostMapping("/ip-store/create")
  @Secured(actions = CREATE)
  public HttpResult createIpStore(@RequestBody IpStoreBody form) {

    ipStoreService.createIpStore(form);
    return HttpResult.ok();
  }

  @PostMapping("/ip-store/update")
  @Secured(actions = UPDATE)
  public HttpResult updateIpStore(@RequestBody IpStoreBody form) {

    ipStoreService.updateIpStore(form);
    return HttpResult.ok();
  }

  @PostMapping("/ip-store/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteAutoNo(String id) {

    ipStoreService.deleteIpStore(id);
    return HttpResult.ok();
  }

}
