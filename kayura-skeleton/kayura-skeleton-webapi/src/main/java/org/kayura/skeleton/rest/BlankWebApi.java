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
