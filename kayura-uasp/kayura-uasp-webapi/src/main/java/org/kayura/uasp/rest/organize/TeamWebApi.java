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
package org.kayura.uasp.rest.organize;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.organize.service.TeamService;
import org.kayura.uasp.team.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class TeamWebApi {

  private final TeamService teamService;
  private final CommandGateway commandGateway;

  public TeamWebApi(TeamService teamService,
                    CommandGateway commandGateway) {
    this.teamService = teamService;
    this.commandGateway = commandGateway;
  }

  @GetMapping("/team/choose/tenant")
  public HttpResult chooseTenantPage(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  /** Team */

  @GetMapping("/team/page")
  public HttpResult queryTeamPage(LoginUser loginUser, TeamQuery query, PageClause pageClause) {

    if (StringUtils.hasText(loginUser.getTenantId())) {
      query.setTenantId(loginUser.getTenantId());
    }
    PageList<TeamVo> pageList = teamService.queryTeamPage(query, pageClause);
    return HttpResult.okBody(pageList);
  }

  @GetMapping("/team/get")
  public HttpResult findTeamById(@RequestParam("id") String id) {

    TeamVo model = teamService.findTeamById(id);
    return HttpResult.okBody(model);
  }

  @PostMapping("/team/create")
  public HttpResult createTeam(LoginUser loginUser,
                               @RequestBody @Validated(Create.class) TeamBody form) {

    if (StringUtils.hasText(loginUser.getTenantId())) {
      form.setTenantId(loginUser.getTenantId());
    }
    teamService.createTeam(form, loginUser);
    return HttpResult.ok();
  }

  @PostMapping("/team/update")
  public HttpResult updateTeam(LoginUser loginUser,
                               @RequestParam("id") String id,
                               @RequestBody @Validated(Update.class) TeamBody form) {

    teamService.updateTeam(id, form, loginUser);
    return HttpResult.ok();
  }

  @PostMapping("/team/delete")
  public HttpResult deleteTeam(@RequestParam("id") String id) {

    teamService.deleteTeam(id);
    return HttpResult.ok();
  }

  /** TeamUser */

  @GetMapping("/team/user/page")
  public HttpResult queryTeamUserPage(TeamUserQuery query, PageClause pageClause) {

    PageList<TeamUserVo> pageList = teamService.queryTeamUserPage(query, pageClause);
    return HttpResult.okBody(pageList);
  }

  @GetMapping("/team/user/get")
  public HttpResult findTeamUserById(@RequestParam("id") String id) {

    TeamUserVo model = teamService.findTeamUserById(id);
    return HttpResult.okBody(model);
  }

  @PostMapping("/team/user/create")
  public HttpResult createTeamUser(@RequestBody @Validated(Create.class) TeamUserBody form) {

    teamService.createTeamUser(form);
    return HttpResult.ok();
  }

  @PostMapping("/team/user/update")
  public HttpResult updateTeamUser(@RequestParam("id") String id,
                                   @RequestBody @Validated(Update.class) TeamUserBody form) {

    teamService.updateTeamUser(id, form);
    return HttpResult.ok();
  }

  @PostMapping("/team/user/delete")
  public HttpResult deleteTeamUser(@RequestParam("id") String id) {

    teamService.deleteTeamUser(id);
    return HttpResult.ok();
  }

}
