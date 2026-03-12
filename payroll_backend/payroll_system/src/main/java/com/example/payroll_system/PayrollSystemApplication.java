package com.example.payroll_system;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@ComponentScan(basePackages = "com.example.payroll_system")
@EnableJpaRepositories(basePackages = "com.example.payroll_system.repository")
public class PayrollSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayrollSystemApplication.class, args);
    }
}
