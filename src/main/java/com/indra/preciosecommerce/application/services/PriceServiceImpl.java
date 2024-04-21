package com.indra.preciosecommerce.application.services;

import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.domain.ports.PriceServicePort;
import com.indra.preciosecommerce.infraestructura.adapters.PriceRepositoryImpl;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceServicePort {

    private final PriceRepositoryImpl priceRepositoryImpl;

    public PriceServiceImpl(PriceRepositoryImpl priceRepositoryImpl) {
        this.priceRepositoryImpl = priceRepositoryImpl;
    }

    @Override
    public Price getPrice(Date date, Long productId, Long brandId) {
        List<PriceEntity> prices = this.priceRepositoryImpl.findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(date, date, productId, brandId);
        Optional<PriceEntity> price = prices.stream()
                .max(Comparator.comparingLong(PriceEntity::getPriority));
        return price.map(this::mapToDomain).orElse(null);
    }

    private Price mapToDomain(PriceEntity priceEntity) {
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

}
