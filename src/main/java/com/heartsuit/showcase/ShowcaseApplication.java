package com.heartsuit.showcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The type Showcase application.
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ShowcaseApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShowcaseApplication.class, args);
	}
}
