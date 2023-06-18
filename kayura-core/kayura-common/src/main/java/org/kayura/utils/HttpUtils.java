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

package org.kayura.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public abstract class HttpUtils {

  private static final Log LOG = LogFactory.getLog(HttpUtils.class);

  public static void writeRequestLog(HttpServletRequest request, String body) {

    LOG.info("==============================================");
    LOG.info("> RequestURL: " + request.getRequestURL());
    LOG.info("> Method: " + request.getMethod());
    LOG.info("> QueryString: " + request.getQueryString());
    LOG.info("> ServletPath: " + request.getServletPath());
    LOG.info("> RemoteHost: " + request.getRemoteHost());
    LOG.info("> RemotePort: " + request.getRemotePort());
    LOG.info("> Headers: ");
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      LOG.info(">   " + headerName + "= " + request.getHeader(headerName));
    }
    LOG.info("> Parameters: ");
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      String paramName = params.nextElement();
      LOG.info(">   " + paramName + "= " + request.getParameter(paramName));
    }
    LOG.info("> Body: " + body);
    LOG.info("==============================================");
  }

  public static String encoderFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {

    fileName = fileName.replace(" ", "_");
    String agent = request.getHeader("USER-AGENT");
    if (agent.contains("Trident")) {
      fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
    } else if (agent.contains("Mozilla")) {
      fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
    }
    return fileName;
  }

  public static void outText(HttpServletResponse response, String text) {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    try {
      OutputStream os = response.getOutputStream();
      os.write(text.getBytes(StandardCharsets.UTF_8));
      os.close();
    } catch (Exception ignored) {
    }
  }

  public static StringBuilder buildRequestUrl(HttpServletRequest request) {

    StringBuilder sb = new StringBuilder();
    int serverPort = request.getServerPort();
    if (serverPort == 443) {
      sb.append("https");
    } else {
      sb.append(request.getScheme());
    }
    sb.append("://").append(request.getServerName());
    if (serverPort != 80 && serverPort != 443) {
      sb.append(":").append(serverPort);
    }
    sb.append(request.getContextPath());
    return sb;
  }

  public static String readClientIp(HttpServletRequest request) {

    String ipAddress = request.getHeader("X-Forwarded-For");
    if (!isEmptyAddress(ipAddress)) {
      if (ipAddress.contains(",")) {
        ipAddress = ipAddress.split(",")[0];
      }
    }
    if (isEmptyAddress(ipAddress)) {
      ipAddress = request.getHeader("X-Real-IP");
    }
    if (isEmptyAddress(ipAddress)) {
      ipAddress = request.getRemoteAddr();
    }
    return ipAddress;
  }

  private static boolean isEmptyAddress(String ipAddress) {
    return ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress);
  }
}
