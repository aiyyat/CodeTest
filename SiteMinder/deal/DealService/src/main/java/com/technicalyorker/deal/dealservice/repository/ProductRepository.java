package com.technicalyorker.deal.dealservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicalyorker.deal.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
