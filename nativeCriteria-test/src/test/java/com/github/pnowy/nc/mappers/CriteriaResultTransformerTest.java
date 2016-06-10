package com.github.pnowy.nc.mappers;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.mappers.CriteriaResultTransformer;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple database test with {@linkplain CriteriaResultTransformer}.
 */
public class CriteriaResultTransformerTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(CriteriaResultTransformerTest.class);

    @Test
    public void shouldSelectAndTransformWithCriteriaResultTransformer() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        Multimap<String, String> addressesGroupedByCities = nc.criteriaResult(new CriteriaResultTransformer<Multimap<String, String>>() {
            @Override
            public Multimap<String, String> transform(CriteriaResult criteriaResult) {
                Multimap<String, String> result = HashMultimap.create();
                while (criteriaResult.next()) {
                    result.put(criteriaResult.getString(1), criteriaResult.getString(4));
                }
                return result;
            }
        });
        log.info("addressesGroupedByCities: {}", addressesGroupedByCities);
        assertThat(addressesGroupedByCities).isNotNull();
        assertThat(addressesGroupedByCities.size()).isGreaterThan(0);
        assertThat(addressesGroupedByCities.get("Warsaw")).isNotEmpty();
    }

}
