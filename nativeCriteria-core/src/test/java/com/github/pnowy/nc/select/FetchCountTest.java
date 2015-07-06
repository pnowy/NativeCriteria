package com.github.pnowy.nc.select;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.github.pnowy.nc.utils.Address;
import com.github.pnowy.nc.utils.HibernateUtil;
import com.github.pnowy.nc.utils.Transactional;
import org.fest.assertions.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Simple database test.
 * Przemek Nowak <przemek.nowak.pl@gmail.com> Date: 30.07.13 17:41
 */
public class FetchCountTest implements Transactional {
    private static final Logger log = LoggerFactory.getLogger(FetchCountTest.class);

    @Override
    public void transaction(NativeQueryProvider provider) {
        NativeCriteria nc = new NativeCriteria(provider, "ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "WARSAW"));

        int i = nc.fetchCount("a.city");
        Assertions.assertThat(i).isEqualTo(1);

        List<Address> res = nc.criteriaResult(new NativeObjectMapper<Address>() {
            @Override
            public Address mapObject(CriteriaResult cr) {
                Address a = new Address();
                a.setId(cr.getLong(0));
                a.setCity(cr.getString(1));
                a.setStreet(cr.getString(2));
                a.setBuildingNumber(cr.getString(3));
                a.setZipCode(cr.getString(4));
                a.setAmount(cr.getBigDecimal(5));
                return a;
            }
        });
        Assertions.assertThat(res).isNotNull().hasSize(1);
    }

    @Test
    public void test() {
        HibernateUtil.executeTransaction(this);
    }
}
