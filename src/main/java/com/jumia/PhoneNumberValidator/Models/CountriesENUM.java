package com.jumia.PhoneNumberValidator.Models;

public enum CountriesENUM {
    CAMEROON("Cameroon", 237), ETHIOPIA("Ethiopia", 251),
    MOROCCO("Morocco", 212),MOZAMBIQUE("Mozambique", 258),
    UGANDA("Uganda", 256);

    private final String countryName;
    private final Integer code;


    CountriesENUM(String countryName, Integer code) {
        this.countryName = countryName;
        this.code = code;
    }

    public String getCountryName() {
        return countryName;
    }

    public Integer getCode() {
        return code;
    }
}
