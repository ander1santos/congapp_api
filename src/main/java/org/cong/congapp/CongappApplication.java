package org.cong.congapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CongappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongappApplication.class, args);
	}

}
