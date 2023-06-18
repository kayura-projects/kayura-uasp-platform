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

package org.kayura.type;

import org.kayura.utils.StringUtils;

import java.util.*;

/**
 * 字符串列表。
 */
public class StringList extends ArrayList<String> {

  public static final StringList EMPTY = new StringList();

  public StringList() {
  }

  public StringList(Collection<String> list) {
    super(list);
  }

  public StringList(String... list) {
    super(Arrays.asList(list));
  }

  public static StringList of(String value) {
    return new StringList(StringUtils.split(value, ","));
  }

  public static StringList of(List<String> list) {
    return new StringList(list);
  }

  public static StringList singletonList(String value) {
    return new StringList(Collections.singletonList(value));
  }
}
