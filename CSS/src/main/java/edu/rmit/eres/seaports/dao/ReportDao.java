/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.model.User;

/**
 * Data Access Object for the user stories (Workboard and Reports)
 * @author Guillaume Prevost
 */
@Repository
public class ReportDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * The name of the table in the database where the Reports are stored
	 */
	private final static String TABLE_NAME = "Report";
	
	/**
	 * Retrieve the report in the database associated to a unique ID
	 * @param id: the unique ID of the required report
	 * @return the report associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public Report find(Integer id) throws NoResultException {
		Report report = entityManager.find(Report.class, id);
		if (report == null)
			throw new NoResultException(ERR_NO_SUCH_USERSTORY);
		return report;
	}
	
	/**
	 * Retrieve a list of all the reports in the database.
	 * WARNING: the use of this method can be time and resource consuming !
	 * @return the list of all the user reports in the database
	 */
	public List<Report> getAllStories() {
		Query query = entityManager.createQuery("SELECT report FROM " + TABLE_NAME + " report");
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the published reports
	 * @return the list of all the published reports
	 */
	public List<Report> getAllPublishedStories() {		
		Query query = entityManager.createQuery("SELECT report FROM " + TABLE_NAME + " report WHERE report.mode = :mode AND report.access = :access") ;
		query.setParameter("access", "public");
		query.setParameter("mode", "published");
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the reports belonging to a user
	 * @param user: the user to retrieve the reports of
	 * @return the list of the user's reports
	 */
	public List<Report> getReports(User user) {		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.owner = :owner");
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the stories belonging to a user
	 * @param user: the user to retrieve the reports of
	 * @return the list of the user's reports
	 */
	public List<Report> getUserStories(User user) {		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.mode != :mode AND us.owner = :owner") ;
		query.setParameter("mode", "active"); // All except active
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve only the private stories belonging to a user
	 * @param user: the user to retrieve the private stories
	 * @return the list of the private stories of the given user
	 */
	public List<Report> getPrivateUserStories(User user) {
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.mode != :mode AND us.access = :access AND us.owner = :owner") ;
		query.setParameter("access", "private"); // Only Private
		query.setParameter("mode", "active"); // All except active
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve only the private stories belonging to a user
	 * @param user: the user to retrieve the public stories
	 * @return the list of the public stories of the given user
	 */
	public List<Report> getPublicUserStories(User user) {		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.mode != :mode AND us.access = :access AND us.owner = :owner") ;
		query.setParameter("access", "public"); // Only public
		query.setParameter("mode", "active"); // All except active
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve only the stories belonging to a user that he has decided to publish
	 * @param user: the user to retrieve the published stories
	 * @return the list of the published stories of the given user
	 */
	public List<Report> getPublishedUserStories(User user) {		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.mode != :mode AND us.access = :access AND us.owner = :owner") ;
		query.setParameter("access", "public");
		query.setParameter("mode", "published");
		query.setParameter("owner", user);
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve the Workboard (the only story in 'active' mode) of a user
	 * @param user: the user to retrieve the workboard of
	 * @return the Workboard of the given user
	 */
	public Report getWorkboard(User user) {		
		Report workboard = null;
		try {
			Query query = entityManager.createQuery("SELECT u FROM " + TABLE_NAME + " u WHERE u.mode = :mode AND u.owner = :owner") ;
			query.setParameter("mode", "active"); // Only the active one
			query.setParameter("owner", user);
			
			workboard = (Report)(query.getSingleResult());
			return workboard;
		}
		catch (NoResultException e) {
			return workboard;
		}
		catch (Exception e) {
			return workboard;
		}
	}
	
	/**
	 * Save a report in the database. Adds it if it doesn't exist or update it
	 * @param report: the report to save
	 * @return the saved report
	 */
	@Transactional
	public Report save(Report report) throws IllegalArgumentException {
		if (report == null || report.getName() == null || report.getName().isEmpty())
			throw new IllegalArgumentException();
				
		if (report.getMode() == null)
			report.setMode("active");
		if (report.getAccess() == null)
			report.setAccess("private");
		
		if (report.getId() == 0) {
			entityManager.persist(report);
			return report;
		}
		else {
			return entityManager.merge(report);
		}		
	}
	
	/**
	 * Delete a report from the database along with all the data element it contains
	 * @param report: the report to delete
	 */
	@Transactional
	public void delete(Report report) throws IllegalArgumentException {
		if (report == null)
			throw new IllegalArgumentException();

		report = entityManager.find(Report.class, report.getId());
		entityManager.remove(report);
	}

	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of user stories returned by the query and checked
	 */
	private List<Report> performQueryAndCheckResultList(Query query) {
		try {
			List<Report> reports = new ArrayList<Report>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof Report)
					reports.add((Report)(obj));
			}
			return reports;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_SUCH_USERSTORY = "No report could be found with the specified parameters.";
}