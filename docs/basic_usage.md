---
currentMenu: basic_usage
---

### Simple select example:

```java
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.setProjection(NativeExps.projection().addProjection("alias.column_name"));  
c.add(NativeExps.isNull("alias.column_name"));
c.setOrder(NativeExps.order().add("alias.column_name", OrderType.ASC));
CriteriaResult res = c.criteriaResult();
List<Long> resp = new ArrayList<>();
while (res.next()) {
	resp.add(res.getLong(0, null));
}

CriteriaResult res = new NativeCriteria(new JpaQueryProvider(entityManager), "table_name", "alias")
		.setProjection(NativeExps.projection().addProjection("alias.column_name"));  
		.add(NativeExps.isNull("alias.column_name"));
		.setOrder(NativeExps.order().add("alias.column_name", OrderType.ASC))
		.criteriaResult();
while (res.next()) {
	resp.add(res.getLong(0, null));
}
```
### Simple select example with inner join

```java
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.addJoin(NativeExps.innerJoin("table_name_to_join", "alias2", "alias.left_column", "alias2.right_column"));
c.setProjection(NativeExps.projection().addProjection(Lists.newArrayList("alias.table_column","alias2.table_column")));
```

### Get result as object list by NativeObjectMapper

```java
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
List<Address> res = nc.criteriaResult(new NativeObjectMapper<Address>() {
    @Override
    public Address mapObject(CriteriaResult cr) {
        Address a = new Address();
        a.setId(cr.getLong(0));
        a.setCity(cr.getString(1));
        a.setStreet(cr.getString(2));
        a.setBuildingNumber(cr.getString(3));
        a.setZipCode(cr.getString(4));
        return a;
    }
});

```

### Get any result by CriteriaResultTransformer

```java
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
Map<String, String> result = nc.criteriaResult(new CriteriaResultTransformer<Map<String, String>>() {
			@Override
			public Map<String, String> transform(CriteriaResult criteriaResult) {
				Map<String, String> result = Maps.newHashMap();
				while (criteriaResult.next()) {
					result.put(criteriaResult.getString(1), criteriaResult.getString(4));
				}
				return result;
			}
		});

```

### NativeBeanPropertyMapper

```java
SpringNativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s");
    nc.setProjection(NativeExps.projection().addProjection(ImmutableMap.<String, String>builder()
            .put("s.id", "id")
            .put("s.name", "name")
            .put("s.first_name", "firstName")
            .put("s.last_name", "lastName")
            .put("s.vat_identification_number", "vat_identification_number")
            .put("s.phone_number", "phonenumber")
            .put("s.email", "email")
            .build()));
    nc.setOrder(NativeExps.order().add("s.id", NativeOrderExp.OrderType.ASC));
NativeBeanPropertyMapper<SupplierDTO> mapper = NativeBeanPropertyMapper.newInstance(SupplierDTO.class);
List<SupplierDTO> results = nc.criteriaResult(mapper);
```
