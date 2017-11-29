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
import edu.rmit.eres.seaports.model.data.DemographicsVariable;
import edu.rmit.eres.seaports.model.data.DemographicsData;
import edu.rmit.eres.seaports.model.datasource.DemographicsDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class DemographicsDataSourceLoader {
	
	/**
	 * Main method used to load  the Demographics Data Source only.
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
		
		LoadDemographicsDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Demographics Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadDemographicsDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		DemographicsDataSourceLoader.LoadDemographicsData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType graphDisplayType = (DisplayType)(session.get(DisplayType.class, 2)); // Graph
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		displayTypes.add(graphDisplayType);
		
		
		// Data Source
		DemographicsDataSource dsDemographics = new DemographicsDataSource("demographics", "Demographics", "", null, null, displayTypes);
		
		// Parameter variable
		DataSourceParameter variableParam = new DataSourceParameter("Variable", "<h6>Total Population</h6><p>Total population is based on the de facto definition of population, which counts all residents regardless of legal status or citizenship--except for refugees not permanently settled in the country of asylum, who are generally considered part of the population of their country of origin. The values shown are midyear estimates.</p>",
				dsDemographics, null, DataSourceParameter.Display.DROPDOWN);
		session.save(variableParam);
		DataSourceParameterOption populationOption = new DataSourceParameterOption("Total Population", "Total Population", variableParam, 1);
		session.save(populationOption);
				
		
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
		
		dsDemographics.setSeaports(seaports);
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 3))); // Category 3 = Non-Climate context
		dsDemographics.setCategories(categories);
		
		session.save(dsDemographics);
	}
	

	/**
	 * Loads the Trade dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadDemographicsData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		// Variables
		DemographicsVariable pop = new DemographicsVariable("Total Population", "Pop", "Total population is based on the de facto definition of population, which counts all residents regardless of legal status or citizenship--except for refugees not permanently settled in the country of asylum, who are generally considered part of the population of their country of origin. The values shown are midyear estimates.", "people", "");
		session.save(pop);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2012-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		String sourceName = "World Development Indicators of The World Bank (based on United Nations World Population Prospects)";
		
		// Population Fiji
		session.save(new DemographicsData(creationDate, r1, 1960, pop, 393383.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 1961, pop, 407152.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1962, pop, 421576.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1963, pop, 436208.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1964, pop, 450452.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1965, pop, 463884.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1966, pop, 476329.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1967, pop, 487911.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1968, pop, 498887.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1969, pop, 509659.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r1, 1970, pop, 520529.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 1971, pop, 531601.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1972, pop, 542811.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1973, pop, 554109.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1974, pop, 565386.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1975, pop, 576592.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1976, pop, 587522.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1977, pop, 598256.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1978, pop, 609344.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1979, pop, 621537.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r1, 1980, pop, 635256.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 1981, pop, 650966.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1982, pop, 668219.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1983, pop, 685422.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1984, pop, 700394.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1985, pop, 711663.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1986, pop, 718493.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1987, pop, 721594.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1988, pop, 722707.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1989, pop, 724355.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r1, 1990, pop, 728339.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 1991, pop, 735209.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1992, pop, 744340.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1993, pop, 754923.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1994, pop, 765664.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1995, pop, 775587.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1996, pop, 784647.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1997, pop, 793098.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1998, pop, 800616.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 1999, pop, 806857.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r1, 2000, pop, 811647.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 2001, pop, 814700.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2002, pop, 816237.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2003, pop, 817224.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2004, pop, 818995.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2005, pop, 822484.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2006, pop, 828060.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2007, pop, 835392.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2008, pop, 843851.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2009, pop, 852479.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r1, 2010, pop, 860559.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r1, 2011, pop, 867921.0, sourceName));
		session.save(new DemographicsData(creationDate, r1, 2012, pop, 874742.0, sourceName));*/
		
		// Population PNG
		session.save(new DemographicsData(creationDate, r2, 1960, pop, 1966957.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 1961, pop, 2001048.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1962, pop, 2037164.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1963, pop, 2075629.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1964, pop, 2116831.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1965, pop, 2161102.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1966, pop, 2208421.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1967, pop, 2258876.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1968, pop, 2313016.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1969, pop, 2371514.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r2, 1970, pop, 2434754.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 1971, pop, 2503073.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1972, pop, 2576093.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1973, pop, 2652585.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1974, pop, 2730859.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1975, pop, 2809692.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1976, pop, 2888510.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1977, pop, 2967620.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1978, pop, 3047769.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1979, pop, 3130125.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r2, 1980, pop, 3215483.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 1981, pop, 3304188.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1982, pop, 3395798.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1983, pop, 3489402.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1984, pop, 3583707.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1985, pop, 3677854.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1986, pop, 3771592.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1987, pop, 3865448.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1988, pop, 3960314.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1989, pop, 4057467.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r2, 1990, pop, 4157903.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 1991, pop, 4261797.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1992, pop, 4369087.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1993, pop, 4480243.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1994, pop, 4595761.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1995, pop, 4715929.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1996, pop, 4841020.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1997, pop, 4970823.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1998, pop, 5104516.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 1999, pop, 5240941.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r2, 2000, pop, 5379226.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 2001, pop, 5518971.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2002, pop, 5660267.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2003, pop, 5803302.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2004, pop, 5948461.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2005, pop, 6095959.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2006, pop, 6245797.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2007, pop, 6397623.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2008, pop, 6550877.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2009, pop, 6704829.0, sourceName));*/
		session.save(new DemographicsData(creationDate, r2, 2010, pop, 6858945.0, sourceName));
		/*session.save(new DemographicsData(creationDate, r2, 2011, pop, 7012977.0, sourceName));
		session.save(new DemographicsData(creationDate, r2, 2012, pop, 7167010.0, sourceName));*/
	}
}