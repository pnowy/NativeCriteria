---
currentMenu: spring_integration
---

## SPRING Integration module

### NativeBeanPropertyMapper

```java

public class Supplier {
    // properties, getters and setters, etc.
}

SpringNativeCriteria nc = new SpringNativeCriteria(new JpaQueryProvider(entityManager), "SUPPLIER", "s")
    .setProjection(NativeExps.projection().addProjection(
            "s.id as id",
            "s.name as name",
            "s.first_name as firstName",
            "s.last_name as lastName",
            "s.vat_identification_number as vat_identification_number"
            "s.phone_number as phonenumber",
            "s.email as email")
    .setOrder(NativeExps.order().add("s.id", NativeOrderExp.OrderType.ASC));
NativeBeanPropertyMapper<SupplierDTO> mapper = NativeBeanPropertyMapper.newInstance(SupplierDTO.class);
List<SupplierDTO> results = nc.criteriaResult(mapper);
```


### Pageable support

```java
SpringNativeCriteria nc = new SpringNativeCriteria(new JpaQueryProvider(entityManager), "SUPPLIER", "s")
        .addJoin(NativeExps.innerJoin("ADDRESS", "a", "a.supplier_id", "s.id"));
        .setProjection(NativeExps.projection().addProjectionWithAliases(
            "s.id as id",
            "a.city as city",
            "s.name as name"
        ));
        
Page<SupplierWithAddress> suppliers = nc.criteriaResult("a.id", NativeBeanPropertyMapper.newInstance(SupplierWithAddress.class),
                                                         new PageRequest(0, 2, new Sort(new Sort.Order(Sort.Direction.ASC, "s.id"))));
List<SupplierWithAddress> pageContent = suppliers.getContent();
```
