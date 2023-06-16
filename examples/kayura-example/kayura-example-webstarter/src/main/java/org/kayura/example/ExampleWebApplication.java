package org.kayura.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ExampleWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleWebApplication.class, args);
  }

}
