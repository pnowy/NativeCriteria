package com.github.pnowy.nc.select;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.core.mappers.CriteriaResultTransformer;
import com.github.pnowy.nc.utils.HibernateUtil;
import com.github.pnowy.nc.utils.Transactional;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple database test.
 */
public class SelectTestCriteriaTransformTest implements Transactional {
    private static final Logger log = LoggerFactory.getLogger(SelectTestCriteriaTransformTest.class);

    @Override
    public void transaction(NativeQueryProvider provider) {
        NativeCriteria nc = new NativeCriteria(provider, "ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "WARSAW"));
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
        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get("WARSAW")).isEqualToIgnoringCase("34-567");
    }

    @Test
    public void test() {
        HibernateUtil.executeTransaction(this);
    }
}
