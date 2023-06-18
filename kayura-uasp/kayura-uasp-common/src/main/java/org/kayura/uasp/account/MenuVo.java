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
