package com.example.demoSpringSellerJwt.Service;

import com.example.demoSpringSellerJwt.Entity.RoleEntity;
import com.example.demoSpringSellerJwt.Entity.SellerEntity;
import com.example.demoSpringSellerJwt.Entity.SellerStatus;
import com.example.demoSpringSellerJwt.Repository.RoleRepository;
import com.example.demoSpringSellerJwt.Repository.SellerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Profile(value = {"local","dev","prod"})
@Service
public class SellerService {

    Logger log=LoggerFactory.getLogger(SellerService.class);

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public SellerEntity registerNewAdmin(SellerEntity seller) {
        RoleEntity role = roleRepository.findById("Admin").get();
        Set<RoleEntity> sellerRoles = new HashSet<>();
        sellerRoles.add(role);
        seller.setRoleEntitySet(sellerRoles);
        seller.setSellerPassword(getEncodedPassword(seller.getSellerPassword()));
        return sellerRepository.save(seller);
    }


    public SellerEntity registerNewUser(SellerEntity seller) {
        RoleEntity role = roleRepository.findById("Seller").get();
        Set<RoleEntity> sellerRoles = new HashSet<>();
        sellerRoles.add(role);
        seller.setRoleEntitySet(sellerRoles);
        seller.setSellerPassword(getEncodedPassword(seller.getSellerPassword()));
        return sellerRepository.save(seller);
    }

    private String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    //** (Iterable<T> findAll() and List<T> findAll() )are same things
    public List<SellerEntity> showAllData() {
        return sellerRepository.findAll();
    }

    //** Optional<T> is used in findById(Id id) predefined method
    public List<SellerEntity> showSpecificData(String sellerFirstName) {
        return sellerRepository.findByFirstName(sellerFirstName);
    }


    public void initSellerRoleAdd() {
        RoleEntity adminRole=new RoleEntity();
        adminRole.setRoleNameId("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleRepository.save(adminRole);

        SellerEntity adminSeller=new SellerEntity();
        adminSeller.setSellerId(1L);
        adminSeller.setSellerFirstName("Akash");
        adminSeller.setSellerLastName("Malick");
        adminSeller.setSellerEmail("akash@gmail.com");
        adminSeller.setSellerPassword(getEncodedPassword("akash@pass"));
        adminSeller.setSellerZipcode(711254L);
        adminSeller.setSellerAcType("Retailer");
        adminSeller.setSellerCity("Howrah");
        adminSeller.setSellerCountry("India");

        adminSeller.setStatus(SellerStatus.Active);

        Set<RoleEntity> roleEntitySet=new HashSet<>();
        roleEntitySet.add(adminRole);
        adminSeller.setRoleEntitySet(roleEntitySet);
        sellerRepository.save(adminSeller);
    }


    public void saveData(SellerEntity seller) {
        sellerRepository.save(seller);
    }

    public SellerEntity findByEmail(String userEmail) {
        return sellerRepository.findByEmail(userEmail);
    }

    public SellerEntity updateNormal(SellerEntity sellerEntity) {
        SellerEntity seller1 = sellerRepository.findById(sellerEntity.getSellerId()).orElse(null);
        seller1.setStatus(sellerEntity.getStatus());
        return sellerRepository.save(seller1);
    }

    public List<SellerEntity> paginationSort(String searchKey, PageRequest pageRequest) {
        return sellerRepository.getSellerList(searchKey,pageRequest);
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void displayAllSeller() {
        List<SellerEntity> s=sellerRepository.findAll();
        System.out.println("Hello corn scheduling is start after 10 seconds: "+ new Date().toString());
        System.out.println("number of records: "+s.size());
        System.out.println("Sellers important Details:");

        // Here Uses for-each loop
        for(SellerEntity list:s)
            log.info(list.getSellerFirstName()+", "+list.getSellerLastName()+", "+list.getSellerEmail()+", "+list.getSellerAcType()
            +", "+list.getStatus());

    }

}

