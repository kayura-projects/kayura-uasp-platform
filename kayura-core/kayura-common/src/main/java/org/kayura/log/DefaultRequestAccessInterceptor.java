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

package org.kayura.log;

import org.jetbrains.annotations.NotNull;
import org.kayura.event.EventGateway;
import org.kayura.utils.HttpUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DefaultRequestAccessInterceptor implements RequestAccessInterceptor {

  public static final String REQUEST_LOGGING_START_TIME = DefaultRequestAccessInterceptor.class.getName() + "_startTime";
  private final EventGateway eventGateway;

  public DefaultRequestAccessInterceptor(EventGateway eventGateway) {
    this.eventGateway = eventGateway;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
    request.setAttribute(REQUEST_LOGGING_START_TIME, System.currentTimeMillis());
    return true;
  }

  @Override
  public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) {

    if (eventGateway.isRegistered(RequestAccess.class)) {

      long startTime = (long) request.getAttribute(REQUEST_LOGGING_START_TIME);
      long endTime = System.currentTimeMillis();
      long duration = endTime - startTime;

      String method = request.getMethod();
      String url = request.getRequestURL().toString();
      String queryString = request.getQueryString();
      String clientIp = HttpUtils.readClientIp(request);
      String userAgent = request.getHeader("User-Agent");
      int status = response.getStatus();
      long contentSize = getContentSize(response);

      eventGateway.publish(RequestAccess.create(request)
        .setMethod(method)
        .setUrl(url)
        .setQuery(queryString)
        .setStartTime(startTime)
        .setEndTime(endTime)
        .setDuration(duration)
        .setClientIp(clientIp)
        .setUserAgent(userAgent)
        .setContentLength(contentSize)
        .setStatusCode(status));
    }
  }

  private static long getContentSize(@NotNull HttpServletResponse response) {
    long contentSize;
    try {
      ResponseSizeCalculatingOutputStream sizeStream = new ResponseSizeCalculatingOutputStream(response.getOutputStream());
      contentSize = sizeStream.getContentLength();
    } catch (IOException e) {
      contentSize = 0;
    }
    return contentSize;
  }

  static class ResponseSizeCalculatingOutputStream extends ServletOutputStream {

    private final ServletOutputStream outputStream;
    private final ByteArrayOutputStream byteArrayOutputStream;

    public ResponseSizeCalculatingOutputStream(ServletOutputStream outputStream) {
      this.outputStream = outputStream;
      this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public void write(int b) throws IOException {
      outputStream.write(b);
      byteArrayOutputStream.write(b);
    }

    @Override
    public void close() throws IOException {
      outputStream.close();
      byteArrayOutputStream.close();
    }

    public long getContentLength() {
      return byteArrayOutputStream.size();
    }

    @Override
    public boolean isReady() {
      return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
    }
  }

}
