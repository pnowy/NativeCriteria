package com.github.pnowy.nc.mappers;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import com.github.pnowy.nc.domain.AddressDTO;
import com.github.pnowy.nc.domain.SupplierDTO;
import com.github.pnowy.nc.spring.NativeBeanPropertyMapper;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import org.junit.Test;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Tests of bean property mapper.
 *
 * @author Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class NativeBeanPropertyMapperTest extends AbstractDbTest {

    private SpringNativeCriteria createAddressDTOCriteria() {
        SpringNativeCriteria nc = createSpringNativeCriteria("ADDRESS", "a");
            nc.setProjection(NativeExps.projection().addProjection(ImmutableMap.<String, String>builder()
                .put("a.id", "id")
                .put("a.city", "city")
                .put("a.street", "street")
                .put("a.building_number", "buildingNumber")
                .put("a.zip_code", "zipCode")
                .put("a.supplier_id", "supplierId")
                .build()));
        return nc;
    }

    private SpringNativeCriteria createSupplierDTOCriteria() {
        SpringNativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s");
        nc.setProjection(NativeExps.projection().addProjection(ImmutableMap.<String, String>builder()
            .put("s.id", "id")
            .put("s.name", "name")
            .put("s.first_name", "firstName")
            .put("s.last_name", "lastName")
            .put("s.vat_identification_number", "vat_identification_number")
            .put("s.phone_number", "phoneNumber")
            .put("s.email", "email")
            .build()));
        nc.setOrder(NativeExps.order().add("s.id", NativeOrderExp.OrderType.ASC));
        return nc;
    }

    @Test
    public void shouldMapSupplierDTO() throws Exception {
        NativeBeanPropertyMapper<SupplierDTO> mapper = NativeBeanPropertyMapper.newInstance(SupplierDTO.class);
        SpringNativeCriteria nc = createSupplierDTOCriteria();
        CriteriaResult cr = nc.criteriaResult();
        if (cr.next()) {
            SupplierDTO supplierDTO = mapper.mapObject(cr);
            assertThat(supplierDTO.getId()).isNotNull().isEqualTo(1);
            assertThat(supplierDTO.getFirstName()).isEqualTo("John");
            assertThat(supplierDTO.getLastName()).isEqualTo("Doe");
            assertThat(supplierDTO.getVatIdentificationNumber()).isEqualTo("6640004523");
            assertThat(supplierDTO.getPhoneNumber()).isEqualTo("+48 883009323");
        }
    }

    @Test
    public void shouldMapFullyPopulatedSupplierDTO() {
        NativeBeanPropertyMapper<SupplierDTO> mapper = NativeBeanPropertyMapper.newInstance(SupplierDTO.class);
        mapper.setCheckFullyPopulated(true);
        SpringNativeCriteria nc = createSupplierDTOCriteria();
        List<SupplierDTO> dtos = nc.criteriaResult(mapper);
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void shouldThrowErrorForNotFullPopulatedDTO() {
        SpringNativeCriteria nc = createSpringNativeCriteria("SUPPLIER", "s");
        nc.setProjection(NativeExps.projection().addProjection("s.id", "id"));
        nc.setOrder(NativeExps.order().add("s.id", NativeOrderExp.OrderType.ASC));
        NativeBeanPropertyMapper<SupplierDTO> mapper = NativeBeanPropertyMapper.newInstance(SupplierDTO.class);
        mapper.setCheckFullyPopulated(true);
        nc.criteriaResult(mapper);
    }

    @Test
    public void shouldReturnListOfObjectsForBeanPropertyMapper() {
        SpringNativeCriteria nc = createSupplierDTOCriteria();
        List<SupplierDTO> dtos = nc.criteriaResult(NativeBeanPropertyMapper.newInstance(SupplierDTO.class));
        assertThat(dtos.size()).isEqualTo(2);
        SupplierDTO first = Iterables.getFirst(dtos, null);
        assert first != null;
        assertThat(first.getId()).isEqualTo(1);
        assertThat(first.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void shouldInitializePrimitivePropertyByDefaultValue() {
        SpringNativeCriteria nc = createAddressDTOCriteria();
        NativeBeanPropertyMapper<AddressDTO> mapper = NativeBeanPropertyMapper.newInstance(AddressDTO.class);
        mapper.setPrimitivesDefaultedForNullValue(true);
        List<AddressDTO> list = nc.criteriaResult(mapper);
        assertThat(list.size()).isEqualTo(5);
        System.out.println(list);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowExceptionForNullValueForPrimitiveProperty() {
        SpringNativeCriteria nc = createAddressDTOCriteria();
        NativeBeanPropertyMapper<AddressDTO> mapper = NativeBeanPropertyMapper.newInstance(AddressDTO.class);
        List<AddressDTO> list = nc.criteriaResult(mapper);
        assertThat(list.size()).isEqualTo(5);
    }

}
