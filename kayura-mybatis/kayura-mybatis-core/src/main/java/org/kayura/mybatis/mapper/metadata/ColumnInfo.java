/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.metadata;

import org.kayura.mybatis.annotation.mapper.Features;
import org.kayura.mybatis.annotation.mapper.SelectModes;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class ColumnInfo implements Constants {

  private Features feature = Features.BASE;
  private String columnName;
  private String columnAlias;
  private String propertyName;
  private String owner;
  private String displayName;
  private String hint;
  private String el;
  private boolean virtual = false;
  private boolean unique = false;
  private boolean nullable = true;
  private SelectModes select = SelectModes.ALL;
  private boolean insert = true;
  private boolean update = true;
  private int min = 0;
  private int max = 0;
  private boolean zero = true;
  private Class<?> javaType;
  private JdbcType jdbcType;
  private Class<? extends TypeHandler<?>> typeHandler;

  public SelectModes getSelect() {
    return virtual ? SelectModes.NONE : this.select;
  }

  public boolean isInsert() {
    return !virtual && this.insert;
  }

  public boolean isUpdate() {
    return !virtual && this.update;
  }

  public String selectColumn(boolean needAlias) {
    StringBuilder sb = new StringBuilder();
    sb.append(owner).append(DOT).append(columnName);
    if (needAlias && !columnName.equals(columnAlias)) {
      sb.append(" AS ").append(columnAlias);
    }
    return sb.toString();
  }

  public String updateSet(boolean patch, String entityAlias) {
    StringBuilder sb = new StringBuilder();
    if (patch) {
      sb.append("<if test=\"").append(propertyName).append("\">");
    }
    sb.append(columnName).append(EQUALS).append(HASH_LEFT_BRACE);
    if (StringKit.isNotBlank(entityAlias)) {
      sb.append(entityAlias).append(DOT);
    }
    sb.append(propertyName).append(RIGHT_BRACE).append(COMMA);
    if (patch) {
      sb.append("</if>");
    }
    return sb.toString();
  }

  public Features getFeature() {
    return feature;
  }

  public ColumnInfo setFeature(Features feature) {
    this.feature = feature;
    return this;
  }

  public String getColumnName() {
    return columnName;
  }

  public ColumnInfo setColumnName(String columnName) {
    this.columnName = columnName;
    return this;
  }

  public String getColumnAlias() {
    return columnAlias;
  }

  public ColumnInfo setColumnAlias(String columnAlias) {
    this.columnAlias = columnAlias;
    return this;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public ColumnInfo setPropertyName(String propertyName) {
    this.propertyName = propertyName;
    return this;
  }

  public String getOwner() {
    return owner;
  }

  public ColumnInfo setOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ColumnInfo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getHint() {
    return hint;
  }

  public ColumnInfo setHint(String hint) {
    this.hint = hint;
    return this;
  }

  public String getEl() {
    return el;
  }

  public ColumnInfo setEl(String el) {
    this.el = el;
    return this;
  }

  public boolean isVirtual() {
    return virtual;
  }

  public ColumnInfo setVirtual(boolean virtual) {
    this.virtual = virtual;
    return this;
  }

  public boolean isUnique() {
    return unique;
  }

  public ColumnInfo setUnique(boolean unique) {
    this.unique = unique;
    return this;
  }

  public boolean isNullable() {
    return nullable;
  }

  public ColumnInfo setNullable(boolean nullable) {
    this.nullable = nullable;
    return this;
  }

  public ColumnInfo setSelect(SelectModes select) {
    this.select = select;
    return this;
  }

  public ColumnInfo setInsert(boolean insert) {
    this.insert = insert;
    return this;
  }

  public ColumnInfo setUpdate(boolean update) {
    this.update = update;
    return this;
  }

  public int getMin() {
    return min;
  }

  public ColumnInfo setMin(int min) {
    this.min = min;
    return this;
  }

  public int getMax() {
    return max;
  }

  public ColumnInfo setMax(int max) {
    this.max = max;
    return this;
  }

  public boolean isZero() {
    return zero;
  }

  public ColumnInfo setZero(boolean zero) {
    this.zero = zero;
    return this;
  }

  public Class<?> getJavaType() {
    return javaType;
  }

  public ColumnInfo setJavaType(Class<?> javaType) {
    this.javaType = javaType;
    return this;
  }

  public JdbcType getJdbcType() {
    return jdbcType;
  }

  public ColumnInfo setJdbcType(JdbcType jdbcType) {
    this.jdbcType = jdbcType;
    return this;
  }

  public Class<? extends TypeHandler<?>> getTypeHandler() {
    return typeHandler;
  }

  public ColumnInfo setTypeHandler(Class<? extends TypeHandler<?>> typeHandler) {
    this.typeHandler = typeHandler;
    return this;
  }
}
