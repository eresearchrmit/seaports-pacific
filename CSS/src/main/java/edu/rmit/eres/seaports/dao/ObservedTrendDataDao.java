/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Variable;
import edu.rmit.eres.seaports.model.data.ObservedTrendData;

/**
 * Data Access Object for the Observed Trend data
 * @author Guillaume Prevost
 */
@Repository
public class ObservedTrendDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ObservedTrendVariableDao observedTrendVariableDao;
	
	/**
	 * The name of the table in the database where the Observed Trend Data are stored
	 */
	public final static String TABLE_NAME = "ObservedTrendData";
	
	/**
	 * Retrieve an ObservedTrendData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required ObservedTrendData
	 * @return the ObservedTrendData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public ObservedTrendData find(Integer id) throws NoResultException {
		ObservedTrendData data = entityManager.find(ObservedTrendData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve all the ObservedTrendData matching the observed trend variable given as parameter
	 * @param variableName: the name of the Observed Trend data to match
	 * @param region: the region of the Observed Trend data to match
	 * @return the list of ObservedTrendData that match the given parameter
	 * @throws NoResultException if no ObservedTrendData matches the given parameter
	 */
	@Transactional
	public List<ObservedTrendData> find(Region region, String variableName) throws NoResultException {
		
		Variable variable = observedTrendVariableDao.find(variableName);		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.variable = :variable");
			query.setParameter("region", region);
			query.setParameter("variable", variable);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of ObservedTrendData returned by the query and checked
	 */
	private List<ObservedTrendData> performQueryAndCheckResultList(Query query) {
		try {
			List<ObservedTrendData> observedTrendData = new ArrayList<ObservedTrendData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof ObservedTrendData)
					observedTrendData.add((ObservedTrendData)(obj));
			}
			return observedTrendData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given Observed Trend data into the database, by adding it or updating it
	 * @param observedTrendData: the Observed Trend data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public ObservedTrendData save(ObservedTrendData observedTrendData) {
		if (observedTrendData.getId() == 0) {
			entityManager.persist(observedTrendData);
			return observedTrendData;
		}
		else {
			entityManager.merge(observedTrendData);
			return observedTrendData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No Observed Trend data found corresponding to the specified parameters";
}