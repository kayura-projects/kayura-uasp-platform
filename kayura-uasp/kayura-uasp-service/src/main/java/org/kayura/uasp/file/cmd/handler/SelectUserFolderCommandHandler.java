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
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.TreeNode;
import org.kayura.uasp.file.OwnerTypes;
import org.kayura.uasp.file.cmd.QueryUserFolderCommand;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.entity.FileShareEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.uasp.file.manage.FileManager;
import org.kayura.uasp.file.manage.FileShareManager;
import org.kayura.uasp.file.utils.FileConst;
import org.kayura.uasp.organize.entity.TeamUserEntity;
import org.kayura.uasp.organize.manage.TeamUserManager;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SelectUserFolderCommandHandler
  implements CommandHandler<QueryUserFolderCommand, HttpResult>, FileConst {

  private final FileFolderManager folderManager;
  private final TeamUserManager teamUserManager;
  private final FileShareManager shareManager;
  private final FileManager fileManager;

  public SelectUserFolderCommandHandler(FileFolderManager folderManager,
                                        TeamUserManager teamUserManager,
                                        FileShareManager shareManager,
                                        FileManager fileManager) {
    this.folderManager = folderManager;
    this.teamUserManager = teamUserManager;
    this.shareManager = shareManager;
    this.fileManager = fileManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryUserFolderCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String userId = Optional.ofNullable(command.getUserId()).orElse(loginUser.getUserId());

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
    TreeNode folderNode = TreeNode.create()
      .setId(FOLDER)
      .setText("我的目录")
      .setDepth(0)
      .setType(FOLDER);
    treeNodes.add(folderNode);
    List<FileFolderEntity> allFolders = folderManager.selectList(w -> {
      w.eq(FileFolderEntity::getOwnerType, OwnerTypes.USER);
      w.eq(FileFolderEntity::getOwnerId, userId);
    });
    List<FileFolderEntity> folders = allFolders.stream().filter(x -> StringUtils.isBlank(x.getParentId())).collect(Collectors.toList());
    fileManager.buildMyChildFolder(folderNode, folders, allFolders);

    // 分享的文件
    TreeNode shareNode = TreeNode.create()
      .setId(SHARE)
      .setText("分享文件")
      .setDepth(0)
      .setType(SHARE);
    treeNodes.add(shareNode);
    buildShareFolder(userId, shareNode);

    // 群组文件
    TreeNode teamNode = TreeNode.create()
      .setId(GROUP)
      .setText("群组文件")
      .setDepth(0)
      .setType(GROUP);
    treeNodes.add(teamNode);
    buildTeamFolder(userId, allFolders, teamNode);

    return HttpResult.okBody(treeNodes);
  }

  void buildTeamFolder(String userId, List<FileFolderEntity> allFolders, TreeNode teamNode) {

    Map<String, String> teams = teamUserManager.selectList(w -> {
      w.distinct();
      w.select(TeamUserEntity::getTeamId);
      w.select(TeamUserEntity::getTeamName);
      w.eq(TeamUserEntity::getTeamStatus, DataStatus.Valid);
      w.eq(TeamUserEntity::getEmployeeId, userId);
      w.isNull(TeamUserEntity::getLeaveTime);
    }).stream().collect(Collectors.toMap(TeamUserEntity::getTeamId, TeamUserEntity::getTeamName));

    if (!teams.isEmpty()) {
      List<FileFolderEntity> teamFolders = folderManager.selectList(w -> {
        w.eq(FileFolderEntity::getOwnerType, OwnerTypes.GROUP);
        w.in(FileFolderEntity::getOwnerId, teams.keySet());
      });
      if (!teamFolders.isEmpty()) {
        for (Map.Entry<String, String> map : teams.entrySet()) {
          TreeNode node = TreeNode.create().setId(map.getKey()).setText(map.getValue()).setType(GROUP);
          teamNode.addChildren(node);
          List<FileFolderEntity> collect = allFolders.stream().filter(x -> StringUtils.hasText(x.getParentId())).collect(Collectors.toList());
          fileManager.buildMyChildFolder(node, collect, teamFolders);
        }
      }
    }
  }

  void buildShareFolder(String userId, TreeNode shareNode) {

    List<FileShareEntity> allShares = shareManager.selectList(w -> {
      w.and(x -> x.isNull(FileShareEntity::getExpireTime).or().gt(FileShareEntity::getExpireTime, DateUtils.now()));
      w.and(x -> x.eq(FileShareEntity::getReceiverId, userId).or().eq(FileShareEntity::getShareId, userId));
    });

    // 分享给我
    TreeNode myReceiveNode = TreeNode.create().setId(MY_RECEIVE).setText("分享给我").setType(SHARE);
    shareNode.addChildren(myReceiveNode);
    List<FileShareEntity> receivers = allShares.stream()
      .filter(x -> userId.equalsIgnoreCase(x.getReceiverId()))
      .toList();
    for (FileShareEntity item : receivers) {
      myReceiveNode.addChildren(TreeNode.create().setId(item.getFolderId()).setText(item.getFolderName()).setType(SHARE));
    }

    // 我分享的
    TreeNode myShareNode = TreeNode.create().setId(MY_SHARE).setText("我分享的").setType(SHARE);
    shareNode.addChildren(myShareNode);
    List<FileShareEntity> sharers = allShares.stream()
      .filter(x -> userId.equalsIgnoreCase(x.getShareId()))
      .toList();
    for (FileShareEntity item : sharers) {
      myShareNode.addChildren(TreeNode.create().setId(item.getFolderId()).setText(item.getFolderName()).setType(SHARE));
    }
  }

}
