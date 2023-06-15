# Kayura UASP 企业应用开发支撑平台

## 项目介绍

Kayura UASP 是一款在国企大型应用背景下一步步发展而来的。
比如在某集团下需要的各种应用系统，如：协同办公、合同、施工、安全、工地、监控等等。
这些系统将交付给集团及下属二、三级单位使用。
它们的业务数据相对独立，公司之间却又互有关联（如：上级查阅下级数据、跨公司部门审批）。
另外还有如供应商系统、报价系统供第三方公司使用，它们的数据又由交付的集团内单位管理。
此应用开发支撑平台，是专门为此应用需求而设计的。

内置了后台管理系统，包括：应用管理、租用公司、往来公司、功能角色、组织架构、员工管理、
自动单号、文件管理、流程角色、业务表单、流程设计、流程监控、APP发布管理等等。

## 项目适应性

本项目对（单体 > 集群 > 分布式 > 微服务）均有很好的适应性。符合二级安全等保要求。

## 快速开始

只需要以下几个步骤，您就得到一个基于 UASP 的开发项目。  
您也可以直接下载搭建好的骨架工程，进行编译与运行。  
[点击查看 kayura-skeleton，已包含一个简单模块示例](https://gitee.com/kayura-projects/kayura-uasp-showcast/tree/master/kayura-skeleton)

### 第1步：创建一个空的数据库

需要 MySQL8 数据库  
密码为：123456 （与application.yml中一致即可）
创建一个名为 xy_uasp_dev 的空白数据库。  
如果希望使用网络中的 MySQL 数据库，请修改 application.yml 配置项。

```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost/xy_uasp_dev?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true
      username: root
      password: 123456
```

### 第2步：项目默认使用了 Redis 做为分布式缓存

默认使用本地Redis服务，请选安装它。[Windows 安装参考](https://redis.com.cn/redis-installation.html)  
如果您要使用网络中的Redis服务，请修改 application.yml 配置项。

```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

### 第3步：创建一个新的spring-boot工程，并引用 UASP 包

```xml

<dependency>
  <groupId>org.kayura</groupId>
  <artifactId>kayura-dependencies</artifactId>
  <version>${kayura.version}</version>
  <type>pom</type>
  <scope>import</scope>
</dependency>
``` 

### 第4步：请参以下代码来修改您工程中的代码：

```java

@SpringBootApplication
public class KayuraUaspWebStarter implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(KayuraUaspWebStarter.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/uasp");
  }
}
``` 

在 resources 目录下创建 [application.yml](https://gitee.com/kayura-projects/kayura-uasp-platform/tree/master/kayura-uasp/kayura-uasp-webstarter/src/main/resources) 文件，
请点击链接查看，并复制到您的项目中。

### 第5步：现在您可以编译和运行项目了。

* 默认账号：root
* 默认密码：root
* WebApi文档说明：/swagger-ui/index.html

## 技术支持
- 邮件：xialiang@vip.qq.com
- QQ群：796316725
- 手机：13556090295