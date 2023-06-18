/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
