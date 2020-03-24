package com.ak.contractors.marketplace.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.ak.contractors.marketplace")
@EntityScan(basePackages = "com.ak.contractors.marketplace.entities" )
@EnableJpaRepositories(basePackages = "com.ak.contractors.marketplace.repository")
@EnableAsync
public class ContractorsMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractorsMarketplaceApplication.class, args);
    }

}
