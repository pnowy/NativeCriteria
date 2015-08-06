package com.github.pnowy.nc.select;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.mappers.CriteriaResultTransformer;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple database test with {@linkplain CriteriaResultTransformer}.
 */
public class CriteriaTransformTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(CriteriaTransformTest.class);

    @Test
    public void testSelectWithCriteriaResultTransformer() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        Map<String, String> result = nc.criteriaResult(new CriteriaResultTransformer<Map<String, String>>() {
            @Override
            public Map<String, String> transform(CriteriaResult criteriaResult) {
                Map<String, String> result = Maps.newHashMap();
                while (criteriaResult.next()) {
                    result.put(criteriaResult.getString(1), criteriaResult.getString(4));
                }
                return result;
            }
        });
        log.info("result: {}", result);
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0);
        assertThat(result.get("Warsaw")).isEqualToIgnoringCase("25-996");
    }

}
