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
import edu.rmit.eres.seaports.model.datasource.ProjectedClimateExtremeDataSource;

/**
 * Class used to load Projected Climate Extreme Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class ProjectedClimateExtremeDataSourceLoader {
		
	/**
	 * Main method used to load the Projected Climate Extreme Data Source only.
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
		
		LoadProjectedClimateExtremeDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Projected Climate Extreme Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadProjectedClimateExtremeDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		ProjectedClimateExtremeDataSourceLoader.LoadProjectedClimateExtremeData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType graphDisplayType = (DisplayType)(session.get(DisplayType.class, 2)); // Graph
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		displayTypes.add(graphDisplayType);
		
		
		// Data Source
		ProjectedClimateExtremeDataSource dsExtreme = new ProjectedClimateExtremeDataSource("projectedClimateExtreme", "Projected Climate Extreme", "", null, null, displayTypes);
		
		// Parameters Climate Variable, with options Temperature, Wind Speed, Rainfall and Relative Humidity
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6>Extreme Temperature, rainfall, wind, sea level expressed as the number of years before an extreme variable is projected to return to a place in future time periods (2025, 2050, 2075, 2100).</h6>",
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
		
		/*seaports.add((Seaport)(session.get(Seaport.class, "PGGUR")));
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
		categories.add((ElementCategory)(session.get(ElementCategory.class, 2))); // Category 2 = Future climate & marine
		dsExtreme.setCategories(categories);
		
		
		session.save(dsExtreme);
	}
	

	/**
	 * Loads the Projected Climate Extreme dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadProjectedClimateExtremeData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		//Region r2 = (Region)(session.get(Region.class, 2)); // PNG
				
		// Climate Variables
		// SHOULD BE ALREADY CREATED BY THE OBSERVED EXTREME DATA SET
		ExtremeVariable extremeTemp = (ExtremeVariable)(session.get(ExtremeVariable.class, 1));
		ExtremeVariable extremeRF = (ExtremeVariable)(session.get(ExtremeVariable.class, 2));
		ExtremeVariable extremeWind = (ExtremeVariable)(session.get(ExtremeVariable.class, 3));
		ExtremeVariable extremeSeaLevel = (ExtremeVariable)(session.get(ExtremeVariable.class, 4));
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2011-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		String sourceName = "Fiji Meteorological Services";
		int year = 2025;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeTemp, year, 1.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeTemp, year, 13.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeTemp, year, 14.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeTemp, year, 24.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeTemp, year, 43.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeTemp, year, 39.4, sourceName));
		year = 2050;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeTemp, year, 1.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeTemp, year, 7.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeTemp, year, 8.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeTemp, year, 14.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeTemp, year, 23.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeTemp, year, 20.5, sourceName));
		year = 2075;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeTemp, year, 1.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeTemp, year, 4.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeTemp, year, 4.8, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeTemp, year, 7.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeTemp, year, 12.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeTemp, year, 9.9, sourceName));
		year = 2100;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeTemp, year, 1.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeTemp, year, 3.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeTemp, year, 3.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeTemp, year, 5.5, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeTemp, year, 7.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeTemp, year, 6.2, sourceName));
		
		
		year = 2025;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeRF, year, 6.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeRF, year, 3.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeRF, year, 8.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeRF, year, 11.5, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeRF, year, 6.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeRF, year, 4.9, sourceName));
		year = 2050;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeRF, year, 6.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeRF, year, 3.5, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeRF, year, 9.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeRF, year, 13.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeRF, year, 8.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeRF, year, 5.6, sourceName));
		year = 2075;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeRF, year, 8.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeRF, year, 4.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeRF, year, 11.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeRF, year, 17.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeRF, year, 9.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeRF, year, 6.5, sourceName));
		year = 2100;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeRF, year, 8.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeRF, year, 4.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeRF, year, 13.4, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeRF, year, 21.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeRF, year, 11.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeRF, year, 7.3, sourceName));
		
		
		year = 2025;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeWind, year, 6.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeWind, year, 18.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeWind, year, 159.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeWind, year, 9.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeWind, year, 215.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeWind, year, 68.4, sourceName));
		year = 2050;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeWind, year, 5.8, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeWind, year, 15.8, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeWind, year, 124.2, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeWind, year, 8.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeWind, year, 165.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeWind, year, 56.4, sourceName));
		year = 2075;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeWind, year, 5.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeWind, year, 13.5, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeWind, year, 95.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeWind, year, 7.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeWind, year, 124.6, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeWind, year, 45.9, sourceName));
		year = 2100;
		session.save(new ExtremeData(creationDate, r1, "Nadi Airport", extremeWind, year, 4.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Laucala Bay", extremeWind, year, 12.3, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Nabouwalu", extremeWind, year, 81.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Vunisea", extremeWind, year, 7.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lakeba", extremeWind, year, 104.9, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Rotuma", extremeWind, year, 40.4, sourceName));
	
		year = 2025;
		session.save(new ExtremeData(creationDate, r1, "Suva Bay", extremeSeaLevel, year, 18.7, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lautoka", extremeSeaLevel, year, 26.9, sourceName));
		year = 2050;
		session.save(new ExtremeData(creationDate, r1, "Suva Bay", extremeSeaLevel, year, 3.8, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lautoka", extremeSeaLevel, year, 9.1, sourceName));
		year = 2075;
		session.save(new ExtremeData(creationDate, r1, "Suva Bay", extremeSeaLevel, year, 1.1, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lautoka", extremeSeaLevel, year, 2.7, sourceName));
		year = 2100;
		session.save(new ExtremeData(creationDate, r1, "Suva Bay", extremeSeaLevel, year, 1.0, sourceName));
		session.save(new ExtremeData(creationDate, r1, "Lautoka", extremeSeaLevel, year, 1.0, sourceName));
	}
}