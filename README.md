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

## 它可以做些什么项目？

- 传统的单项目
- 一级项目、二级项目(由一级项目管理员授权)
- SAAS 项目
- 支持的部署方式：单体、集群、分布式、微服务
- 还内置了管理移动端的应用安装包模块

## 与其它同类型开发平台相比，有什么不同？

观察过其它几种开发框架，几乎都只提供直接源码的方式集成到您的业务系统中去。
这样意味着一但集成后，所有的BUG功能升级，均要靠自己来完成。
但本项目还提供了引用jar包的方式提供集成，这样做可以得到以下几点优势：

1. 可以简化集成过程：  
   使用JAR包方式集成，只需要将JAR包引入到项目中，并配置相关依赖，可以快速完成集成过程。相比之下，
   使用源码方式集成需要下载源码、配置开发环境、编译和构建项目等步骤，相对较为繁琐。

2. 提高开发效率：  
   通过使用JAR包集成，可以直接使用平台提供的封装好的功能和组件，无需关注底层实现细节。
   这样可以减少开发人员的工作量和开发周期，提高开发效率。

3. 简化维护和升级：  
   使用JAR包集成，可以将企业应用开发支撑平台作为一个独立的模块来管理。这样可以方便地进行维护和升级，
   当平台提供新的版本或修复bug时，只需要替换或更新升级版本号，而不需要重新编译和构建整个项目。

而且后端开发平台的源码均保持开源，您可以放心的使用。并承若所有BUG都能得到及时的修复。

## 如何开始？

- [在线后台管理示例](https://uasp.kayura.cn) :point_left:
- [从零构建本地项目](help-doc/new-project.md) :point_left:
- [骨架示例工程（kayura-skeleton）](examples/kayura-skeleton) :point_left:推荐

## 项目包结构

| 模块包名                | 描述                                 |
|---------------------|------------------------------------|
| kayura-core         | 项目核心代码库，定义的公共类型及接口。                |
| kayura-mybatis      | 根据系统的需求完全定制化的mybatis-plus,并支持多表关联。 |
| kayura-uasp         | 支撑平台核心业务库,所有功能模块代码。                |
| kayura-dependencies | 预定义的依赖配置。                          |
| examples            | 存放所有示例工程的目录，它们保持完全的独立。             |
| - kayura-skeleton   | 构建一个标准项目所需最小工程代码,包含1个演示模块。         |
| - kayura-example    | 演示 UASP 各种接口方法的用法代码。               |

## 后台管理 - 前端UI

已集成至 [kayura-uasp-webui](kayura-uasp/kayura-uasp-webui) 工程中。  
工程源码由 angular 15 开发，可联系作者获取。

## 引入其它主要开源技术

| 项目名称             | 版本号    | 描述             |
|------------------|--------|----------------|
| spring-framework | 5.3.27 |                |
| spring-boot      | 2.7.13 |                |
| spring-security  | 5.8.3  |                |
| activiti         | 7.9.1  | 高度定制化,简化了设计与集成 |

## 技术支持

- 邮件：xialiang@vip.qq.com
- QQ群：796316725