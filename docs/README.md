# Native Criteria

#### Ultra lightweight library to generate dynamic SQL based on hibernate session

---

#### Main advantages / assumptions

* simple, friendly API
* without any other generated classed based on database schema \(no more fights with always problematics metamodels\)
* possibility to build complex joins with detailed projections
* work on all databases supported by Hibernate
* designed for a select queries \(not supported update queries now\)
* separate module for spring integration \(Spring Pageable abstraction support\)
* set of mappers for simple result transformations
* support custom SQL query parts
* separate test module with examples build on top of JPA & Spring Boot

#### Available on Maven Central Repository

```xml
<!-- core module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-core</artifactId>
    <version>3.2.0</version>
</dependency>

<!-- spring integration module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-spring</artifactId>
    <version>3.2.0</version>
</dependency>
```



