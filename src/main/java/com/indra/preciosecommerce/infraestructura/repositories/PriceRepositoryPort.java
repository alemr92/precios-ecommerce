package com.indra.preciosecommerce.infraestructura.repositories;


import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;

import java.util.Date;
import java.util.List;

public interface PriceRepositoryPort {
    List<PriceEntity> findPriceByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(Date dateStart, Date dateEnd, Long productId, Long brandId);
}