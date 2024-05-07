package com.indra.preciosecommerce.application.services;

import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.domain.models.exceptions.ProductNotFoundException;
import com.indra.preciosecommerce.infraestructura.adapters.PriceRepositoryImpl;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.indra.preciosecommerce.MotherPrice.mapToPrice;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepositoryImpl priceRepositoryImpl;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Test
    void getsPrice() {
        Date date = new Date();
        Long productId = 35455L;
        Long brandId = 1L;
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(35.50);
        priceEntity.setCurrency("EUR");

        when(priceRepositoryImpl.findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(any(), any(), any(), any()))
                .thenReturn(List.of(priceEntity));

        when(modelMapper.map(priceEntity, Price.class)).thenReturn(mapToPrice(priceEntity));

        Price price = priceServiceImpl.getPrice(date, productId, brandId);

        assertEquals(35.50, price.getPrice());
    }

    @Test
    void throwsProductNotFoundException() {
        Date date = new Date();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepositoryImpl.findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(date, date, productId, brandId))
                .thenReturn(Collections.emptyList());

        assertThrows(ProductNotFoundException.class, () -> priceServiceImpl.getPrice(date, productId, brandId));
    }
}