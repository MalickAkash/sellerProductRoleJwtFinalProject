package com.example.demoSpringSellerJwt.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    private String roleNameId;
    private String roleDescription;

    public String getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(String roleNameId) {
        this.roleNameId = roleNameId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}



