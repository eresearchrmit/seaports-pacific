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
 * Data Access Object for the reports
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
			throw new NoResultException(ERR_NO_SUCH_REPORT);
		return report;
	}
	
	/**
	 * Retrieve a list of all the reports in the database.
	 * WARNING: the use of this method can be time and resource consuming !
	 * @return the list of all the user reports in the database
	 */
	public List<Report> getAllReports() {
		Query query = entityManager.createQuery("SELECT report FROM " + TABLE_NAME + " report");
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the published reports
	 * @return the list of all the public reports
	 */
	public List<Report> getAllPublicReports() {		
		Query query = entityManager.createQuery("SELECT report FROM " + TABLE_NAME + " report WHERE report.access = :access") ;
		query.setParameter("access", "public"); // Only public reports
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the reports belonging to a user
	 * @param user: the user to retrieve the reports of
	 * @return the list of the user's reports
	 */
	public List<Report> getUserReports(User user) {
		if (user == null)
			throw new IllegalArgumentException();
		
		Query query = entityManager.createQuery("SELECT report FROM " + TABLE_NAME + " report WHERE report.owner = :owner");
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	
	/**
	 * Retrieve only the private reports belonging to a user
	 * @param user: the user to retrieve the private reports
	 * @return the list of the private reports of the given user
	 */
	public List<Report> getPrivateUserReports(User user) {
		if (user == null)
			throw new IllegalArgumentException();
		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.access = :access AND us.owner = :owner") ;
		query.setParameter("access", "private"); // Only private reports
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	
	/**
	 * Retrieve only the private reports belonging to a user
	 * @param user: the user to retrieve the public reports
	 * @return the list of the public reports of the given user
	 */
	public List<Report> getPublicUserReports(User user) {		
		if (user == null)
			throw new IllegalArgumentException();
		
		Query query = entityManager.createQuery("SELECT us FROM " + TABLE_NAME + " us WHERE us.access = :access AND us.owner = :owner") ;
		query.setParameter("access", "public"); // Only public reports
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
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
	 * @return the list of reports returned by the query and checked
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
	public static final String ERR_NO_SUCH_REPORT = "No report could be found with the specified parameters.";
}