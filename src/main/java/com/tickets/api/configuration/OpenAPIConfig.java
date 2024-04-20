package com.tickets.api.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
	
	private static final String SPRINGDOC_USE_ROOT_PATH = "http://localhost:9000/";

	@Value("${ahmed.openapi.dev-url}")
	private String devUrl;

	@Value("${ahmed.openapi.prod-url}")
    private String prodUrl;
	
	@ConditionalOnProperty(name = SPRINGDOC_USE_ROOT_PATH, havingValue = "true")


	@Bean
	  public OpenAPI myOpenAPI() {
	    Server devServer = new Server();
	    devServer.setUrl(devUrl);
	    devServer.setDescription("Server URL in Development environmentc");

	    Server prodServer = new Server();
	    prodServer.setUrl(prodUrl);
	    prodServer.setDescription("Server URL in Production environment c");

	    Contact contact = new Contact();
	    contact.setEmail("hello.haidara@gmail.com");
	    contact.setName("Ahmed HAIDARA");
	    contact.setUrl("https://ahmedhaidara.com");

	    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

	    Info info = new Info()
	        .title("Ticket API for Ennov Test purpose")
	        .version("1.0")
	        .contact(contact)
	        .description("This API exposes endpoints to manage Tickets.").termsOfService("hello.haidara@gmail.com")
	        .license(mitLicense);

	    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	  }

}
