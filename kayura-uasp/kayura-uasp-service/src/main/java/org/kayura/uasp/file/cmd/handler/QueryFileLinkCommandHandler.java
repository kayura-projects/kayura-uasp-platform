package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.file.FileLinkQuery;
import org.kayura.uasp.file.FileLinkVo;
import org.kayura.uasp.file.cmd.QueryFileLinkCommand;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.file.utils.FileConst;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryFileLinkCommandHandler implements CommandHandler<QueryFileLinkCommand, HttpResult>, FileConst {

  private final FileLinkManager linkManager;
  private final ModelMapper modelMapper;

  public QueryFileLinkCommandHandler(FileLinkManager linkManager,
                                     ModelMapper modelMapper) {
    this.linkManager = linkManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryFileLinkCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FileLinkQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<FileLinkVo> pageList = linkManager.selectPage(w -> {
      w.of(query);
      if (LATELY.equalsIgnoreCase(query.getType())) {
        w.orderByDesc(FileLinkEntity::getUploadTime);
      } else if (MY_FILE.equalsIgnoreCase(query.getType())) {
        w.eq(FileLinkEntity::getClassify, query.getClassify());
      } else if (FOLDER.equalsIgnoreCase(query.getType())) {
        if (StringUtils.hasText(query.getFolderId())) {
          w.eq(FileLinkEntity::getFolderId, query.getFolderId());
        }
      }
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.eq(FileLinkEntity::getTenantId, loginUser.getTenantId());
      } else if (StringUtils.hasText(query.getTenantId())) {
        w.eq(FileLinkEntity::getTenantId, query.getTenantId());
      } else {
        w.isNull(FileLinkEntity::getTenantId);
      }
      w.isNull(FileLinkEntity::getBusinessKey);
    }, pageClause).streamMap(m -> modelMapper.map(m, FileLinkVo.class));

    return HttpResult.okBody(pageList);
  }
}
