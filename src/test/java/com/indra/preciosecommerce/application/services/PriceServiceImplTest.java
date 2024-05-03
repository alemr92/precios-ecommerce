package com.indra.preciosecommerce.application.services;

import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.infraestructura.adapters.PriceRepositoryImpl;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static com.indra.preciosecommerce.MotherPrice.mapToPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest

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
}