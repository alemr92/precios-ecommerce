package com.indra.preciosecommerce.domain.ports;


import com.indra.preciosecommerce.domain.models.Price;

import java.util.Date;

public interface PriceServicePort {
    Price getPrice(Date date, Long productId, Long brandId);
}
