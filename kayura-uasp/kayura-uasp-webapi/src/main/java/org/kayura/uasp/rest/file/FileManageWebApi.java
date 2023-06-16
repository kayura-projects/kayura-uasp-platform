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
package org.kayura.uasp.rest.file;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.file.FileLinkQuery;
import org.kayura.uasp.file.FolderPayload;
import org.kayura.uasp.file.MoveFilePayload;
import org.kayura.uasp.file.cmd.*;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.UASP_FILE_MANAGE;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_FILE_MANAGE)
public class FileManageWebApi {

  private final CommandGateway commandGateway;

  public FileManageWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/file/folder/my-tree")
  public HttpResult MyFolderTree(QueryUserFolderCommand command) {

    return commandGateway.send(command);
  }

  @GetMapping("/file/choose/my-folder")
  public HttpResult chooseMyFolderTree(ChooseUserFolderCommand command) {

    return commandGateway.send(command);
  }

  /** fileLink */

  @PostMapping("/file/page")
  public HttpResult queryMyFileItemPage(QueryFileLinkCommand command,
                                        @RequestBody FileLinkQuery query,
                                        PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/file/get")
  public HttpResult findFileDetailById(GetFileLinkCommand command,
                                       @RequestParam("id") String linkId) {

    return commandGateway.send(command.setLinkId(linkId));
  }

  @PostMapping("/file/delete")
  public HttpResult deleteFileLinkById(DeleteFileLinkCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/file/move")
  public HttpResult moveFileLinks(MoveFileLinkCommand command,
                                  @RequestBody @Validated MoveFilePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  /** fileFolder */

  @PostMapping("/file/folder/create")
  public HttpResult createFolder(CreateFileFolderCommand command,
                                 @RequestBody @Validated(Create.class) FolderPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/file/folder/update")
  public HttpResult updateFolder(UpdateFileFolderCommand command,
                                 @RequestBody @Validated(Update.class) FolderPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/file/folder/delete")
  public HttpResult deleteFolder(DeleteFileFolderCommand command,
                                 @RequestParam("id") String folderId) {

    return commandGateway.send(command.setFolderId(folderId));
  }
}
