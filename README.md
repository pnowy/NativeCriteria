
[![Build Status](https://travis-ci.org/pnowy/NativeCriteria.svg?branch=develop)](https://travis-ci.org/pnowy/NativeCriteria)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.pnowy.nc%22)
[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/pnowy/NativeCriteria)

### Development && Participation

If you want to participate on the development please perform the pull request to the **develop** branch.

### Documentation

Documentation is available on [https://pnowy.gitbooks.io/nativecriteria/content/](https://pnowy.gitbooks.io/nativecriteria/content/)

### Library available on Maven Central Repository
```xml
<!-- core module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-core</artifactId>
    <version>3.0.0</version>
</dependency>

<!-- spring integration module -->
<dependency>
    <groupId>com.github.pnowy.nc</groupId>
    <artifactId>nativeCriteria-spring</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Simple example:
```java
// SELECT a.city FROM address a WHERE a.zip_code IS NULL AND city := ? ORDER BY a.city ASC
NativeCriteria nc = new NativeCriteria(new JpaQueryProvider(entityManager), "address", "a")
      .setProjection(NativeExps.projection().addProjection("a.city"))                       
      .add(NativeExps.isNull("a.zip_code"));                                                
      .setOrder(NativeExps.order().add("a.city", OrderType.ASC));

// dynamic where part
if (StringUtils.isNotEmpty(city)) {
    nc.add(NativeExps.eq("a.city", city))
}            
      
// get the results
CriteriaResult res = c.criteriaResult();                                                    
List<String> cityNames = new ArrayList<>();
while (res.next()) {                                                                        
   resp.add(res.getString("a.city"));                                                       
}
```
