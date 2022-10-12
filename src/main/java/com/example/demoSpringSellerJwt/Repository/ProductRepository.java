package com.example.demoSpringSellerJwt.Repository;

import com.example.demoSpringSellerJwt.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

