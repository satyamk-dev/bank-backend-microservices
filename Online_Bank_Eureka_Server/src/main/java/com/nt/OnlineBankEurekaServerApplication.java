package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OnlineBankEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankEurekaServerApplication.class, args);
	System.out.println("OnlineBankEurekaServerApplication.main()");
	}

}
