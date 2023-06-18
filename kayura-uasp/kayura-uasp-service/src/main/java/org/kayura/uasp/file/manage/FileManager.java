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

package org.kayura.uasp.file.manage;

import org.kayura.type.TreeNode;
import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.utils.FileConst;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileManager implements FileConst {

  public void buildBasicNodes(TreeNode basic) {
    basic.addChildren(TreeNode.create().setId(LATELY).setText("最近").setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.IMAGE.getValue()).setText(FileClassify.IMAGE.getName()).setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.VIDEO.getValue()).setText(FileClassify.VIDEO.getName()).setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.DOC.getValue()).setText(FileClassify.DOC.getName()).setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.PDF.getValue()).setText(FileClassify.PDF.getName()).setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.ZIP.getValue()).setText(FileClassify.ZIP.getName()).setType(MY_FILE));
    basic.addChildren(TreeNode.create().setId(FileClassify.OTHER.getValue()).setText(FileClassify.OTHER.getName()).setType(MY_FILE));
  }

  public void buildMyChildFolder(TreeNode treeNode, List<FileFolderEntity> folders, List<FileFolderEntity> allFolders) {

    for (FileFolderEntity folder : folders) {
      TreeNode node = TreeNode.create().setId(folder.getFolderId()).setText(folder.getName()).setType(FOLDER);
      treeNode.addChildren(node);
      List<FileFolderEntity> collect = allFolders.stream()
        .filter(x -> folder.getFolderId().equalsIgnoreCase(x.getParentId()))
        .collect(Collectors.toList());
      buildMyChildFolder(node, collect, allFolders);
    }
  }

}
