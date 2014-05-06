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

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class ObservedTrendDataSourceLoader {
	
	/**
	 * 'Annual' season for measure of data
	 */
	public static final String SEASON_ANNUAL = "annual";
	
	/**
	 * String 'Warm' season for measure of data
	 */
	public static final String SEASON_WARM = "warm season";
	
	/**
	 * String 'Cool' season for measure of data
	 */
	public static final String SEASON_COOL = "cool season";
	
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
		
		LoadObservedTrendDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the CSIRO Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadObservedTrendDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		ObservedTrendDataSourceLoader.LoadObservedTrendData(session);
		
		
		// Display Types offered by this data source
		DisplayType textDisplayType = (DisplayType)(session.get(DisplayType.class, 1)); // Text
		DisplayType pictureDisplayType = (DisplayType)(session.get(DisplayType.class, 5)); // Picture
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(textDisplayType);
		displayTypes.add(pictureDisplayType);
		
		
		// Data Source
		ObservedTrendDataSource dsTrend = new ObservedTrendDataSource("observedTrend", "Observed Trend", "", null, null, displayTypes);
		
		// Parameters Climate Variable, with options Temperature, Wind Speed, Rainfall and Relative Humidity
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6>MIN/MAX TEMPERATURE</h6><p>Minimum/Maximum air temperature in degrees Celsius (&#176;C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p></p><h6>SEA LEVEL</h6><p>???.</p>",
				dsTrend, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(variableParam);
		DataSourceParameterOption variableMinTemp = new DataSourceParameterOption("Minimum Temperatures", "Minimum Temperatures", variableParam, 1);
		session.save(variableMinTemp);
		DataSourceParameterOption variableMaxTemp = new DataSourceParameterOption("Maximum Temperatures", "Maximum Temperatures", variableParam, 2);
		session.save(variableMaxTemp);
		DataSourceParameterOption variableRainfall = new DataSourceParameterOption("Rainfall", "Rainfall", variableParam, 3);
		session.save(variableRainfall);
		DataSourceParameterOption variableSeaLevel = new DataSourceParameterOption("Sea Level", "Sea Level", variableParam, 4);
		session.save(variableSeaLevel);
				
		// Availability of the data source for each seaport
		List<Seaport> seaports = new ArrayList<Seaport>();
		seaports.add((Seaport)(session.get(Seaport.class, "FJSUV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLTK")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJMAL")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLEV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJWAI")));
		
		seaports.add((Seaport)(session.get(Seaport.class, "PGGUR")));
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
		seaports.add((Seaport)(session.get(Seaport.class, "PGWWK")));
		
		dsTrend.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 1))); // Category 1 = Observed climate & marine
		dsTrend.setCategories(categories);
		
		
		session.save(dsTrend);
	}
	

	/**
	 * Loads the CSIRO and CMAR dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadObservedTrendData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		//Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		// Climate Variables
		ObservedTrendVariable minTe = new ObservedTrendVariable("Minimum Temperatures", "MinTemp", "Trend in minimum temperatures", "&#8451;", "&#8451;");
		ObservedTrendVariable maxTe = new ObservedTrendVariable("Maximum Temperatures", "MaxTemp", "Trend in maximum temperatures", "&#8451;", "&#8451;");
		ObservedTrendVariable rf = new ObservedTrendVariable("Rainfall", "RF", "Trend in rainfall", "mm/y", "%");
		ObservedTrendVariable slr = new ObservedTrendVariable("Sea Level", "SLR", "Short-term changes in sea level", "%");
		session.save(minTe);
		session.save(maxTe);
		session.save(rf);
		session.save(slr);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		Date periodStartDate = null;
		Date periodStartDate2 = null;
		Date periodEndDate = null;
		try {
			creationDate = dateFormatter.parse("2011-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		try {
			periodStartDate = dateFormatter.parse("1961-01-01");
		} catch (ParseException e) {
			periodStartDate = new Date();
		}
		try {
			periodStartDate2 = dateFormatter.parse("1993-01-01");
		} catch (ParseException e) {
			periodStartDate2 = new Date();
		}
		try {
			periodEndDate = dateFormatter.parse("2010-01-01");
		} catch (ParseException e) {
			periodEndDate = new Date();
		}
		
		String sourceName = "Fiji Meteorological Services";
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, minTe, SEASON_ANNUAL, 0.6, null, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, minTe, SEASON_WARM, 0.7, null, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, minTe, SEASON_COOL, 1.0, null, sourceName));
		
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, maxTe, SEASON_ANNUAL, 1.1, null, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, maxTe, SEASON_WARM, 1.2, null, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, maxTe, SEASON_COOL, 1.0, null, sourceName));
		
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, rf, SEASON_ANNUAL, 0.65, 0.03, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, rf, SEASON_WARM, 1.3, 0.08, sourceName));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodEndDate, rf, SEASON_COOL, 0.76, 0.11, sourceName));
		
		String sourceName2 = "Climate Change in the Pacific: Scientific Assessment and New Research: Vol 2 Country Reports, Ch 5 Fiji Islands";
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodStartDate2, slr, SEASON_ANNUAL, 0.6, null, sourceName2));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodStartDate2, slr, SEASON_WARM, 0.6, null, sourceName2));
		session.save(new ObservedTrendData(creationDate, r1, periodStartDate, periodStartDate2, slr, SEASON_COOL, 0.6, null, sourceName2));
	}
}