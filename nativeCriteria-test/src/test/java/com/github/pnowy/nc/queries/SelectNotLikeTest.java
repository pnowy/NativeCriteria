package com.github.pnowy.nc.queries;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectNotLikeTest extends AbstractDbTest {

    @Test
    public void shouldSelectProductsWithCategoriesAndSuppliers() {
        SpringNativeCriteria nc = createSpringNativeCriteria("PRODUCT", "p");
        nc.add(NativeExps.notLike("p.name", "%DALVIK%"));
        nc.setProjection(NativeExps.projection().addProjectionWithAliases("p.name as name"));

        final List<String > list = nc.criteriaResult((NativeObjectMapper<String>) cr -> cr.getString("p.name"));
        assertThat(list).doesNotContainSubsequence("DALVIK");
    }

}
