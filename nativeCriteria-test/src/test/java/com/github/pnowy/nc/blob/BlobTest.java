package com.github.pnowy.nc.blob;

import com.github.pnowy.nc.AbstractMySqlTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.domain.Address;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Additional MySQL tests (issue #26).
 */
public class BlobTest extends AbstractMySqlTest {

    @Test
    public void shouldUseLimitClause() throws Exception {
        NativeCriteria nc = createNativeCriteria("address", "a").setLimit(2);
        List<Address> addresses = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
        assertThat(addresses).hasSize(2);
    }

    @Test
    public void shouldRetrieveBlobValue() throws Exception {
        NativeCriteria nc = createNativeCriteria("image", "i");
        nc.setProjection(NativeExps.projection().addProjection(Lists.newArrayList("i.name", "i.content")));
        CriteriaResult cr = nc.criteriaResult();
        while (cr.next()) {
            byte[] blob = cr.getBlob("i.content");
            assertThat(blob.getClass()).isOfAnyClassIn(byte[].class.getClass());
            assertThat(blob).isNotEmpty();
        }
    }
}
