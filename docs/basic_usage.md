---
currentMenu: basic_usage
---

### Simple select:

```java
// SELECT a.city FROM address a WHERE a.zip_code IS NULL ORDER BY a.city ASC
NativeCriteria nc = new NativeCriteria(new JpaQueryProvider(entityManager), "address", "a")
      .setProjection(NativeExps.projection().addProjection("a.city"))
      .add(NativeExps.isNull("a.zip_code"));
      .setOrder(NativeExps.order().add("a.city", OrderType.ASC));
CriteriaResult res = c.criteriaResult();
List<String> cityNames = new ArrayList<>();
while (res.next()) {
	resp.add(res.getString("a.city"));
}
```
### Simple select with inner join:

```java
//SELECT s.id as supplierId, a.city as supplierCity FROM supplier s INNER JOIN address a ON s.id=a.supplier_id
NativeCriteria c = new NativeCriteria(new JpaQueryProvider(entityManager), "supplier", "s")
      .addJoin(NativeExps.innerJoin("address", "a", "s.id", "a.supplier_id"))
      .setProjection(NativeExps.projection().addProjection("s.id as supplierId","a.city as supplierCity")));
```

### Fetch count:

```java
//SELECT s.id as supplierId, a.city as supplierCity FROM supplier s INNER JOIN address a ON s.id=a.supplier_id
NativeCriteria nc =  new NativeCriteria(new JpaQueryProvider(entityManager), "ADDRESS", "a").add(NativeExps.eq("a.city", "Warsaw"));
int i = nc.fetchCount("a.city");
```

### Select top rows (LIMIT/OFFSET query part):

```java
 NativeCriteria nc = createNativeCriteria("ADDRESS", "a")
        .setOrder(NativeExps.ascOrder("a.id"))
        .setLimit(2)
        .setOffset(1);
List<Address> addresses = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
```

### Custom SQL query part:

```java
 NativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s").addJoin(NativeExps.customSql("JOIN ADDRESS a ON a.supplier_id=s.id"));
 CriteriaResult result = nc.criteriaResult();
 while (result.next()) {
    String currentRecordDesc = result.getCurrentRecordDesc();
    log.info(currentRecordDesc);
}
```

[See the mappers for convinient retrieving the data](mappers.md)
