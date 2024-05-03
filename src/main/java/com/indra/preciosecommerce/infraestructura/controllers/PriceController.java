package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.domain.models.exceptions.ProductNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * The type Price controller.
 */
@RestController
@RequestMapping("/api")
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
    @GetMapping("/getPrice")
    public ResponseEntity<Price> getPrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        Price price = priceServiceImpl.getPrice(date, productId, brandId);
        if (price == null) {
            throw new ProductNotFoundException("Producto no encontrado");
        }
        return ResponseEntity.ok(price);
    }

    /**
     * Handle product not found exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}