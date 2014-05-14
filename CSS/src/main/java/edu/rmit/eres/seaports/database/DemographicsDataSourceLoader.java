/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.database;

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
import edu.rmit.eres.seaports.model.data.DemographicsVariable;
import edu.rmit.eres.seaports.model.data.DemographicsData;
import edu.rmit.eres.seaports.model.datasource.DemographicsDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class DemographicsDataSourceLoader {
	
	/**
	 * Main method used to load  the Demographics Data Source only.
	 * On an existing database, this may duplicate data.
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
		
		LoadDemographicsDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Demographics Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadDemographicsDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		DemographicsDataSourceLoader.LoadDemographicsData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType graphDisplayType = (DisplayType)(session.get(DisplayType.class, 2)); // Graph
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		displayTypes.add(graphDisplayType);
		
		
		// Data Source
		DemographicsDataSource dsDemographics = new DemographicsDataSource("demographics", "Demographics", "", null, null, displayTypes);
		
		// Parameter Import/Export, letting the user choose between import trade data or export trade data
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6?</h6>",
				dsDemographics, null, DataSourceParameter.Display.DROPDOWN);
		session.save(variableParam);
		DataSourceParameterOption populationOption = new DataSourceParameterOption("Total Population", "Total Population", variableParam, 1);
		session.save(populationOption);
				
		
		// Availability of the data source for each seaport
		List<Seaport> seaports = new ArrayList<Seaport>();
		seaports.add((Seaport)(session.get(Seaport.class, "FJSUV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLTK")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJMAL")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLEV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJWAI")));
				
		dsDemographics.setSeaports(seaports);
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 3))); // Category 3 = Non-Climate context
		dsDemographics.setCategories(categories);
		
		session.save(dsDemographics);
	}
	

	/**
	 * Loads the Trade dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadDemographicsData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		//Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		// Variables
		DemographicsVariable pop = new DemographicsVariable("Total Population", "Total Population", "", "people", "");
		session.save(pop);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2011-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		// Fiji
		String sourceName = "Fiji Bureau of Statistics";
		
		
		// Population
		session.save(new DemographicsData(creationDate, r1, 1911, pop, 139541.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1921, pop, 157266.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1936, pop, 198379.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1946, pop, 259638.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1956, pop, 345727.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1976, pop, 588068.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1986, pop, 715375.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1996, pop, 755077.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2007, pop, 837271.0, sourceName));
	}
}