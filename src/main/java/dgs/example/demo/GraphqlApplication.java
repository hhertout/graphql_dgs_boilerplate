package dgs.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        if (Objects.equals(System.getenv("API_KEY"), "")) {
            throw new IllegalStateException("'API_KEY' ENV VARIABLE IS NOT SET");
        }
        
        SpringApplication.run(GraphqlApplication.class, args);
    }

}
