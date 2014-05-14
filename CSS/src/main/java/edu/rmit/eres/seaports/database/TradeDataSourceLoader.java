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
import edu.rmit.eres.seaports.model.data.TradeData;
import edu.rmit.eres.seaports.model.data.TradeVariable;
import edu.rmit.eres.seaports.model.datasource.TradeDataSource;

/**
 * Class used to load CSIRO Data Source in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class TradeDataSourceLoader {
	
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
		
		LoadTradeDataSource(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Trade Data Source in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadTradeDataSource(Session session)
	{
		// Loads the underlying Observed Trend dataset
		TradeDataSourceLoader.LoadTradeData(session);
		
		
		// Display Types offered by this data source
		DisplayType tableDisplayType = (DisplayType)(session.get(DisplayType.class, 3)); // Table
		DisplayType graphDisplayType = (DisplayType)(session.get(DisplayType.class, 2)); // Graph
		
		List<DisplayType> displayTypes = new ArrayList<DisplayType>();
		displayTypes.add(tableDisplayType);
		displayTypes.add(graphDisplayType);
		
		
		// Data Source
		TradeDataSource dsTrade = new TradeDataSource("trade", "Trade Data", "", null, null, displayTypes);
		
		// Parameter Import/Export, letting the user choose between import trade data or export trade data
		DataSourceParameter importExportParam = new DataSourceParameter("Import/Export", "<h6>Import/Export</h6><p>Choose 'import' to get the values of top 10 goods imports over a decade</p><p>Choose 'export' to get the values of top 10 goods exports over a decade</p>",
				dsTrade, null, DataSourceParameter.Display.RADIO);
		session.save(importExportParam);
		DataSourceParameterOption importOption = new DataSourceParameterOption("Import", "Import", importExportParam, 1);
		session.save(importOption);
		DataSourceParameterOption exportOption = new DataSourceParameterOption("Export", "Export", importExportParam, 2);
		session.save(exportOption);
				
		
		// Availability of the data source for each seaport
		List<Seaport> seaports = new ArrayList<Seaport>();
		seaports.add((Seaport)(session.get(Seaport.class, "FJSUV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLTK")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJMAL")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJLEV")));
		seaports.add((Seaport)(session.get(Seaport.class, "FJWAI")));
				
		dsTrade.setSeaports(seaports);
		
		// Availability of data sources for each element category
		List<ElementCategory> categories = new ArrayList<ElementCategory>();
		categories.add((ElementCategory)(session.get(ElementCategory.class, 3))); // Category 3 = Non-Climate context
		dsTrade.setCategories(categories);
		
		
		session.save(dsTrade);
	}
	

	/**
	 * Loads the Trade dataset in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadTradeData(Session session)
	{
		// Regions
		Region r1 = (Region)(session.get(Region.class, 1)); // Fiji
		//Region r2 = (Region)(session.get(Region.class, 2)); // PNG
		
		// Climate Variables
		TradeVariable liveAnimals = new TradeVariable("Live animals and animal products", "", "Live animals and animal products", "FDJ", "");
		TradeVariable vegetables = new TradeVariable("Vegetable Products", "", "Vegetable products", "FDJ", "");
		TradeVariable food = new TradeVariable("Prepared food, beverages, tobacco", "", "Prepared food, beverages, tobacco", "FDJ", "");
		TradeVariable minerals = new TradeVariable("Mineral products", "", "Mineral products", "FDJ", "");
		TradeVariable chemical = new TradeVariable("Chemical", "", "Chemical products", "FDJ", "");
		TradeVariable wood = new TradeVariable("Wood, cork, rushes", "", "Wood, cork, rushes", "FDJ", "");
		TradeVariable textiles = new TradeVariable("Textiles", "", "Textiles", "FDJ", "");
		TradeVariable stones = new TradeVariable("Precious, semi-precious stones and metal", "", "Precious, semi-precious stones and metal", "FDJ", "");
		TradeVariable metal = new TradeVariable("Metal", "", "Metal", "FDJ", "");
		TradeVariable machinery = new TradeVariable("Machinery, electrical applicances, parts", "", "Machinery, electrical applicances, parts", "FDJ", "");
		TradeVariable plastic = new TradeVariable("Plastic, rubber articles", "", "Plastic, rubber articles", "FDJ", "");
		TradeVariable baseMetal = new TradeVariable("Base metal", "", "Base metal", "FDJ", "");
		TradeVariable transport = new TradeVariable("Transport equipment", "", "Transport equipment", "FDJ", "");
		session.save(liveAnimals);
		session.save(vegetables);
		session.save(food);
		session.save(minerals);
		session.save(chemical);
		session.save(wood);
		session.save(textiles);
		session.save(stones);
		session.save(metal);
		session.save(machinery);
		session.save(plastic);
		session.save(baseMetal);
		session.save(transport);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date creationDate = null;
		try {
			creationDate = dateFormatter.parse("2011-01-01");
		} catch (ParseException e) {
			creationDate = new Date();
		}
		
		// Fiji
		String sourceName = "Fiji Bureau of Statistics";
		
		
		// Exports
		session.save(new TradeData(creationDate, r1, 2000, liveAnimals, false, 92188.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, liveAnimals, false, 101315.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, liveAnimals, false, 85442.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, liveAnimals, false, 91724.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, liveAnimals, false, 93194.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, liveAnimals, false, 94194.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, liveAnimals, false, 120325.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, liveAnimals, false, 115771.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, liveAnimals, false, 146714.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, liveAnimals, false, 175259.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, liveAnimals, false, 254995.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, vegetables, false, 40899.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, vegetables, false, 40357.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, vegetables, false, 36165.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, vegetables, false, 40441.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, vegetables, false, 50311.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, vegetables, false, 47201.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, vegetables, false, 54858.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, vegetables, false, 61742.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, vegetables, false, 65681.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, vegetables, false, 62812.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, vegetables, false, 75096.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, food, false, 295991.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, food, false, 309811.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, food, false, 321294.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, food, false, 323452.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, food, false, 338380.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, food, false, 384578.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, food, false, 398521.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, food, false, 391572.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, food, false, 458837.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, food, false, 384693.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, food, false, 311184.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, minerals, false, 106736.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, minerals, false, 166914.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, minerals, false, 197857.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, minerals, false, 196599.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, minerals, false, 180985.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, minerals, false, 282782.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, minerals, false, 296711.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, minerals, false, 297447.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, minerals, false, 421457.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, minerals, false, 256691.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, minerals, false, 413579.0, sourceName));

		session.save(new TradeData(creationDate, r1, 2000, chemical, false, 9956.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, chemical, false, 11034.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, chemical, false, 12746.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, chemical, false, 17111.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, chemical, false, 16390.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, chemical, false, 17475.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, chemical, false, 19107.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, chemical, false, 28842.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, chemical, false, 36046.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, chemical, false, 36397.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, chemical, false, 44209.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, wood, false, 45549.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, wood, false, 43354.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, wood, false, 40793.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, wood, false, 32134.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, wood, false, 42967.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, wood, false, 47167.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, wood, false, 38811.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, wood, false, 52657.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, wood, false, 60259.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, wood, false, 38381.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, wood, false, 81338.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, textiles, false, 370704.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, textiles, false, 335747.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, textiles, false, 242316.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, textiles, false, 263615.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, textiles, false, 269199.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, textiles, false, 145275.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, textiles, false, 114441.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, textiles, false, 112819.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, textiles, false, 114631.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, textiles, false, 98522.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, textiles, false, 117231.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, stones, false, 70971.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, stones, false, 87576.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, stones, false, 72240.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, stones, false, 79424.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, stones, false, 92128.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, stones, false, 67927.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, stones, false, 50886.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, stones, false, 3874.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, stones, false, 29941.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, stones, false, 48844.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, stones, false, 152764.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, metal, false, 11857.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, metal, false, 12599.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, metal, false, 13828.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, metal, false, 17627.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, metal, false, 18560.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, metal, false, 18379.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, metal, false, 22096.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, metal, false, 28101.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, metal, false, 31182.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, metal, false, 30583.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, metal, false, 53996.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, machinery, false, 19210.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, machinery, false, 16604.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, machinery, false, 16644.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, machinery, false, 16316.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, machinery, false, 20165.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, machinery, false, 19277.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, machinery, false, 17847.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, machinery, false, 30157.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, machinery, false, 29600.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, machinery, false, 27013.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, machinery, false, 25548.0, sourceName));
		
		
		// Imports
		session.save(new TradeData(creationDate, r1, 2000, liveAnimals, true, 78268.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, liveAnimals, true, 147952.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, liveAnimals, true, 139635.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, liveAnimals, true, 138584.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, liveAnimals, true, 141813.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, liveAnimals, true, 138948.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, liveAnimals, true, 135554.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, liveAnimals, true, 130190.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, liveAnimals, true, 148275.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, liveAnimals, true, 139721.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, liveAnimals, true, 205658.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, vegetables, true, 102922.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, vegetables, true, 110395.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, vegetables, true, 116969.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, vegetables, true, 124077.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, vegetables, true, 131250.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, vegetables, true, 132351.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, vegetables, true, 153943.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, vegetables, true, 164864.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, vegetables, true, 218010.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, vegetables, true, 184591.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, vegetables, true, 220897.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, food, true, 56310.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, food, true, 70603.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, food, true, 74419.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, food, true, 90641.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, food, true, 100518.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, food, true, 107368.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, food, true, 124764.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, food, true, 120948.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, food, true, 172149.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, food, true, 213730.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, food, true, 172689.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, minerals, true, 339995.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, minerals, true, 449118.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, minerals, true, 441388.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, minerals, true, 470534.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, minerals, true, 596086.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, minerals, true, 802607.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, minerals, true, 1043453.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, minerals, true, 976998.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, minerals, true, 1252068.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, minerals, true, 743070.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, minerals, true, 1130356.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, chemical, true, 102191.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, chemical, true, 124250.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, chemical, true, 122642.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, chemical, true, 133876.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, chemical, true, 160585.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, chemical, true, 151955.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, chemical, true, 167912.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, chemical, true, 149499.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, chemical, true, 199212.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, chemical, true, 177143.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, chemical, true, 210780.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, plastic, true, 79886.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, plastic, true, 90468.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, plastic, true, 90717.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, plastic, true, 107778.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, plastic, true, 123284.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, plastic, true, 147223.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, plastic, true, 144641.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, plastic, true, 142915.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, plastic, true, 153128.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, plastic, true, 127090.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, plastic, true, 162778.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, textiles, true, 387064.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, textiles, true, 270421.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, textiles, true, 212433.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, textiles, true, 244547.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, textiles, true, 246619.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, textiles, true, 173699.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, textiles, true, 156591.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, textiles, true, 153214.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, textiles, true, 156786.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, textiles, true, 134290.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, textiles, true, 171058.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, baseMetal, true, 88528.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, baseMetal, true, 91741.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, baseMetal, true, 111718.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, baseMetal, true, 128675.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, baseMetal, true, 158135.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, baseMetal, true, 170483.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, baseMetal, true, 188769.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, baseMetal, true, 163719.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, baseMetal, true, 186299.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, baseMetal, true, 160477.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, baseMetal, true, 175126.0, sourceName));
		
		session.save(new TradeData(creationDate, r1, 2000, machinery, true, 265232.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, machinery, true, 299750.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, machinery, true, 279854.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, machinery, true, 311030.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, machinery, true, 364917.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, machinery, true, 406195.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, machinery, true, 465707.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, machinery, true, 409854.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, machinery, true, 516053.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, machinery, true, 446007.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, machinery, true, 448633.0, sourceName));

		session.save(new TradeData(creationDate, r1, 2000, transport, true, 95843.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2001, transport, true, 119297.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2002, transport, true, 145488.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2003, transport, true, 266239.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2004, transport, true, 193807.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2005, transport, true, 184937.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2006, transport, true, 226968.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2007, transport, true, 170656.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2008, transport, true, 233874.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2009, transport, true, 164120.0, sourceName));
		session.save(new TradeData(creationDate, r1, 2010, transport, true, 182615.0, sourceName));
	}
}