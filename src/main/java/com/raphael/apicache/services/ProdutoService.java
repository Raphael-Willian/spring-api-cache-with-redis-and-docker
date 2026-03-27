package com.raphael.apicache.services;

import com.raphael.apicache.dtos.request.ProdutoRequest;
import com.raphael.apicache.dtos.response.ProdutoResponse;
import com.raphael.apicache.models.Produto;
import com.raphael.apicache.repositorys.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponse> getAll() {

        List<Produto> listaDeProdutos = produtoRepository.findAll();

        return listaDeProdutos.stream()
                .map(p -> new ProdutoResponse(
                        p.getNameProduct(),
                        p.getDescription(),
                        p.getPriceOfProduct()
                ))
                .toList();

    }

    public ProdutoResponse getProductId(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com o ID: " + id + " não foi encontrado."));

        ProdutoResponse resposta = new ProdutoResponse(produto.getNameProduct(), produto.getDescription(), produto.getPriceOfProduct());

        return  resposta;
    }

    public ProdutoResponse createProduct(ProdutoRequest request) {

        Produto produto = new Produto(request.getNome(), request.getDescricao(), request.getPreco());

        produtoRepository.save(produto);
        ProdutoResponse resposta = new ProdutoResponse(produto.getNameProduct(), produto.getDescription(), produto.getPriceOfProduct());

        return resposta;

    }


}
