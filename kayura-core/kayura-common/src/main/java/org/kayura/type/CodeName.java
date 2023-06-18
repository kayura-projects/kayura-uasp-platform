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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CodeName {

  public static List<CodeName> EMPTY = Collections.unmodifiableList(new ArrayList<>());

  private String code;
  private String name;

  public CodeName() {
  }

  public CodeName(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static CodeName create() {
    return new CodeName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CodeName codeName = (CodeName) o;
    return code.equals(codeName.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  public String getCode() {
    return code;
  }

  public CodeName setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public CodeName setName(String name) {
    this.name = name;
    return this;
  }
}
