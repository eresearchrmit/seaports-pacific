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
import edu.rmit.eres.seaports.model.data.ClimateEmissionScenario;
import edu.rmit.eres.seaports.model.data.ProjectedClimateChangeData;
import edu.rmit.eres.seaports.model.data.ProjectedClimateChangeVariable;
import edu.rmit.eres.seaports.model.datasource.ObservedTrendDataSource;

/**
 * Class used to load Projected Climate Change Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class ProjectedClimateChangeDataSourceLoader {
	
	/**
	 * 'Annual' season for measure of data
	 */
	public static final String SEASON_ANNUAL = "Annual";
	
	/**
	 * String 'Warm' season for measure of data
	 */
	public static final String SEASON_WET = "Wet season (Nov-Apr)";
	
	/**
	 * String 'Cool' season for measure of data
	 */
	public static final String SEASON_DRY = "Dry season (May-Oct)";
	
	/**
	 * Main method used to load  the Projected Climate Change Data Source only.
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
		
		LoadProjectedClimateChangeDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Projected Climate Change Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadProjectedClimateChangeDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		ProjectedClimateChangeDataSourceLoader.LoadProjectedClimateChangeData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType pictureDisplayType = (DisplayType)(session.get(DisplayType.class, 5)); // Picture
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		displayTypes.add(pictureDisplayType);
				
		// Data Source
		ObservedTrendDataSource ds = new ObservedTrendDataSource("projectedClimateChange", "Projected Climate Change", "", null, null, displayTypes);
		
		// Parameter Climate Variable, with options Temperature, Rainfall and Relative Humidity
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6>TEMPERATURE/RAINFALL</h6><p>changes to annual mean surface air temperature (&#176;C) and rainfall (mm/day) relative to 1990 for 3 time periods (centred on 2030, 2055, 2090)  and 3 emissions scenarios (low, medium, high).</p>",
				ds, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(variableParam);
		DataSourceParameterOption variableTemp = new DataSourceParameterOption("Temperature", "Surface Air Temperature", variableParam, 1);
		session.save(variableTemp);
		DataSourceParameterOption variableRainfall = new DataSourceParameterOption("Rainfall", "Total Rainfall", variableParam, 2);
		session.save(variableRainfall);
		//DataSourceParameterOption variableSeaLevel = new DataSourceParameterOption("Sea Level Rise", "Sea Level Rise", variableParam, 3);
		//session.save(variableSeaLevel);
		//DataSourceParameterOption variableMaxTemp = new DataSourceParameterOption("Relative Humidity", "Relative Humidity", variableParam, 3);
		//session.save(variableMaxTemp);
				
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
		
		ds.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 2))); // Category 2 = Future climate & marine
		ds.setCategories(categories);
		
		session.save(ds);
	}
	

	/**
	 * Loads the FProjected Climate Change dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadProjectedClimateChangeData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		
		// Emission Scenarios
		ClimateEmissionScenario b1 = new ClimateEmissionScenario("B1",  "low");
		ClimateEmissionScenario a1b = new ClimateEmissionScenario("A1B",  "medium");
		ClimateEmissionScenario a2 = new ClimateEmissionScenario("A2",  "high");
		session.save(b1);
		session.save(a1b);
		session.save(a2);
		
		// Climate Variables
		ProjectedClimateChangeVariable temp = new ProjectedClimateChangeVariable("Surface Air Temperature", "T", "Projected multi-model mean changes in annual mean surface air temperature for 2030, 2055 and 2090, relative to 1990, under the A2 (high), A1B (medium) and B1 (low) emissions scenarios. All models agree on warming in all locations.", "&#8451;", "");
		ProjectedClimateChangeVariable rf = new ProjectedClimateChangeVariable("Total Rainfall", "RF", "Projected multi-modal mean changes in annual rainfall (mm/day) for 2030, 2055 and 2090, relative to 1990, under the A2 (high); A1B (medium) and B1 (low) emissions scenarios. Regions where at least 80% of the models agree on the direction of change are stippled.", "mm/day", "");
		//ProjectedClimateChangeVariable slr = new ProjectedClimateChangeVariable("Sea Level Rise", "SLR", "The projection (in cms) of sea level rise for the A1B (medium) emissions scenario in the Fiji region for the average rise around the future time period 2081-2100 relative to the past time period 1981 - 2000", "cm", "");
		//ObservedTrendVariable rh = new ObservedTrendVariable("Relative Humidity", "RH", "Trend in relative humidity", "%", "%");
		session.save(temp);
		session.save(rf);
		//session.save(slr);
		//session.save(rh);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2012-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		String sourceName = "Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Fiji Islands";
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, temp, b1, SEASON_ANNUAL, 0.6, 0.4, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, temp, b1, SEASON_ANNUAL, 1.0, 0.5, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, temp, b1, SEASON_ANNUAL, 1.4, 0.7, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, temp, a1b, SEASON_ANNUAL, 0.7, 0.5, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, temp, a1b, SEASON_ANNUAL, 1.4, 0.5, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, temp, a1b, SEASON_ANNUAL, 2.1, 0.8, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, temp, a2, SEASON_ANNUAL, 0.7, 0.3, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, temp, a2, SEASON_ANNUAL, 1.4, 0.3, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, temp, a2, SEASON_ANNUAL, 2.6, 0.6, sourceName));
		
		
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, b1, SEASON_ANNUAL, 3.0, 11.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, b1, SEASON_ANNUAL, 1.0, 10.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, b1, SEASON_ANNUAL, 2.0, 14.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a1b, SEASON_ANNUAL, 1.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a1b, SEASON_ANNUAL, 3.0, 14.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a1b, SEASON_ANNUAL, 3.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a2, SEASON_ANNUAL, 2.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a2, SEASON_ANNUAL, 4.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a2, SEASON_ANNUAL, 7.0, 15.0, sourceName));
		
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, b1, SEASON_WET, 5.0, 10.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, b1, SEASON_WET, 5.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, b1, SEASON_WET, 5.0, 18.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a1b, SEASON_WET, 3.0, 11.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a1b, SEASON_WET, 6.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a1b, SEASON_WET, 8.0, 19.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a2, SEASON_WET, 5.0, 14.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a2, SEASON_WET, 7.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a2, SEASON_WET, 14.0, 14.0, sourceName));
		
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, b1, SEASON_DRY, -1.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, b1, SEASON_DRY, -5.0, 14.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, b1, SEASON_DRY, -3.0, 17.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a1b, SEASON_DRY, -2.0, 17.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a1b, SEASON_DRY, -2.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a1b, SEASON_DRY, -4.0, 19.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2030, rf, a2, SEASON_DRY, -2.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2055, rf, a2, SEASON_DRY, -1.0, 18.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, rf, a2, SEASON_DRY, -1.0, 22.0, sourceName));
		
		//session.save(new ProjectedClimateChangeData(creationDate, r1, 2090, slr, a1b, SEASON_ANNUAL, 0.0, 0.0, sourceName));
		
		
		sourceName = "Climate Change in the Pacific: Scientific Assessment and New Research: Vol 5 Papua New Guinea";
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, temp, b1, SEASON_ANNUAL, 0.7, 0.4, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, temp, b1, SEASON_ANNUAL, 1.1, 0.5, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, temp, b1, SEASON_ANNUAL, 1.6, 0.6, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, temp, a1b, SEASON_ANNUAL, 0.8, 0.4, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, temp, a1b, SEASON_ANNUAL, 1.5, 0.5, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, temp, a1b, SEASON_ANNUAL, 2.4, 0.8, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, temp, a2, SEASON_ANNUAL, 0.7, 0.3, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, temp, a2, SEASON_ANNUAL, 1.5, 0.4, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, temp, a2, SEASON_ANNUAL, 2.8, 0.6, sourceName));
		
		
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, b1, SEASON_ANNUAL, 3.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, b1, SEASON_ANNUAL, 8.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, b1, SEASON_ANNUAL, 10.0, 14.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a1b, SEASON_ANNUAL, 3.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a1b, SEASON_ANNUAL, 7.0, 17.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a1b, SEASON_ANNUAL, 15.0, 20.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a2, SEASON_ANNUAL, 5.0, 9.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a2, SEASON_ANNUAL, 7.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a2, SEASON_ANNUAL, 15.0, 21.0, sourceName));
		
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, b1, SEASON_WET, 4.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, b1, SEASON_WET, 10.0, 13.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, b1, SEASON_WET, 12.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a1b, SEASON_WET, 5.0, 11.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a1b, SEASON_WET, 9.0, 17.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a1b, SEASON_WET, 16.0, 18.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a2, SEASON_WET, 6.0, 10.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a2, SEASON_WET, 8.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a2, SEASON_WET, 15.0, 20.0, sourceName));
		
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, b1, SEASON_DRY, 1.0, 15.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, b1, SEASON_DRY, 7.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, b1, SEASON_DRY, 10.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a1b, SEASON_DRY, 1.0, 16.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a1b, SEASON_DRY, 5.0, 20.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a1b, SEASON_DRY, 15.0, 24.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2030, rf, a2, SEASON_DRY, 4.0, 12.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2055, rf, a2, SEASON_DRY, 6.0, 17.0, sourceName));
		session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, rf, a2, SEASON_DRY, 15.0, 26.0, sourceName));
		
		//session.save(new ProjectedClimateChangeData(creationDate, r2, 2090, temp, a1b, SEASON_ANNUAL, 0.0, 0.0, sourceName));
	}
}