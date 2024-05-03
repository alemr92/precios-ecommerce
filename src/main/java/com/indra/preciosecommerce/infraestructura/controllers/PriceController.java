package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.services.PriceServiceImpl;
import com.indra.preciosecommerce.domain.models.Price;
import com.indra.preciosecommerce.domain.models.exceptions.ProductNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final PriceServiceImpl priceServiceImpl;

    public PriceController(PriceServiceImpl priceServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
    }

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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}