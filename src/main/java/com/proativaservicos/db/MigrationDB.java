package com.proativaservicos.db;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class MigrationDB {
	
	@Resource(lookup = "java:jboss/datasources/crmproativa")
    private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		
		System.out.println("Iniciando a migracao");
		
		Flyway flyway = Flyway.configure().dataSource(dataSource).load();
		
		flyway.baseline();
		
		flyway.migrate();
	}
	

}
