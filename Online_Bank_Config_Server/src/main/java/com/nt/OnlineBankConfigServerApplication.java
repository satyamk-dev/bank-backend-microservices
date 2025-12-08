package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class OnlineBankConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankConfigServerApplication.class, args);
	System.out.println("OnlineBankConfigServerApplication.main()");
	}

}
