package org.holo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class HoloBackendApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    SpringApplication.run(HoloBackendApplication.class, args);
  }

}
