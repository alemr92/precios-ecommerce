package com.indra.preciosecommerce.application.services;

import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.domain.models.exceptions.ProductNotFoundException;
import com.indra.preciosecommerce.domain.ports.PriceService;
import com.indra.preciosecommerce.infraestructura.adapters.PriceRepositoryImpl;
import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Price service.
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepositoryImpl priceRepositoryImpl;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Price service.
     *
     * @param priceRepositoryImpl the price repository
     * @param modelMapper         the model mapper
     */
    public PriceServiceImpl(PriceRepositoryImpl priceRepositoryImpl, ModelMapper modelMapper) {
        this.priceRepositoryImpl = priceRepositoryImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public Price getPrice(Date date, Long productId, Long brandId) {
        List<PriceEntity> prices = priceRepositoryImpl.findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(date, date, productId, brandId);
        Optional<PriceEntity> maxPriorityPrice = prices.stream()
                .max(Comparator.comparingLong(PriceEntity::getPriority));
        return maxPriorityPrice.map(this::convertToPriceModel)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
    }

    private Price convertToPriceModel(PriceEntity priceEntity) {
        return modelMapper.map(priceEntity, Price.class);
    }
}