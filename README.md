# Kayura UASP 企业应用开发支撑平台

## 项目介绍

Kayura UASP 是一款在大型应用背景下一步步发展而来的。
比如在某集团下需要的各种应用系统，如：协同办公、合同、施工、安全、工地、监控等等。
这些系统将交付给集团及下属二、三级单位使用。
它们的业务数据相对独立，公司之间却又互有关联（如：上级查阅下级数据、跨公司部门审批）。
另外还有如供应商系统、报价系统供第三方公司使用，它们的数据又由交付的集团内单位管理。
此应用开发支撑平台，是专门为此应用需求而设计的。

内置了后台管理系统，包括：应用管理、租用公司、往来公司、功能角色、组织架构、员工管理、
自动单号、文件管理、流程角色、业务表单、流程设计、流程监控、APP发布管理等等。

## 支持开发的项目类型

- 传统的单项目
- 一级项目、二级项目(由一级项目管理员授权)
- SAAS 项目
- 还可管理移动端的应用安装包

## 支持的部署方式

- 单体
- 集群
- 分布式
- 微服务

## 如何开始？

- [从新项目开始构建](help-doc/new-project.md)
- [从示例工程开始构建](kayura-skeleton)

## 项目源码结构

- kayura-core: 项目核心代码库，定义的公共类型及接口
  - kayura-common:
  - kayura-security:
  - kayura-spring-boot:
  - kayura-swagger-ui:
  - kayura-core-dependencies
- kayura-mybatis:
  - kayura-mybatis-common:
  - kayura-mybatis-core:
  - kayura-mybatis-extension:
  - kayura-mybatis-spring-boot:
  - kayura-mybatis-dependencies:
- kayura-uasp:
  - kayura-uasp-common:
  - kayura-uasp-service:
  - kayura-uasp-webapi:
  - kayura-uasp-spring-boot:
  - kayura-uasp-webui:
  - kayura-uasp-spring-boot:
  - kayura-uasp-dependencies:
- kayura-dependencies:

## 示例工程结构

> 构建一个标准项目所需最小演示代码：  
> **您可以直接使用该工程代码上开发自己的应用项目。**

- kayura-skeleton:
  - kayura-skeleton-common:
  - kayura-skeleton-service:
  - kayura-skeleton-webapi:
  - kayura-skeleton-webstarter:

---

> 演示 UASP 各种接口方法的用法代码：

- kayura-example:
  - kayura-example-common:
  - kayura-example-service:
  - kayura-example-webapi:
  - kayura-example-webstarter:

## 技术支持

- 邮件：xialiang@vip.qq.com
- QQ群：796316725
- 手机：13556090295