package com.indra.preciosecommerce.infraestructura.adapters;


import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import com.indra.preciosecommerce.infraestructura.repositories.PriceJpaRepository;
import com.indra.preciosecommerce.infraestructura.repositories.PriceRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class PriceRepositoryImpl implements PriceRepositoryPort {
    private final PriceJpaRepository priceJpaRepository;

    @Autowired
    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<PriceEntity> findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(Date dateStart, Date dateEnd, Long productId, Long brandId) {
        return priceJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(dateStart, dateEnd, productId, brandId);
    }
}