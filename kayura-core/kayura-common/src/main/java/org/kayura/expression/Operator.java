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

package org.kayura.expression;

import org.kayura.type.EnumValue;

public enum Operator implements EnumValue {

  Eq("=", "等于"),
  Gt(">", "大于"),
  Lt("<", "小于"),
  GtEq(">=", "大于等于"),
  LtEq("<=", "小于等于"),
  IsNull("IS NULL", "为Null"),
  IsNotNull("<=", "不为Null"),
  In("in", "在列表"),
  NotIn("not in", "不在列表"),
  Like("like", "包含"),
  NotLike("like", "不包含");

  private final String value;
  private final String name;

  Operator(final String value, String name) {
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
