package com.example.demoSpringSellerJwt.Service;

import com.example.demoSpringSellerJwt.Entity.RoleEntity;
import com.example.demoSpringSellerJwt.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity createNewRole(RoleEntity role) {
        return roleRepository.save(role);
    }
}

