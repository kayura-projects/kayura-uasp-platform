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
package org.kayura.uasp.rest.dev;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dict.DictDefinePayload;
import org.kayura.uasp.dict.DictDefineQuery;
import org.kayura.uasp.organize.cmd.*;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_DICT_DEFINE)
public class DictDefineWebApi {

  private final CommandGateway commandGateway;

  public DictDefineWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  /** DictDefine */

  @GetMapping("/dict-define/tree")
  @Secured(actions = QUERY)
  public HttpResult selectDefineTree(ChooseDictDefineCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/dict-define/page")
  @Secured(actions = QUERY)
  public HttpResult selectDictDefinePage(QueryDictDefineCommand command,
                                         @RequestBody @Validated DictDefineQuery query,
                                         PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/dict-define/get")
  @Secured(actions = QUERY)
  public HttpResult selectDictDefineById(GetDictDefineCommand command,
                                         @RequestParam("id") String defineId) {

    return commandGateway.send(command.setDefineId(defineId));
  }

  @PostMapping("/dict-define/create")
  @Secured(actions = MANAGE)
  public HttpResult createDictDefine(CreateDictDefineCommand command,
                                     @RequestBody @Validated(Create.class) DictDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/dict-define/update")
  @Secured(actions = MANAGE)
  public HttpResult updateDictDefine(UpdateDictDefineCommand command,
                                     @RequestBody @Validated(Update.class) DictDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/dict-define/delete")
  @Secured(actions = MANAGE)
  public HttpResult deleteDictDefine(DeleteDictDefineCommand command,
                                     @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
