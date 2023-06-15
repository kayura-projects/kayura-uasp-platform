package org.kayura.uasp.file;

import org.kayura.type.EnumValue;

/** 文件归类：IMAGE,VIDEO,DOC,ZIP,OTHER */
public enum FileClassify implements EnumValue {

  IMAGE("IMAGE", "图片"),
  VIDEO("VIDEO", "视频"),
  DOC("DOC", "文档"),
  PDF("PDF", "PDF"),
  ZIP("ZIP", "压缩包"),
  OTHER("OTHER", "其它");

  private final String value;
  private final String name;

  FileClassify(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public String getName() {
    return name;
  }
}
