package com.indra.preciosecommerce.infraestructura.repositories;

import com.indra.preciosecommerce.infraestructura.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(
            Date dateStart, Date dateEnd, Long productId, Long brandId);
}