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
public class CsiroDataSourceLoader {

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
		
		LoadCsiroDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the CSIRO Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadCsiroDataSource(Session session)
	{
		// Loads the underlying Csiro dataset
		CsiroDataSourceLoader.LoadCsiroData(session);
		
		
		// Display Types offered by this data source
		DisplayType textDisplayType = (DisplayType)(session.get(DisplayType.class, 1));
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3));
		DisplayType pictureDisplayType = (DisplayType)(session.get(DisplayType.class, 5));
		
		List<DisplayType> displayTypesCsiro = new ArrayList<DisplayType>();
		displayTypesCsiro.add(textDisplayType);
		displayTypesCsiro.add(tableDisplayType);
		displayTypesCsiro.add(pictureDisplayType);
		
		
		// Data Source
		CsiroDataSource dsCsiro = new CsiroDataSource("csiro", "CSIRO", null, null, displayTypesCsiro);
		
		// Parameters Climate Variable, with options Temperature, Wind Speed, Rainfall and Relative Humidity
		DataSourceParameter csiroVariableParam = new DataSourceParameter("Variable", "<h6>MEAN TEMPERATURE</h6><p>Mean air temperature in degrees Celsius (&#176;C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>DAILY RELATIVE HUMIDITY</h6><p>Calculated at 2 m above ground and expressed in percent (%). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>WIND SPEED</h6><p>Mean wind speed, in metres per second (m/sec) as measured at 10m above the ground. Values are given as change from modelled baseline (1981-2000) climate.</p>",
				dsCsiro, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(csiroVariableParam);
		DataSourceParameterOption csiroVariableTemperature = new DataSourceParameterOption("Temperature", "Temperature", csiroVariableParam, 1);
		session.save(csiroVariableTemperature);
		DataSourceParameterOption csiroVariableWindSpeed = new DataSourceParameterOption("Wind Speed", "Wind Speed", csiroVariableParam, 2);
		session.save(csiroVariableWindSpeed);
		DataSourceParameterOption csiroVariableRainfall = new DataSourceParameterOption("Rainfall", "Rainfall", csiroVariableParam, 3);
		session.save(csiroVariableRainfall);
		DataSourceParameterOption csiroVariableHumidity = new DataSourceParameterOption("Relative Humidity", "Relative Humidity", csiroVariableParam, 4);
		session.save(csiroVariableHumidity);
		
		// Parameter Emission Scenario, with option Medium and High
		DataSourceParameter climateEmissionScnParam = new DataSourceParameter("Emission Scenario", "<p>The emission scenarios represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the 'A1 storyline' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p>Emission Scenario A1B: medium CO2 emissions, peaking around 2030</p><p>Emission Scenario A1FI: high CO2 emissions, increasing throughout the 21st century</p>", 
				dsCsiro, null, DataSourceParameter.Display.RADIO);
		session.save(climateEmissionScnParam);
		DataSourceParameterOption mediumEmScn = new DataSourceParameterOption("Medium (A1B)", "A1B", climateEmissionScnParam, 1);
		session.save(mediumEmScn);
		DataSourceParameterOption highEmScn = new DataSourceParameterOption("High (A1FI)", "A1FI", climateEmissionScnParam, 2);
		session.save(highEmScn);
		
		// Parameter Year, with options 2030, 2055 and 2070
		DataSourceParameter yearParam = new DataSourceParameter("Year", "<p>Time periods are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p> <p>Three future time periods are available: 2030, 2055 and 2070.</p>", 
				dsCsiro, null, DataSourceParameter.Display.DROPDOWN);
		session.save(yearParam);
		DataSourceParameterOption year2030 = new DataSourceParameterOption("2030", "2030", yearParam, 1);
		session.save(year2030);
		DataSourceParameterOption year2055 = new DataSourceParameterOption("2055", "2055", yearParam, 2);
		session.save(year2055);
		DataSourceParameterOption year2070 = new DataSourceParameterOption("2070", "2070", yearParam, 3);
		session.save(year2070);
		
		
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
		
		dsCsiro.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 3))); // Category 3 = Future Climate
		dsCsiro.setCategories(categories);
		
		
		session.save(dsCsiro);
	}
	

	/**
	 * Loads the CSIRO and CMAR dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadCsiroData(Session session)
	{
		Region r1 = (Region)(session.get(Region.class, 1));
		Region r2 = (Region)(session.get(Region.class, 2));
		Region r3 = (Region)(session.get(Region.class, 3));
		
		// Climate models
		ClimateModel csiro_mk3_5 = new ClimateModel("csiro_mk3_5", "CSIRO Mk3.5", "The CSIRO Mk3.5 climate model");
		session.save(csiro_mk3_5);
		ClimateModel mri_cgcm2_3_2 = new ClimateModel("mri_cgcm2_3_2", "MRI-CGCM2.3.2", "The MRI-CGCM 2.3.2 climate model");
		session.save(mri_cgcm2_3_2);
		ClimateModel ipsl_cm4 = new ClimateModel("ipsl_cm4", "IPSL-CM4", "The IPSL-CM4 climate model");
		session.save(ipsl_cm4);
		ClimateModel miroc_3_2_medres = new ClimateModel("miroc_3_2_medres", "MIROC 3.2 (medres)", "The Miroc 3.2 MedRes climate model"); 
		session.save(miroc_3_2_medres);
		ClimateModel cccma_cgcm3_1_t63 = new ClimateModel("cccma_cgcm3_1_t63", "CCCMA CGCM 3.1 T63", "The CCCMA CGCM 3.1 T63 climate model"); 
		session.save(cccma_cgcm3_1_t63);
		ClimateModel reference = new ClimateModel("reference", "(Reference)", "Reference climate model: considering there is no change"); 
		session.save(reference);
		
		// Climate emission scenarios
		ClimateEmissionScenario A1B = new ClimateEmissionScenario("A1B", "medium");
		session.save(A1B);
		ClimateEmissionScenario A1FI = new ClimateEmissionScenario("A1FI", "high");
		session.save(A1FI);
		ClimateEmissionScenario base = new ClimateEmissionScenario("Base", "No CO2 emissions increase");
		session.save(base);
		
		// Climate Variables
		CsiroVariable te = new CsiroVariable("Temperature", "T", "Forecasted hange of temperature from now", "&#8451;", "&#8451;");
		CsiroVariable ws = new CsiroVariable("Wind speed", "WS", "Forecasted wind speed", "km/h");
		CsiroVariable rf = new CsiroVariable("Rainfall", "RF", "Forecasted rain fall", "mm/y");
		CsiroVariable rh = new CsiroVariable("Relative humidity", "RH", "Forecasted relative humidity", "%");
		session.save(te);
		session.save(ws);
		session.save(rf);
		session.save(rh);
		
		DateFormat dateFormatter = new SimpleDateFormat("F");
		Date dateCsiroData = null;
		try {
			dateCsiroData = dateFormatter.parse("2013-02-01");
		} catch (ParseException e) {
			dateCsiroData = new Date();
		}
		/*Date dateCmarData = null;
		try {
			dateCmarData = dateFormatter.parse("2013-03-07");
		} catch (ParseException e) {
			dateCmarData = new Date();
		}*/
		
		// Baseline data for the 3 regions
		/*CsiroDataBaseline baselineData = new CsiroDataBaseline(dateCsiroData, r1, te, 16.1);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r1, ws, 4.6);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r1, rf, 1029.8);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r1, rh, 74.8);
		session.save(baselineData);*/
		
		/*baselineData = new CsiroDataBaseline(dateCsiroData, r2, te, 12.8);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r2, ws, 5.3);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r2, rf, 870.0);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r2, rh, 71.3);
		session.save(baselineData);*/
		
		/*baselineData = new CsiroDataBaseline(dateCsiroData, r3, te, 17.6);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r3, ws, 5.4);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r3, rf, 441.4);
		session.save(baselineData);
		baselineData = new CsiroDataBaseline(dateCsiroData, r3, rh, 59.2);
		session.save(baselineData);*/
		
		// Climate Parameters & Data
		
		
		
		// Region 1 - Temperature
		Double baselineValue = 16.1;
		
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2030, baselineValue, 1.3));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2055, baselineValue, 2.4));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2070, baselineValue, 3.0));
		
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2030, baselineValue, 1.2));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2055, baselineValue, 3.0));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2070, baselineValue, 4.1));
		
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, te, 2030, baselineValue, 1.0));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, te, 2055, baselineValue, 2.0));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, te, 2070, baselineValue, 2.5));
		
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, te, 2030, baselineValue, 1.0));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, te, 2055, baselineValue, 2.4));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, te, 2070, baselineValue, 3.4));
		
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2030, baselineValue, 0.7));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2055, baselineValue, 1.3));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2070, baselineValue, 1.7));
		
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2030, baselineValue, 0.7));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2055, baselineValue, 1.7));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2070, baselineValue, 2.3));
		
		
		// Region 1 - Wind Speed
		baselineValue = 4.6;
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2030,baselineValue,  0.00));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2055, baselineValue, 0.00));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2070, baselineValue, 0.10));
		

		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2030, baselineValue, 0.00));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2055, baselineValue, 0.10));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2070, baselineValue, 0.10));
		

		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, ws, 2030, baselineValue, -2.10));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, ws, 2055, baselineValue, -4.00));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, ws, 2070, baselineValue, -5.00));

		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2030, baselineValue, -2.00));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2055, baselineValue, -4.90));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2070, baselineValue, -6.80));
		
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2030, baselineValue, -1.30));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2055, baselineValue, -2.40));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2070, baselineValue, -3.00));

		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2030, baselineValue, -1.20));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2055, baselineValue, -2.90));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2070, baselineValue, -4.10));
		
		
		// Region 1 - Rainfall
		baselineValue = 1029.8;
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2030, baselineValue, -4.70));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2055, baselineValue, -8.80));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2070, baselineValue, -11.10));
		
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2030, baselineValue, -4.50));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2055, baselineValue, -10.90));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2070, baselineValue, -15.10));
		
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rf, 2030, baselineValue, -4.50));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rf, 2055, baselineValue, -8.40));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rf, 2070, baselineValue, -10.60));
		
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2030, baselineValue, -4.30));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2055, baselineValue, -10.40));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2070, baselineValue, -14.40));
		

		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2030, baselineValue, 4.10));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2055, baselineValue, 7.70));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2070, baselineValue, 9.70));

		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2030, baselineValue, 4.00));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2055, baselineValue, 9.50));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2070, baselineValue, 13.20));
		
		

		// Region 1 - Relative Humidity
		baselineValue = 74.8;
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2030, baselineValue, -1.30));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2055, baselineValue, -5.60));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2070, baselineValue, -7.10));
		
		
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2030, baselineValue, -2.90));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2055, baselineValue, -7.00));
		session.save(new CsiroData(dateCsiroData, r1, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2070, baselineValue, -9.70));
		
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rh, 2030, baselineValue, -0.20));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rh, 2055, baselineValue, -0.40));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1B, rh, 2070, baselineValue, -0.50));

		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2030, baselineValue, -0.20));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2055, baselineValue, -0.50));
		session.save(new CsiroData(dateCsiroData, r1, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2070, baselineValue, -0.70));
		
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2030, baselineValue, 1.20));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2055, baselineValue, 2.30));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2070, baselineValue, 2.90));
		
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2030, baselineValue, 1.20));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2055, baselineValue, 2.90));
		session.save(new CsiroData(dateCsiroData, r1, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2070, baselineValue, 4.00));
		
		// CMAR Data
		/*session.save(new CmarData(dateCmarData, params, slr, 2030, "-34.5,151.5,167;-33.5,152.5,176;-32.5,152.5,168;-31.5,15r1, ipsl_cm4, MOST_LIKELY, A1B6;-30.5,153.5,164;-29.5,153.5,163;-28.5,153.5,159", "sealevelrise-ecs-2030.png"));
		session.save(new CmarData(dateCmarData, params, slr, 2070, "-34.5,151.5,437;-33.5,152.5,447;-32.5,152.5,445;-31.5,153.5,439;-30.5,153.5,436;-29.5,153.5,434;-28.5,153.5,429", "sealevelrise-ecs-2070.png"));
		*/
		
		
		
		// Region 2 - Temperature
		baselineValue = 12.8;
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2030, baselineValue, 1.1));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2055, baselineValue, 2.0));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2070, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2030, baselineValue, 1.0));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2055, baselineValue, 2.5));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2070, baselineValue, 3.5));
		
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, te, 2030, baselineValue, 1.0));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, te, 2055, baselineValue, 1.9));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, te, 2070, baselineValue, 2.4));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, te, 2030, baselineValue, 1.0));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, te, 2055, baselineValue, 2.4));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, te, 2070, baselineValue, 3.3));
		
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2030, baselineValue, 0.6));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2055, baselineValue, 1.2));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, te, 2070, baselineValue, 1.5));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2030, baselineValue, 0.6));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2055, baselineValue, 1.5));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, te, 2070, baselineValue, 2.0));
		
		// Region 2 - Wind Speed
		baselineValue = 5.3;
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2030, baselineValue, -1.60));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2055, baselineValue, -3.10));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2070, baselineValue, -3.90));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2030, baselineValue, -1.60));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2055, baselineValue, -3.80));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2070, baselineValue, -5.30));

		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, ws, 2030, baselineValue, -2.70));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, ws, 2055, baselineValue, -5.00));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, ws, 2070, baselineValue, -6.40));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2030, baselineValue, -2.60));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2055, baselineValue, -6.30));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, ws, 2070, baselineValue, -8.70));

		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2030, baselineValue, 0.10));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2055, baselineValue, 0.20));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, ws, 2070, baselineValue, 0.20));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2030, baselineValue, 0.10));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2055, baselineValue, 0.20));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, ws, 2070, baselineValue, 0.30));
		
		// Region 2 - Rainfall
		baselineValue = 870.0;
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2030, baselineValue, -4.50));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2055, baselineValue, -8.50));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2070, baselineValue, -10.80));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2030, baselineValue, -4.40));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2055, baselineValue, -10.60));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2070, baselineValue, -14.70));

		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rf, 2030, baselineValue, -4.10));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rf, 2055, baselineValue, -7.70));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rf, 2070, baselineValue, -9.80));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2030, baselineValue, -4.00));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2055, baselineValue, -9.60));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rf, 2070, baselineValue, -13.30));
		
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2030, baselineValue, 2.00));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2055, baselineValue, 3.70));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rf, 2070, baselineValue, 4.60));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2030, baselineValue, 1.90));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2055, baselineValue, 4.60));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rf, 2070, baselineValue, 6.30));
		
		// Region 2 - Relative Humidity
		baselineValue = 71.3;
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2030, baselineValue, -2.20));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2055, baselineValue, -4.20));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2070, baselineValue, -5.30));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2030, baselineValue, -2.10));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2055, baselineValue, -5.20));
		session.save(new CsiroData(dateCsiroData, r2, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2070, baselineValue, -7.20));

		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rh, 2030, baselineValue, -0.80));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rh, 2055, baselineValue, -1.60));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1B, rh, 2070, baselineValue, -2.00));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2030, baselineValue, -0.80));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2055, baselineValue, -2.00));
		session.save(new CsiroData(dateCsiroData, r2, ipsl_cm4, MOST_LIKELY, A1FI, rh, 2070, baselineValue, -2.70));

		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2030, baselineValue, 0.20));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2055, baselineValue, 0.30));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1B, rh, 2070, baselineValue, 0.40));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2030, baselineValue, 0.20));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2055, baselineValue, 0.40));
		session.save(new CsiroData(dateCsiroData, r2, miroc_3_2_medres, COOLER_WETTER, A1FI, rh, 2070, baselineValue, 0.60));
		
		
		// CMAR Data
		/*session.save(new CmarData(dateCmarData, params, slr, 2030, "-39.5,146.5,128;-39.5,147.5,132;-38.5,148.5,156;-38.5,149.5,163;-37.5,150.5,180;-36.5,150.5,176;-35.5,151.5,172;-34.5,151.5,167", "sealevelrise-ssve-2030.png"));
		session.save(new CmarData(dateCmarData, params, slr, 2070, "-39.5,146.5,369;-39.5,147.5,375;-38.5,148.5,416;-38.5,149.5,426;-37.5,150.5,444;-36.5,150.5,440;-35.5,151.5,440;-34.5,151.5,437", "sealevelrise-ssve-2070.png"));
		*/
		
		// Region 3 - Temperature
		baselineValue = 17.6;
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2030, baselineValue, 1.1));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2055, baselineValue, 2.1));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, te, 2070, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2030, baselineValue, 1.1));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2055, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, te, 2070, baselineValue, 3.6));

		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, te, 2030, baselineValue, 0.7));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, te, 2055, baselineValue, 1.2));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, te, 2070, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, te, 2030, baselineValue, 0.6));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, te, 2055, baselineValue, 1.5));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, te, 2070, baselineValue, 2.1));

		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, te, 2030, baselineValue, 0.9));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, te, 2055, baselineValue, 1.7));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, te, 2070, baselineValue, 2.1));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, te, 2030, baselineValue, 0.9));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, te, 2055, baselineValue, 2.1));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, te, 2070, baselineValue, 2.9));
		
		// Region 3 - Wind Speed
		baselineValue = 5.4;
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2030, baselineValue, -1.5));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2055, baselineValue, -2.8));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, ws, 2070, baselineValue, -3.60));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2030, baselineValue, -1.50));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2055, baselineValue, -3.5));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, ws, 2070, baselineValue, -4.90));

		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, ws, 2030, baselineValue, 1.10));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, ws, 2055, baselineValue, 2.1));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, ws, 2070, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, ws, 2030, baselineValue, 1.1));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, ws, 2055, baselineValue, 2.6));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, ws, 2070, baselineValue, 3.6));

		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, ws, 2030, baselineValue, 0.3));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, ws, 2055, baselineValue, 0.5));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, ws, 2070, baselineValue, 0.6));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, ws, 2030, baselineValue, 0.3));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, ws, 2055, baselineValue, 0.6));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, ws, 2070, baselineValue, 0.9));
		
		// Region 3 - Rainfall
		baselineValue = 441.4;
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2030, baselineValue, -6.2));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2055, baselineValue, -11.6));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rf, 2070, baselineValue, -14.70));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2030, baselineValue, -6.00));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2055, baselineValue, -14.5));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rf, 2070, baselineValue, -20.10));

		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rf, 2030, baselineValue, -6.7));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rf, 2055, baselineValue, -12.6));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rf, 2070, baselineValue, -16.00));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rf, 2030, baselineValue, -6.5));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rf, 2055, baselineValue, -15.7));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rf, 2070, baselineValue, -21.70));

		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rf, 2030, baselineValue, 0.8));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rf, 2055, baselineValue, 1.4));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rf, 2070, baselineValue, 1.8));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rf, 2030, baselineValue, 0.7));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rf, 2055, baselineValue, 1.8));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rf, 2070, baselineValue, 2.5));
		
		// Region 3 - Relative Humidity
		baselineValue = 59.2;
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2030, baselineValue, -1.5));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2055, baselineValue, -2.9));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1B, rh, 2070, baselineValue, -3.70));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2030, baselineValue, -1.50));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2055, baselineValue, -3.6));
		session.save(new CsiroData(dateCsiroData, r3, csiro_mk3_5, HOTTER_DRIER, A1FI, rh, 2070, baselineValue, -5.00));

		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rh, 2030, baselineValue, -0.9));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rh, 2055, baselineValue, -1.8));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1B, rh, 2070, baselineValue, -2.3));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rh, 2030, baselineValue, -0.9));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rh, 2055, baselineValue, -2.2));
		session.save(new CsiroData(dateCsiroData, r3, miroc_3_2_medres, MOST_LIKELY, A1FI, rh, 2070, baselineValue, -3.1));
		
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rh, 2030, baselineValue, -0.2));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rh, 2055, baselineValue, -0.3));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1B, rh, 2070, baselineValue, -0.4));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rh, 2030, baselineValue, -0.2));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rh, 2055, baselineValue, -0.4));
		session.save(new CsiroData(dateCsiroData, r3, cccma_cgcm3_1_t63, COOLER_WETTER, A1FI, rh, 2070, baselineValue, -0.6));

		// CMAR Data
		/*session.save(new CmarData(dateCmarData, params, slr, 2030, "-27.5,113.5,125;-28.5,113.5,124;-29.5,114.5,121;-30.5,114.5,120;-31.5,115.5,119;-32.5,115.5,118;-33.5,114.5,118;-34.5,114.5,117;-34.5,115.5,117;-35.5,116.5,119;-35.5,117.5,119;-35.5,118.5,121;-34.5,119.5,118;-34.5,120.5,118;-34.5,121.5,123;-34.5,122.5,123;-34.5,123.5,124;-33.5,124.5,120", "sealevelrise-sswfw-2030.png"));
		session.save(new CmarData(dateCmarData, params, slr, 2070, "-27.5,113.5,370;-28.5,113.5,369;-29.5,114.5,365;-30.5,114.5,363;-31.5,115.5,361;-32.5,115.5,360;-33.5,114.5,360;-34.5,114.5,360;-34.5,115.5,361;-35.5,116.5,363;-35.5,117.5,363;-35.5,118.5,364;-34.5,119.5,354;-34.5,120.5,354;-34.5,121.5,361;-34.5,122.5,360;-34.5,123.5,360;-33.5,124.5,354", "sealevelrise-sswfw-2070.png"));
		*/
	}
}