/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

package org.kayura.log;

import org.kayura.event.Event;

public class RequestAccess extends Event {

  private String loginUserId;
  private String method;
  private String url;
  private String query;
  private String body;
  private String clientIp;
  private String userAgent;
  private Long startTime;
  private Long endTime;
  private Long duration;
  private Long contentLength;
  private Integer statusCode;

  protected RequestAccess(Object source) {
    super(source);
  }

  public static RequestAccess create(Object source) {
    return new RequestAccess(source);
  }

  public String getLoginUserId() {
    return loginUserId;
  }

  public RequestAccess setLoginUserId(String loginUserId) {
    this.loginUserId = loginUserId;
    return this;
  }

  public String getMethod() {
    return method;
  }

  public RequestAccess setMethod(String method) {
    this.method = method;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public RequestAccess setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getQuery() {
    return query;
  }

  public RequestAccess setQuery(String query) {
    this.query = query;
    return this;
  }

  public String getBody() {
    return body;
  }

  public RequestAccess setBody(String body) {
    this.body = body;
    return this;
  }

  public String getClientIp() {
    return clientIp;
  }

  public RequestAccess setClientIp(String clientIp) {
    this.clientIp = clientIp;
    return this;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public RequestAccess setUserAgent(String userAgent) {
    this.userAgent = userAgent;
    return this;
  }

  public Long getStartTime() {
    return startTime;
  }

  public RequestAccess setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public Long getEndTime() {
    return endTime;
  }

  public RequestAccess setEndTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public Long getDuration() {
    return duration;
  }

  public RequestAccess setDuration(Long duration) {
    this.duration = duration;
    return this;
  }

  public Long getContentLength() {
    return contentLength;
  }

  public RequestAccess setContentLength(Long contentLength) {
    this.contentLength = contentLength;
    return this;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public RequestAccess setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
    return this;
  }
}
