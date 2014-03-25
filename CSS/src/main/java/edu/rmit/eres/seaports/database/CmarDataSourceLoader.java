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
public class CmarDataSourceLoader {
	
	/**
	 * 'Hotter & Drier' climate model string
	 */
	public static final String HOTTER_DRIER = "Hotter & Drier";
	
	/**
	 * 'Most Likely' climate model string
	 */
	public static final String MOST_LIKELY = "Most Likely";
	
	/**
	 * 'Cooler & Wetter' climate model string
	 */
	public static final String COOLER_WETTER = "Cooler & Wetter";
	
	/**
	 * 'Baseline' climate model string
	 */
	public static final String BASELINE = "Baseline";
	
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
		
		LoadCmarDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the CMAR Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadCmarDataSource(Session session)
	{
		// Loads the underlying Cmar dataset
		CmarDataSourceLoader.LoadCmarData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType mapDisplayType = (DisplayType)(session.get(DisplayType.class, 4)); // Map
		
		List<DisplayType> displayTypesCmar = new ArrayList<DisplayType>();
		displayTypesCmar.add(tableDisplayType);
		displayTypesCmar.add(mapDisplayType);
		
		
		// Data Source
		CmarDataSource dsCmar = new CmarDataSource("cmar", "CMAR", "", null, null, displayTypesCmar);
		
		
		DataSourceParameter cmarVariableParam = new DataSourceParameter("Variable", "<p><h6>Sea Level Rise</h6>Sea level data was derived from the CSIRO Marine and Atmospheric Research (CMAR). Data provided by CMAR was taken from the average of 17 climate model simulations for the medium (A1B) emissions scenario. This average was plotted around the Australian coastline to allow the identification of particular locations. Figures from these locations were then added to the globally averaged sea level projections for 2030 and 2070, using only the medium emissions scenario. These global projections included estimates for ice-sheet melt and were taken from the 50th percentile range.</p>", 
				dsCmar, null, DataSourceParameter.Display.DROPDOWN);
		session.save(cmarVariableParam);
		DataSourceParameterOption cmarVariableSeaLevelRise = new DataSourceParameterOption("Sea Level Rise", "Sea Level Rise", cmarVariableParam, 1);
		session.save(cmarVariableSeaLevelRise);
		
		// Parameters Climate Variable, with options Temperature, Wind Speed, Rainfall and Relative Humidity
		/*DataSourceParameter csiroVariableParam = new DataSourceParameter("Variable", "<h6>MEAN TEMPERATURE</h6><p>Mean air temperature in degrees Celsius (&#176;C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>DAILY RELATIVE HUMIDITY</h6><p>Calculated at 2 m above ground and expressed in percent (%). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>WIND SPEED</h6><p>Mean wind speed, in metres per second (m/sec) as measured at 10m above the ground. Values are given as change from modelled baseline (1981-2000) climate.</p>",
				dsCmar, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(csiroVariableParam);
		DataSourceParameterOption csiroVariableTemperature = new DataSourceParameterOption("Temperature", "Temperature", csiroVariableParam, 1);
		session.save(csiroVariableTemperature);
		DataSourceParameterOption csiroVariableWindSpeed = new DataSourceParameterOption("Wind Speed", "Wind Speed", csiroVariableParam, 2);
		session.save(csiroVariableWindSpeed);
		DataSourceParameterOption csiroVariableRainfall = new DataSourceParameterOption("Rainfall", "Rainfall", csiroVariableParam, 3);
		session.save(csiroVariableRainfall);
		DataSourceParameterOption csiroVariableHumidity = new DataSourceParameterOption("Relative Humidity", "Relative Humidity", csiroVariableParam, 4);
		session.save(csiroVariableHumidity);*/
		
		// Parameter Emission Scenario, with option Medium and High
		DataSourceParameter climateEmissionScnParam = new DataSourceParameter("Emission Scenario", "<p>The emission scenarios represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the 'A1 storyline' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p>Emission Scenario A1B: medium CO2 emissions, peaking around 2030</p><p>Emission Scenario A1FI: high CO2 emissions, increasing throughout the 21st century</p>", 
				dsCmar, null, DataSourceParameter.Display.RADIO);
		session.save(climateEmissionScnParam);
		DataSourceParameterOption mediumEmScn = new DataSourceParameterOption("Medium (A1B)", "A1B", climateEmissionScnParam, 1);
		session.save(mediumEmScn);
		/*DataSourceParameterOption highEmScn = new DataSourceParameterOption("High (A1FI)", "A1FI", climateEmissionScnParam, 2);
		session.save(highEmScn);*/
		
		// Parameter Year, with options 2030, 2055 and 2070
		DataSourceParameter yearParam = new DataSourceParameter("Year", "<p>Time periods are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p> <p>Three future time periods are available: 2030, 2055 and 2070.</p>", 
				dsCmar, null, DataSourceParameter.Display.DROPDOWN);
		session.save(yearParam);
		DataSourceParameterOption year2030 = new DataSourceParameterOption("2030", "2030", yearParam, 1);
		session.save(year2030);
		/*DataSourceParameterOption year2055 = new DataSourceParameterOption("2055", "2055", yearParam, 2);
		session.save(year2055);
		DataSourceParameterOption year2070 = new DataSourceParameterOption("2070", "2070", yearParam, 3);
		session.save(year2070);*/
		
		
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
		
		dsCmar.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 3))); // Category 3 = Future Climate
		dsCmar.setCategories(categories);
		
		
		session.save(dsCmar);
	}
	

	/**
	 * Loads the CSIRO and CMAR dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadCmarData(Session session)
	{
		Region r1 = (Region)(session.get(Region.class, 1));
		Region r2 = (Region)(session.get(Region.class, 2));
		Region r3 = (Region)(session.get(Region.class, 3));
		
		// Climate models
		//ClimateModel csiro_mk3_5 = new ClimateModel("csiro_mk3_5", "CSIRO Mk3.5", "The CSIRO Mk3.5 climate model");
		//session.save(csiro_mk3_5);
		//ClimateModel mri_cgcm2_3_2 = new ClimateModel("mri_cgcm2_3_2", "MRI-CGCM2.3.2", "The MRI-CGCM 2.3.2 climate model");
		//session.save(mri_cgcm2_3_2);
		ClimateModel ipsl_cm4 = (ClimateModel)(session.get(ClimateModel.class, 3));
		//ClimateModel ipsl_cm4 = new ClimateModel("ipsl_cm4", "IPSL-CM4", "The IPSL-CM4 climate model");
		//session.save(ipsl_cm4);
		ClimateModel miroc_3_2_medres = (ClimateModel)(session.get(ClimateModel.class, 4));
		//ClimateModel miroc_3_2_medres = new ClimateModel("miroc_3_2_medres", "MIROC 3.2 (medres)", "The Miroc 3.2 MedRes climate model"); 
		//session.save(miroc_3_2_medres);
		//ClimateModel cccma_cgcm3_1_t63 = new ClimateModel("cccma_cgcm3_1_t63", "CCCMA CGCM 3.1 T63", "The CCCMA CGCM 3.1 T63 climate model"); 
		//session.save(cccma_cgcm3_1_t63);
		//ClimateModel reference = new ClimateModel("reference", "(Reference)", "Reference climate model: considering there is no change"); 
		//session.save(reference);
		
		// Climate emission scenarios
		ClimateEmissionScenario A1B = (ClimateEmissionScenario)(session.get(ClimateEmissionScenario.class, 1));
		//ClimateEmissionScenario A1B = new ClimateEmissionScenario("A1B", "medium");
		//session.save(A1B);
		//ClimateEmissionScenario A1FI = new ClimateEmissionScenario("A1FI", "high");
		//session.save(A1FI);
		//ClimateEmissionScenario base = new ClimateEmissionScenario("Base", "No CO2 emissions increase");
		//session.save(base);
		
		// Climate Variables
		CsiroVariable slr = new CsiroVariable("Sea Level Rise", "SLR", "Forecasted sea level rise", "mm");
		session.save(slr);
		
		DateFormat dateFormatter = new SimpleDateFormat("F");
		
		Date dateCmarData = null;
		try {
			dateCmarData = dateFormatter.parse("2013-03-07");
		} catch (ParseException e) {
			dateCmarData = new Date();
		}
		
		// Region 1
		session.save(new CmarData(dateCmarData, r1, ipsl_cm4, MOST_LIKELY, A1B, slr, 2030, "-34.5,151.5,167;-33.5,152.5,176;-32.5,152.5,168;-31.5,156;-30.5,153.5,164;-29.5,153.5,163;-28.5,153.5,159"));
		session.save(new CmarData(dateCmarData, r1, ipsl_cm4, MOST_LIKELY, A1B, slr, 2070, "-34.5,151.5,437;-33.5,152.5,447;-32.5,152.5,445;-31.5,153.5,439;-30.5,153.5,436;-29.5,153.5,434;-28.5,153.5,429"));
		
		// Region 2
		session.save(new CmarData(dateCmarData, r2, ipsl_cm4, MOST_LIKELY, A1B, slr, 2030, "-39.5,146.5,128;-39.5,147.5,132;-38.5,148.5,156;-38.5,149.5,163;-37.5,150.5,180;-36.5,150.5,176;-35.5,151.5,172;-34.5,151.5,167"));
		session.save(new CmarData(dateCmarData, r2, ipsl_cm4, MOST_LIKELY, A1B, slr, 2070, "-39.5,146.5,369;-39.5,147.5,375;-38.5,148.5,416;-38.5,149.5,426;-37.5,150.5,444;-36.5,150.5,440;-35.5,151.5,440;-34.5,151.5,437"));
		
		// Region 3
		session.save(new CmarData(dateCmarData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, slr, 2030, "-27.5,113.5,125;-28.5,113.5,124;-29.5,114.5,121;-30.5,114.5,120;-31.5,115.5,119;-32.5,115.5,118;-33.5,114.5,118;-34.5,114.5,117;-34.5,115.5,117;-35.5,116.5,119;-35.5,117.5,119;-35.5,118.5,121;-34.5,119.5,118;-34.5,120.5,118;-34.5,121.5,123;-34.5,122.5,123;-34.5,123.5,124;-33.5,124.5,120"));
		session.save(new CmarData(dateCmarData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, slr, 2070, "-27.5,113.5,370;-28.5,113.5,369;-29.5,114.5,365;-30.5,114.5,363;-31.5,115.5,361;-32.5,115.5,360;-33.5,114.5,360;-34.5,114.5,360;-34.5,115.5,361;-35.5,116.5,363;-35.5,117.5,363;-35.5,118.5,364;-34.5,119.5,354;-34.5,120.5,354;-34.5,121.5,361;-34.5,122.5,360;-34.5,123.5,360;-33.5,124.5,354"));
	}
}