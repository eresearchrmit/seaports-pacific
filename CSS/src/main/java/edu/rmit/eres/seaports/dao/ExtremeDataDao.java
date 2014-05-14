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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Variable;
import edu.rmit.eres.seaports.model.data.ExtremeData;

/**
 * Data Access Object for the Extreme data
 * @author Guillaume Prevost
 */
@Repository
public class ExtremeDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ExtremeVariableDao extremeVariableDao;
	
	/**
	 * The name of the table in the database where the Extreme Data are stored
	 */
	public final static String TABLE_NAME = "ExtremeData";
	
	/**
	 * Retrieve an ExtremeData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required ExtremeData
	 * @return the ExtremeData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public ExtremeData find(Integer id) throws NoResultException {
		ExtremeData data = entityManager.find(ExtremeData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve all the ExtremeData matching the observed trend variable given as parameter
	 * @param variableName: the name of the Extreme data to match
	 * @param region: the region of the Extreme data to match
	 * @return the list of ExtremeData that match the given parameter
	 * @throws NoResultException if no ExtremeData matches the given parameter
	 */
	@Transactional
	public List<ExtremeData> find(Region region, String variableName) throws NoResultException {
		
		Variable variable = extremeVariableDao.find(variableName);		
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
	 * Retrieve all the ExtremeData matching the observed trend variable given as parameter
	 * @param variableName: the name of the Extreme data to match
	 * @param region: the region of the Extreme data to match
	 * @param maxYear: the maximum year of the data to match
	 * @return the list of ExtremeData that match the given parameter
	 * @throws NoResultException if no ExtremeData matches the given parameter
	 */
	@Transactional
	public List<ExtremeData> find(Region region, String variableName, int maxYear) throws NoResultException {
		
		Variable variable = extremeVariableDao.find(variableName);		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.variable = :variable AND d.year < :maxYear");
			query.setParameter("region", region);
			query.setParameter("variable", variable);
			query.setParameter("maxYear", maxYear);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of ExtremeData returned by the query and checked
	 */
	private List<ExtremeData> performQueryAndCheckResultList(Query query) {
		try {
			List<ExtremeData> observedExtremeData = new ArrayList<ExtremeData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof ExtremeData)
					observedExtremeData.add((ExtremeData)(obj));
			}
			return observedExtremeData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given Extreme data into the database, by adding it or updating it
	 * @param observedTrendData: the Extreme data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public ExtremeData save(ExtremeData observedExtremeData) {
		if (observedExtremeData.getId() == 0) {
			entityManager.persist(observedExtremeData);
			return observedExtremeData;
		}
		else {
			entityManager.merge(observedExtremeData);
			return observedExtremeData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No Extreme data found corresponding to the specified parameters";
}