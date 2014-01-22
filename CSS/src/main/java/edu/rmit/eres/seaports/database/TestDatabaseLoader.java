/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.database;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;
import edu.rmit.eres.seaports.security.UserLoginService;

/**
 * Class used to load all the test data of the Climate Smart Seaports application in the test database.
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class TestDatabaseLoader {

	/**
	 * The password correspond to the SHA-256 hash of 'password'
	 */
	private static final String DEFAULT_PASSWORD = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
	
	/**
	 * Main method loading all the test data in the test database using Hibernate.
	 * Running this is equivalent to importing the 'seaports_test_dump.sql' SQL 
	 * script in an empty 'seaports' database, minus the engineering model examples.
	 * @param args: no parameters
	 */
	public static void main(String[] args)
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		config.configure("hibernate-test.cfg.xml");
		new SchemaExport(config).create(true,true);

		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();	

		
		// Regions
		Region r1 = new Region("Region 1", "176.284180,-15.891800 -178.046875,-15.891800 -178.046875,-20.070571 176.284180,-20.070571");
		Region r2 = new Region("Region 2", "");
		session.save(r1);
		session.save(r2);
		
		// Seaports
		Seaport port1 = new Seaport("CODE1", "Port 1", r1, "City 1");
		Seaport port2 = new Seaport("CODE2", "Port 2", r1, "City 2");
		Seaport port3 = new Seaport("CODE3", "Port 3", r1, "City 3");
		session.save(port1);
		session.save(port2);
		session.save(port3);
				
		// Loads the element categories
		CategoriesLoader.LoadCategories(session);
		
		// Loads the display types available
		DisplayTypesLoader.LoadDisplayTypes(session);
		
		// Loads the various data sources
		CsiroDataSourceLoader.LoadCsiroDataSource(session);
		
		
				
		// Retrieves the created element categories
		ElementCategory nonClimateCategory = (ElementCategory)(session.get(ElementCategory.class, 1));
		ElementCategory observedClimateCategory = (ElementCategory)(session.get(ElementCategory.class, 2));
		ElementCategory futureClimateCategory = (ElementCategory)(session.get(ElementCategory.class, 3));
		ElementCategory applicationsCategory = (ElementCategory)(session.get(ElementCategory.class, 4));
		// Retrieves the created  data sources
		DataSource dsCsiro = (DataSource)(session.get(DataSource.class, 1)); // ID 1 = CSIRO
		// Retrieves the created display types
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // ID 3 = Table
		// Retrieves the created parameter options
		DataSourceParameterOption csiroVariableTemperature = (DataSourceParameterOption)(session.get(DataSourceParameterOption.class, 1)); // ID 3 = Temperature
		DataSourceParameterOption mediumEmScn = (DataSourceParameterOption)(session.get(DataSourceParameterOption.class, 5)); // ID 5 = Medium Emission Scenario
		DataSourceParameterOption year2055 = (DataSourceParameterOption)(session.get(DataSourceParameterOption.class, 8)); // ID 8 = Year 2055
		
		
		
		
		
		// Add Users
		User u1 = new User("testuser1", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		User u2 = new User("testuser2", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser2", "testuser2");
		User u3 = new User("testuser3", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser3", "testuser3");
		User u4 = new User("testuser4", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser4", "testuser4");
		User u5 = new User("testadmin1", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_ADMINISTRATOR, "email@company.com", "testadmin1", "testadmin1");
		User u6 = new User("testadmin2", DEFAULT_PASSWORD, true, true, UserLoginService.ROLE_ADMINISTRATOR, "email@company.com", "testadmin2", "testadmin2");
		session.save(u1);
		session.save(u2);
		session.save(u3);
		session.save(u4);
		session.save(u5);
		session.save(u6);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try { date = dateFormatter.parse("2013-04-10"); } catch (ParseException e) {}
		
		// Add Reports
		Report user1wb = new Report("User 1 Workboard", "Purpose", "private", u1, port1, null);
		user1wb.setCreationDate(date);
		
		Report user1us1 = new Report("User 1 Story 1", "Purpose", "private", u1, port2, null);
		user1us1.setCreationDate(date);
		
		Report user1us2 = new Report("User 1 Story 2 (Public)", "Purpose", "public", u1, port1, null);
		user1us2.setCreationDate(date);
		
		Report user1us3 = new Report("User 1 Story 3 (Published)", "Purpose", "public", u1, port1, null);
		user1us3.setCreationDate(date);
		user1us3.setElements(new ArrayList<Element>());
		
		Report user2wb = new Report("User 2 Workboard (Empty)", "Purpose", "private", u2, port2, null);
		user2wb.setCreationDate(date);
		
		Report user2us = new Report("User 2 Story (Empty)", "Purpose", "private", u2, port1, null);
		user2us.setCreationDate(date);
		
		session.save(user1wb);
		session.save(user1us1);
		session.save(user1us2);
		session.save(user1us3);
		session.save(user2wb);
		session.save(user2us);
	
		
		
		// Add Elements in the report
	    date = new Date();
	    String content = "This, is, a, test, for, CSV, Element";
	    InputElement ie1 = new InputElement(date, "Test 1", nonClimateCategory, user1wb, true, 1, "text/csv", content.getBytes());
	    session.save(ie1);
	    
	    content = "This is the second test";
	    InputElement ie2 = new InputElement(date, "Test 2", observedClimateCategory, user1wb, true, 1, "text/plain", content.getBytes());
	    session.save(ie2);
	    
	    content = "This is the third test";
	    InputElement ie3 = new InputElement(date, "Test 3", futureClimateCategory, user1wb, true, 1, "text/plain", content.getBytes());
	    session.save(ie3);
	    
	    content = "Report's Data Element content test";
	    InputElement ie4 = new InputElement(date, "Test 4", nonClimateCategory, user1us1, true, 1, "text/plain", content.getBytes());
	    session.save(ie4);
	    
	    InputElement text1 = new InputElement(date, "Comment text 1", nonClimateCategory, user1us1, true, 2, "text/plain", content.getBytes());
	    session.save(text1);
	    
	    InputElement ie5 = new InputElement(date, "Test 5", applicationsCategory, user1us1, true, 3, "text/plain", content.getBytes());
	    session.save(ie5);
	    
	    content = "This is a second text comment";
	    InputElement text2 = new InputElement(date, "Comment text 2", futureClimateCategory, user1us1, true, 4, "text/plain", content.getBytes());
	    session.save(text2);
	    
	    InputElement ie6 = new InputElement(date, "Test 6", applicationsCategory, user1us1, true, 5, "text/plain", content.getBytes());
	    session.save(ie6);
		

		// CSIRO Data Element
		List<DataSourceParameterOption> selectedOptions = new ArrayList<DataSourceParameterOption>();
		selectedOptions.add(csiroVariableTemperature);
		selectedOptions.add(mediumEmScn);
		selectedOptions.add(year2055);
	    DataElement deCsiro = new DataElement(date, "CSIRO Data Element Test", futureClimateCategory, user1wb, true, 1, dsCsiro, selectedOptions, tableDisplayType);
	    session.save(deCsiro);
	    
	    
	    try {
			ReportPublication pub = new ReportPublication(user1us3);
			session.save(pub);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		// Commit all the transaction
		session.getTransaction().commit();
		
		System.out.println("Created TEST database schema with test data");
		System.out.println("DONE !");
	}
}
