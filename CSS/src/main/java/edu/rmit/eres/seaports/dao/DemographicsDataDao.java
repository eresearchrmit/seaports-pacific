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
import edu.rmit.eres.seaports.model.data.DemographicsData;

/**
 * Data Access Object for the Extreme data
 * @author Guillaume Prevost
 */
@Repository
public class DemographicsDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DemographicsVariableDao demographicsVariableDao;
	
	/**
	 * The name of the table in the database where the demographics Data are stored
	 */
	public final static String TABLE_NAME = "DemographicsData";
	
	/**
	 * Retrieve an DemographicsData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required DemographicsData
	 * @return the DemographicsData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public DemographicsData find(Integer id) throws NoResultException {
		DemographicsData data = entityManager.find(DemographicsData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve all the DemographicsData matching the region given as parameter
	 * @param region: the region of the Demographics data to match
	 * @return the list of DemographicsData that match the given parameter
	 * @throws NoResultException if no DemographicsData matches the given parameter
	 */
	@Transactional
	public List<DemographicsData> find(Region region) throws NoResultException {
			
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region");
			query.setParameter("region", region);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve all the DemographicsData matching the region and the demographics variable given as parameter
	 * @param variableName: the name of the Demographics data to match
	 * @param region: the region of the Demographics data to match
	 * @return the list of DemographicsData that match the given parameter
	 * @throws NoResultException if no DemographicsData matches the given parameter
	 */
	@Transactional
	public List<DemographicsData> find(Region region, String variableName) throws NoResultException {
		
		Variable variable = demographicsVariableDao.find(variableName);		
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
	 * @return the list of DemographicsData returned by the query and checked
	 */
	private List<DemographicsData> performQueryAndCheckResultList(Query query) {
		try {
			List<DemographicsData> demographicsData = new ArrayList<DemographicsData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof DemographicsData)
					demographicsData.add((DemographicsData)(obj));
			}
			return demographicsData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given demographics data into the database, by adding it or updating it
	 * @param demographicsData: the demographics data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public DemographicsData save(DemographicsData demographicsData) {
		if (demographicsData.getId() == 0) {
			entityManager.persist(demographicsData);
			return demographicsData;
		}
		else {
			entityManager.merge(demographicsData);
			return demographicsData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No demographics data found corresponding to the specified parameters";
}