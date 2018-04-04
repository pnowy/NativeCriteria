package com.github.pnowy.nc.spring;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.exceptions.InvalidDataAccessException;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.*;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * <p>Bean mapper is inspired by Spring {@link org.springframework.jdbc.core.BeanPropertyRowMapper}.
 *
 * Some parts of the code (and comments) are from that mapper so if you are interested of the subject
 * you could take a look at Spring Code.
 *
 * <p>{@link NativeObjectMapper} implementation that converts a row into a new instance
 * of the specified mapped target class. The mapped target class must be a
 * top-level class and it must have a default or no-arg constructor.
 *
 * <p>Column values are mapped based on properties names obtained from given object class.
 * This mapper supports only criteria with fixed projections so before you will start use it
 * generate {@link SpringNativeCriteria} with defined projection.
 *
 * <p><strong>The names are matched either directly from provided aliases in defined projection
 * or by transforming a bean property name to lower case or camel case.</strong></p>
 *
 * <p>For example if we have the bean with property firstName (with getters and setters)
 * the projection alias which will be mapped could have the following form:
 * firstName, first_name, firstname. See the examples on the module nativeCriteria-test.
 *
 * <p>Mapping is provided for fields in the target class for many common types, e.g.:
 * String, boolean, Boolean, byte, Byte, short, Short, int, Integer, long, Long,
 * float, Float, double, Double, BigDecimal, {@code java.util.Date}, etc.

 * <p>For 'null' values read from the database, we will attempt to call the setter, but in the case of
 * Java primitives, this causes a TypeMismatchException. This class can be configured (using the
 * primitivesDefaultedForNullValue property) to trap this exception and use the primitives default value.
 * Be aware that if you use the values from the generated bean to update the database the primitive value
 * will have been set to the primitive's default value instead of null.
 *
 * <p>Please note that this class is designed to provide convenience rather than high performance.
 * For best performance, consider using a custom {@link NativeObjectMapper} implementation.
 *
 * @author Przemek Nowak based on Spring {@link org.springframework.jdbc.core.BeanPropertyRowMapper}
 */
public class NativeBeanPropertyMapper<T> implements NativeObjectMapper<T> {
    protected final Logger logger = LoggerFactory.getLogger(NativeBeanPropertyMapper.class);

    /** The class we are mapping to */
    private Class<T> mappedClass;

    /** Whether we're strictly validating */
    private boolean checkFullyPopulated = false;

    /** Whether we're defaulting primitives when mapping a null value */
    private boolean primitivesDefaultedForNullValue = false;

    /** Map of the fields we provide mapping for */
    private Map<String, PropertyDescriptor> mappedFields;

    /**
     * Create a new {@code BeanPropertyRowMapper} for bean-style configuration.
     * @see #setMappedClass
     * @see #setCheckFullyPopulated
     */
    public NativeBeanPropertyMapper() {
    }

    /**
     * Create a new {@code BeanPropertyRowMapper}, accepting unpopulated
     * properties in the target bean.
     * <p>Consider using the {@link #newInstance} factory method instead,
     * which allows for specifying the mapped type once only.
     * @param mappedClass the class that each row should be mapped to
     */
    public NativeBeanPropertyMapper(Class<T> mappedClass) {
        initialize(mappedClass);
    }

    /**
     * Create a new {@code BeanPropertyRowMapper}.
     * @param mappedClass the class that each row should be mapped to
     * @param checkFullyPopulated whether we're strictly validating that
     * all bean properties have been mapped from corresponding database fields
     */
    public NativeBeanPropertyMapper(Class<T> mappedClass, boolean checkFullyPopulated) {
        initialize(mappedClass);
        this.checkFullyPopulated = checkFullyPopulated;
    }

    /**
     * Set the class that each row should be mapped to.
     */
    public void setMappedClass(Class<T> mappedClass) {
        if (this.mappedClass == null) {
            initialize(mappedClass);
        }
        else {
            if (!this.mappedClass.equals(mappedClass)) {
                throw new InvalidDataAccessException("The mapped class can not be reassigned to map to " +
                    mappedClass + " since it is already providing mapping for " + this.mappedClass);
            }
        }
    }

    /**
     * Get the class that we are mapping to.
     */
    public final Class<T> getMappedClass() {
        return this.mappedClass;
    }

    /**
     * Set whether we're strictly validating that all bean properties have been mapped
     * from corresponding database fields.
     * <p>Default is {@code false}, accepting unpopulated properties in the target bean.
     */
    public void setCheckFullyPopulated(boolean checkFullyPopulated) {
        this.checkFullyPopulated = checkFullyPopulated;
    }

    /**
     * Return whether we're strictly validating that all bean properties have been
     * mapped from corresponding database fields.
     */
    public boolean isCheckFullyPopulated() {
        return this.checkFullyPopulated;
    }

    /**
     * Set whether we're defaulting Java primitives in the case of mapping a null value
     * from corresponding database fields.
     * <p>Default is {@code false}, throwing an exception when nulls are mapped to Java primitives.
     */
    public void setPrimitivesDefaultedForNullValue(boolean primitivesDefaultedForNullValue) {
        this.primitivesDefaultedForNullValue = primitivesDefaultedForNullValue;
    }

    /**
     * Return whether we're defaulting Java primitives in the case of mapping a null value
     * from corresponding database fields.
     */
    public boolean isPrimitivesDefaultedForNullValue() {
        return this.primitivesDefaultedForNullValue;
    }


    /**
     * Initialize the mapping metadata for the given class.
     * @param mappedClass the mapped class
     */
    protected void initialize(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
        this.mappedFields = new HashMap<>();
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                this.mappedFields.put(pd.getName(), pd);
            }
        }
    }

    /**
     * Convert a name in camelCase to an underscored name in lower case.
     * Any upper case letters are converted to lower case with a preceding underscore.
     *
     * @param name the original name
     * @return the converted name
     */
    protected String underscoreName(String name) {
        if (!StringUtils.hasLength(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(lowerCaseName(name.substring(0, 1)));
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = lowerCaseName(s);
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            }
            else {
                result.append(s);
            }
        }
        return result.toString();
    }

    /**
     * Convert the given name to lower case.
     * By default, conversions will happen within the US locale.
     * @param name the original name
     * @return the converted name
     * @since 4.2
     */
    protected String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }


    /**
     * Extract the values from {@link CriteriaResult}
     */
    @Override
    @SuppressWarnings("deprecation")
    public T mapObject(CriteriaResult cr) {
        Assert.state(this.mappedClass != null, "Mapped class was not specified");
        T mappedObject = BeanUtils.instantiate(this.mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
        initBeanWrapper(bw);

        Set<String> populatedProperties = (isCheckFullyPopulated() ? new HashSet<>() : null);

        for (String beanProperty : mappedFields.keySet()) {
            PropertyDescriptor pd = this.mappedFields.get(beanProperty);
            if (pd != null) {
                try {
                    String alias = pd.getName();
                    Object value = null;
                    if (cr.hasProperty(alias)) {
                        value = cr.getValue(pd.getName(), null);
                        if (populatedProperties != null) {
                            populatedProperties.add(pd.getName());
                        }
                    }
                    if (value == null) {
                        alias = lowerCaseName(pd.getName());
                        if (cr.hasProperty(alias)) {
                            value = cr.getValue(lowerCaseName(alias));
                            if (populatedProperties != null) {
                                populatedProperties.add(pd.getName());
                            }
                        }
                    }
                    if (value == null) {
                        alias = underscoreName(pd.getName());
                        if (cr.hasProperty(alias)) {
                            value = cr.getValue(alias);
                            if (populatedProperties != null) {
                                populatedProperties.add(pd.getName());
                            }
                        }
                    }
                    if (logger.isDebugEnabled() && cr.getRowNumber() == 0) {
                        logger.debug("Mapping column (retrieved by alias) '" + alias + "' to property '" +
                            pd.getName() + "' of type " + pd.getPropertyType());
                    }
                    try {
                        bw.setPropertyValue(pd.getName(), value);
                    }
                    catch (TypeMismatchException ex) {
                        if (value == null && this.primitivesDefaultedForNullValue) {
                            // we should initialize primitive by default value
                            if (logger.isDebugEnabled()) {
                                logger.debug("Intercepted TypeMismatchException for row " + cr.getRowNumber() + " and column '" +
                                    alias + "' with null value when setting property '" + pd.getName() +
                                    "' of type " + pd.getPropertyType() + " on object: " + mappedObject);
                            }
                        }
                        else {
                            throw ex;
                        }
                    }
                }
                catch (NotWritablePropertyException ex) {
                    throw new DataRetrievalFailureException(
                        "Unable to map property " + pd.getName(), ex);
                }
            }
        }

        if (populatedProperties != null && !populatedProperties.equals(mappedFields.keySet())) {
            throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields " +
                "necessary to populate object of class [" + this.mappedClass + "]: " + this.mappedFields.keySet());
        }

        return mappedObject;
    }

    /**
     * Initialize the given BeanWrapper to be used for row mapping.
     * To be called for each row.
     * <p>The default implementation is empty. Can be overridden in subclasses.
     * @param bw the BeanWrapper to initialize
     */
    protected void initBeanWrapper(BeanWrapper bw) {
    }

    /**
     * Static factory method to create a new {@code BeanPropertyRowMapper}
     * (with the mapped class specified only once).
     * @param mappedClass the class that each row should be mapped to
     */
    public static <T> NativeBeanPropertyMapper<T> newInstance(Class<T> mappedClass) {
        return new NativeBeanPropertyMapper<T>(mappedClass);
    }

}
