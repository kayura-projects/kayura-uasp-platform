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
