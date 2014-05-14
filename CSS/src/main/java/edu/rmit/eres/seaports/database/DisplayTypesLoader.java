/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;

/**
 * Class used to load the display types existing in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class DisplayTypesLoader {
	
	/**
	 * Main method used to load the display types existing in the database.
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
		
		LoadDisplayTypes(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the display types existing in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadDisplayTypes(Session session)
	{
		DisplayType textDisplayType = new DisplayType("Text");
		session.save(textDisplayType);
		
		DisplayType graphDisplayType = new DisplayType("Graph");
		session.save(graphDisplayType);
		
		DisplayType tableDisplayType = new DisplayType("Table");
		session.save(tableDisplayType);
		
		DisplayType mapDisplayType = new DisplayType("Map");
		session.save(mapDisplayType);
		
		DisplayType pictureDisplayType = new DisplayType("Picture");
		session.save(pictureDisplayType);
		
		
		System.out.println("Set up element categories");
	}
}
