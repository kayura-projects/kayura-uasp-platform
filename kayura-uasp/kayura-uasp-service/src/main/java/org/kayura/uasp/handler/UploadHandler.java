package org.kayura.uasp.handler;

import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.StoreTypes;

import java.io.File;
import java.util.List;

public interface UploadHandler {

  StoreTypes getStoreType();

  String getLogicPath();

  String getTempPath();

  void storeFile(StoreFileArgs args) throws Exception;

  File readMultiFile(List<DownloadFile> downloads) throws Exception;

  File readSingleFile(DownloadFile download) throws Exception;

  List<String> storeFileNames();
}
