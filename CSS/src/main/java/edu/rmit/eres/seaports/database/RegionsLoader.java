/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.rmit.eres.seaports.model.*;

/**
 * Class used to load the Regions and Seaports in the database
 * @author Guillaume Prevost
 */
@SuppressWarnings("deprecation")
public class RegionsLoader {
	
	/**
	 * Main method used to load Regions and Seaports in the database.
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
		
		LoadRegions(session);
		
		session.getTransaction().commit();
	}
	
	/**
	 * Loads the Regions and Seaports in the database
	 * @param session: the Hibernate Session object which takes care of persisting objects in the database
	 */
	public static void LoadRegions(Session session)
	{
		// Regions
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
		
		// Seaports
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
		
		System.out.println("Set up regions and seaports");
	}
}
