package com.github.pnowy.nc.queries;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.domain.Address;

/**
 * Simple database test with fetch count method usage.
 * For each query is it possible to use the fetch count which returns the database count.
 */
public class FetchCountTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(FetchCountTest.class);

    @Test
    public void shouldSelectAddressCount() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "Warsaw"));
        int i = nc.fetchCount("a.city");
        assertThat(i).isEqualTo(2);

        List<Address> res = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
        assertThat(res).isNotNull().hasSize(2);
    }

}
