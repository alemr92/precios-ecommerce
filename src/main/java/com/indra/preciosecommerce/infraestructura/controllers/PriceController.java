package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * The type Price controller.
 */
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceServiceImpl priceServiceImpl;

    /**
     * Instantiates a new Price controller.
     *
     * @param priceServiceImpl the price service
     */
    public PriceController(PriceServiceImpl priceServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
    }

    /**
     * Gets price.
     *
     * @param date      the date
     * @param productId the product id
     * @param brandId   the brand id
     * @return the price
     */
    @GetMapping()
    public ResponseEntity<Price> getPrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        Price price = priceServiceImpl.getPrice(date, productId, brandId);
        return ResponseEntity.ok(price);
    }
}