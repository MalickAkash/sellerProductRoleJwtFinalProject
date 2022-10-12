package com.example.demoSpringSellerJwt.Repository;

import com.example.demoSpringSellerJwt.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,String> {
}
