/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.metadata;

import org.kayura.mybatis.annotation.mapper.IdGenTypes;
import org.kayura.mybatis.annotation.mapper.KeySequence;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.builder.BuilderException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityInfo implements Constants {

  private Class<?> entityType;
  private Map<String, ColumnMap> columnMap;

  private String idColumn;
  private String idAlias;
  private String idProperty;
  private Class<?> idType;
  private IdGenTypes idGenType = IdGenTypes.INPUT;
  private KeySequence keySequence;

  private String schema;
  private String tableName;
  private String tableAlias;
  private String resultMap;

  private boolean logicDelete = false;
  private String deleteColumn;
  private Class<?> deleteType;
  private String deletedValue;

  private MbConfiguration configuration;
  private String currentNamespace;
  private List<Sorting> sorts = new ArrayList<>();
  private List<JoinTable> joinTables = new ArrayList<>();
  private List<ColumnInfo> columns = new ArrayList<>();

  private String allSqlSelect;
  private String sqlSelect;

  public EntityInfo(MbConfiguration configuration, Class<?> entityClazz) {
    this.configuration = configuration;
    this.entityType = entityClazz;
  }

  public String getSqlStatement(String sqlMethod) {
    return currentNamespace + DOT + sqlMethod;
  }

  public boolean deleteTypeIsChar() {
    return String.class.equals(deleteType);
  }

  public void addJoinTable(JoinTable joinTable) {
    if (joinTables.stream().anyMatch(a -> a.getAlias().equals(joinTable.getAlias()))) {
      throw new BuilderException("关联表别名：" + joinTable.getAlias() + " 被重复定义。");
    }
    joinTables.add(joinTable);
  }

  public void addSort(Sorting sorting) {
    sorts.add(sorting);
  }

  public String defaultOrderBy() {

    if (!this.sorts.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      boolean isFirst = true;
      for (Sorting sort : this.sorts) {
        if (!isFirst) {
          sb.append(COMMA).append(SPACE);
        }
        sb.append(sort.getOwner()).append(DOT).append(sort.getColumnName());
        if (sort.isSortDesc()) {
          sb.append(SPACE).append("DESC");
        }
        isFirst = false;
      }
      return sb.toString();
    }
    return null;
  }

  public String selectIdColumn(boolean needAlias) {
    if (StringKit.isNotBlank(this.getIdColumn())) {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getTableAlias()).append(DOT).append(this.getIdColumn());
      if (needAlias && this.getIdColumn().equals(this.getIdAlias())) {
        sb.append(" AS ").append(this.getIdAlias());
      }
      return sb.toString();
    }
    return null;
  }

  public void addColumn(ColumnInfo column) {
    columns.add(column);
  }

  public Map<String, ColumnMap> getColumnMap() {

    if (columnMap == null) {
      this.columnMap = new HashMap<>();

      if (StringKit.isNotBlank(this.idProperty)) {
        ColumnMap idMap = ColumnMap.builder();
        idMap.setSelectName(this.selectIdColumn(true));
        idMap.setFieldName(this.selectIdColumn(false));
        this.columnMap.put(this.getIdProperty(), idMap);
      }

      for (ColumnInfo column : this.getColumns()) {
        ColumnMap colMap = ColumnMap.builder();
        colMap.setSelectName(column.selectColumn(true));
        colMap.setFieldName(column.selectColumn(false));
        this.columnMap.put(column.getPropertyName(), colMap);
      }
    }

    return this.columnMap;
  }

  public List<String> chooseSelect(Predicate<ColumnInfo> predicate) {

    List<String> sqlColumns = new ArrayList<>();

    String keySelect = selectIdColumn(true);
    if (StringKit.isNotBlank(keySelect)) {
      sqlColumns.add(keySelect);
    }

    if (!this.getColumns().isEmpty()) {
      sqlColumns.addAll(this.getColumns().stream()
        .filter(predicate)
        .map(m -> m.selectColumn(true))
        .collect(Collectors.toList()));
    }

    return sqlColumns;
  }

  public Class<?> getEntityType() {
    return entityType;
  }

  public EntityInfo setEntityType(Class<?> entityType) {
    this.entityType = entityType;
    return this;
  }

  public EntityInfo setColumnMap(Map<String, ColumnMap> columnMap) {
    this.columnMap = columnMap;
    return this;
  }

  public String getIdColumn() {
    return idColumn;
  }

  public EntityInfo setIdColumn(String idColumn) {
    this.idColumn = idColumn;
    return this;
  }

  public String getIdAlias() {
    return idAlias;
  }

  public EntityInfo setIdAlias(String idAlias) {
    this.idAlias = idAlias;
    return this;
  }

  public String getIdProperty() {
    return idProperty;
  }

  public EntityInfo setIdProperty(String idProperty) {
    this.idProperty = idProperty;
    return this;
  }

  public Class<?> getIdType() {
    return idType;
  }

  public EntityInfo setIdType(Class<?> idType) {
    this.idType = idType;
    return this;
  }

  public IdGenTypes getIdGenType() {
    return idGenType;
  }

  public EntityInfo setIdGenType(IdGenTypes idGenType) {
    this.idGenType = idGenType;
    return this;
  }

  public KeySequence getKeySequence() {
    return keySequence;
  }

  public EntityInfo setKeySequence(KeySequence keySequence) {
    this.keySequence = keySequence;
    return this;
  }

  public String getSchema() {
    return schema;
  }

  public EntityInfo setSchema(String schema) {
    this.schema = schema;
    return this;
  }

  public String getTableName() {
    return tableName;
  }

  public EntityInfo setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  public EntityInfo setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias;
    return this;
  }

  public String getResultMap() {
    return resultMap;
  }

  public EntityInfo setResultMap(String resultMap) {
    this.resultMap = resultMap;
    return this;
  }

  public boolean isLogicDelete() {
    return logicDelete;
  }

  public EntityInfo setLogicDelete(boolean logicDelete) {
    this.logicDelete = logicDelete;
    return this;
  }

  public String getDeleteColumn() {
    return deleteColumn;
  }

  public EntityInfo setDeleteColumn(String deleteColumn) {
    this.deleteColumn = deleteColumn;
    return this;
  }

  public Class<?> getDeleteType() {
    return deleteType;
  }

  public EntityInfo setDeleteType(Class<?> deleteType) {
    this.deleteType = deleteType;
    return this;
  }

  public String getDeletedValue() {
    return deletedValue;
  }

  public EntityInfo setDeletedValue(String deletedValue) {
    this.deletedValue = deletedValue;
    return this;
  }

  public MbConfiguration getConfiguration() {
    return configuration;
  }

  public EntityInfo setConfiguration(MbConfiguration configuration) {
    this.configuration = configuration;
    return this;
  }

  public String getCurrentNamespace() {
    return currentNamespace;
  }

  public EntityInfo setCurrentNamespace(String currentNamespace) {
    this.currentNamespace = currentNamespace;
    return this;
  }

  public List<Sorting> getSorts() {
    return sorts;
  }

  public EntityInfo setSorts(List<Sorting> sorts) {
    this.sorts = sorts;
    return this;
  }

  public List<JoinTable> getJoinTables() {
    return joinTables;
  }

  public EntityInfo setJoinTables(List<JoinTable> joinTables) {
    this.joinTables = joinTables;
    return this;
  }

  public List<ColumnInfo> getColumns() {
    return columns;
  }

  public EntityInfo setColumns(List<ColumnInfo> columns) {
    this.columns = columns;
    return this;
  }

  public String getAllSqlSelect() {
    return allSqlSelect;
  }

  public EntityInfo setAllSqlSelect(String allSqlSelect) {
    this.allSqlSelect = allSqlSelect;
    return this;
  }

  public String getSqlSelect() {
    return sqlSelect;
  }

  public EntityInfo setSqlSelect(String sqlSelect) {
    this.sqlSelect = sqlSelect;
    return this;
  }
}
