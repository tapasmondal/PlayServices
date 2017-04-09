package com.play.app.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.play.app.entity.User;

@Configuration
@EnableJpaRepositories("com.play.app.repositories")
@EnableJpaAuditing
public class RepositoryConfig {

	@Bean
	@Qualifier(value="dataSource")
	public DataSource dataSource() {

		BasicDataSource ds = new BasicDataSource();

		String url = "jdbc:postgresql://localhost:5432/innovazi_play_prod";
		String user = "innovazi";
		String pass = "Salman@123";

		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);

		return ds;

	}

	
	
	@Bean
	@Qualifier(value="devDataSource")
	public DataSource devDataSource() {

		BasicDataSource ds = new BasicDataSource();

		String url = "jdbc:postgresql://localhost:5432/Play";
		String user = "postgres";
		String pass = "root";

		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);

		return ds;

	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabasePlatform("com.play.app.configuration.CustomPostgreSQLDialect");
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.play.app.entity");
		factory.setDataSource(devDataSource());
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	AuditorAware<User> auditorProvider() {
		return new AuditAwareConfig();
	}

	// @Bean
	// public DataSourceInitializer dataSourceInitializer() throws
	// ClassNotFoundException {
	// ResourceDatabasePopulator resourceDatabasePopulator = new
	// ResourceDatabasePopulator();
	// resourceDatabasePopulator.setSqlScriptEncoding("UTF-8");
	// resourceDatabasePopulator.addScript(new
	// ClassPathResource("/import.sql"));
	//
	// DataSourceInitializer dataSourceInitializer = new
	// DataSourceInitializer();
	// dataSourceInitializer.setDataSource(devDataSource());
	// dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	// return dataSourceInitializer;
	// }

}