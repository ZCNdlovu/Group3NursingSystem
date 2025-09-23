package za.ac.cput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "za.ac.cput")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
