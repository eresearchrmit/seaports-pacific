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

import edu.rmit.eres.seaports.model.ClimateEmissionScenario;
import edu.rmit.eres.seaports.model.CmarData;
import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Variable;

/**
 * Data Access Object for the CMAR data
 * @author Guillaume Prevost
 */
@Repository
public class CmarDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private ClimateEmissionScenarioDao climateEmissionScenarioDao;
	
	@Autowired
	private CsiroVariableDao climateVariableDao;
	
	/**
	 * The name of the table in the database where the CMAR Data are stored
	 */
	public final static String TABLE_NAME = "CmarData";
	
	/**
	 * Retrieve an CmarData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required CmarData
	 * @return the CmarData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public CmarData find(Integer id) throws NoResultException {
		CmarData cmarData = entityManager.find(CmarData.class, id);
		if (cmarData == null)
			throw new NoResultException(ERR_NO_RESULT);
		return cmarData;
	}
	
	/**
	 * Retrieve all the CmarData matching the region, emission scenario, climate model and year that are given as parameters
	 * @param regionName: the name of the region to match
	 * @param emissionScenario: the emission scenario to match
	 * @param climateModel: the climate model to match
	 * @param year: the year to match
	 * @return the list of CmarData that match all the given parameters
	 * @throws NoResultException if no CmarData matches the given parameters
	 */
	@Transactional
	public List<CmarData> find(String regionName, String emissionScenarioName, String modelName, Integer year) throws NoResultException {
		Region region = regionDao.find(regionName);
		ClimateEmissionScenario emissionScenario = climateEmissionScenarioDao.find(emissionScenarioName);
		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.emissionScenario = :emissionScenario AND d.modelName = :modelName AND d.year = :year");
			query.setParameter("region", region);
			query.setParameter("emissionScenario", emissionScenario);
			query.setParameter("modelName", modelName);
			query.setParameter("year", year);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve the CmarData matching the required variable, region, emission scenario and year that are given as parameters
	 * @param regionName: the name of the region to match
	 * @param emissionScenario: the emission scenario to match
	 * @param year: the year to match
	 * @param variableName: the name of the variable to match
	 * @return the (unique) CmarData that match all the given parameters
	 * @throws NoResultException if no CmarData matches the given parameters
	 */
	@Transactional
	public List<CmarData> find(String regionName, String emissionScenarioName, Integer year, String variableName) throws NoResultException {
		Region region = regionDao.find(regionName);
		ClimateEmissionScenario emissionScenario = climateEmissionScenarioDao.find(emissionScenarioName);
		Variable variable = climateVariableDao.find(variableName);
		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.emissionScenario = :emissionScenario AND d.variable = :variable AND d.year = :year");
			query.setParameter("region", region);
			query.setParameter("emissionScenario", emissionScenario);
			query.setParameter("variable", variable);
			query.setParameter("year", year);
			
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve the CmarData matching the required variable, region, emission scenario and year that are given as parameters
	 * @param regionName: the name of the region to match
	 * @param emissionScenario: the emission scenario to match
	 * @param year: the year to match
	 * @param variableId: the id of the variable to match
	 * @return the (unique) CmarData that match all the given parameters
	 * @throws NoResultException if no CmarData matches the given parameters
	 */
	@Transactional
	public List<CmarData> find(String regionName, String emissionScenarioName, Integer year, int variableId) throws NoResultException {
		Region region = regionDao.find(regionName);
		ClimateEmissionScenario emissionScenario = climateEmissionScenarioDao.find(emissionScenarioName);
		Variable variable = climateVariableDao.find(variableId);
		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.emissionScenario = :emissionScenario AND d.variable = :variable AND d.year = :year");
			query.setParameter("region", region);
			query.setParameter("emissionScenario", emissionScenario);
			query.setParameter("variable", variable);
			query.setParameter("year", year);
			
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve the CmarData matching the required variable, region, emission scenario, climate model and year that are given as parameters
	 * @param regionName: the name of the region to match
	 * @param emissionScenario: the emission scenario to match
	 * @param climateModel: the climate model to match
	 * @param year: the year to match
	 * @param variableName: the name of the variable to match
	 * @return the (unique) CmarData that match all the given parameters
	 * @throws NoResultException if no CmarData matches the given parameters
	 */
	@Transactional
	public CmarData find(String regionName, String emissionScenarioName, String modelName, Integer year, String variableName) throws NoResultException {
		Region region = regionDao.find(regionName);
		ClimateEmissionScenario emissionScenario = climateEmissionScenarioDao.find(emissionScenarioName);
		Variable variable = climateVariableDao.find(variableName);
		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.emissionScenario = :emissionScenario AND d.modelName = :modelName AND d.variable = :variable AND d.year = :year");
			query.setParameter("region", region);
			query.setParameter("emissionScenario", emissionScenario);
			query.setParameter("modelName", modelName);
			query.setParameter("variable", variable);
			query.setParameter("year", year);
			
			return (CmarData)(query.getSingleResult());
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of CmarData returned by the query and checked
	 */
	private List<CmarData> performQueryAndCheckResultList(Query query) {
		try {
			List<CmarData> cmarData = new ArrayList<CmarData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof CmarData)
					cmarData.add((CmarData)(obj));
			}
			return cmarData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given CMAR data into the database, by adding it or updating it
	 * @param csiroData: the CMAR data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public CmarData save(CmarData cmarData) {
		if (cmarData.getId() == 0) {
			entityManager.persist(cmarData);
			return cmarData;
		}
		else {
			entityManager.merge(cmarData);
			return cmarData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No CMAR data found corresponding to the specified parameters";
}