package com.example.demoSpringSellerJwt.Entity;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "seller")
public class SellerEntity {
    @Id
    @SequenceGenerator(
            name = "seller_seq",
            sequenceName = "seller_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seller_seq"
    )
    private Long sellerId;
    @NotNull
    private String sellerFirstName;
    @NotNull
    private String sellerLastName;
    @NotNull
    private String sellerPassword;
    @NotNull
    private String sellerEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "seller_status")
    private  SellerStatus status;

    private String sellerAcType;
    private String sellerCity;
    private String sellerCountry;
    private Long sellerZipcode;


    // These are also unidirectional...
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "sellers_roles",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name_id")
    )
    private Set<RoleEntity> roleEntitySet;

    // These two are also unidirectional...

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(name = "sellers_product",
//            joinColumns = @JoinColumn(name = "seller_user_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<ProductEntity> productEntityList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id",referencedColumnName = "sellerId")
    private List<ProductEntity> productEntityList;

    public SellerEntity() {
    }

    public SellerEntity(String sellerFirstName, String sellerLastName, String sellerPassword, String sellerEmail, SellerStatus status, String sellerAcType, String sellerCity, String sellerCountry, Long sellerZipcode, Set<RoleEntity> roleEntitySet, List<ProductEntity> productEntityList) {
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        this.sellerPassword = sellerPassword;
        this.sellerEmail = sellerEmail;
        this.status = status;
        this.sellerAcType = sellerAcType;
        this.sellerCity = sellerCity;
        this.sellerCountry = sellerCountry;
        this.sellerZipcode = sellerZipcode;
        this.roleEntitySet = roleEntitySet;
        this.productEntityList = productEntityList;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public SellerStatus getStatus() {
        return status;
    }

    public void setStatus(SellerStatus status) {
        this.status = status;
    }

    public String getSellerAcType() {
        return sellerAcType;
    }

    public void setSellerAcType(String sellerAcType) {
        this.sellerAcType = sellerAcType;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerCountry() {
        return sellerCountry;
    }

    public void setSellerCountry(String sellerCountry) {
        this.sellerCountry = sellerCountry;
    }

    public Long getSellerZipcode() {
        return sellerZipcode;
    }

    public void setSellerZipcode(Long sellerZipcode) {
        this.sellerZipcode = sellerZipcode;
    }

    public Set<RoleEntity> getRoleEntitySet() {
        return roleEntitySet;
    }

    public void setRoleEntitySet(Set<RoleEntity> roleEntitySet) {
        this.roleEntitySet = roleEntitySet;
    }

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }
}
