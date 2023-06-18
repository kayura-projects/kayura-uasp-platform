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

package org.kayura.mybatis.session;

import org.kayura.mybatis.builder.xml.MbXMLConfigBuilder;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class MbSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {

  @Override
  public MbSqlSessionFactory build(Reader reader, String environment, Properties properties) {
    try {
      MbXMLConfigBuilder parser = new MbXMLConfigBuilder(reader, environment, properties);
      return build(parser.parse());
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        reader.close();
      } catch (IOException e) {
        // Intentionally ignore. Prefer previous error.
      }
    }
  }

  @Override
  public MbSqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
    try {
      MbXMLConfigBuilder parser = new MbXMLConfigBuilder(inputStream, environment, properties);
      return build(parser.parse());
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        inputStream.close();
      } catch (IOException e) {
        // Intentionally ignore. Prefer previous error.
      }
    }
  }

  @Override
  public MbSqlSessionFactory build(Configuration config) {

    MbConfiguration configuration = (MbConfiguration) config;
    MbSqlSessionFactory sqlSessionFactory = new MbSqlSessionFactory(config);
    configuration.signGlobalConfig(sqlSessionFactory);
    return sqlSessionFactory;
  }

}
