package com.anattests.test1good;

import java.util.Map;

public class Country {

    private static final Map<String, Double> TAX_RATES = Map.of(
            "IL", 0.17,
            "US", 0.08
    );
    private static final double DEFAULT_TAX_RATE = 0.20;

    private String code; // ISO code, e.g. "IL", "US"
    private String name;

    public Country() {
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTaxRate() {
        return TAX_RATES.getOrDefault(code, DEFAULT_TAX_RATE);
    }

    public double applyTax(double amount) {
        return amount * (1 + getTaxRate());
    }
}
