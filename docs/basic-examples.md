# Basic examples

#### Simple select

```java
// SELECT a.city FROM address a WHERE a.zip_code IS NULL ORDER BY a.city ASC
NativeCriteria nc = new NativeCriteria(new JpaQueryProvider(entityManager), "address", "a") // <1>
      .setProjection(NativeExps.projection().addProjection("a.city"))                       // <2>
      .add(NativeExps.isNull("a.zip_code"));                                                // <3>
      .setOrder(NativeExps.order().add("a.city", OrderType.ASC));                           // <4>
// get the results
CriteriaResult res = c.criteriaResult();                                                    // <5>
List<String> cityNames = new ArrayList<>();
while (res.next()) {                                                                        // <6>
   resp.add(res.getString("a.city"));                                                       // <7>
}

<1> Definition of the criteria. The first parameter is a query provider. It could be JPA or HibernateSession provider
<2> Definition of the projection. We use here the alias for main table defined on the (1)
<3> Added example criteria (zip code is null)
<4> Added ordering
<5> Generate criteria result (this is the moment when query is executed)
<6> Retrieve results on the loop (there are predefined mappers, you will see more on next chapters)
<7> Retrieve string value from results and add to response
```

#### Simple select with inner join

```java
// SELECT s.id as supplierId, a.city as supplierCity
// FROM supplier s
// INNER JOIN address a ON s.id=a.supplier_id
NativeCriteria c = new NativeCriteria(new JpaQueryProvider(entityManager), "supplier", "s")
      .addJoin(NativeExps.innerJoin("address", "a", "s.id", "a.supplier_id"))
      .setProjection(NativeExps.projection().addProjection("s.id as supplierId","a.city as supplierCity")));
```

#### Fetch count

```java
// SELECT s.id as supplierId, a.city as supplierCity
// FROM supplier s INNER JOIN address a ON s.id=a.supplier_id
NativeCriteria nc = 
    new NativeCriteria(new JpaQueryProvider(entityManager), "ADDRESS", "a").add(NativeExps.eq("a.city", "Warsaw"));
int i = nc.fetchCount("a.city");
```

#### Select top rows \(LIMIT/OFFSET query parts\)

```java
NativeCriteria nc = createNativeCriteria("ADDRESS", "a")
        .setOrder(NativeExps.ascOrder("a.id"))
        .setLimit(2)
        .setOffset(1);
List<Address> addresses = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
```

#### Custom SQL query part

```java
NativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s")
                    .addJoin(NativeExps.customSql("JOIN ADDRESS a ON a.supplier_id=s.id"));
 CriteriaResult result = nc.criteriaResult();
 while (result.next()) {
    String currentRecordDesc = result.getCurrentRecordDesc();
    log.info(currentRecordDesc);
}
```

#### [See the mappers for convenient retrieving the data](/mappers.md)



