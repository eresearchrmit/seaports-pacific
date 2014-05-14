/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
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
import edu.rmit.eres.seaports.model.datasource.CurrentClimateRiskDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class CurrentClimateRiskDataSourceLoader {

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
		
		LoadCurrentClimateRiskDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Vulnerability Assessment Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadCurrentClimateRiskDataSource(Session session)
	{
		// Display Types offered by this data source
		DisplayType textDisplayType = (DisplayType)(session.get(DisplayType.class, 1));
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3));
		
		List<DisplayType> displayTypesCurrentClimateRisk = new ArrayList<DisplayType>();
		displayTypesCurrentClimateRisk.add(textDisplayType);
		displayTypesCurrentClimateRisk.add(tableDisplayType);
		
		
		// Data Source
		CurrentClimateRiskDataSource dsCurrentClimateRisk = new CurrentClimateRiskDataSource("currentClimateRisk", "Current Climate Risk Assessment", "<p><i>?.</i></p>", 
				null, null, displayTypesCurrentClimateRisk);
		
		
		// Parameters Weather Event Type
		DataSourceParameter weatherEventTypeParam = new DataSourceParameter("Event Type", "", dsCurrentClimateRisk, null, DataSourceParameter.Display.DROPDOWN);		
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
		/*DataSourceParameter riskAreasParam = new DataSourceParameter("Risk Area", "", dsCurrentClimateRisk, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(riskAreasParam);
		DataSourceParameterOption riskAreasMarineInfrastructure = new DataSourceParameterOption("", "Marine Infrastructure", riskAreasParam, 1);
		session.save(riskAreasMarineInfrastructure);
		DataSourceParameterOption riskAreasPortInfrastructure = new DataSourceParameterOption("Port Infrastructure", "Port Infrastructure", riskAreasParam, 2);
		session.save(riskAreasPortInfrastructure);
		DataSourceParameterOption riskAreasPortSuperstructure = new DataSourceParameterOption("Port Superstructure", "Port Superstructure", riskAreasParam, 3);
		session.save(riskAreasPortSuperstructure);
		DataSourceParameterOption riskAreasOperations = new DataSourceParameterOption("Operations", "Operations", riskAreasParam, 4);
		session.save(riskAreasOperations);
		DataSourceParameterOption riskAreasSupplychain = new DataSourceParameterOption("Supply Chain", "Supply Chain", riskAreasParam, 5);
		session.save(riskAreasSupplychain);
		DataSourceParameterOption riskAreasWorkforce = new DataSourceParameterOption("Workforce", "Workforce", riskAreasParam, 6);
		session.save(riskAreasWorkforce);
		DataSourceParameterOption riskAreasFinancial = new DataSourceParameterOption("Financial", "Financial", riskAreasParam, 7);
		session.save(riskAreasFinancial);
		DataSourceParameterOption riskAreasLegal = new DataSourceParameterOption("Legal/Regulations", "Legal/Regulations", riskAreasParam, 8);
		session.save(riskAreasLegal);
		DataSourceParameterOption riskAreasEnvironment = new DataSourceParameterOption("Environment", "Environment", riskAreasParam, 9);
		session.save(riskAreasEnvironment);
		DataSourceParameterOption riskAreasStakeholders = new DataSourceParameterOption("Stakeholders", "Stakeholders", riskAreasParam, 10);
		session.save(riskAreasStakeholders);
		DataSourceParameterOption riskAreasReputation = new DataSourceParameterOption("Reputation", "Reputation", riskAreasParam, 11);
		session.save(riskAreasReputation);*/
				
		// Rating of the consequence of risk
		DataSourceParameter consequence1 = new DataSourceParameter("Marine Infrastructure", "<p>1: No impact or slight reduction in efficiency.</p><p>2: Interruption measured in hours, slight delays.</p><p>3: Interruption measured in days.</p><p>4: Operations halted for weeks.</p><p>5: Operations suspended indefinitely.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence1);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence1, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence1, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence1, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence1, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence1, 5));
		
		DataSourceParameter consequence2 = new DataSourceParameter("Port Infrastructure", "<p>1: No impact or slight reduction in efficiency.</p><p>2: Interruption measured in hours, slight delays.</p><p>3: Interruption measured in days.</p><p>4: Operations halted for weeks.</p><p>5: Operations suspended indefinitely.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence2);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence2, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence2, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence2, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence2, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence2, 5));
		
		DataSourceParameter consequence3 = new DataSourceParameter("Port Superstructure", "<p>1: No impact or slight reduction in efficiency.</p><p>2: Interruption measured in hours, slight delays.</p><p>3: Interruption measured in days.</p><p>4: Operations halted for weeks.</p><p>5: Operations suspended indefinitely.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence3);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence3, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence3, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence3, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence3, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence3, 5));
		
		DataSourceParameter consequence4 = new DataSourceParameter("Operations", "<p>1: No impact or slight reduction in operations/equipment efficiency.</p><p>2: Interruption measured in hours, slight delays.</p><p>3: Interruption measured in days.</p><p>4: Operations halted for weeks.</p><p>5: Operations suspended indefinitely.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence4);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence4, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence4, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence4, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence4, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence4, 5));
		
		DataSourceParameter consequence5 = new DataSourceParameter("Supply Chain", "<p>1: No impact or slight reduction in commodity transfer..</p><p>2: Interruption measured in hours, slight delays.</p><p>3: Interruption measured in days.</p><p>4: Operations halted for weeks.</p><p>5: Operations suspended indefinitely.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence5);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence5, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence5, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence5, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence5, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence5, 5));
		
		DataSourceParameter consequence6 = new DataSourceParameter("Workforce", "<p>1: Local treatment with quick recovery.</p><p>2:Medical treatment required or short-term acute health effects. Off-work for hours.</p><p>3: Lost time injury and short/medium health effects. Off-work for week(s).</p><p>4: Extensive injuries or chronic health issues, over months.</p><p>5: Single fatality or permanent disability or long-term off-work.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence6);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence6, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence6, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence6, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence6, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence6, 5));
		
		DataSourceParameter consequence7 = new DataSourceParameter("Financial", "<p>1: Slight increase in costs and liabilities or slight reduction in income: insignificant.</p><p>2: Increase in costs/liabilities or reduction in income: affects profit slightly.</p><p>3: Increase in costs/liabilities or reduction in income: affects profit moderately.</p><p>4: Increase in costs/liabilities or reduction in income: significantly affects profit.</p><p>5: Company is into negative earnings.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence7);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence7, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence7, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence7, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence7, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence7, 5));
		
		DataSourceParameter consequence8 = new DataSourceParameter("Legal/Regulations", "<p>1: Compliance not needed.</p><p>2: Regulatory/contractual condictions apply.</p><p>3: Unable to meet regulatory or contractual condition.</p><p>4: Possibility of prosecution or fines.</p><p>5: Possibility or imprisonment.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence8);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence8, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence8, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence8, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence8, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence8, 5));
		
		DataSourceParameter consequence9 = new DataSourceParameter("Environment", "<p>1: On-site impact with little damage.</p><p>2: On or off-site impact with damage repaired in weeks.</p><p>3: On or off-site impact with damage repaired in month.</p><p>4: Major on or off-site impact with damage remediation taking years.</p><p>5: Permanent damage to an ecosystem with irreversible effects.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence9);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence9, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence9, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence9, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence9, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence9, 5));
		
		DataSourceParameter consequence10 = new DataSourceParameter("Stakeholders", "<p>1: Minor opposition. Need to improve relationships.</p><p>2: Possible objections requiring minor actions to resolve.</p><p>3: Possible strong and widespread objections requiring major actions.</p><p>4: Project requires large-scale plans, or changes to contract.</p><p>5: Project likely to be stopped by widespread objections, denied approval.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence10);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence10, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence10, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence10, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence10, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence10, 5));
		
		DataSourceParameter consequence11 = new DataSourceParameter("Reputation", "<p>1: Clients suffer minor disruptions.</p><p>2: Clients suffer disruptions, delays or loss.</p><p>3: Clients suffer significant disruptions, delays or loss.</p><p>4: Clients consider using another port.</p><p>5: Clients use another port.</p>", 
				dsCurrentClimateRisk, null, DataSourceParameter.Display.RADIO);
		session.save(consequence11);
		session.save(new DataSourceParameterOption("Not vulnerable", "1", consequence11, 1));
		session.save(new DataSourceParameterOption("Could be vulnerable", "2", consequence11, 2));
		session.save(new DataSourceParameterOption("Somewhat vulnerable", "3", consequence11, 3));
		session.save(new DataSourceParameterOption("Moderately vulnerable", "4", consequence11, 4));
		session.save(new DataSourceParameterOption("Significantly vulnerable", "5", consequence11, 5));
		
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
				
		dsCurrentClimateRisk.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 1))); // Category 1 = Observed climate & marine
		dsCurrentClimateRisk.setCategories(categories);
		
		session.save(dsCurrentClimateRisk);
	}
}