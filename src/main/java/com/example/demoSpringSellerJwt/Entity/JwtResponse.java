package com.example.demoSpringSellerJwt.Entity;


public class JwtResponse {
    private SellerEntity seller;
    private String jwtToken;

    public JwtResponse(SellerEntity seller, String jwtToken) {
        this.seller = seller;
        this.jwtToken = jwtToken;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerEntity seller) {
        this.seller = seller;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
