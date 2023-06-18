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
