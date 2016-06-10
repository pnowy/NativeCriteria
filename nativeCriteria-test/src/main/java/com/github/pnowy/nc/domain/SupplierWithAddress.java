package com.github.pnowy.nc.domain;

import com.google.common.base.MoreObjects;

public class SupplierWithAddress extends Supplier {
    protected String city;
    protected String street;
    protected String buildingNumber;
    protected String zipCode;

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
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("vatIdentificationNumber", vatIdentificationNumber)
            .add("phoneNumber", phoneNumber)
            .add("email", email)
            .add("city", city)
            .add("street", street)
            .add("buildingNumber", buildingNumber)
            .add("zipCode", zipCode)
            .toString();
    }
}
