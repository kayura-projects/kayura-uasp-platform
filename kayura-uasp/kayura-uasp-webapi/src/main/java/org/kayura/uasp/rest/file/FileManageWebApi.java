/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
