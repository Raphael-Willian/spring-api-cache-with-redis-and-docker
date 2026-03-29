package com.raphael.apicache.services;

import com.raphael.apicache.dtos.request.ProdutoRequest;
import com.raphael.apicache.dtos.response.ProdutoResponse;
import com.raphael.apicache.models.Produto;
import com.raphael.apicache.repositorys.ProdutoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Cacheable("produtos")
    public List<ProdutoResponse> getAll() {
        System.out.println("BUSCANDO NO BANCO...");

        List<Produto> listaDeProdutos = produtoRepository.findAll();

        return listaDeProdutos.stream()
                .map(p -> new ProdutoResponse(
                        p.getIdProduct(),
                        p.getNameProduct(),
                        p.getDescription(),
                        p.getPriceOfProduct()
                ))
                .toList();

    }

    @Cacheable(value = "produto", key = "#id")
    public ProdutoResponse getProductId(UUID id) {
        System.out.println("BUSCANDO NO BANCO...");
        Produto produto = produtoRepository.findByIdProduct(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));;

        return  new ProdutoResponse(produto.getNameProduct(), produto.getDescription(), produto.getPriceOfProduct());
    }

    @CacheEvict(value = "produtos", allEntries = true)
    public ProdutoResponse createProduct(ProdutoRequest request) {

        Produto produto = new Produto(request.getNome(), request.getDescricao(), request.getPreco());

        produtoRepository.save(produto);

        return new ProdutoResponse(produto.getNameProduct(), produto.getDescription(), produto.getPriceOfProduct());

    }

    @Caching(evict = {
            @CacheEvict(value = "produto", key = "#request.id"),
            @CacheEvict(value = "produtos", allEntries = true)
    })
    public ProdutoResponse putProduct(ProdutoRequest request) {

        Produto produto = produtoRepository.findByIdProduct(request.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNameProduct(request.getNome());
        produto.setDescription(request.getDescricao());
        produto.setPriceOfProduct(request.getPreco());

        produtoRepository.save(produto);

        return new ProdutoResponse(produto.getNameProduct(), produto.getDescription(), produto.getPriceOfProduct());


    }

    @Caching(evict = {
            @CacheEvict(value = "produto", key = "#p0"),
            @CacheEvict(value = "produtos", allEntries = true)
    })
    public void deleteProduct(UUID idProduct) {

        Produto produto = produtoRepository.findByIdProduct(idProduct)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));;

        System.out.println("Produto deletado: " + produto.getNameProduct() + " | " + produto.getDescription() + " | " + produto.getPriceOfProduct());

        produtoRepository.delete(produto);

    }


}
