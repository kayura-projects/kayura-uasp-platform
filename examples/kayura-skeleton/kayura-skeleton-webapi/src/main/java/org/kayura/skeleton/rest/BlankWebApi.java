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

package org.kayura.skeleton.rest;

import org.kayura.security.LoginUser;
import org.kayura.security.annotation.Secured;
import org.kayura.skeleton.BlankPayload;
import org.kayura.skeleton.BlankQuery;
import org.kayura.skeleton.BlankVo;
import org.kayura.skeleton.service.BlankService;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.common.IdPayload;
import org.kayura.vaildation.Create;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("/api/skeleton")
public class BlankWebApi {

  private static final String SKE_BLANK = "SKE_BLANK";
  private final BlankService blankService;

  public BlankWebApi(BlankService blankService) {
    this.blankService = blankService;
  }

  @PostMapping("/blank/page")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectBlankPage(@RequestBody BlankQuery query,
                                    PageClause pageClause) {
    PageList<BlankVo> pageList = blankService.selectBlankPage(query, pageClause);
    return HttpResult.okBody(pageList);
  }

  @PostMapping("/blank/query")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectBlankList(@RequestBody BlankQuery query,
                                    OrderByClause orderByClause) {
    List<BlankVo> list = blankService.selectBlankList(query, orderByClause);
    return HttpResult.okBody(list);
  }

  @GetMapping("/blank/get")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectById(@RequestParam("id") String id) {

    BlankVo model = blankService.selectById(id);
    return HttpResult.okBody(model);
  }

  @PostMapping("/blank/create")
  @Secured(resource = SKE_BLANK, actions = CREATE)
  public HttpResult createBlank(@RequestBody @Validated(Create.class) BlankPayload payload,
                                LoginUser loginUser) {

    blankService.createBlank(payload, loginUser);
    return HttpResult.ok();
  }

  @PostMapping("/blank/update")
  @Secured(resource = SKE_BLANK, actions = UPDATE)
  public HttpResult updateBlank(@RequestParam("id") String id,
                                @RequestBody @Validated(Create.class) BlankPayload payload,
                                LoginUser loginUser) {

    blankService.updateBlank(id, payload, loginUser);
    return HttpResult.ok();
  }

  @PostMapping("/blank/delete")
  @Secured(resource = SKE_BLANK, actions = DELETE)
  public HttpResult deleteBlank(@RequestBody IdPayload payload) {

    blankService.deleteBlank(payload);
    return HttpResult.ok();
  }
}
