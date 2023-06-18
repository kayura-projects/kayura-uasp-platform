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
