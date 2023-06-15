/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.annotation.mapper.IdGenTypes;
import org.kayura.mybatis.annotation.mapper.SelectModes;
import org.kayura.mybatis.mapper.metadata.ColumnInfo;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.mapper.metadata.JoinTable;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.builder.BuilderException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlFragmentBuilder extends SqlTagTool {

  private final EntityInfo info;

  public SqlFragmentBuilder(EntityInfo info) {
    this.info = info;
  }

  public EntityInfo getEntityTable() {
    return info;
  }

  /**
   * 获取 Select 主键列的 SQL 脚本。
   */
  public String selectIdColumn() {
    if (StringKit.isNotBlank(info.getIdColumn())) {
      StringBuilder sb = new StringBuilder();
      sb.append(info.getTableAlias()).append(DOT).append(info.getIdColumn());
      if (info.getIdColumn().equals(info.getIdAlias())) {
        sb.append(" AS ").append(info.getIdAlias());
      }
      return sb.toString();
    }
    return null;
  }

  /**
   * 获取 Insert 时的主键列 SQL 脚本。
   */
  public String insertIdColumn() {

    if (StringKit.isNotBlank(info.getIdColumn()) && !IdGenTypes.AUTO_INC.equals(info.getIdGenType())) {
      return info.getIdColumn();
    }
    return EMPTY;
  }

  public String insertIdProperty() {

    if (StringKit.isNotBlank(info.getIdProperty())) {
      if (!IdGenTypes.AUTO_INC.equals(info.getIdGenType())) {
        return safeParam(info.getIdProperty());
      }
    }
    return EMPTY;
  }

  /**
   * 获取 Select 时，输出的列 SQL 脚本。
   */
  public String allSelectColumns(SelectModes selectMode, boolean isChoose) {

    List<String> sqlColumns = new ArrayList<>();

    String keySelect = selectIdColumn();
    if (StringKit.isNotBlank(keySelect)) {
      sqlColumns.add(keySelect);
    }

    if (!info.getColumns().isEmpty()) {
      sqlColumns.addAll(info.getColumns().stream()
        .filter(a -> SelectModes.ALL.equals(a.getSelect()) || selectMode.equals(a.getSelect()))
        .map(m -> m.selectColumn(true))
        .toList());
    }

    if (isChoose) {
      return chooseTag(
        StringKit.format(FMT_NOT_NULL_TWO, WRAPPER, QUERIER_COLUMNS),
        unSafeParam(QUERIER_COLUMNS),
        StringKit.join(sqlColumns, COMMA));
    } else {
      return StringKit.join(sqlColumns, COMMA);
    }
  }

  /**
   * 生成所有可 Insert 的表字段集。
   */
  public String allInsertColumns() {

    // 生成示例
    // id_,
    // parent_id_,
    // code_,
    // name_,
    // user_id_,
    // price_,
    // amount_,
    // create_at_,
    // sort_,
    // removed_

    List<String> insertColumns = new ArrayList<>();
    String idColumn = insertIdColumn();
    if (StringKit.isNotBlank(idColumn)) {
      insertColumns.add(idColumn);
    }

    if (!info.getColumns().isEmpty()) {
      insertColumns.addAll(info.getColumns().stream()
        .filter(ColumnInfo::isInsert)
        .map(ColumnInfo::getColumnName)
        .collect(Collectors.toList()));
    }

    return StringKit.join(insertColumns, COMMA);
  }

  /**
   * 生成所有可 Insert 实体属性集。
   */
  public Object allInsertProperties() {

    // #{id},
    // #{parentId},
    // #{code},
    // #{name},
    // #{userId},
    // #{price},
    // #{amount},
    // #{createAt},
    // #{sort},
    // #{removed}

    List<String> insertProperties = new ArrayList<>();
    String idProperty = insertIdProperty();
    if (StringKit.isNotBlank(idProperty)) {
      insertProperties.add(idProperty);
    }

    if (!info.getColumns().isEmpty()) {
      insertProperties.addAll(info.getColumns().stream()
        .filter(ColumnInfo::isInsert)
        .map(m -> safeParam(m.getPropertyName()))
        .collect(Collectors.toList()));
    }

    return StringKit.join(insertProperties, COMMA);
  }

  public String allSetColumns(boolean patch, String entityAlias) {

    // if patch == false
    // parent_id_ = #{entity.parentId},
    // ...

    // if patch == true
    // <if test="parentId != null">parent_id_ = #{entity.parentId},</if>
    // ...

    StringBuilder sb = new StringBuilder();
    info.getColumns().forEach(e -> {
      if (e.isUpdate()) {
        sb.append(e.updateSet(patch, entityAlias));
      }
    });

    return sb.toString();
  }

  public String formTable() {
    return allFromTable(false);
  }

  public String allFromTable() {
    return allFromTable(true);
  }

  public String allFromTable(boolean needJoinTable) {

    // 生成示例
    // simple_table mt
    // LEFT JOIN simple_table stp ON stp.id_ = mt.parent_id_
    // LEFT JOIN simple_user su ON su.user_id_ = mt.user_id_

    StringBuilder sb = new StringBuilder();
    sb.append(info.getTableName()).append(SPACE).append(info.getTableAlias());

    if (needJoinTable) {
      List<JoinTable> joinTables = info.getJoinTables();
      for (JoinTable e : joinTables) {
        sb.append(SPACE);
        sb.append(e.getJoinType().name()).append(" JOIN ");
        sb.append(e.getTable()).append(SPACE).append(e.getAlias());
        sb.append(" ON ");
        sb.append(LEFT_BRACKET);
        sb.append(e.getAlias()).append(DOT).append(e.getPkName());
        sb.append(" = ");
        sb.append(e.getFrom()).append(DOT).append(e.getFkName());
        sb.append(RIGHT_BRACKET);
        if (StringKit.hasText(e.getCondition())) {
          sb.append(" AND ");
          sb.append(LEFT_BRACKET);
          sb.append(e.getCondition());
          sb.append(RIGHT_BRACKET);
        }
      }
    }

    return sb.toString();
  }

  public String logicDeleteSet(boolean needOwner) {

    // removed_ = 1
    // removed_ = 'D'

    StringBuilder sb = new StringBuilder();
    if (needOwner) {
      sb.append(info.getTableAlias()).append(DOT);
    }
    sb.append(info.getDeleteColumn()).append(SPACE).append(EQUALS).append(SPACE);
    if (info.deleteTypeIsChar()) {
      sb.append(SINGLE_QUOTE);
    }
    sb.append(info.getDeletedValue());
    if (info.deleteTypeIsChar()) {
      sb.append(SINGLE_QUOTE);
    }
    return sb.toString();
  }

  private String logicDeleteWhere() {

    // ( mt.removed_ IS NULL OR mt.removed_ <> 1 )

    if (info.isLogicDelete()) {
      if (StringKit.isBlank(info.getDeleteColumn())) {
        throw new BuilderException("标记的逻辑删除实体，但未定义删除逻辑字段。");
      }

      boolean isChar = info.deleteTypeIsChar();

      StringBuilder sb = new StringBuilder();
      sb.append(LEFT_BRACKET).append(SPACE);
      sb.append(info.getTableAlias()).append(DOT).append(info.getDeleteColumn()).append(SPACE).append("IS NULL");
      sb.append(" OR ");
      sb.append(info.getTableAlias()).append(DOT).append(info.getDeleteColumn()).append(SPACE).append(HTML_LT).append(HTML_GT);
      sb.append(SPACE);
      if (isChar) {
        sb.append(SINGLE_QUOTE);
      }
      sb.append(info.getDeletedValue());
      if (isChar) {
        sb.append(SINGLE_QUOTE);
      }
      sb.append(SPACE).append(RIGHT_BRACKET);
      return sb.toString();
    }

    return EMPTY;
  }

  public String orderByWhere() {

    // <choose>
    //   <when test="ew != null and ew.sqlOrderBy != null">
    //     ORDER BY ${ew.sqlOrderBy}
    //   </when>
    //   <otherwise>
    //     ORDER BY mt.code_, mt.sort_
    //   </otherwise>
    // </choose>

    StringBuilder sb = new StringBuilder();
    sb.append("<choose>");
    sb.append("<when test=\"ew.sqlOrderBy != null\">");
    sb.append("ORDER BY ${ew.sqlOrderBy}");
    sb.append("</when>");
    sb.append("<otherwise>");
    if (!info.getSorts().isEmpty()) {
      sb.append("ORDER BY ").append(info.defaultOrderBy());
    }
    sb.append("</otherwise>");
    sb.append("</choose>");
    return sb.toString();
  }

  public String allQueryWrapper(boolean needLogicDelete, boolean needGroupBy, boolean needOrderBy) {

    StringBuilder sb = new StringBuilder();
    sb.append("<where>");
    // logicDelete
    if (needLogicDelete) {
      sb.append(SPACE).append(logicDeleteWhere()).append(SPACE);
    }
    // custom where
    sb.append("<if test=\"ew.sqlWhere != null\">");
    sb.append(" AND ( ${ew.sqlWhere} ) ");
    sb.append("</if>");
    sb.append("</where>");
    if (needGroupBy) {
      sb.append("<if test=\"ew.sqlGroupBy != null\">");
      sb.append("GROUP BY ${ew.sqlGroupBy}");
      sb.append("<if test=\"ew.sqlHaving != null\">");
      sb.append("HAVING ${ew.sqlHaving}");
      sb.append("</if>");
      sb.append("</if>");
    }
    if (needOrderBy) {
      sb.append("<if test=\"ew.sqlGroupBy == null\">");
      sb.append(orderByWhere());
      sb.append("</if>");
    }
    return sb.toString();
  }

}
