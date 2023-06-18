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
