package com.github.pnowy.nc.spring;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.domain.Address;
import com.github.pnowy.nc.domain.SupplierWithAddress;
import org.junit.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test of pageable support.
 */
public class SpringPageableTest extends AbstractDbTest {

    @Test
    public void shouldReturnPageImplementationForSimpleEntity() throws Exception {
        SpringNativeCriteria nc = createSpringNativeCriteria("ADDRESS", "a");
        PageImpl<Address> addressPage = nc.criteriaResult("a.id", Address.NATIVE_OBJECT_MAPPER, new PageRequest(0, 2, new Sort(new Sort
            .Order(Sort.Direction.ASC, "a.id"))));
        assertThat(addressPage.getTotalPages()).isEqualTo(3);
        assertThat(addressPage.getTotalElements()).isEqualTo(6);
        List<Address> pageContent = addressPage.getContent();
        assertThat(pageContent).hasSize(2);
        assertThat(pageContent.get(0).getId()).isEqualTo(1L);
        assertThat(pageContent.get(1).getId()).isEqualTo(2L);
    }

    @Test
    public void shouldReturnPageImplementationForJoin() throws Exception {
        SpringNativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s");
        nc.setProjection(NativeExps.projection().addProjectionWithAliases(
            "s.id as id",
            "a.city as city",
            "s.name as name"
        ));
        nc.addJoin(NativeExps.innerJoin("ADDRESS", "a", "a.supplier_id", "s.id"));
        PageImpl<SupplierWithAddress> suppliers = nc.criteriaResult("a.id", NativeBeanPropertyMapper.newInstance(SupplierWithAddress.class),
                                                          new PageRequest(0, 2, new Sort(new Sort.Order(Sort.Direction.ASC, "s.id"))));
        assertThat(suppliers.getTotalPages()).isEqualTo(2);
        assertThat(suppliers.getTotalElements()).isEqualTo(4L);
        List<SupplierWithAddress> pageContent = suppliers.getContent();
        assertThat(pageContent).hasSize(2);
    }

}
