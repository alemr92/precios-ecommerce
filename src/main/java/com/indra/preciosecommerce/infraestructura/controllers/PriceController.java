package com.indra.preciosecommerce.infraestructura.controllers;

import com.indra.preciosecommerce.application.usecases.GetPriceUseCase;
import com.indra.preciosecommerce.domain.models.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    public PriceController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping("/getPrice")
    public ResponseEntity<Price> getPrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        Price price = getPriceUseCase.getPrice(date, productId, brandId);
        return ResponseEntity.ok(price);
    }
}