package com.example.restaurant;

import com.example.restaurant.entity.AppUser;
import com.example.restaurant.model.enums.AppUserRole;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class RestaurantApplication implements CommandLineRunner {

    final UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }


    @Override
    public void run(String... params) throws Exception {
//        AppUser admin = new AppUser();
//        admin.setUsername("admin");
//        admin.setPassword("admin");
//        admin.setEmail("admin@email.com");
//        admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));
//
//        userService.signup(admin);
//
//        AppUser client = new AppUser();
//        client.setUsername("client");
//        client.setPassword("client");
//        client.setEmail("client@email.com");
//        client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_WAITER)));
//
//        userService.signup(client);
    }
}
