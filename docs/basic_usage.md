---
currentMenu: basic_usage
---

### Simple select:

```java
// SELECT a.city FROM address a WHERE a.zip_code IS NULL ORDER BY a.city ASC
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "address", "a")
      .setProjection(NativeExps.projection().addProjection("a.city"))
      .add(NativeExps.isNull("a.zip_code"));
      .setOrder(NativeExps.order().add("a.city", OrderType.ASC));
CriteriaResult res = c.criteriaResult();
List<String> cityNames = new ArrayList<>();
while (res.next()) {
	resp.add(res.String("a.city"));
}
```
### Simple select with inner join:

```java
//SELECT s.id as supplierId, a.city as supplierCity FROM supplier s INNER JOIN address a ON s.id=a.supplier_id
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "supplier", "s")
      .addJoin(NativeExps.innerJoin("address", "a", "s.id", "a.supplier_id"));
      .setProjection(NativeExps.projection().addProjection("s.id as supplierId","a.city as supplierCity")));
```
