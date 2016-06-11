package com.github.pnowy.nc.spring;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Extended {@link NativeCriteria} with some features available on Spring Data Commons.
 *
 * @author Przemek Nowak
 */
public class SpringNativeCriteria extends NativeCriteria {

    /**
     * Constructor.
     *
     * @param nativeProvider native select provider
     * @param mainTable      the main table
     * @param mainAlias      the main alias
     */
    public SpringNativeCriteria(NativeQueryProvider nativeProvider, String mainTable, String mainAlias) {
        super(nativeProvider, mainTable, mainAlias);
    }

    /**
     * Return result list based on bean property mapper.
     *
     * @param mapper object mapper
     * @param <T>    object mapper type
     * @return result list
     */
    public <T> List<T> criteriaResult(NativeBeanPropertyMapper<T> mapper) {
        CriteriaResult criteriaResult = criteriaResult();
        List<T> result = Lists.newLinkedList();
        while (criteriaResult.next()) {
            result.add(mapper.mapObject(criteriaResult));
        }
        return result;
    }

    /**
     * <p>Sets the Spring {@link Pageable} with {@link OrderByApproach#DEFAULT} approach.</p>
     *
     * <p>See more {@link #setPageable(Pageable, OrderByApproach)}</p>
     *
     * @param pageable spring pageable
     * @return native criteria
     */
    public NativeCriteria setPageable(Pageable pageable) {
        return setPageable(pageable, OrderByApproach.DEFAULT);
    }

    /**
     * <p>Sets the Spring {@link Pageable} to the native criteria. This method transform pageable spring parameters to the native
     * criteria parameters (limit, offset and ordering).</p>
     *
     * <p>With pageable you could select the correct order by approach regarding NULL-s ordering.</p>
     *
     * <p>In order to get directly {@link PageImpl} you could use one of the methods which take the native objects mappers and pageable.</p>
     *
     * <p>With this method you have to first set pageable parameter and after that you have to create {@link PageImpl} on you own. For
     * the counting result you could use {@link #fetchCount(String)} method. See example below.</p>
     *
     * <pre><code>
     *  List&lt;ExampleDTO&gt; result = nc.criteriaResult(nativeObjectMapper&lt;ExampleDTO&gt;)
     *  return new PageImpl&lt;&gt;(result, pageable, nc.fetchCount("result.id", true));
     * </code></pre>
     *
     * @param pageable spring pageable
     * @param orderByApproach nulls ordering approach
     *
     * @return native criteria
     */
    public NativeCriteria setPageable(Pageable pageable, OrderByApproach orderByApproach) {
        if (pageable == null) {
            throw new NullPointerException("Pageable cannot be null!");
        }
        if (orderByApproach == null) {
            throw new NullPointerException("Order by approach cannot be null!");
        }

        this.setOffset(pageable.getOffset());
        this.setLimit(pageable.getPageSize());

        if (pageable.getSort() != null) {
            NativeOrderExp nativeOrder = NativeExps.order();
            for (Sort.Order sort : pageable.getSort()) {
                nativeOrder.add(orderByApproach.sort(sort));
            }
            this.setOrder(nativeOrder);
        }
        return this;
    }

    /**
     * <p>Execute query for page based on provided pageable params.</p>
     *
     * @param countAlias the alias (column) which will be used in order to count total number of record for provided query
     * @param distinctCount whether use distinct for counting total
     * @param nativeObjectMapper native object mapper to transform result to objects
     * @param pageable spring pageable
     * @param <T> the native mapper and page implementation type
     * @return page results
     */
    @SuppressWarnings("unchecked")
    public <T> PageImpl<T> criteriaResult(String countAlias, boolean distinctCount, NativeObjectMapper<T> nativeObjectMapper, Pageable pageable) {
        setPageable(pageable);
        List<T> result = criteriaResult(nativeObjectMapper);
        return new PageImpl(result, pageable, fetchCount(countAlias, distinctCount));
    }

    /**
     * <p>Execute query for page based on provided pageable params with default distinct set to true when counting total.</p>
     *
     * @param countAlias the alias (column) which will be used in order to count total number of record for provided query
     * @param nativeObjectMapper native object mapper to transform result to objects
     * @param pageable spring pageable
     * @param <T> the native mapper and page implementation type
     * @return page results
     */
    public <T> PageImpl<T> criteriaResult(String countAlias, NativeObjectMapper<T> nativeObjectMapper, Pageable pageable) {
        return criteriaResult(countAlias, true, nativeObjectMapper, pageable);
    }

    /**
     * <p>Execute query for page based on provided pageable params.</p>
     *
     * @param countAlias the alias (column) which will be used in order to count total number of record for provided query
     * @param distinctCount whether use distinct for counting total
     * @param beanPropertyMapper bean property mapper to transform result to objects, see {@link NativeBeanPropertyMapper}
     * @param pageable spring pageable
     * @param <T> the mapper and page implementation type
     * @return page results
     */
    @SuppressWarnings("unchecked")
    public <T> PageImpl<T> criteriaResult(String countAlias, boolean distinctCount, NativeBeanPropertyMapper<T> beanPropertyMapper, Pageable pageable) {
        setPageable(pageable);
        List<T> result = criteriaResult(beanPropertyMapper);
        return new PageImpl(result, pageable, fetchCount(countAlias, distinctCount));
    }


    /**
     * <p>Execute query for page based on provided pageable params with default distinct set to true when counting total.</p>
     *
     * @param countAlias the alias (column) which will be used in order to count total number of record for provided query
     * @param beanPropertyMapper bean property mapper to transform result to objects, see {@link NativeBeanPropertyMapper}
     * @param pageable spring pageable
     * @param <T> the mapper and page implementation type
     * @return page results
     */
    public <T> PageImpl<T> criteriaResult(String countAlias, NativeBeanPropertyMapper<T> beanPropertyMapper, Pageable pageable) {
        return criteriaResult(countAlias, true, beanPropertyMapper, pageable);
    }

}
