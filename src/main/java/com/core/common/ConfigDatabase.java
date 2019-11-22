package com.core.common;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ConfigDatabase {
	private static final String PROPERTY_KEY_URL = "spring.datasource.url";
	private static final String PROPERTY_KEY_USERNAME = "spring.datasource.username";
	private static final String PROPERTY_KEY_PASSWORD = "spring.datasource.password";
	private static final String DB_NAME = "collection";
	private static final String INIT_SCRIPTS = "classpath:/init/*.sql"; //$NON-NLS-1$
	private static final String SQL_EXTENSION = ".sql"; //$NON-NLS-1$

	DataSource dataSource;

	@Autowired
	private ResourcePatternResolver resolver;

	@Autowired
	private Environment environment;

	private static Resource getUpdateScript(final Resource[] resources, final String name) {
		for (Resource res : resources) {
			if (res.getFilename().equals(name)) {
				return res;
			}
		}

		return null;
	}

	@Bean
	@ConfigurationProperties("datasource")
	public DataSource getDataSource() {
		try {
			String url = this.environment.getProperty(PROPERTY_KEY_URL);
			String username = this.environment.getProperty(PROPERTY_KEY_USERNAME);
			String password = this.environment.getProperty(PROPERTY_KEY_PASSWORD);

			dataSource = DataSourceBuilder.create().url(url).username(username).password(password).build();

			initDatabase(DB_NAME);
			return dataSource;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Config Datadase set Error!");
			log.error(e.toString());
			return null;
		}
	}

	private void initDatabase(final String db) {
		log.info("initDatabase: Init DataBase");
		if (this.dataSource == null) {
			throw new RuntimeException("initDatabase : dataSource is null");
		}

		Resource[] resources = null;
		try {
			resources = this.resolver.getResources(INIT_SCRIPTS);
		} catch (IOException e) {
			log.error(e.toString());
			throw new RuntimeException("initDatabase : dataSource is null");
		}
		for (Resource res : resources) {
			log.info("initDatabase: res = " + res.getFilename());
		}

		Resource scriptFile = getUpdateScript(resources, db + SQL_EXTENSION);
		this.executeScript(scriptFile);

		log.info("initDatabase: Init DataBase Done");
	}

	private void executeScript(final Resource res) {
		if (res == null) {
			throw new RuntimeException("executeScript : res is null");
		}

		String name = res.getFilename();
		log.info("executeScript : execute start = " + name);

		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(res);

		DatabasePopulatorUtils.execute(resourceDatabasePopulator, this.dataSource);
		log.info("executeScript : execute done = " + res.getFilename());
	}
}
