package com.indra.preciosecommerce;

import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;

import java.util.Calendar;
import java.util.Date;

public class MotherPrice {

    public static Price givesOnePrice(double priceValue, Date date, long priceList) {
        return new Price(1L, date, date, priceList, 35455L, 1L, priceValue, "EUR");
    }

    public static Date returnsADateByDayAndHours(int day, int hours){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    public static Price mapToPrice(PriceEntity priceEntity) {
        Price price = new Price();
        price.setBrandId(priceEntity.getBrandId());
        price.setStartDate(priceEntity.getStartDate());
        price.setEndDate(priceEntity.getEndDate());
        price.setPriceList(priceEntity.getPriceList());
        price.setProductId(priceEntity.getProductId());
        price.setPriority(priceEntity.getPriority());
        price.setPrice(priceEntity.getPrice());
        price.setCurrency(priceEntity.getCurrency());
        return price;
    }

    public static Date returnsADateByDays(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}
