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

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dict.DictDefinePayload;
import org.kayura.uasp.dict.DictDefineQuery;
import org.kayura.uasp.dict.DictItemPayload;
import org.kayura.uasp.dict.DictItemQuery;
import org.kayura.uasp.organize.cmd.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_DICT)
public class DictionaryWebApi {

  private final CommandGateway commandGateway;

  public DictionaryWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  /** DictDefine */

  @GetMapping("/dict/define/tree")
  @Secured(actions = QUERY)
  public HttpResult selectDefineTree(ChooseDictDefineCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/dict/define/page")
  @Secured(actions = QUERY)
  public HttpResult selectDictDefinePage(QueryDictDefineCommand command,
                                         @RequestBody @Validated DictDefineQuery query,
                                         PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/dict/define/get")
  @Secured(actions = QUERY)
  public HttpResult selectDictDefineById(GetDictDefineCommand command,
                                         @RequestParam("id") String defineId) {

    return commandGateway.send(command.setDefineId(defineId));
  }

  @PostMapping("/dict/define/create")
  @Secured(actions = CREATE)
  public HttpResult createDictDefine(CreateDictDefineCommand command,
                                     @RequestBody @Validated(Create.class) DictDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/dict/define/update")
  @Secured(actions = UPDATE)
  public HttpResult updateDictDefine(CreateDictDefineCommand command,
                                     @RequestBody @Validated(Update.class) DictDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/dict/define/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteDictDefine(DeleteDictDefineCommand command,
                                     @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  /** DictItem */

  @PostMapping("/dict/item/page")
  @Secured(actions = QUERY)
  public HttpResult selectDictItemPage(QueryDictItemCommand command,
                                       @RequestBody @Validated DictItemQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @PostMapping("/dict/item/tree")
  @Secured(actions = QUERY)
  public HttpResult selectDictItemTree(QueryDictItemCommand command,
                                       @RequestBody @Validated DictItemQuery query) {

    return commandGateway.send(command.setQuery(query).setOutput(OutputTypes.TREE));
  }

  @GetMapping("/dict/item/get")
  @Secured(actions = QUERY)
  public HttpResult selectDictItemById(GetDictItemCommand command,
                                       @RequestParam("id") String itemId) {

    return commandGateway.send(command.setItemId(itemId));
  }

  @PostMapping("/dict/item/create")
  @Secured(actions = CREATE)
  public HttpResult createDictItem(CreateDictItemCommand command,
                                   @RequestBody @Validated(Create.class) DictItemPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/dict/item/update")
  @Secured(actions = UPDATE)
  public HttpResult updateDictItem(UpdateDictItemCommand command,
                                   @RequestBody @Validated(Update.class) DictItemPayload payload) {

    return commandGateway.send(command.setItemId(payload.getItemId()).setPayload(payload));
  }

  @PostMapping("/dict/item/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteDictItem(DeleteDictItemCommand command,
                                   @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
