package com.danven.web_library.domain.user;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 10L;

    private static final List<Address> records = new ArrayList<>();

    private String country;
    private String city;
    private String houseNumber;
    private String postalCode;

    private Address(AddressBuilder builder) {

        validateCountry(builder.country);
        validateCity(builder.city);
        validateHouseNumber(builder.houseNumber);
        validatePostalCode(builder.postalCode);

        this.country = builder.country;
        this.city = builder.city;
        this.houseNumber = builder.houseNumber;
        this.postalCode = builder.postalCode;

        records.add(this);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        validateCountry(country);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validateCity(city);
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        validateHouseNumber(houseNumber);
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        validatePostalCode(postalCode);
        this.postalCode = postalCode;
    }

    private static void validateCountry(String country) {
        if (country == null || country.isBlank()) {
            throw new ValidationException("Country is invalid");
        }
    }

    private static void validateCity(String city) {
        if (city == null || city.isBlank()) {
            throw new ValidationException("City is invalid");
        }
    }

    private static void validateHouseNumber(String houseNumber) {
        if (houseNumber == null || houseNumber.isBlank()) {
            throw new ValidationException("House number is invalid");
        }
    }

    private static void validatePostalCode(String postalCode) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new ValidationException("Postal code is invalid");
        }
    }

    public static class AddressBuilder {
        private String country;
        private String city;
        private String houseNumber;
        private String postalCode;

        public AddressBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    public static List<Address> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, houseNumber, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

}
