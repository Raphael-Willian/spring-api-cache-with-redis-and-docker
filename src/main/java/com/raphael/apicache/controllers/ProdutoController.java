package com.raphael.apicache.controllers;

import com.raphael.apicache.dtos.request.ProdutoRequest;
import com.raphael.apicache.dtos.response.ProdutoResponse;
import com.raphael.apicache.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    private ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAllProducts() {
        List<ProdutoResponse> response = produtoService.getAll();
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
