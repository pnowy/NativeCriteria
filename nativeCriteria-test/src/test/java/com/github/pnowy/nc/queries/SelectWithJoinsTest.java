package com.github.pnowy.nc.queries;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import com.github.pnowy.nc.domain.ProductDTO;
import com.github.pnowy.nc.spring.NativeBeanPropertyMapper;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Set of test with selecting data with joins.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class SelectWithJoinsTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(SelectWithJoinsTest.class);

//    SELECT
//        p.id as productId,
//        p.name as productName,
//        c.name as categoryName,
//        s.name as supplierName
//    FROM
//      product p
//    INNER JOIN
//      category c ON c.id = p.category_id
//    INNER JOIN
//      supplier s ON s.id = p.supplier_id

    @Test
    public void shouldSelectProductsWithCategoriesAndSuppliers() throws Exception {
        SpringNativeCriteria nc = createSpringNativeCriteria("PRODUCT", "p");
        nc
            .addJoin(NativeExps.innerJoin("CATEGORY", "c", "p.category_id", "c.id"))
            .addJoin(NativeExps.innerJoin("SUPPLIER", "s", "p.supplier_id", "s.id"))
            .setProjection(NativeExps.projection().addProjectionWithAliases(
                "p.id as productId",
                "p.name as productName",
                "c.name as categoryName",
                "s.name as supplierName"))
            .setOrder(NativeExps.order().add("p.id", NativeOrderExp.OrderType.ASC));


        final List<ProductDTO> list = nc.criteriaResult(NativeBeanPropertyMapper.newInstance(ProductDTO.class));
        log.info("Retrieved data: {}", list);
        assertThat(list).isNotEmpty();
        final ProductDTO dto = list.get(0);
        assertThat(dto.getProductId()).isNotNull().isEqualTo(1L);
        assertThat(dto.getCategoryName()).isNotNull();
        assertThat(dto.getProductName()).isNotNull();
        assertThat(dto.getSupplierName()).isNotNull();
    }



}
