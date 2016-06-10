package com.github.pnowy.nc.domain;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;

/**
 * <p>Test class mapping address table.</p>
 *
 * Przemek Nowak
 */
public class Address extends AbstractEntity {
    protected String city;
    protected String street;
    protected String buildingNumber;
    protected String zipCode;

    public static NativeObjectMapper<Address> NATIVE_OBJECT_MAPPER = new NativeObjectMapper<Address>() {
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
    };

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "buildingNumber='" + buildingNumber + '\'' +
                ", id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
