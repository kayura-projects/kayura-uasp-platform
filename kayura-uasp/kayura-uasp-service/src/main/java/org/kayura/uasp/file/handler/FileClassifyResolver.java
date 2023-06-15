package org.kayura.uasp.file.handler;

import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.UploadFile;

@FunctionalInterface
public interface FileClassifyResolver {
  FileClassify resolve(UploadFile uploadFile);
}
