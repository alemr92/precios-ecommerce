package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static com.indra.preciosecommerce.MotherPrice.givesOnePrice;
import static com.indra.preciosecommerce.MotherPrice.returnsADateByDayAndHours;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceServiceImpl priceServiceImpl;
    private PriceController priceController;

    @BeforeEach
    public void setup() {
        priceController = new PriceController(priceServiceImpl);
    }

    @Test
     void getsAPrice() {
        Date date = returnsADateByDayAndHours(14,10);
        when(priceServiceImpl.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(35.50, date, 1));
        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(35.50, price.getPrice());
    }
}