package com.mm.ussd.enumeration;

public enum CountryEnum {
    KENYA("1", "Kenya", "KES", 6.10),
    MALAWI("2", "Malawi", "MWK", 42.50);

    private String entryNumber;
    private String name;
    private String foreignCurrencyCode;
    private double exchangeRate;

    CountryEnum(final String entryNumber, final String name, final String foreignCurrencyCode, final double exchangeRate){
        this.entryNumber = entryNumber;
        this.name = name;
        this.foreignCurrencyCode = foreignCurrencyCode;
        this.exchangeRate = exchangeRate;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForeignCurrencyCode() {
        return foreignCurrencyCode;
    }

    public void setForeignCurrencyCode(String foreignCurrencyCode) {
        this.foreignCurrencyCode = foreignCurrencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
