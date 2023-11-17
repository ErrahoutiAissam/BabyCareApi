package com.errahouti.BabyCareApi;

import com.errahouti.BabyCareApi.controller.auth.AuthService;
import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.repository.UserRepo;
import com.errahouti.BabyCareApi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@SpringBootApplication
public class BabyCareAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BabyCareAppApplication.class, args);

	}
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthService service,
//			UserRepo userRepo,
//			UserService userDetailsService
//
//	) {
//		return args -> {
//			UserDetails userDetails = userDetailsService.loadUserByUsername("test2@mail.com");
//			System.out.println(userDetails);
//
//
//
////			System.out.println(userRepo.findByEmail("test2@mail.com"));
////			var admin = RegisterRequest.builder()
////					.firstName("Admin")
////					.lastName("Admin")
////					.email("admin@mail.com")
////					.password("password")
////					.role(Role.ADMIN)
////					.build();
////			System.out.println(service.register(admin).getToken());
//
////			var manager = RegisterRequest.builder()
////					.firstname("Admin")
////					.lastname("Admin")
////					.email("manager@mail.com")
////					.password("password")
////					.role(MANAGER)
////					.build();
////			System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//		};
//	}



}
