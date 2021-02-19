package com.cs673.gofoodie;

import com.cs673.gofoodie.models.Role;
import com.cs673.gofoodie.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableAutoConfiguration
public class GofoodieApplication {

  public static void main(String[] args) {

    SpringApplication.run(GofoodieApplication.class, args);
  }

  @Bean
  CommandLineRunner init(RoleRepository roleRepository) {

    return args -> {

      Role adminRole = roleRepository.findByUserRole("ADMIN");
      if (adminRole == null) {
        Role newAdminRole = new Role();
        newAdminRole.setUserRole("ADMIN");
        roleRepository.save(newAdminRole);
      }

      Role userRole = roleRepository.findByUserRole("USER");
      if (userRole == null) {
        Role newUserRole = new Role();
        newUserRole.setUserRole("USER");
        roleRepository.save(newUserRole);
      }
    };

  }

}
