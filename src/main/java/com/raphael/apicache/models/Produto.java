package com.raphael.apicache.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProduct;

    private String nameProduct;
    private String description;
    private BigDecimal priceOfProduct;

    //Como o projeto é voltado a estudo. Não estou preocupado com Boilerplate.

    //Ou Simplesmente usar o @AllArgsConstructor do Lombok
    public Produto(UUID idProduct, String nameProduct, String description, BigDecimal priceOfProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.priceOfProduct = priceOfProduct;
    }
    //Ou @RequiredArgsConstructor lombok
    public Produto(String nameProduct, String description, BigDecimal priceOfProduct) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.priceOfProduct = priceOfProduct;
    }

    //Ou simplesmente usar o @NoArgsConstructor do Lombok
    public Produto(){}

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(BigDecimal priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }
}
