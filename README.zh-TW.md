[English](README.md) | [簡體中文](README.zh-CN.md) | [繁體中文](README.zh-TW.md)

# Mybatis Provider Spring Boot Starter

讓 Spring Boot 應用快速整合 `mybatis`，支援針對不同資料庫使用不同的語句。

## 快速開始

- 匯入相依性

```xml
    <dependency>
        <groupId>com.jiovan.spring.starter</groupId>
        <artifactId>mybatis-provider-spring-boot-starter</artifactId>
        <version>LATEST</version>
    </dependency>
```

> 預設情況下，此 starter 會自動生效，你可以透過 `jiovan.mybatis-provider.enabled = false` 關閉它。

- 使用字首 `jiovan.mybatis-provider` 設定 Spring Boot 的 `application.yml`（**可選**）

```yml
jiovan:
    mybatis-provider:
        config-file: 'classpath:/META-INF/mybatis/database-id-provider.xml'
```

這將建立一個 `DatabaseIdProvider` Bean，支援全球主流的關係型資料庫。

- 按如下方式編寫 Mybatis mapper 語句：

```xml
<select id="foo" resultType="bar" databaseId="mysql">
</select>
```

> 注意其中的 `databaseId` 部分，它是上一步 `configFile` 節點中設定的資料庫識別符號。

## 文件

- GitHub：https://github.com/jiovan-labs/mybatis-provider-spring-boot-starter
- MyBatis 主頁：https://mybatis.org/mybatis-3
- MyBatis GitHub：https://github.com/mybatis/mybatis-3
- 關係型資料庫：https://db-engines.com/en/ranking/relational+dbms

## 環境要求

- jdk 17+

## 許可證

本專案採用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 許可證。

請參閱 `NOTICE.txt` 檔案瞭解所需的宣告與歸屬資訊。

## 捐贈

喜歡這個工具包嗎？歡迎[向我們捐贈](https://jiovan.com/donation)以支援開發。

## 版權

北京玖漫科技有限公司

## 網站

- 玖漫：https://jiovan.com
