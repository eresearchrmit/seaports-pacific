/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;
import edu.rmit.eres.seaports.security.UserLoginService;

/**
 * Class used to load all the initial data of the Climate Smart Seaports application only.
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class DatabaseLoader {

	/**
	 * This password correspond to the SHA-256 hash of 'password'
	 */
	private static final String DEFAULT_PASSWORD = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
	
	/**
	 * Main method loading all the initial data in the database using Hibernate
	 * Running this is equivalent to importing the 'seaports_dump.sql' SQL script 
	 * in an empty 'seaports' database, minus the engineering model examples.
	 * @param args: no parameters
	 */
	public static void main(String[] args)
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		config.configure("hibernate.cfg.xml");
		new SchemaExport(config).create(true,true);

		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();	

		// Initial user accounts
		User user = new User("user", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_USER, "email@company.com", "User", "User");
		session.save(user);
		User admin = new User("admin", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_ADMINISTRATOR, "email@company.com", "Admin", "Admin");
		session.save(admin);

		// Loads the regions and seaports
		RegionsLoader.LoadRegions(session);
		
		// Loads the element categories
		CategoriesLoader.LoadCategories(session);
		
		// Loads the display types available
		DisplayTypesLoader.LoadDisplayTypes(session);
		
		// Loads the various data sources
		ObservedTrendDataSourceLoader.LoadObservedTrendDataSource(session);
		ObservedExtremeDataSourceLoader.LoadObservedExtremeDataSource(session);
		FutureTrendDataSourceLoader.LoadFutureTrendDataSource(session);
		FutureExtremeDataSourceLoader.LoadFutureExtremeDataSource(session);
		VulnerabilityDataSourceLoader.LoadVulnerabilityDataSource(session);
		CurrentClimateRiskDataSourceLoader.LoadCurrentClimateRiskDataSource(session);
		FutureClimateRiskDataSourceLoader.LoadFutureClimateRiskDataSource(session);
		
		session.getTransaction().commit();
		
		System.out.println("Created database schema with initial data");
		System.out.println("DONE !");
	}
}
