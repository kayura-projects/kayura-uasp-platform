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

package org.kayura.uasp.account;

import org.kayura.type.Properties;

import java.util.List;

public class MenuVo {

  private String header;
  private String code;
  private String label;
  private String icon;
  private Properties style;
  private Boolean disabled;
  private Boolean divider;
  private Boolean active;
  private String styleClass;
  private String iconClass;
  private List<MenuVo> items;
  private String routerLink;
  private String url;
  private String target;
  private String badge;
  private String badgeClass;

  public static MenuVo create() {
    return new MenuVo();
  }

  public String getHeader() {
    return header;
  }

  public MenuVo setHeader(String header) {
    this.header = header;
    return this;
  }

  public String getCode() {
    return code;
  }

  public MenuVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getLabel() {
    return label;
  }

  public MenuVo setLabel(String label) {
    this.label = label;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public MenuVo setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public Properties getStyle() {
    return style;
  }

  public MenuVo setStyle(Properties style) {
    this.style = style;
    return this;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public MenuVo setDisabled(Boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  public Boolean getDivider() {
    return divider;
  }

  public MenuVo setDivider(Boolean divider) {
    this.divider = divider;
    return this;
  }

  public Boolean getActive() {
    return active;
  }

  public MenuVo setActive(Boolean active) {
    this.active = active;
    return this;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public MenuVo setStyleClass(String styleClass) {
    this.styleClass = styleClass;
    return this;
  }

  public String getIconClass() {
    return iconClass;
  }

  public MenuVo setIconClass(String iconClass) {
    this.iconClass = iconClass;
    return this;
  }

  public List<MenuVo> getItems() {
    return items;
  }

  public MenuVo setItems(List<MenuVo> items) {
    this.items = items;
    return this;
  }

  public String getRouterLink() {
    return routerLink;
  }

  public MenuVo setRouterLink(String routerLink) {
    this.routerLink = routerLink;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public MenuVo setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public MenuVo setTarget(String target) {
    this.target = target;
    return this;
  }

  public String getBadge() {
    return badge;
  }

  public MenuVo setBadge(String badge) {
    this.badge = badge;
    return this;
  }

  public String getBadgeClass() {
    return badgeClass;
  }

  public MenuVo setBadgeClass(String badgeClass) {
    this.badgeClass = badgeClass;
    return this;
  }
}
