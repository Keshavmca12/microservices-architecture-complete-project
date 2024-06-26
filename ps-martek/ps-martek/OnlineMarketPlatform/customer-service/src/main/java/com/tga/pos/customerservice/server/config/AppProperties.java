package com.tga.pos.customerservice.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@org.springframework.context.annotation.Configuration
@RefreshScope
public class AppProperties {

	@Value("${app.info}")
	private String appInfo;

	@Value("${app.version}")
	private String appVersion;

	@Value("${app.public_routes}")
	private String publicAPI;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private Long jwtExpiration;

	@Value("${app.db.host}")
	private String dbHost;

	@Value("${app.db.port}")
	private String dbPort;

	@Value("${app.data.database}")
	private String database;

	@Value("${app.kafka.bootstrap-servers}")
	private String kafkaBootstrapServer;


	/// Applying new MOngo Libraries

	@Value("${spring.data.mongodb.uri}")
	public String mongoUri;
	@Value("${spring.data.mongodb.database}")
	public String mongoDatabase;

	public String getMongoUri() {
		return mongoUri;
	}

	public void setMongoUri(String mongoUri) {
		this.mongoUri = mongoUri;
	}

	public String getMongoDatabase() {
		return mongoDatabase;
	}

	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}



	public String[] getPublicAPI() {
		List<String> apis = new ArrayList<String>();
		String split [] = this.publicAPI.split(",");
		for (int i = 0; i < split.length; i++) {
			apis.add(split[i]);
		}
		System.out.println("*******apis****"+apis);
		return apis.toArray(split);
	}

	public String getJwtSecret() {
		return this.jwtSecret;
	}

	public Long getJwtExpiration() {
		return this.jwtExpiration;
	}

	public String appInfo() {
		return this.appInfo + ", " + this.appVersion;
	}

	public String getDbHost() {
		return dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public String getDatabase() {
		return database;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public String getKafkaBootstrapServer() {
		return kafkaBootstrapServer;
	}

}
