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
