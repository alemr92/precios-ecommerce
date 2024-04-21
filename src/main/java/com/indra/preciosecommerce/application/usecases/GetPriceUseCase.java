package com.indra.preciosecommerce.application.usecases;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GetPriceUseCase{

    private final PriceServiceImpl priceServiceImpl;

    public GetPriceUseCase(PriceServiceImpl priceServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
    }

    public Price getPrice(Date date, Long productId, Long brandId) {
        return priceServiceImpl.getPrice(date, productId, brandId);
    }
}