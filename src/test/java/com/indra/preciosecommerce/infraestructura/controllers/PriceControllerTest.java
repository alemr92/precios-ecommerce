package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static com.indra.preciosecommerce.MotherPrice.givesOnePrice;
import static com.indra.preciosecommerce.MotherPrice.returnsADateByDayAndHours;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceControllerTest {

    @Mock
    private PriceServiceImpl priceServiceImpl;
    private PriceController priceController;

    @BeforeEach
    public void setup() {
        priceController = new PriceController(priceServiceImpl);
    }

    @Test
     void getsPriceAt10AMOnDay14() {
        Date date = returnsADateByDayAndHours(14,10);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(35.50, date, 1));
        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(35.50, price.getPrice());
    }

    @Test
     void getsPriceAt4PMOnDay14() {
        Date date = returnsADateByDayAndHours(14,16);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(25.45, date, 2));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(25.45, price.getPrice());
    }

    @Test
     void getsPriceAt9PMOnDay14() {
        Date date = returnsADateByDayAndHours(14,21);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(35.50, date, 1));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(35.50, price.getPrice());
    }

    @Test
     void getsPriceAt10AMOnDay15() {
        Date date = returnsADateByDayAndHours(15,10);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(30.50, date, 3));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(30.50, price.getPrice());
    }

    @Test
     void getsPriceAt9PMOnDay16() {
        Date date = returnsADateByDayAndHours(16,21);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(38.95, date, 4));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(38.95, price.getPrice());
    }
}