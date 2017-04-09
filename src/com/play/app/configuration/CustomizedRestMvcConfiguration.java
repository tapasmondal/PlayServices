package com.play.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

	
	@Override
	  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath("/public-rest-api");
	  }
}
