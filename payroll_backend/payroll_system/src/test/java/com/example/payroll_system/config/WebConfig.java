package com.example.payroll_system.config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableConfigurationProperties
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS requests from your frontend (React app)
        registry.addMapping("/api/**") // Define which API endpoints you want to allow cross-origin requests for
                .allowedOrigins("http://localhost:3000") // URL of your React frontend (change as needed)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*") // Allow all headers (you can customize if needed)
                .allowCredentials(true); // Allow credentials (cookies, authentication)
    }
}
