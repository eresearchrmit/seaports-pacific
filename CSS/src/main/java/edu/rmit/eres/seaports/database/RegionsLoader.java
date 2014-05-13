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
		Region r2 = new Region("Papua New Guinea", "");
		Region r3 = new Region("Vanuatu", "");
		Region r4 = new Region("Tonga", "");
		Region r5 = new Region("American Samoa", "");
		Region r6 = new Region("Wallis and Futuna", "");
		Region r7 = new Region("Solomon Islands", "");
		Region r8 = new Region("New Caledonia", "");
		session.save(r1);
		session.save(r2);
		session.save(r3);
		session.save(r4);
		session.save(r5);
		session.save(r6);
		session.save(r7);
		session.save(r8);
		
		// Seaports (as found on http://www.searates.com)
		Seaport port1 = new Seaport("FJSUV", "Suva", -18.133425, 178.423022, r1, "Suva");
		Seaport port2 = new Seaport("FJLTK", "Lautoka", -17.608857, 177.438925, r1, "Lautoka");
		Seaport port3 = new Seaport("FJMAL", "Malau", -16.360833, 179.366413, r1, "Malau");
		Seaport port4 = new Seaport("FJLEV", "Levuka", -17.685122, 178.837091, r1, "Levuka");
		Seaport port5 = new Seaport("FJWAI", "Wairiki", -16.939701, 178.661095, r1, "Wairiki");
		
		//Seaport port4 = new Seaport("FJSVU", "Savu Savu", r1, "Savu Savu");
		//Seaport port5 = new Seaport("FJLBS", "Lambasa", r1, "Lambasa");
		
		session.save(port1);
		session.save(port2);
		session.save(port3);
		session.save(port4);
		session.save(port5);
		
		Seaport port6 = new Seaport("PGGUR", "Alotau", -10.316240, 150.455280, r2, "Alotau");
		Seaport port7 = new Seaport("PGATP", "Aitape", -3.143782, 142.351887, r2, "Aitape");
		Seaport port8 = new Seaport("PGBUA", "Buka", -5.432515, 154.671298, r2, "Buka");
		//Seaport port8 = new Seaport("PGBAA", "Bialla", r2, "Bialla");
		Seaport port9 = new Seaport("PGDAU", "Daru", -9.067936, 143.209952, r2, "Daru");
		Seaport port10 = new Seaport("PGKVG", "Kavieng", -2.585032, 150.789260, r2, "Kavieng");
		Seaport port11 = new Seaport("PGKIE", "Kieta", -6.218631, 155.639023, r2, "Kieta");
		Seaport port12 = new Seaport("PGKIM", "Kimbe", -5.545805, 150.144840, r2, "Kimbe");
		Seaport port13 = new Seaport("PGLAE", "Lae", -6.737482, 147.008960, r2, "Lae");
		Seaport port14 = new Seaport("PGLOR", "Lorengau", -2.025895, 147.271036, r2, "Lorengau, Manus Island");
		Seaport port15 = new Seaport("PGMAG", "Madang", -5.214895, 145.803209, r2, "Madang");
		Seaport port16 = new Seaport("PGROR", "Oro Bay", -8.896389, 148.494167, r2, "Buna");		
		Seaport port17 = new Seaport("PGPOM", "Port Moresby", -9.463927, 147.153606, r2, "Port Moresby");
		Seaport port18 = new Seaport("PGRAB", "Rabaul", -4.202961, 152.163644, r2, "Rabaul");
		//Seaport port19 = new Seaport("PGSQT", "Samarai", r2, "Samarai");
		Seaport port20 = new Seaport("PGVAI", "Vanimo", -2.682094, 141.297859, r2, "Vanimo");
		Seaport port21 = new Seaport("PGWWK", "Wewak", -3.571131, 143.644540, r2, "Wewak");
		session.save(port6);
		session.save(port7);
		session.save(port8);
		session.save(port9);
		session.save(port10);
		session.save(port11);
		session.save(port12);
		session.save(port13);
		session.save(port14);
		session.save(port15);
		session.save(port16);
		session.save(port17);
		session.save(port18);
		//session.save(port19);
		session.save(port20);
		session.save(port21);
		
		System.out.println("Set up regions and seaports");
	}
}
