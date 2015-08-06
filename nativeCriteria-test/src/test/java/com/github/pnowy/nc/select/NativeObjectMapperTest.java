package com.github.pnowy.nc.select;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
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
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class NativeObjectMapperTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(NativeObjectMapperTest.class);

    @Test
    public void testSelectWithObjectMapper() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "Warsaw"));
        List<Address> res = nc.criteriaResult(new NativeObjectMapper<Address>() {
            @Override
            public Address mapObject(CriteriaResult cr) {
                Address a = new Address();
                a.setId(cr.getLong(0));
                a.setCity(cr.getString(1));
                a.setStreet(cr.getString(2));
                a.setBuildingNumber(cr.getString(3));
                a.setZipCode(cr.getString(4));
                return a;
            }
        });
        log.info("Result list: {}", res);
        assertThat(res).isNotNull().hasSize(1);
    }

}
