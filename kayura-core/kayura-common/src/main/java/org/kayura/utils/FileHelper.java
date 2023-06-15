package org.kayura.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  public static List<String> findDirFileNames(String path) {

    List<String> fileNames = new ArrayList<>();
    findSubDirFileNames(fileNames, new File(path));
    return fileNames;
  }

  private static void findSubDirFileNames(List<String> fileNames, File path) {

    if (path.exists()) {
      File[] files = path.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            fileNames.add(file.getName());
          } else if (file.isDirectory()) {
            findSubDirFileNames(fileNames, file);
          }
        }
      }
    }
  }

}
