package com.indra.preciosecommerce;

import com.indra.preciosecommerce.domain.models.Price;

import java.util.Date;

public class MotherPrice {

    public static Price givesOnePrice(double priceValue, Date date, long priceList) {
        return new Price(1L, date, date, priceList, 35455L, 1L, priceValue, "EUR");
    }
}
