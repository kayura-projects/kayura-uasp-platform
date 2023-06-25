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
