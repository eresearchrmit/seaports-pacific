/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;
import edu.rmit.eres.seaports.model.datasource.FutureClimateRiskDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class FutureClimateRiskDataSourceLoader {

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
		
		LoadFutureClimateRiskDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Vulnerability Assessment Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadFutureClimateRiskDataSource(Session session)
	{
		// Display Types offered by this data source
		DisplayType textDisplayType = (DisplayType)(session.get(DisplayType.class, 1));
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3));
		
		List<DisplayType> displayTypesFutureClimateRisk = new ArrayList<DisplayType>();
		displayTypesFutureClimateRisk.add(textDisplayType);
		displayTypesFutureClimateRisk.add(tableDisplayType);
		
		
		// Data Source
		FutureClimateRiskDataSource dsFutureClimateRisk = new FutureClimateRiskDataSource("futureClimateRisk", "Future Climate Risk Assessment", "<p><i>?.</i></p>", 
				null, null, displayTypesFutureClimateRisk);
		
		
		// Parameters Weather Event Type
		DataSourceParameter weatherEventTypeParam = new DataSourceParameter("Event Type", "", dsFutureClimateRisk, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(weatherEventTypeParam);
		DataSourceParameterOption weatherEventTypeSeaCurrent = new DataSourceParameterOption("Sea current", "Sea current", weatherEventTypeParam, 1);
		session.save(weatherEventTypeSeaCurrent);
		DataSourceParameterOption weatherEventTypeWaveClimate = new DataSourceParameterOption("Wave climate", "Wave climate", weatherEventTypeParam, 2);
		session.save(weatherEventTypeWaveClimate);
		DataSourceParameterOption weatherEventTypeSeaTemp = new DataSourceParameterOption("Sea Surface Temperature", "Sea Surface Temperature", weatherEventTypeParam, 3);
		session.save(weatherEventTypeSeaTemp);
		DataSourceParameterOption weatherEventTypeSeaAcidity = new DataSourceParameterOption("Sea acidity", "Sea acidity", weatherEventTypeParam, 4);
		session.save(weatherEventTypeSeaAcidity);
		DataSourceParameterOption weatherEventTypeStormSurge = new DataSourceParameterOption("Storm surge", "Storm surge", weatherEventTypeParam, 5);
		session.save(weatherEventTypeStormSurge);
		DataSourceParameterOption weatherEventTypeCyclone = new DataSourceParameterOption("Cyclone", "Cyclone", weatherEventTypeParam, 6);
		session.save(weatherEventTypeCyclone);
		DataSourceParameterOption weatherEventTypeRainfall = new DataSourceParameterOption("Intense rainfall", "Intense rainfall", weatherEventTypeParam, 7);
		session.save(weatherEventTypeRainfall);
		DataSourceParameterOption weatherEventTypeWind = new DataSourceParameterOption("Wind speed/direction", "Wind speed/direction", weatherEventTypeParam, 8);
		session.save(weatherEventTypeWind);
		DataSourceParameterOption weatherEventTypeHeatwave = new DataSourceParameterOption("Heat wave", "Heat wave", weatherEventTypeParam, 9);
		session.save(weatherEventTypeHeatwave);
		DataSourceParameterOption weatherEventTypeDrought = new DataSourceParameterOption("Drought", "Drought", weatherEventTypeParam, 10);
		session.save(weatherEventTypeDrought);
		
		// Parameter Risk Areas
		DataSourceParameter riskAreasParam = new DataSourceParameter("Risk Area", "", dsFutureClimateRisk, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(riskAreasParam);
		DataSourceParameterOption riskAreasMarineInfrastructure = new DataSourceParameterOption("Marine Infrastructure", "Marine Infrastructure", riskAreasParam, 1);
		session.save(riskAreasMarineInfrastructure);
		DataSourceParameterOption riskAreasPortInfrastructure = new DataSourceParameterOption("Port Infrastructure", "Port Infrastructure", riskAreasParam, 1);
		session.save(riskAreasPortInfrastructure);
		DataSourceParameterOption riskAreasPortSuperstructure = new DataSourceParameterOption("Port Superstructure", "Port Superstructure", riskAreasParam, 1);
		session.save(riskAreasPortSuperstructure);
		DataSourceParameterOption riskAreasSupplychain = new DataSourceParameterOption("Supply Chain", "Supply Chain", riskAreasParam, 1);
		session.save(riskAreasSupplychain);
		DataSourceParameterOption riskAreasOperations = new DataSourceParameterOption("Operations", "Operations", riskAreasParam, 1);
		session.save(riskAreasOperations);
		DataSourceParameterOption riskAreasWorkforce = new DataSourceParameterOption("Workforce", "Workforce", riskAreasParam, 1);
		session.save(riskAreasWorkforce);
		DataSourceParameterOption riskAreasFinancial = new DataSourceParameterOption("Financial", "Financial", riskAreasParam, 1);
		session.save(riskAreasFinancial);
		DataSourceParameterOption riskAreasLegal = new DataSourceParameterOption("Legal/Regulations", "Legal/Regulations", riskAreasParam, 1);
		session.save(riskAreasLegal);
		DataSourceParameterOption riskAreasEnvironment = new DataSourceParameterOption("Environment", "Environment", riskAreasParam, 1);
		session.save(riskAreasEnvironment);
		DataSourceParameterOption riskAreasStakeholders = new DataSourceParameterOption("Stakeholders", "Stakeholders", riskAreasParam, 1);
		session.save(riskAreasStakeholders);
		DataSourceParameterOption riskAreasReputation = new DataSourceParameterOption("Reputation", "Reputation", riskAreasParam, 1);
		session.save(riskAreasReputation);
				
		// Description
		DataSourceParameter descriptionParam = new DataSourceParameter("Description", "", dsFutureClimateRisk, null, DataSourceParameter.Display.TEXT);
		session.save(descriptionParam);
		session.save(new DataSourceParameterOption("", "", descriptionParam, 1));
		
		// Details of Risk
		DataSourceParameter detailsParam = new DataSourceParameter("Details of Risk", "Current thresolds", dsFutureClimateRisk, null, DataSourceParameter.Display.TEXT);
		session.save(detailsParam);
		session.save(new DataSourceParameterOption("", "", detailsParam, 1));
		
		// Future Consequences
		DataSourceParameter futureConsequencesParam = new DataSourceParameter("Future Consequences", "", dsFutureClimateRisk, null, DataSourceParameter.Display.TEXT);
		session.save(futureConsequencesParam);
		session.save(new DataSourceParameterOption("", "", futureConsequencesParam, 1));
				
		// Rating of the consequence of risk
		DataSourceParameter consequence = new DataSourceParameter("Consequence Rating", "", dsFutureClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence);
		session.save(new DataSourceParameterOption("None or Negligible", "0", consequence, 1));
		session.save(new DataSourceParameterOption("Minor", "1", consequence, 2));
		session.save(new DataSourceParameterOption("Medium", "2", consequence, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence, 5));
		
		// Rating of the likelihood of a risk
		DataSourceParameter likelihood = new DataSourceParameter("Likelihood", "", dsFutureClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(likelihood);
		session.save(new DataSourceParameterOption("Rare", "0", likelihood, 1));
		session.save(new DataSourceParameterOption("Unlikely", "1", likelihood, 2));
		session.save(new DataSourceParameterOption("Possible", "2", likelihood, 3));
		session.save(new DataSourceParameterOption("Probable", "3", likelihood, 4));
		session.save(new DataSourceParameterOption("Almost Certain", "4", likelihood, 5));
		
		
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
				
		dsFutureClimateRisk.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 2))); // Category 2 = Future climate & marine
		dsFutureClimateRisk.setCategories(categories);
		
		session.save(dsFutureClimateRisk);
	}
}