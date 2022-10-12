package com.example.demoSpringSellerJwt.Controller;

import com.example.demoSpringSellerJwt.Entity.JwtRequest;
import com.example.demoSpringSellerJwt.Entity.JwtResponse;
import com.example.demoSpringSellerJwt.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile(value = {"local","dev","prod"}) //these line is not needed for profiles environment setUp (optional)
@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
