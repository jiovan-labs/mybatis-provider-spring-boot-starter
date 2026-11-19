# Mybatis Provider Spring Boot Starter

Spring Boot application integrates `mybatis` quickly, to support different statements of different databases.

## Quickstart

- Import dependencies

```xml
    <dependency>
        <groupId>com.jiovan.spring.starter</groupId>
        <artifactId>mybatis-provider-spring-boot-starter</artifactId>
        <version>LATEST</version>
    </dependency>
```

> By default, this starter will auto take effect, you can turn it off by `jiovan.mybatis-provider.enabled = false`

- Configure Spring Boot `application.yml` with prefix `jiovan.mybatis-provider` (**Optional**)

```yml
jiovan:
    mybatis-provider:
        config-file: 'classpath:/META-INF/mybatis/database-id-provider.xml'
```

This will create a `DatabaseIdProvider` bean, which supports most popular relational databases in the world.

- Write your mybatis mapper statements as following:

```xml
<select id="foo" resultType="bar" databaseId="mysql">
</select>
```

> Note that the `databaseId` segment, that is the database identifier from your `configFile` node of the previous step.

## Document

- Github: https://github.com/jiovan-labs/mybatis-provider-spring-boot-starter
- Mybatis homepage: https://mybatis.org/mybatis-3
- Mybatis github: https://github.com/mybatis/mybatis-3
- Relational databases: https://db-engines.com/en/ranking/relational+dbms

## Requirement

- jdk 17+

## License

This project is under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

See the `NOTICE.txt` file for required notices and attributions.

## Donation

You like this package? Then [donate to us](https://jiovan.com/donation) to support the development.

## Copyright

Beijing Jiovan Technology Ltd.

## Website

- Jiovan: https://jiovan.com
