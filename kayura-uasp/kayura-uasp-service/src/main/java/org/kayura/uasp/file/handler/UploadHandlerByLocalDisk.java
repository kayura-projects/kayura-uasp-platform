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

package org.kayura.uasp.file.handler;

import org.kayura.except.ExceptUtils;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.StoreTypes;
import org.kayura.uasp.handler.StoreFileArgs;
import org.kayura.uasp.handler.UploadHandler;
import org.kayura.utils.AesUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.FileHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UploadHandlerByLocalDisk implements UploadHandler {

  private static final Log logger = LogFactory.getLog(UploadHandlerByLocalDisk.class);
  private final String storePath;
  private final String tempPath;

  public UploadHandlerByLocalDisk(String storePath, String tempPath) {
    this.storePath = storePath;
    this.tempPath = tempPath;
  }

  @Override
  public StoreTypes getStoreType() {
    return StoreTypes.NFS;
  }

  @Override
  public String getLogicPath() {
    return DateUtils.formatNow("yyyy/MMdd");
  }

  @Override
  public String getTempPath() {
    return this.tempPath;
  }

  @Override
  public void storeFile(StoreFileArgs args) throws Exception {

    // 获取文件存储绝对路径
    String logicPath = args.getLogicPath();
    File filePath = Paths.get(this.storePath, logicPath).toFile();
    if (!filePath.exists()) {
      try {
        if (!filePath.mkdirs()) {
          if (logger.isWarnEnabled()) {
            logger.warn("未能创建目录 " + filePath.getPath());
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    String absolutePath = filePath.getAbsolutePath();

    // 准备
    File writeFile = new File(absolutePath, args.getFileId());

    if (logger.isInfoEnabled()) {
      logger.info("准备写入文件 -> " + writeFile.getPath());
    }

    // 写入存储文件
    if (Boolean.TRUE.equals(args.getEncrypted())) {
      FileUtils.writeByteArrayToFile(writeFile, args.getFileContent());
    } else {
      args.getFile().transferTo(writeFile);
    }

  }

  @Override
  public File readMultiFile(List<DownloadFile> downloads) throws Exception {

    String rawValue = downloads.stream().map(DownloadFile::getFileId).collect(Collectors.joining("|"));
    String zipName = DigestUtils.md5DigestAsHex(rawValue.getBytes()) + ".zip";
    File downFile = new File(tempPath, zipName);
    if (!downFile.exists()) {

      OutputStream os = new BufferedOutputStream(Files.newOutputStream(downFile.toPath()));
      ZipOutputStream out = new ZipOutputStream(os);

      byte[] buf = new byte[1024 * 32];
      int len;

      List<String> fileNames = new ArrayList<>();
      try {
        for (DownloadFile download : downloads) {

          File srcFile = readSingleFile(download);
          String srcFileName = download.getDisplayName();
          long count = fileNames.stream().filter(a -> a.equalsIgnoreCase(download.getDisplayName())).count();
          if (count > 0) {
            if (srcFileName.indexOf(".") > 0) {
              String onlyName = srcFileName.substring(0, srcFileName.lastIndexOf("."));
              String onlyPostfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1);
              srcFileName = onlyName + "(" + count + ")." + onlyPostfix;
            } else {
              srcFileName = srcFileName + "(" + count + ")";
            }
          }
          fileNames.add(download.getDisplayName());
          ZipEntry entry = new ZipEntry(srcFileName);
          out.putNextEntry(entry);
          BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(srcFile.toPath()));
          while ((len = bis.read(buf)) > 0) {
            out.write(buf, 0, len);
          }
          bis.close();
          out.closeEntry();
        }
        out.finish();
        out.close();
      } catch (Exception ex) {
        if (!downFile.delete()) {
          if (logger.isWarnEnabled()) {
            logger.warn("未能删除文件 " + downFile.getPath());
          }
        }
        throw ex;
      }
    }

    return downFile;
  }

  @Override
  public File readSingleFile(DownloadFile download) throws Exception {

    String absPath = Paths.get(this.storePath, download.getStorePath()).toString();
    File srcFile = new File(absPath, download.getFileId());
    if (!srcFile.exists()) {
      ExceptUtils.io("要下载的文件不存在。");
    }

    // 处理加密文件
    if (download.getEncrypted()) {
      File aesFile = new File(tempPath, download.getFileId());
      if (!aesFile.exists()) {
        byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
        fileContent = AesUtils.aesDecrypt(fileContent, download.getSalt());
        FileUtils.writeByteArrayToFile(aesFile, fileContent);
      }
      srcFile = aesFile;
    }

    return srcFile;
  }

  @Override
  public List<String> storeFileNames() {
    return FileHelper.findDirFileNames(this.storePath);
  }

}
