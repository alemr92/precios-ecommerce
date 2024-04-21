package com.indra.preciosecommerce.application.services;


import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.infraestructura.adapters.PriceRepositoryImpl;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceServiceImplIntegrationTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepositoryImpl priceRepositoryImpl;

    @Test
    public void getsPrice() {
        Date date = new Date();
        Long productId = 35455L;
        Long brandId = 1L;
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(35.50);
        priceEntity.setCurrency("EUR");

        when(priceRepositoryImpl.findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(date, date, productId, brandId))
                .thenReturn(List.of(priceEntity));

        Price price = priceService.getPrice(date, productId, brandId);

        assertEquals(35.50, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
}