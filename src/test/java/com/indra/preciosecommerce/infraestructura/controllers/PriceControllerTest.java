package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.usecases.GetPriceUseCase;
import com.indra.preciosecommerce.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static com.indra.preciosecommerce.MotherPrice.givesOnePrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceControllerTest {
    @Mock
    private GetPriceUseCase getPriceUseCase;
    private PriceController priceController;

    @BeforeEach
    public void setup() {
        priceController = new PriceController(getPriceUseCase);
    }

    @Test
     void getsPriceAt10AMOnDay14() {
        Date date = new Date(120, Calendar.JUNE, 14, 10, 0); // Año 2020 es 120
        when(getPriceUseCase.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(35.50, date, 1));
        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(35.50, price.getPrice());
    }

    @Test
     void getsPriceAt4PMOnDay14() {
        Date date = new Date(120, Calendar.JUNE, 14, 16, 0); // Año 2020 es 120
        when(getPriceUseCase.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(25.45, date, 2));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(25.45, price.getPrice());
    }

    @Test
     void getsPriceAt9PMOnDay14() {
        Date date = new Date(120, Calendar.JUNE, 14, 21, 0); // Año 2020 es 120
        when(getPriceUseCase.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(35.50, date, 1));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(35.50, price.getPrice());
    }

    @Test
     void getsPriceAt10AMOnDay15() {
        Date date = new Date(120, Calendar.JUNE, 15, 10, 0); // Año 2020 es 120
        when(getPriceUseCase.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(30.50, date, 3));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(30.50, price.getPrice());
    }

    @Test
     void getsPriceAt9PMOnDay16() {
        Date date = new Date(120, Calendar.JUNE, 16, 21, 0); // Año 2020 es 120
        when(getPriceUseCase.getPrice(date, 35455L, 1L))
                .thenReturn(givesOnePrice(38.95, date, 4));

        Price price = priceController.getPrice(date, 35455L, 1L).getBody();

        assertNotNull(price);
        assertEquals(38.95, price.getPrice());
    }


}