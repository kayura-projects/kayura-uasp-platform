package org.kayura.uasp.file;

public class UploadResult extends FileLinkVo {

  private boolean isNew;

  public static UploadResult create() {
    return new UploadResult();
  }

  public boolean isNew() {
    return isNew;
  }

  public UploadResult setNew(boolean aNew) {
    isNew = aNew;
    return this;
  }

}
