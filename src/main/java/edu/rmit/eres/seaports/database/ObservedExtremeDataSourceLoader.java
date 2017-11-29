/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
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
import edu.rmit.eres.seaports.model.data.ExtremeData;
import edu.rmit.eres.seaports.model.data.ExtremeVariable;
import edu.rmit.eres.seaports.model.datasource.ObservedExtremeDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class ObservedExtremeDataSourceLoader {
		
	/**
	 * Main method used to load  the CSIRO Data Source only.
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
		
		LoadObservedExtremeDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Observed Extreme Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadObservedExtremeDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		ObservedExtremeDataSourceLoader.LoadObservedExtremeData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		
		
		// Data Source
		ObservedExtremeDataSource dsExtreme = new ObservedExtremeDataSource("observedExtreme", "Observed Extreme", "Extreme Temperature, rainfall, wind, sea level expressed as the number of years before an extreme variable returns to a place.", null, null, displayTypes);
		
		// Parameters Climate Variable, with options Temperature, Wind Speed, Rainfall and Relative Humidity
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6>?</h6>",
				dsExtreme, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(variableParam);
		DataSourceParameterOption variableExtremeMaxTemp = new DataSourceParameterOption("Extreme maximum temperature", "Extreme maximum temperature", variableParam, 1);
		session.save(variableExtremeMaxTemp);
		DataSourceParameterOption variableExtremeRainfall = new DataSourceParameterOption("Extreme rainfall (> 200mm)", "Extreme rainfall", variableParam, 2);
		session.save(variableExtremeRainfall);
		DataSourceParameterOption variableExtremeWind = new DataSourceParameterOption("Extreme wind (> 80kt, > 148km/h)", "Extreme wind", variableParam, 3);
		session.save(variableExtremeWind);
		DataSourceParameterOption variableExtremeSeaLevel = new DataSourceParameterOption("Extreme sea level (> 2.4/2.8m)", "Extreme sea level", variableParam, 4);
		session.save(variableExtremeSeaLevel);
				
		// Availability of the data source for each seaport
		List<Seaport> seaports = new ArrayList<Seaport>();
		seaports.add((Seaport)(session.get(Seaport.class, "FJSUV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLTK")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJMAL")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLEV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJWAI")));
		
		/*eaports.add((Seaport)(session.get(Seaport.class, "PGGUR")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGATP")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGBUA")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGDAU")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGKVG")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGKIE")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGKIM")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGLAE")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGLOR")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGMAG")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGROR")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGPOM")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGRAB")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGVAI")));
		seaports.add((Seaport)(session.get(Seaport.class, "PGWWK")));*/
		
		dsExtreme.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 1))); // Category 1 = Observed climate & marine
		dsExtreme.setCategories(categories);
		
		
		session.save(dsExtreme);
	}
	

	/**
	 * Loads the Observed Extreme dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadObservedExtremeData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		//Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		// Climate Variables
		ExtremeVariable extremeTemp = new ExtremeVariable("Extreme maximum temperature", "T > 35&#8451", "Return periods of exceeding maximum temperature of 35&#8451", "year");
		ExtremeVariable extremeRF = new ExtremeVariable("Extreme rainfall", "RF > 200mm", "Return periods of exceeding daily extreme rainfall (200mm)", "year");
		ExtremeVariable extremeWind = new ExtremeVariable("Extreme wind", "WS > 80kt", "Return periods of exceeding daily extreme wind of 80 knot (148km/h)", "year");
		ExtremeVariable extremeSeaLevel = new ExtremeVariable("Extreme sea level", "Extreme SL", "Return periods of extreme sea level (2.4m/2.8m)", "year");
		session.save(extremeTemp);
		session.save(extremeRF);
		session.save(extremeWind);
		session.save(extremeSeaLevel);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2011-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		String sourceName = "Fiji Meteorological Services";
		int year = 2010;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeTemp, year, 2.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeTemp, year, 23.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeTemp, year, 25.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeTemp, year, 40.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeTemp, year, 77.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeTemp, year, 74.3, sourceName));
		
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeRF, year, 5.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeRF, year, 2.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeRF, year, 7.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeRF, year, 9.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeRF, year, 6.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeRF, year, 4.4, sourceName));
		
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeWind, year, 7.5, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeWind, year, 21.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeWind, year, 205.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeWind, year, 10.8, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeWind, year, 282.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeWind, year, 83.6, sourceName));
	
		session.save(new ExtremeData(creationDate, r1, "Suva Bay", extremeSeaLevel, year, 98.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lautoka", extremeSeaLevel, year, 80.2, sourceName));
	}
}