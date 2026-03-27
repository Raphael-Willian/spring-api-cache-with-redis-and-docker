package com.raphael.apicache.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    private ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<ProdutoResponse> getAllProducts() {
        ProdutoResponse response = produtoService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getProductId(@PathVariable UUID idProduct) {
        ProdutoResponse response =  produtoService.getProductId(idProduct);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> createProduct(@RequestBody ProdutoRequest request) {
        ProdutoResponse response = produtoService.createProduct(request);
        return ResponseEntity.ok(response);
    }

}
