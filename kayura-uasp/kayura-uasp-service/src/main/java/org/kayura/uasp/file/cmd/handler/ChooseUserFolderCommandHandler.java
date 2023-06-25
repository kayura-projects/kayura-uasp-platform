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

package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.TreeNode;
import org.kayura.uasp.file.OwnerTypes;
import org.kayura.uasp.file.cmd.ChooseUserFolderCommand;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.uasp.file.manage.FileManager;
import org.kayura.uasp.file.utils.FileConst;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChooseUserFolderCommandHandler implements CommandHandler<ChooseUserFolderCommand, HttpResult>, FileConst {

  private final FileManager fileManager;
  private final FileFolderManager folderManager;

  public ChooseUserFolderCommandHandler(FileManager fileManager,
                                        FileFolderManager folderManager) {
    this.fileManager = fileManager;
    this.folderManager = folderManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseUserFolderCommand command) {


    String userId = command.getUserId();

    List<TreeNode> treeNodes = new ArrayList<>();

    // 文件归类节点
    TreeNode basicNode = TreeNode.create()
      .setId(MY_FILE)
      .setText("我的文件")
      .setDepth(0)
      .setType(MY_FILE);
    treeNodes.add(basicNode);
    fileManager.buildBasicNodes(basicNode);

    // 我的目录
    TreeNode folderNode = TreeNode.create().setId(FOLDER).setText("我的目录").setDepth(0).setType(FOLDER);
    treeNodes.add(folderNode);
    List<FileFolderEntity> allFolders = folderManager.selectList(w -> {
      w.eq(FileFolderEntity::getOwnerType, OwnerTypes.USER);
      w.eq(FileFolderEntity::getOwnerId, userId);
    });
    List<FileFolderEntity> folders = allFolders.stream().filter(x -> StringUtils.isBlank(x.getParentId())).collect(Collectors.toList());
    fileManager.buildMyChildFolder(folderNode, folders, allFolders);

    return HttpResult.okBody(treeNodes);
  }

}
