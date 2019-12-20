package com.github.pnowy.nc.queries;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.domain.Address;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple presentation how to use select query with top and offset.
 */
public class SelectTopRowsQueryTest extends AbstractDbTest {

    @Test
    public void shouldSelectTopRowsForQueryWithOffset() {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.setOrder(NativeExps.ascOrder("a.id"));
        nc.setLimit(2);
        nc.setOffset(1);
        List<Address> addresses = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
        assertThat(addresses.size()).isEqualTo(2);
    }
}
