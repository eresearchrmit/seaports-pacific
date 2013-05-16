package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import war.model.*;

@SuppressWarnings("deprecation")
public class BomDataLoader {
	
	public static final String bomPictureFolderPath = "src/main/java/database/bom-pictures/";
	
	public static void main(String[] args)
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		config.configure("database/hibernate.cfg.xml");
		new SchemaExport(config).create(true,true);

		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();	
		
		LoadBomData(session);
		
		session.getTransaction().commit();
	}
	
	public static void LoadBomData(Session session)
	{
		
		DateFormat yearFormatter = new SimpleDateFormat("yyyy");
		Date datePastData = new Date();
		
		Date dateShortTermPastStart;
		try { dateShortTermPastStart = yearFormatter.parse("1970"); } 
		catch (ParseException e) { dateShortTermPastStart = new Date(); }
		
		Date dateShortTermPastEnd;
		try { dateShortTermPastEnd = yearFormatter.parse("2012"); } 
		catch (ParseException e) { dateShortTermPastEnd = new Date(); }
		
		Date dateSLRShortTermPastEnd;
		try { dateSLRShortTermPastEnd = yearFormatter.parse("2011"); } 
		catch (ParseException e) { dateSLRShortTermPastEnd = new Date(); }
		
		Date dateLongTermPastStart;
		try { dateLongTermPastStart = yearFormatter.parse("1880"); } 
		catch (ParseException e) { dateLongTermPastStart = new Date(); }
		
		Date dateLongTermPastEnd;
		try { dateLongTermPastEnd = yearFormatter.parse("2012"); } 
		catch (ParseException e) { dateLongTermPastEnd = new Date(); }
		
		session.save(new PastData("Trend in mean temperatures", datePastData, dateShortTermPastStart, dateShortTermPastEnd, 
				"http://www.bom.gov.au/cgi-bin/climate/change/trendmaps.cgi?map=tmean&area=aus&season=0112&period=1970", 
				"trend-mean-temp"));
		
		session.save(new PastData("Trend in maximum temperatures", datePastData, dateShortTermPastStart, dateShortTermPastEnd, 
				"http://www.bom.gov.au/cgi-bin/climate/change/trendmaps.cgi?map=tmax&area=aus&season=0112&period=1970", 
				"trend-max-temp"));
		
		session.save(new PastData("Trend in total annual rainfall", datePastData, dateShortTermPastStart, dateShortTermPastEnd, 
				"http://www.bom.gov.au/cgi-bin/climate/change/trendmaps.cgi?map=rain&area=aus&season=0112&period=1970", 
				"trend-rainfall"));
		
		session.save(new PastData("Long-term sea level rise measurements", datePastData, dateLongTermPastStart, dateLongTermPastEnd, 
				"http://www.cmar.csiro.au/sealevel/sl_hist_few_hundred.html", 
				"long-term-slr"));
		
		session.save(new PastData("Shorter-term changes in sea level", datePastData, dateShortTermPastStart, dateSLRShortTermPastEnd, 
				"http://www.csiro.au/Outcomes/Climate/Understanding/State-of-the-Climate-2012.aspx", 
				"short-term-slr"));
	}
}
