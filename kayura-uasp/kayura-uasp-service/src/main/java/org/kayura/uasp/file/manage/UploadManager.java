package org.kayura.uasp.file.manage;

import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.UploadFile;
import org.kayura.uasp.file.UploadResult;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.entity.FileStoreEntity;
import org.kayura.uasp.file.handler.FileClassifyHandler;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UploadManager {

  private final FileLinkManager linkManager;
  private final FileStoreManager storeManager;
  private final FileClassifyHandler classifyHandler;
  private final ModelMapper modelMapper;

  public UploadManager(FileLinkManager linkManager,
                       FileStoreManager storeManager,
                       FileClassifyHandler classifyHandler,
                       ModelMapper modelMapper) {
    this.linkManager = linkManager;
    this.storeManager = storeManager;
    this.classifyHandler = classifyHandler;
    this.modelMapper = modelMapper;
  }

  public UploadResult storeUploadFile(UploadFile form) {

    FileLinkEntity fileLink = FileLinkEntity.create()
      .setLinkId(linkManager.nextId())
      .setFolderId(form.getFolderId())
      .setBusinessKey(form.getBusinessKey())
      .setCategory(form.getCategory())
      .setDisplayName(form.getFileName())
      .setUploaderId(form.getUploaderId())
      .setUploaderName(form.getUploaderName())
      .setUploadTime(DateUtils.now())
      .setPostfix(form.getPostfix())
      .setDownloads(0)
      .setTags(form.getTags());

    // 只读文件
    if (Boolean.TRUE.equals(form.getReadonly())) {
      FileStoreEntity fileStore = storeManager.selectOne(w -> {
        w.eq(FileStoreEntity::getHashCode, form.getHashCode());
      });
      if (fileStore != null) {
        fileLink.setFileId(fileStore.getFileId());
      }
    }

    // 附件不存在，将要求创建新文件
    boolean isNew = StringUtils.isBlank(fileLink.getFileId());
    if (isNew) {
      FileClassify classify = this.classifyHandler.resolve(form);
      FileStoreEntity fileStore = FileStoreEntity.create()
        .setFileId(storeManager.nextId())
        .setContentType(form.getContentType())
        .setClassify(classify)
        .setFileSize(form.getFileSize())
        .setStoreType(form.getStoreType())
        .setStorePath(form.getLogicPath())
        .setSalt(form.getSalt())
        .setHashCode(form.getHashCode())
        .setReadonly(Boolean.TRUE.equals(form.getReadonly()))
        .setEncrypted(Boolean.TRUE.equals(form.getEncrypt()))
        .setExists(Boolean.TRUE);
      fileLink.setFileId(fileStore.getFileId());

      // 保存至数据库
      storeManager.insertOne(fileStore);
    }

    // 写入
    linkManager.insertOne(fileLink);

    // 构建返回结果
    FileLinkEntity link = linkManager.selectById(fileLink.getLinkId());
    UploadResult result = modelMapper.map(link, UploadResult.class)
      .setNew(isNew);
    return result;
  }

}
