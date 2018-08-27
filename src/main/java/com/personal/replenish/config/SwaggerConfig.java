package com.personal.replenish.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.personal.replenish.controller"))
				//.paths(PathSelectors.regex("/replenisher/tasks.*"))
				//.paths(PathSelectors.regex("/replenisher/users.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Replenish Task with Swagger")
				.description("Replenish Task with Swagger")
				.termsOfServiceUrl("")
				.contact("Pavan Komandur")
				.license("Apache License Version 2.0")
				.licenseUrl("")
				.version("2.0")
				.build();
	}


}
