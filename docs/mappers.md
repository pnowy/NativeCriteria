# Mappers

#### Get result as object list by NativeObjectMapper

```java
//SELECT a.* FROM address
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(session), "ADDRESS", "a")
                        .add(NativeExps.eq("a.city", "WARSAW"));
List<Address> res = nc.criteriaResult(cr -> {
    Address a = new Address();
    a.setId(cr.getLong(0));
    a.setCity(cr.getString(1));
    a.setStreet(cr.getString(2));
    a.setBuildingNumber(cr.getString(3));
    a.setZipCode(cr.getString(4));
    return a;
});
```

###### TIP: You could always define the mapper as separate object:

```java
NativeCriteria nc = createNativeCriteria("ADDRESS", "a").add(NativeExps.eq("a.city", "Warsaw"));
List<Address> res = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
```

#### Get any result by CriteriaResultTransformer

```java
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(session), "ADDRESS", "a");
Multimap<String, String> zipCodesGroupedByCities = nc.criteriaResult(new CriteriaResultTransformer<Multimap<String,String>>() {
    @Override
    public Multimap<String, String> transform(CriteriaResult criteriaResult) {
        Multimap<String, String> result = HashMultimap.create();
        while (criteriaResult.next()) {
            // 1 - city code, 4 - zip code
            result.put(criteriaResult.getString(1), criteriaResult.getString(4));
        }
        return result;
    }
});
```

###### [TIP: See more mappers on Spring module](/spring-data-integration.md)



