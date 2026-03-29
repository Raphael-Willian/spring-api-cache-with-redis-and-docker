package com.raphael.apicache.repositorys;

import com.raphael.apicache.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Override
    Optional<Produto> findById(UUID uuid);
    Optional<Produto> findByIdProduct(UUID uuid);
}
