Main advantages / assumptions:
* simple, friendly API
* without any other generated classed based on database schema
* possibility to build complex joins with detailed projections
* work on all databases supported by Hibernate
* designed for a select queries (not supported update queries now)

## Simple select example:

```
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.setProjection(NativeExps.projection().addProjection("alias.column_name"));  
c.add(NativeExps.isNull("alias.column_name"));
c.setOrder(NativeExps.order().add("alias.column_name", OrderType.ASC));
CriteriaResult res = c.criteriaResult();
while (res.next())
{
    resp.add(res.getLong(0, null));
}

```
## Simple select example with inner join

```
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.addJoin(NativeExps.innerJoin("table_name_to_join", "alias2", "alias.left_column", "alias2.right_column"));
c.setProjection(NativeExps.projection().addProjection(Lists.newArrayList("alias.table_column","alias2.table_column")));

```

## Get result as object list by NativeObjectMapper

```
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
List<Address> res = nc.getResultAsList(new NativeObjectMapper<Address>() {
        @Override
	public Address mapObject(CriteriaResult cr)
	{
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

## Get any result by CriteriaResultTransformer

```
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
Map<String, String> result = nc.criteriaResult(new CriteriaResultTransformer<Map<String, String>>() {
			@Override
			public Map<String, String> transform(CriteriaResult criteriaResult)
			{
				Map<String, String> result = Maps.newHashMap();
				while (criteriaResult.next())
				{
					result.put(criteriaResult.getString(1), criteriaResult.getString(4));
				}
				return result;
			}
		});

```

## Logger to log execution sql time in log4j properties:
log4j.logger.NativeCriteriaPerformance=INFO

## Library available on Maven Central Repository
```
        <dependency>
            <groupId>com.github.pnowy.nc</groupId>
            <artifactId>nativeCriteria-core</artifactId>
            <version>1.1</version>
        </dependency>
```