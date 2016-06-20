---
currentMenu: index
---

[![Build Status](https://travis-ci.org/pnowy/NativeCriteria.svg?branch=develop)](https://travis-ci.org/pnowy/NativeCriteria)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pnowy.nc/nativeCriteria-core)

### Main advantages / assumptions:
 * simple, friendly API
 * without any other generated classed based on database schema (no more fights with always problematics metamodels)
 * possibility to build complex joins with detailed projections
 * work on all databases supported by Hibernate
 * designed for a select queries (not supported update queries now)
 * separate module for spring integration (Spring Pageable abstraction support)
 * set of mappers for simple result transformations
 * support custom SQL query parts
 * separate test module with examples build on top of JPA & Spring Boot

### Library available on Maven Central Repository
```xml
<!-- core module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-core</artifactId>
    <version>2.0</version>
</dependency>

<!-- spring integration module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-spring</artifactId>
    <version>2.0</version>
</dependency>
```

### [See basic usage examples!](basic_usage.md)

### [See working examples with Spring Boot on separate test module](https://github.com/pnowy/NativeCriteria/tree/develop/nativeCriteria-test)
