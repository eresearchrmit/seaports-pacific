/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;
import edu.rmit.eres.seaports.model.datasource.PastClimateImpactDescriptionDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class PastClimateImpactDescriptionDataSourceLoader {

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
		
		LoadPastClimateImpactDescriptionDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Vulnerability Assessment Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadPastClimateImpactDescriptionDataSource(Session session)
	{
		// Display Types offered by this data source
		DisplayType graphDisplayType = (DisplayType)(session.get(DisplayType.class, 2));
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3));
		
		List<DisplayType> displayTypesVulnerability = new ArrayList<DisplayType>();
		displayTypesVulnerability.add(graphDisplayType);
		displayTypesVulnerability.add(tableDisplayType);
		
		
		// Data Source
		PastClimateImpactDescriptionDataSource dsVulnerability = new PastClimateImpactDescriptionDataSource("pastClimateImpactDescription", "Past Climate Impact Description", "<p><i>This section identifies your current vulnerability to particular climate related events. When considering the questions below, think of the impact on all aspects of port's business: infrastructure (dredging, drainage, berths, storage, rail, road), port assets (machinery, buildings, equipment), people (injuries, work disruptions), legal (regulatory requirements, contract negotiations), financial (loss of income, increased costs), reputation.</i></p><p><i>Please complete this page for each different climate related event that has impacted the port in recent years.</i><p>Rate the effect that the impact will have on the port:<br />1 - No Impact: No impact or slight reduction in efficiency / no real cost<br />2 - Insignificant: Interruption measured in hours, slight delays / small cost<br />3 - Moderate: Interruption measured in days / some costs<br />4 - Major: Operations halted for weeks / significant costs<br />5 - Extreme: Operations suspended indefinitely / major costs<br /></p>", 
				null, null, displayTypesVulnerability);
		
		// Parameters Weather Event Type
		DataSourceParameter weatherEventTypeParam = new DataSourceParameter("Weather Event", "<p>Disruptive climate-related events are those that caused a significant alteration to the &quot;normal&quot; functioning of the port, whether this was for a few hours, a few weeks, or longer.</p>",
				dsVulnerability, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(weatherEventTypeParam);
		DataSourceParameterOption weatherEventTypeHeatwave = new DataSourceParameterOption("Heatwave", "Heatwave", weatherEventTypeParam, 1);
		session.save(weatherEventTypeHeatwave);
		DataSourceParameterOption weatherEventTypeWind = new DataSourceParameterOption("Strong wind", "Strong wind", weatherEventTypeParam, 2);
		session.save(weatherEventTypeWind);
		DataSourceParameterOption weatherEventTypeRain = new DataSourceParameterOption("Heavy rain", "Heavy rain", weatherEventTypeParam, 3);
		session.save(weatherEventTypeRain);
		DataSourceParameterOption weatherEventTypeElecStorm = new DataSourceParameterOption("Electrical storm", "Electrical storm", weatherEventTypeParam, 4);
		session.save(weatherEventTypeElecStorm);
		DataSourceParameterOption weatherEventTypeStorm = new DataSourceParameterOption("Storm (wind and rain combined)", "Storm (wind and rain combined)", weatherEventTypeParam, 5);
		session.save(weatherEventTypeStorm);
		DataSourceParameterOption weatherEventTypeCyclone = new DataSourceParameterOption("Cyclone", "Cyclone", weatherEventTypeParam, 6);
		session.save(weatherEventTypeCyclone);
		DataSourceParameterOption weatherEventTypeHail = new DataSourceParameterOption("Hail", "Hail", weatherEventTypeParam, 7);
		session.save(weatherEventTypeHail);
		DataSourceParameterOption weatherEventTypeStormSurge = new DataSourceParameterOption("Storm surge", "Storm surge", weatherEventTypeParam, 8);
		session.save(weatherEventTypeStormSurge);
		DataSourceParameterOption weatherEventTypeSeaLevelRise = new DataSourceParameterOption("Sea level rise", "Sea level rise", weatherEventTypeParam, 9);
		session.save(weatherEventTypeSeaLevelRise);
		DataSourceParameterOption weatherEventTypeFog = new DataSourceParameterOption("Fog", "Fog", weatherEventTypeParam, 10);
		session.save(weatherEventTypeFog);
		DataSourceParameterOption weatherEventTypeDrought = new DataSourceParameterOption("Drought", "Drought", weatherEventTypeParam, 11);
		session.save(weatherEventTypeDrought);
		DataSourceParameterOption weatherEventTypeFlood = new DataSourceParameterOption("Flood", "Flood", weatherEventTypeParam, 12);
		session.save(weatherEventTypeFlood);
		DataSourceParameterOption weatherEventTypeOther = new DataSourceParameterOption("Other", "Other", weatherEventTypeParam, 13);
		session.save(weatherEventTypeOther);
		
		// Parameter Date
		DataSourceParameter dateParam = new DataSourceParameter("Date", "<p>The year the disruptive event occured.</p>",
				dsVulnerability, null, DataSourceParameter.Display.DROPDOWN);		
		session.save(dateParam);
		int endYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		int i = 1;
		for (Integer year = endYear - 30; year <= endYear ; year++) {
			DataSourceParameterOption weatherEventYear = new DataSourceParameterOption(year.toString(), year.toString(), dateParam, i);
			session.save(weatherEventYear);
			i++;
		}
		
		// Parameter "Direct or Indirect"
		DataSourceParameter climateEmissionScnParam = new DataSourceParameter("Direct or indirect impact", "<p>Direct impacts are those that specifically impacted the port, for example, heavy rain on site. Indirect impacts are those that have caused a flow-on impact to the port business, for example, shut down of electricity supply due to climate impacts, leaving the port without power. Select either Direct or Indirect. If the event caused both Direct and Indirect impacts, select the one that was MORE significant.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(climateEmissionScnParam);
		DataSourceParameterOption mediumEmScn = new DataSourceParameterOption("Direct", "1", climateEmissionScnParam, 1);
		session.save(mediumEmScn);
		DataSourceParameterOption highEmScn = new DataSourceParameterOption("Indirect", "0", climateEmissionScnParam, 2);
		session.save(highEmScn);
		
		// Impact
		DataSourceParameter impactParam = new DataSourceParameter("Impact", "<p>Describe how the climate related event impacted your business. E.g.: Rain caused onsite flooding; the cyclone damaged roads bringing cargo to the port.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.TEXT);
		session.save(impactParam);
		session.save(new DataSourceParameterOption("", "", impactParam, 1));
		
		// Rating of the consequence of events
		DataSourceParameter consequence1 = new DataSourceParameter("Marine infrastructure", "<p>Impacts on dredging schedules, alterations to channels, navigation aids, impacts to shipping.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence1);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence1, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence1, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence1, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence1, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence1, 5));
		
		DataSourceParameter consequence2 = new DataSourceParameter("Port infrastructure", "<p>Impact on seawalls, revetments, berths, piers.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence2);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence2, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence2, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence2, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence2, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence2, 5));
		
		DataSourceParameter consequence3 = new DataSourceParameter("Port superstructure", "Impact on paving, drainage systems, warehouses, silos, buildings.", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence3);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence3, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence3, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence3, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence3, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence3, 5));
		
		DataSourceParameter consequence4 = new DataSourceParameter("Supply chain", "Impact on road, rail, inland waterways.", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence4);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence4, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence4, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence4, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence4, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence4, 5));
		
		DataSourceParameter consequence5 = new DataSourceParameter("Operations", "<p>Impact  to moving goods/bulk commodities to and from boats and across the port; impact on equipment.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence5);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence5, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence5, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence5, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence5, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence5, 5));
		
		DataSourceParameter consequence6 = new DataSourceParameter("Workforce", "<p>Loss of staff/contractor time to injury on site, or inability to get to work because of the climate event.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence6);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence6, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence6, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence6, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence6, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence6, 5));
		
		DataSourceParameter consequence7 = new DataSourceParameter("Financial", "<p>Loss of income, increase in costs.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence7);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence7, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence7, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence7, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence7, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence7, 5));
		
		DataSourceParameter consequence8 = new DataSourceParameter("Legal/Regulatory", "<p>Cost of compliance, cost of non-compliance.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence8);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence8, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence8, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence8, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence8, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence8, 5));
		
		DataSourceParameter consequence9 = new DataSourceParameter("Environment", "<p>Impact to the natural environment caused by port operations as a result of the climate related event. i.e.: excessive dust due to dry, windy conditions; contaminated water/chemical spill due to flooding.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence9);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence9, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence9, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence9, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence9, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence9, 5));
		
		DataSourceParameter consequence10 = new DataSourceParameter("Trade", "<p>Impact on trade throughput due to a climate event at port or elsewhere; change in shipping schedule; change to trade goods.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence10);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence10, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence10, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence10, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence10, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence10, 5));
		
		DataSourceParameter consequence11 = new DataSourceParameter("Stakeholders", "<p>Impact on partners, leaseholders or community from climate events at the port.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence11);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence11, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence11, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence11, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence11, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence11, 5));
		
		DataSourceParameter consequence12 = new DataSourceParameter("Reputation", "<p>Loss of good name; impact on stakeholders.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(consequence12);
		session.save(new DataSourceParameterOption("No Impact", "0", consequence12, 1));
		session.save(new DataSourceParameterOption("Insignificant", "1", consequence12, 2));
		session.save(new DataSourceParameterOption("Moderate", "2", consequence12, 3));
		session.save(new DataSourceParameterOption("Major", "3", consequence12, 4));
		session.save(new DataSourceParameterOption("Extreme", "4", consequence12, 5));
				
		// Other Consequences
		DataSourceParameter consequenceOtherParam = new DataSourceParameter("Other business consequences", "<p>Specify in the text box provided any other business consequences not listed above.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.TEXT);
		session.save(consequenceOtherParam);
		session.save(new DataSourceParameterOption("", "", consequenceOtherParam, 1));
		
		// Adequate Response Parameter
		DataSourceParameter responseAdequateParam = new DataSourceParameter("Would you say your response was adequate?", "<p>What did the Port learn from the event? Did it ignore the event or make changes?</p>", 
				dsVulnerability, null, DataSourceParameter.Display.RADIO);
		session.save(responseAdequateParam);
		session.save(new DataSourceParameterOption("Yes", "1", responseAdequateParam, 1));
		session.save(new DataSourceParameterOption("No", "0", responseAdequateParam, 2));
		
		// Changes implemented after event
		DataSourceParameter changesParam = new DataSourceParameter("What were the changes implemented as a result of this event?", "<p>Changes may be to management systems, to safety protocols, maintenance processes, communication protocols, insurance coverage and so forth.</p>", 
				dsVulnerability, null, DataSourceParameter.Display.TEXT);
		session.save(changesParam);
		session.save(new DataSourceParameterOption("", "", changesParam, 1));
		
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
				
		dsVulnerability.setSeaports(seaports);
		
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 1))); // Category 1 = Observed climate & marine
		categories.add((ElementCategory)(session.get(ElementCategory.class, 4))); // Category 4 = Applications
		dsVulnerability.setCategories(categories);
		
		session.save(dsVulnerability);
	}
}