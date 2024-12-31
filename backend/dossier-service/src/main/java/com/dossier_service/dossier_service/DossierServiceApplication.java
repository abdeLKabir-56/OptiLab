package com.dossier_service.dossier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class DossierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossierServiceApplication.class, args);
	}

}
