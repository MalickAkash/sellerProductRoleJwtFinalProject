package com.example.demoSpringSellerJwt.Service;


import com.example.demoSpringSellerJwt.Entity.JwtRequest;
import com.example.demoSpringSellerJwt.Entity.JwtResponse;
import com.example.demoSpringSellerJwt.Entity.SellerEntity;
import com.example.demoSpringSellerJwt.Repository.SellerRepository;
import com.example.demoSpringSellerJwt.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String sellerEmail = jwtRequest.getSellerEmail();
        String sellerPassword = jwtRequest.getSellerPassword();
        authenticate(sellerEmail, sellerPassword);

        UserDetails userDetails= loadUserByUsername(sellerEmail);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        SellerEntity seller = sellerRepository.findByEmail(sellerEmail);
        return new JwtResponse(seller, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String sellerEmail) throws UsernameNotFoundException {
        SellerEntity seller = sellerRepository.findByEmail(sellerEmail);

        if (seller != null) {
            return new org.springframework.security.core.userdetails.User(
                    seller.getSellerEmail(),
                    seller .getSellerPassword(),
                    getAuthority(seller)
            );
        } else {
            throw new UsernameNotFoundException("User not found with useremail: " + sellerEmail);
        }
    }

    private Set getAuthority(SellerEntity seller) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        seller.getRoleEntitySet().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleNameId()));
        });
        return authorities;
    }


    private void authenticate(String sellerEmail, String sellerPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sellerEmail, sellerPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}



