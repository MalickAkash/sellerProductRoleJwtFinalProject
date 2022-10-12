package com.example.demoSpringSellerJwt;

import com.example.demoSpringSellerJwt.Controller.SellerController;
import com.example.demoSpringSellerJwt.Entity.RoleEntity;
import com.example.demoSpringSellerJwt.Repository.RoleRepository;
import com.example.demoSpringSellerJwt.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class DemoSpringSellerJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSellerJwtApplication.class, args);
	}

}
