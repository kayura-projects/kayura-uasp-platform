## 从新开始构建工程

只需要以下几个步骤，您就得到一个基于 UASP 的开发项目。

### 第1步：创建一个空的数据库

需要 MySQL8 数据库  
密码为：123456 （与application.yml中一致即可）  
创建一个名为 xy_uasp_dev 的空白数据库，程序首次运行时自动创建所需的表。  
如果希望使用网络中的其它 MySQL 数据库，请修改 application.yml 配置项。

```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost/xy_uasp_dev?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true
      username: root
      password: 123456
```

### 第2步：项目默认使用了 Redis 做为分布式缓存

默认使用本地Redis服务，若没有请先安装它。[Windows 安装参考](https://redis.com.cn/redis-installation.html)  
如果您要使用网络中的其它Redis服务，请修改 application.yml 配置项。

```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

### 第3步：创建一个新的spring-boot工程，并引用 UASP 包

```xml
<dependencyManagement>
  <dependency>
    <groupId>org.kayura</groupId>
    <artifactId>kayura-dependencies</artifactId>
    <version>${kayura.version}</version>
    <type>pom</type>
    <scope>import</scope>
  </dependency>
</dependencyManagement>
``` 

### 第4步：添加或修改 application.yml 配置文件

> 配置文件内容，请参考 [application.yml](../kayura-skeleton/kayura-skeleton-webstarter/src/main/resources) 文件，
> 请点击链接查看，并复制到您的项目中。

### 第4步：请参以下代码来修改您工程中的代码：

**这一步不是必需的**，您可以在程运行后输入地址 /uasp 来访问后台应用界面。  
也可以参考修改如下启动代码，自动跳转至 /uasp 地址。

```java
@SpringBootApplication
public class ExampleWebApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(ExampleWebApplication.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // 添加一个跳转，当访问 / 时，将返回 301 跳转至 /uasp 路径。
    registry.addRedirectViewController("/", "/uasp");
  }
}
``` 

### 第5步：现在您可以编译和运行项目了。

* 默认账号：root
* 默认密码：root

## 技术支持

- 邮件：xialiang@vip.qq.com
- QQ群：796316725