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

		// Regions & Ports
		Region r1 = new Region("Fiji", "176.284180,-15.891800 -178.046875,-15.891800 -178.046875,-20.070571 176.284180,-20.070571");
		Region r2 = new Region("New Caledonia", "");
		Region r3 = new Region("Vaunuatu", "");
		Region r4 = new Region("Tonga", "");
		Region r5 = new Region("American Samoa", "");
		Region r6 = new Region("Wallis and Futuna", "");
		Region r7 = new Region("Solomon Islands", "");
		Region r8 = new Region("Papua New Guinea", "");
		session.save(r1);
		session.save(r2);
		session.save(r3);
		session.save(r4);
		session.save(r5);
		session.save(r6);
		session.save(r7);
		session.save(r8);
		
		Seaport port1 = new Seaport("FJLEV", "Levuka", r1, "Levuka");
		Seaport port2 = new Seaport("FJSUV", "Suva", r1, "Suva");
		Seaport port3 = new Seaport("FJLTK", "Lautoka", r1, "Lautoka");
		Seaport port4 = new Seaport("FJSVU", "Savu Savu", r1, "Savu Savu");
		Seaport port5 = new Seaport("FJLBS", "Lambasa", r1, "Lambasa");
		
		session.save(port1);
		session.save(port2);
		session.save(port3);
		session.save(port4);
		session.save(port5);
		
		/*CsiroDataLoader.LoadCsiroData(session);
		EngineeringModelDataLoader.LoadEngineeringModelData(session);
		BomDataLoader.LoadBomData(session);
		AcornSatDataLoader.LoadAcornSatData(session);
		AbsDataLoader.LoadAbsData(session);
		BitreDataLoader.LoadBitreData(session);*/
		
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
		Report user1wb = new Report("User 1 Workboard", "Purpose", "active", "private", u1, port3, null);
		user1wb.setCreationDate(date);
		
		Report user1us1 = new Report("User 1 Story 1", "Purpose", "passive", "private", u1, port5, null);
		user1us1.setCreationDate(date);
		
		Report user1us2 = new Report("User 1 Story 2 (Public)", "Purpose", "passive", "public", u1, port3, null);
		user1us2.setCreationDate(date);
		
		Report user1us3 = new Report("User 1 Story 3 (Published)", "Purpose", "published", "public", u1, port3, null);
		user1us3.setCreationDate(date);
		user1us3.setPublishDate(date);
		user1us3.setElements(new ArrayList<Element>());
		
		Report user2wb = new Report("User 2 Workboard (Empty)", "Purpose", "active", "private", u2, port5, null);
		user2wb.setCreationDate(date);
		
		Report user2us = new Report("User 2 Story (Empty)", "Purpose", "passive", "private", u2, port3, null);
		user2us.setCreationDate(date);
		
		session.save(user1wb);
		session.save(user1us1);
		session.save(user1us2);
		session.save(user1us3);
		session.save(user2wb);
		session.save(user2us);

		
		// Display Types
		DisplayType textDisplayType = new DisplayType("Text");
		DisplayType graphDisplayType = new DisplayType("Graph");
		DisplayType mapDisplayType = new DisplayType("Map");
		DisplayType tableDisplayType = new DisplayType("Table");
		session.save(textDisplayType);
		session.save(graphDisplayType);
		session.save(mapDisplayType);
		session.save(tableDisplayType);
		
		List<Seaport> seaportsDS1 = new ArrayList<Seaport>();
		seaportsDS1.add(port1);
		seaportsDS1.add(port2);
		seaportsDS1.add(port3);
		seaportsDS1.add(port4);
		seaportsDS1.add(port5);
		
		// Loads the various datasets
		CsiroDataLoader.LoadCsiroData(session);
		
		
		// Data Source 1
		List<DisplayType> displayTypesCsiro = new ArrayList<DisplayType>();
		displayTypesCsiro.add(textDisplayType);
		displayTypesCsiro.add(graphDisplayType);
		displayTypesCsiro.add(mapDisplayType);
		displayTypesCsiro.add(tableDisplayType);
		
		CsiroDataSource dsCsiro = new CsiroDataSource("CSIRO", null, null, displayTypesCsiro);
		
		DataSourceParameter csiroVariableParam = new DataSourceParameter("Variable", "<h6>MEAN TEMPERATURE</h6><p>Mean air temperature in degrees Celsius (°C) as measured at 2 m above ground. Values are given as change from modelled baseline (1981-2000) climate.</p><h6>RAINFALL</h6><p>Mean rainfall in millimetres (mm). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>DAILY RELATIVE HUMIDITY</h6><p>Calculated at 2 m above ground and expressed in percent (%). Values are given as change from modelled baseline (1981-2000) climate.</p><h6>WIND SPEED</h6><p>Mean wind speed, in metres per second (m/sec) as measured at 10m above the ground. Values are given as change from modelled baseline (1981-2000) climate.</p>",
				dsCsiro, null, DataSourceParameter.Display.DROPDOWN);		
		DataSourceParameterOption csiroVariableTemperature = new DataSourceParameterOption("Temperature", "Temperature", csiroVariableParam, 1);
		DataSourceParameterOption csiroVariaableWindSpeed = new DataSourceParameterOption("Wind Speed", "Wind Speed", csiroVariableParam, 2);
		DataSourceParameterOption csiroVariableRainfall = new DataSourceParameterOption("Rainfall", "Rainfall", csiroVariableParam, 3);
		DataSourceParameterOption csiroVariableHumidity = new DataSourceParameterOption("Relative Humidity", "Relative Humidity", csiroVariableParam, 4);
		
		DataSourceParameter climateEmissionScnParam = new DataSourceParameter("Emission Scenario", "<p>The emission scenarios represent the future development of greenhouse gas emissions and are based on assumptions about economic, technological and population growth. The two emissions scenarios that are available here are from the 'A1 storyline' and were developed by the IPCC Special Report on Emissions Scenarios (SRES).</p><p>As a general guide:</p><p>Emission Scenario A1B: medium CO2 emissions, peaking around 2030</p><p>Emission Scenario A1FI: high CO2 emissions, increasing throughout the 21st century</p>", dsCsiro, null, DataSourceParameter.Display.RADIO);
		DataSourceParameterOption mediumEmScn = new DataSourceParameterOption("Medium (A1B)", "A1B", climateEmissionScnParam, 1);
		DataSourceParameterOption highEmScn = new DataSourceParameterOption("High (A1FI)", "A1FI", climateEmissionScnParam, 2);
		
		DataSourceParameter yearParam = new DataSourceParameter("Year", "<p>Time periods are expressed relative to the 1981-2000 baseline period and are centred on a given decade. For example, the 2030s time period refers to the period 2020-2039.</p> <p>Three future time periods are available: 2030, 2055 and 2070.</p>", dsCsiro, null, DataSourceParameter.Display.DROPDOWN);
		DataSourceParameterOption year2030 = new DataSourceParameterOption("2030", "2030", yearParam, 1);
		DataSourceParameterOption year2055 = new DataSourceParameterOption("2055", "2055", yearParam, 2);
		DataSourceParameterOption year2070 = new DataSourceParameterOption("2070", "2070", yearParam, 3);
		
		
		session.save(dsCsiro);
		session.save(csiroVariableParam);
		session.save(csiroVariableTemperature);
		session.save(csiroVariaableWindSpeed);
		session.save(csiroVariableRainfall);
		session.save(csiroVariableHumidity);
		session.save(climateEmissionScnParam);
		session.save(mediumEmScn);
		session.save(highEmScn);
		session.save(yearParam);
		session.save(year2030);
		session.save(year2055);
		session.save(year2070);
		
		
		// Availability of data sources for each seaport
		List<DataSource> dataSourcesAvailable = new ArrayList<DataSource>();
		dataSourcesAvailable.add(dsCsiro);
		port1.setDataSourcesAvailable(dataSourcesAvailable);
		session.save(port1);
		
		dataSourcesAvailable = new ArrayList<DataSource>();
		dataSourcesAvailable.add(dsCsiro);
		port2.setDataSourcesAvailable(dataSourcesAvailable);
		session.save(port2);
		
		dataSourcesAvailable = new ArrayList<DataSource>();
		dataSourcesAvailable.add(dsCsiro);
		port3.setDataSourcesAvailable(dataSourcesAvailable);
		session.save(port3);
		
		dataSourcesAvailable = new ArrayList<DataSource>();
		dataSourcesAvailable.add(dsCsiro);
		port4.setDataSourcesAvailable(dataSourcesAvailable);
		session.save(port4);
		
		dataSourcesAvailable = new ArrayList<DataSource>();
		dataSourcesAvailable.add(dsCsiro);
		port5.setDataSourcesAvailable(dataSourcesAvailable);
		session.save(port5);		
		
		
		// Element Categories
		ElementCategory nonClimateCategory = new ElementCategory("Non-climate context", 
				"<h6>Non-Climate Context</h6><p class=\"justified\">Non-climate data helps set the operational context of ports. It also provides a starting point for consideration of possible impacts of non-climate factors into the future. For example, population growth along the coast can constrain a port's ability to expand in the future, and to retreat as sea level rise and climatic conditions change. National population growth can also be a driver of increased activity at container import ports, which may lead to congestion problems. <p class=\"justified\">This tab identifies particular non-climate-related information.  It includes trade and population data. Note that only limited data may be available for some ports. <p class=\"justified\">Two publicly available data sets are offered within this section. These are urban pressure data derived from the Australian Bureau of Statistics (ABS), and freight data from Ports Australia and individual ports' published annual reports. <p class=\"justified\">Recognising that non-climate information will be gathered by ports themselves, this section allows for ports to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. <p class=\"justified\">Users can select from three main data sources on this tab:</p> <p class=\"justified\"><strong>\"ABS\"</strong> data, which provides Population change data;</p><p class=\"justified\"><strong>\"Ports Australia data\"</strong> which provides data on:</p><ul><li>Freight throughput by cargo type,</li><li>Commercial vessel calls by type, and</li> <li>Export commodities by type</li></ul><p class=\"justified\"><strong>\"Custom file\"</strong> data which can be text and/or images provided by the port, relating to their current, non-climate context.</p><a href=\"/public/guidelines#non-climate\" target=\"_blank\">Read more...</a>", 
				"<h6>Non-Climate Context</h6><p class=&quot;justified&quot;>This tab identifies particular non-climate-related information.  It includes trade and population data. Note that only limited data may be available for some ports. <p class=&quot;justified&quot;>Two publicly available data sets are offered within this section. These are urban pressure data derived from the Australian Bureau of Statistics (ABS), and freight data from Ports Australia and individual ports' published annual reports.<p class=&quot;justified&quot;>Recognising that non-climate information will be gathered by ports themselves, this section allows for ports to upload port-specific files and information regarding organisational objectives, current risks, or data on throughput volume or the types of activity that characterise the port. <p class=&quot;justified&quot;>Users can select from three main data sources on this tab:</p><p class=&quot;justified&quot;><strong>&quot;ABS&quot;</strong> data, which provides Population change data;</p><p class=&quot;justified&quot;><strong>&quot;Ports Australia data&quot;</strong> which provides data on:</p><ul><li>Freight throughput by cargo type,</li><li>Commercial vessel calls by type, and</li><li>Export commodities by type</li></ul><p class=&quot;justified&quot;><strong>&quot;Custom file&quot;</strong> data which can be text and/or images provided by the port, relating to their current, non-climate context.</p><a href=&quot;/public/guidelines#non-climate&quot; target=&quot;_blank&quot;>Read more...</a>");
		ElementCategory observedClimateCategory = new ElementCategory("Observed climate",
				"<h6>Observed Climate</h6><p class=\"justified\">Climate change is already increasing the intensity and frequency of many extreme weather events. Extreme events occur naturally, however, climate change is influencing these events and record-breaking weather is becoming more common.</p> <p class=\"justified\">This tab sets the historical and current context of climate and marine observations for ports, to assist ports understand their current climate context. It includes data publicly available from the Bureau of Meteorology and the CSIRO.</p> Three types of data have been selected: <ul><li>national trends for temperature and rainfall;</li><li>global and national trends for sea-level rise; and</li><li>specific weather station data (including examples of extreme weather events).</li></ul><p>Users select from two main data sources in this tab:</p><strong>\"CSIRO and BoM trend data\"</strong> which provides national or global data on: <ul>	<li>Trend in mean temperatures,</li><li>Trend in maximum temperatures,</li><li>Trend in total annual rainfall,</li><li>Long-term sea level rise measurements and</li> <li>Shorter term changes in sea level.</li></ul><strong>\"ACORN-SAT\"</strong> which provides data from specific weather stations for:<ul><li>Mean measurements (annual mean surface temperature, annual mean rainfall, annual mean relative humidity and annual mean wind speed)</li><li>Extreme measurements (highest temperature, highest daily rainfall, maximum wind gust)</li></ul><br /><p>All data has been chosen to support the data in the Future Climate section. Please note, however, that the Future Climate is based on modelled data and is not directly comparable to the factual data sourced in this section.</p><br /><p class=\"justified\">For further information about extreme weather in Australia, head to the Bureau of Meteorology's \"<a href=\"http://www.bom.gov.au/climate/change/index.shtml#tabs=Climate-change-tracker&tracker=trend-maps\" target=\"_blank\">Climate Change Tracker</a>\" website.</p> <br /><a href=\"/public/guidelines#observed-climate\" target=\"_blank\">Read more...</a>", 
				"<h6>Observed Climate</h6><p class=&quot;justified&quot;>This tab sets the historical and current context of climate and marine observations for ports, to assist ports understand their current climate context. It includes data publicly available from the Bureau of Meteorology and the CSIRO.</p> Three types of data have been selected: <ul><li>national trends for temperature and rainfall;</li><li>global and national trends for sea-level rise; and</li><li>specific weather station data (including examples of extreme weather events).</li></ul><p>Users select from two main data sources in this tab:</p><strong>&quot;CSIRO and BoM trend data&quot;</strong> which provides national or global data on: <ul><li>Trend in mean temperatures,</li><li>Trend in maximum temperatures,</li>	<li>Trend in total annual rainfall,</li><li>Long-term sea level rise measurements and</li><li>Shorter term changes in sea level.</li></ul><strong>&quot;ACORN-SAT&quot;</strong> which provides data from specific weather stations for:<ul><li>Mean measurements (annual mean surface temperature, annual mean rainfall, annual mean relative humidity and annual mean wind speed)</li><li>Extreme measurements (highest temperature, highest daily rainfall, maximum wind gust)</li></ul><br /><p class=&quot;justified&quot;>For further information about extreme weather in Australia, head to the Bureau of Meteorology's &quot;<a href=&quot;http://www.bom.gov.au/climate/change/index.shtml#tabs=Climate-change-tracker&tracker=trend-maps&quot; target=&quot;_blank&quot;>Climate Change Tracker</a>&quot; website.</p><br /><a href=&quot;/public/guidelines#observed-climate&quot; target=&quot;_blank&quot;>Read more...</a>");
		ElementCategory futureClimateCategory = new ElementCategory("Future climate", 
				"<h6>Future Climate</h6><p class=\"justified\">The future climate context faced by ports is an important factor in future planning and risk assessment processes. Direct impacts on ports, and indirect impacts on supply chains, will impact capital investment, maintenance requirements, as well as knowledge, skill and training requirements for personnel. Understanding potential future climate impacts allows ports to adequately assess their future planning options, and accommodate the most appropriate adaptation choices. <p class=\"justified\">This tab assists ports to identify some of the future climate projections data relevant for their region. <p class=\"justified\">Global climate models were selected using CSIRO's Climate Futures tool. Projections were classified using two climate variables: rainfall and temperature, and grouped into \"climate futures\", for example \"hotter &amp; drier\" or \"cooler and wetter\". Likelihoods were then assigned according to the number of climate models that fell within each category. In this application, three global climate models from the following categories are offered for users to choose from: \"hotter &amp; drier\", \"cooler &amp; wetter\" and \"most likely\"</p><p class=\"justified\">Users select from two data sources (CSIRO and CMAR), and then select from a choice of variables.</p><strong>\"CSIRO\"</strong> which displays future climate projection data for:<ul><li>Temperature, rainfall wind speed and relative humidity</li><li>Two emissions scenarios: A1B (medium) and A1FI (high)</li><li>Three climate models: most likely, hotter &amp; drier, and cooler &amp; wetter</li><li>Three time periods: 2030, 2055 and 2070</li></ul><p class=\"justified\"><strong>\"CMAR\"</strong> which only displays sea-level rise data for the \"medium emissions scenario (A1B)\", using a \"most likely\" climate model, for two time periods, \"2030\" and \"2070\".</p><br /><a href=\"/public/guidelines#future-climate\" target=\"_blank\">Read more...</a>", 
				"<h6>Future Climate</h6><p class=&quot;justified&quot;>This tab assists ports to identify some of the future climate projections data relevant for their region.   <p class=&quot;justified&quot;>Global climate models were selected using CSIRO's Climate Futures tool. Projections were classified using two climate variables: rainfall and temperature, and grouped into &quot;climate futures&quot;, for example &quot;hotter &amp; drier&quot; or &quot;cooler and wetter&quot;. Likelihoods were then assigned according to the number of climate models that fell within each category. In this application, three global climate models from the following categories are offered for users to choose from: &quot;hotter &amp; drier&quot;, &quot;cooler &amp; wetter&quot; and &quot;most likely&quot;</p><p class=&quot;justified&quot;>Users select from two data sources (CSIRO and CMAR), and then select from a choice of variables.</p><strong>&quot;CSIRO&quot;</strong> which displays future climate projection data for:<ul><li>Temperature, rainfall wind speed and relative humidity</li><li>Two emissions scenarios: A1B (medium) and A1FI (high)</li><li>Three climate models: most likely, hotter &amp; drier, and cooler &amp; wetter</li><li>Three time periods: 2030, 2055 and 2070</li></ul><p class=&quot;justified&quot;><strong>&quot;CMAR&quot;</strong> which only displays sea-level rise data for the &quot;medium emissions scenario (A1B)&quot;, using a &quot;most likely&quot; climate model, for two time periods, &quot;2030&quot; and &quot;2070&quot;.</p><br /><a href=&quot;/public/guidelines#future-climate&quot; target=&quot;_blank&quot;>Read more...</a>");
		ElementCategory applicationsCategory = new ElementCategory("Applications", 
				"<h6>Applications</h6><strong>Concrete Deterioration Model</strong><p class=\"justified\">Climate change will affect the rate of deterioration of materials such as concrete, timber and steel. The main construction material at ports is concrete and the rate of its deterioration will affect maintenance schedules, budgets and long term plans for refurbishment and replacement.</p><p class=\"justified\">This tab provides access to a tool designed by engineers that models rates of concrete deterioration under conditions of climate change in a port environment.</p>It provides:<ol><li>a set of example outputs for those who are not engineers, and</li><li>the tool for the engineers that have the knowledge to use it</li></ol>The tool enables the user to define:<ul><li>The concrete: e.g.: distance from coast, zone of activity (atmosphere, tidal, splash, submerged), size of structure,  depth of cover, diameter of rebar, water-to-cement ratio, cement content, depth of carbonation from maintenance records etc.;</li><li>The potential climate influences (following the range provided in the Future Climate tab), and;</li><li>A date range for all years 2013 - 2070</li></ul><p class=\"justified\">Data is not currently available for port areas in the South South West Flatlands West (SSWFW) Region.</p>				<strong>Vulnerability Assessment</strong><p class=\"justified\">When considering impacts of climate change, the term vulnerability represents exposure to a particular climate variable combined with the level of sensitivity to that variable, or the degree of impact.</p><p class=\"justified\">The Climate Smart Seaports Tool assists users to investigate vulnerability to current extreme climate-related events. How a port copes with, and responds to current extreme weather events, can be an indication of how well it will cope with future climate change.</p><p class=\"justified\">This tab allows users to identify the current vulnerability of a nominated port to particular climate related events. It presents a series of questions, exploring how recent climate-related events have disrupted operations at the port, and what this has meant for the port's business.</p> <p class=\"justified\">When considering the questions in this tab, think of the impact on port assets (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), and people (injuries, work disruptions).</p><a href=\"/public/guidelines#applications\" target=\"_blank\">Read more...</a>", 
				"<h6>Applications</h6><strong>Concrete Deterioration Model</strong><p class=&quot;justified&quot;>This tab connects users to an engineering tool that models rates of concrete deterioration under conditions of climate change in a port environment.</p> It provides:<ol><li>a set of example outputs for those who are not engineers, and</li><li>the tool for the engineers that have the knowledge to use it</li></ol>The tool enables the user to define: <ul><li>The concrete: e.g.: distance from coast, zone of activity (atmosphere, tidal, splash, submerged), size of structure,  depth of cover, diameter of rebar, water-to-cement ratio, cement content, depth of carbonation from maintenance records etc.;</li><li>The potential climate influences (following the range provided in the Future Climate tab), and;</li><li>A date range for all years 2013 - 2070</li></ul> <p class=&quot;justified&quot;>Data is not currently available for port areas in the South South West Flatlands West (SSWFW) Region.</p> <strong>Vulnerability Assessment</strong><p class=&quot;justified&quot;>This tab allows users to identify the current vulnerability of a nominated port to particular climate related events. It presents a series of questions, exploring how recent climate-related events have disrupted operations at the port, and what this has meant for the port's business.</p> <p class=&quot;justified&quot;>When considering the questions in this tab, think of the impact on port assets (machinery, buildings, equipment), infrastructure (drainage, rail, road, berths), and people (injuries, work disruptions).</p> <a href=&quot;/public/guidelines#applications&quot; target=&quot;_blank&quot;>Read more...</a>");
		
		session.save(nonClimateCategory);
		session.save(observedClimateCategory);
		session.save(futureClimateCategory);
		session.save(applicationsCategory);
		
		
		// Add Data Elements
		// TODO: Modify to add test data
	    date = new Date();
	    String content = "This is a test for Data Element";
	    InputElement ie1 = new InputElement(date, "Test 1", nonClimateCategory, user1wb, true, 1, "csv", content.getBytes());
	   
	    content = "This is the second test";
	    InputElement ie2 = new InputElement(date, "Test 2", observedClimateCategory, user1wb, true, 1, "xml", content.getBytes());
		
	    content = "This is the third test";
	    InputElement ie3 = new InputElement(date, "Test 3", futureClimateCategory, user1wb, true, 1, "txt", content.getBytes());
		
	    content = "User Story's Data Element content test";
	    
	    InputElement ie4 = new InputElement(date, "Test 4", nonClimateCategory, user1us1, true, 1, "txt", content.getBytes());
	    
	    InputElement text1 = new InputElement(date, "Comment text 1", nonClimateCategory, user1us1, true, 2, "txt", content.getBytes());
	    
	    InputElement ie5 = new InputElement(date, "Test 5", applicationsCategory, user1us1, true, 3, "txt", content.getBytes());
	    
	    content = "This is a second text comment";
	    InputElement text2 = new InputElement(date, "Comment text 2", futureClimateCategory, user1us1, true, 4, "txt", content.getBytes());
		
	    InputElement ie6 = new InputElement(date, "Test 6", applicationsCategory, user1us1, true, 5, "txt", content.getBytes());
	    
		session.save(ie1);
		session.save(ie2);
		session.save(ie3);
		session.save(ie4);
		session.save(text1);
		session.save(ie5);
		session.save(text2);
		session.save(ie6);

		// CSIRO Data Element
		List<DataSourceParameterOption> selectedOptions = new ArrayList<DataSourceParameterOption>();
		selectedOptions.add(csiroVariableTemperature);
		selectedOptions.add(mediumEmScn);
		selectedOptions.add(year2055);
	    DataElement deCsiro = new DataElement(date, "CSIRO Data Element Test", futureClimateCategory, user1wb, true, 1, dsCsiro, selectedOptions, tableDisplayType);
	    session.save(deCsiro);
	    
	    // CMAR Data Element
	    /*ArrayList<CmarData> cmarDataList = new ArrayList<CmarData>();
	    cmarDataList.add((CmarData)(session.get(CmarData.class, 1)));
	    DataElementCmar deCmar = new DataElementCmar(date, "CMAR Data Element Test", true, 0, DisplayType.TABLE, user1wb, cmarDataList, true);
	    session.save(deCmar);*/
	    
	    /*selectedOptions = new ArrayList<DataSourceParameterOption>();
		selectedOptions.add(option2a2);
		selectedOptions.add(option2b2);
		selectedOptions.add(option2c2);
	    DataElement deCmar = new DataElement(date, "CMAR Data Element Test", futureClimateCategory, user1wb, true, 2, ds2, selectedOptions, tableDisplayType);
	    session.save(deCmar);*/
	    
	    // Engineering Model Data Element
	    /*DataElementEngineeringModel deEngModel = new DataElementEngineeringModel(date, "Data Element for " + engVar, true, 0, user1wb, engineeringModelDataList);
	    session.save(deEngModel);*/
	    
	    // Vulnerability Data Element
	    /*WeatherEvent we = new WeatherEvent("Heavy rain", 2006, true, "Impact of the event", "1;2;4;0;0;1;5;3;2;0;1;4", "Other consequences", false, "Changes implemented");
	    session.save(we);
	    session.save(new DataElementVulnerability(date, "Vulnerability Assessment", true, 0, DisplayType.GRAPH, user1wb, we));
	    */
	    
	    
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
