package com.github.pnowy.nc.mappers;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.github.pnowy.nc.domain.Address;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple database test with {@linkplain NativeObjectMapper}.
 *
 * @author Przemek Nowak
 */
public class NativeObjectMapperTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(NativeObjectMapperTest.class);

    @Test
    public void shouldRetrieveDataByNativeObjectMapper() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "Warsaw"));
        List<Address> res = nc.criteriaResult(Address.NATIVE_OBJECT_MAPPER);
        log.info("Result list: {}", res);
        assertThat(res).isNotNull().hasSize(2);
    }

}
