package com.indra.preciosecommerce.application.services;


import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import com.indra.preciosecommerce.infraestructura.repositories.PriceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static com.indra.preciosecommerce.MotherPrice.returnsADateByDays;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceServiceImplIntegrationTest {

    @Autowired
    private PriceServiceImpl priceService;

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @BeforeEach
    public void setUp() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProductId(35455L);
        priceEntity.setBrandId(1L);
        priceEntity.setPriceList(1L);
        priceEntity.setStartDate(returnsADateByDays(-1));
        priceEntity.setEndDate(returnsADateByDays(1));
        priceEntity.setPrice(35.50);
        priceEntity.setCurrency("EUR");

        priceJpaRepository.save(priceEntity);
    }

    @Test
    void testGetPrice() {
        Date date = new Date();
        Long productId = 35455L;
        Long brandId = 1L;

        Price price = priceService.getPrice(date, productId, brandId);

        assertEquals(35.50, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
}