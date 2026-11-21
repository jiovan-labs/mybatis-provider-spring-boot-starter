[English](README.md) | [简体中文](README.zh-CN.md) | [繁體中文](README.zh-TW.md)

# Mybatis Provider Spring Boot Starter

让 Spring Boot 应用快速集成 `mybatis`，支持针对不同数据库使用不同的语句。

## 快速开始

- 导入依赖

```xml
    <dependency>
        <groupId>com.jiovan.spring.starter</groupId>
        <artifactId>mybatis-provider-spring-boot-starter</artifactId>
        <version>LATEST</version>
    </dependency>
```

> 默认情况下，此 starter 会自动生效，你可以通过 `jiovan.mybatis-provider.enabled = false` 关闭它。

- 使用前缀 `jiovan.mybatis-provider` 配置 Spring Boot 的 `application.yml`（**可选**）

```yml
jiovan:
    mybatis-provider:
        config-file: 'classpath:/META-INF/mybatis/database-id-provider.xml'
```

这将创建一个 `DatabaseIdProvider` Bean，支持全球主流的关系型数据库。

- 按如下方式编写 Mybatis mapper 语句：

```xml
<select id="foo" resultType="bar" databaseId="mysql">
</select>
```

> 注意其中的 `databaseId` 部分，它是上一步 `configFile` 节点中配置的数据库标识符。

## 文档

- GitHub：https://github.com/jiovan-labs/mybatis-provider-spring-boot-starter
- MyBatis 主页：https://mybatis.org/mybatis-3
- MyBatis GitHub：https://github.com/mybatis/mybatis-3
- 关系型数据库：https://db-engines.com/en/ranking/relational+dbms

## 环境要求

- jdk 17+

## 许可证

本项目采用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。

请参阅 `NOTICE.txt` 文件了解所需的声明与归属信息。

## 捐赠

喜欢这个工具包吗？欢迎[向我们捐赠](https://jiovan.com/donation)以支持开发。

## 版权

北京玖漫科技有限公司

## 网站

- 玖漫：https://jiovan.com
