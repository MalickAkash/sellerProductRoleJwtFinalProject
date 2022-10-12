package com.example.demoSpringSellerJwt.Controller;

import com.example.demoSpringSellerJwt.Entity.RoleEntity;
import com.example.demoSpringSellerJwt.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Profile(value = {"local","dev","prod"}) //these line is not needed for profiles environment setUp (optional)
    @PostMapping({"/createNewRole"})
    @PreAuthorize("hasRole('Admin')")
    public RoleEntity createNewRole(@RequestBody RoleEntity role) {
        return roleService.createNewRole(role);
    }
}
