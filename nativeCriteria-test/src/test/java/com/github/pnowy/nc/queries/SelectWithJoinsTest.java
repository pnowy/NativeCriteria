package com.github.pnowy.nc.queries;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set of test with selecting data with joins.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class SelectWithJoinsTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(SelectWithJoinsTest.class);

//    SELECT
//        productID,
//        productName,
//        categoryName,
//    companyName AS supplier
//        FROM
//    products
//    INNER JOIN
//    categories ON categories.categoryID = products.categoryID
//    INNER JOIN
//    suppliers ON suppliers.supplierID = products.supplierID

    @Test
    public void shouldSelectProductsWithCategoriesAndSuppliers() throws Exception {
        SpringNativeCriteria nc = createSpringNativeCriteria("PRODUCT", "p");

        nc
            .addJoin(NativeExps.innerJoin("CATEGORY", "c", "p.category_id", "c.id"))
            .addJoin(NativeExps.innerJoin("SUPPLIER", "s", "p.supplier_id", "s.id"))
            .setProjection(NativeExps.projection().addProjectionWithAliases("p.id as projectId"));


        final CriteriaResult cr = nc.criteriaResult();
        while (cr.next()) {
            System.out.println(cr.getCurrentRecordDesc());
        }
//        int i = nc.fetchCount("a.city");
//        assertThat(i).isEqualTo(2);

//        List<Address> res = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
//        assertThat(res).isNotNull().hasSize(2);
    }



}
