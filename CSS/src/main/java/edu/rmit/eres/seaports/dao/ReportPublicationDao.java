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

import edu.rmit.eres.seaports.model.ReportPublication;
import edu.rmit.eres.seaports.model.User;

/**
 * Data Access Object for the reports publications
 * @author Guillaume Prevost
 * @since 16th jan. 2014
 */
@Repository
public class ReportPublicationDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * The name of the table in the database where the Reports Publications are stored
	 */
	private final static String TABLE_NAME = "ReportPublication";
	
	/**
	 * Retrieve the report in the database associated to a unique ID
	 * @param id: the unique ID of the required report
	 * @return the report associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public ReportPublication find(Integer id) throws NoResultException {
		ReportPublication snapshot = entityManager.find(ReportPublication.class, id);
		if (snapshot == null)
			throw new NoResultException(ERR_NO_SUCH_PUBLICATION);
		return snapshot;
	}
	
	/**
	 * Retrieve a list of all the report publications in the database.
	 * WARNING: the use of this method can be time and resource consuming !
	 * @return the list of all the report publications in the database
	 */
	public List<ReportPublication> getAllReportPublications() {
		Query query = entityManager.createQuery("SELECT publication FROM " + TABLE_NAME + " publication");
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Retrieve all the report publications belonging to a user
	 * @param user: the user to retrieve the report publications of
	 * @return the list of the user's report publications
	 */
	public List<ReportPublication> getReportPublications(User user) throws IllegalArgumentException {		
		if (user == null)
			throw new IllegalArgumentException();
		
		Query query = entityManager.createQuery("SELECT publication FROM " + TABLE_NAME + " publication WHERE publication.owner = :owner");
		query.setParameter("owner", user); // Only of this user
		
		return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Save a report publication in the database. Adds it if it doesn't exist or update it
	 * @param publication: the report publication to save
	 * @return the saved report publication
	 */
	@Transactional
	public ReportPublication save(ReportPublication publication) throws IllegalArgumentException {
		if (publication == null || publication.getName() == null || publication.getName().isEmpty())
			throw new IllegalArgumentException();
		
		if (publication.getId() == 0) {
			entityManager.persist(publication);
			return publication;
		}
		else {
			return entityManager.merge(publication);
		}		
	}
	
	/**
	 * Delete a report publication from the database
	 * @param publication: the report publication to delete
	 */
	@Transactional
	public void delete(ReportPublication publication) throws IllegalArgumentException {
		if (publication == null)
			throw new IllegalArgumentException();

		publication = entityManager.find(ReportPublication.class, publication.getId());
		entityManager.remove(publication);
	}

	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of reports publications returned by the query and checked
	 */
	private List<ReportPublication> performQueryAndCheckResultList(Query query) {
		try {
			List<ReportPublication> publications = new ArrayList<ReportPublication>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof ReportPublication)
					publications.add((ReportPublication)(obj));
			}
			return publications;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_SUCH_PUBLICATION = "No report publication could be found with the specified parameters.";
}