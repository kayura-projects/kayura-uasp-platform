/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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

  /**
   * 查询空白页面
   *
   * @param query      空白查询条件
   * @param pageClause 分页条件
   * @return 空白页面列表
   */
  @PostMapping("/blank/page")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectBlankPage(@RequestBody BlankQuery query, PageClause pageClause) {
    PageList<BlankVo> pageList = blankService.selectBlankPage(query, pageClause);
    return HttpResult.okBody(pageList);
  }

  /**
   * 查询空白列表
   *
   * @param query         空白查询条件
   * @param orderByClause 排序条件
   * @return 空白列表
   */
  @PostMapping("/blank/query")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectBlankList(@RequestBody BlankQuery query, OrderByClause orderByClause) {
    List<BlankVo> list = blankService.selectBlankList(query, orderByClause);
    return HttpResult.okBody(list);
  }

  /**
   * 根据ID查询空白
   *
   * @param id 空白ID
   * @return 空白信息
   */
  @GetMapping("/blank/get")
  @Secured(resource = SKE_BLANK, actions = QUERY)
  public HttpResult selectById(@RequestParam("id") String id) {
    BlankVo model = blankService.selectById(id);
    return HttpResult.okBody(model);
  }

  /**
   * 创建空白
   *
   * @param payload   创建空白的数据
   * @param loginUser 登录用户信息
   * @return 操作结果
   */
  @PostMapping("/blank/create")
  @Secured(resource = SKE_BLANK, actions = CREATE)
  public HttpResult createBlank(@RequestBody @Validated(Create.class) BlankPayload payload,
                                LoginUser loginUser) {
    blankService.createBlank(payload, loginUser);
    return HttpResult.ok();
  }

  /**
   * 更新空白
   *
   * @param id        空白ID
   * @param payload   更新空白的数据
   * @param loginUser 登录用户信息
   * @return 操作结果
   */
  @PostMapping("/blank/update")
  @Secured(resource = SKE_BLANK, actions = UPDATE)
  public HttpResult updateBlank(@RequestParam("id") String id,
                                @RequestBody @Validated(Create.class) BlankPayload payload,
                                LoginUser loginUser) {
    blankService.updateBlank(id, payload, loginUser);
    return HttpResult.ok();
  }

  /**
   * 删除空白
   *
   * @param payload 要删除的空白ID
   * @return 操作结果
   */
  @PostMapping("/blank/delete")
  @Secured(resource = SKE_BLANK, actions = DELETE)
  public HttpResult deleteBlank(@RequestBody IdPayload payload) {
    blankService.deleteBlank(payload);
    return HttpResult.ok();
  }
}
