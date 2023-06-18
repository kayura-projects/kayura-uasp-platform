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
