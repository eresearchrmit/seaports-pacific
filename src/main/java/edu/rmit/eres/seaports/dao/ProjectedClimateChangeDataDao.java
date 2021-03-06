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
import edu.rmit.eres.seaports.model.data.ClimateEmissionScenario;
import edu.rmit.eres.seaports.model.data.ProjectedClimateChangeData;

/**
 * Data Access Object for the Projected Climate Change data
 * @author Guillaume Prevost
 */
@Repository
public class ProjectedClimateChangeDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ProjectedClimateChangeVariableDao projectedClimateChangeVariableDao;
	
	/**
	 * The name of the table in the database where the Future Trend Data are stored
	 */
	public final static String TABLE_NAME = "ProjectedClimateChangeData";
	
	/**
	 * Retrieve an FutureTrendData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required ObservedTrendData
	 * @return the FutureTrendData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public ProjectedClimateChangeData find(Integer id) throws NoResultException {
		ProjectedClimateChangeData data = entityManager.find(ProjectedClimateChangeData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve all the FutureTrendData matching the future trend variable given as parameter
	 * @param variableName: the name of the Future Trend data to match
	 * @param region: the region of the Future Trend data to match
	 * @return the list of FutureTrendData that match the given parameter
	 * @throws NoResultException if no FutureTrendData matches the given parameter
	 */
	@Transactional
	public List<ProjectedClimateChangeData> find(Region region, String variableName) throws NoResultException {
		
		Variable variable = projectedClimateChangeVariableDao.find(variableName);		
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
	 * Retrieve all the FutureTrendData matching the future trend variable given as parameter
	 * @param variableName: the name of the Future Trend data to match
	 * @param region: the region of the Future Trend data to match
	 * @param emissionScenario: the CO2 emission scenario of the Future Trend data to match
	 * @return the list of FutureTrendData that match the given parameter
	 * @throws NoResultException if no FutureTrendData matches the given parameter
	 */
	@Transactional
	public List<ProjectedClimateChangeData> find(Region region, String variableName, ClimateEmissionScenario emissionScenario) throws NoResultException {
		
		Variable variable = projectedClimateChangeVariableDao.find(variableName);		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.variable = :emissionScenario AND d.emissionScenario = :emissionScenario");
			query.setParameter("region", region);
			query.setParameter("variable", variable);
			query.setParameter("emissionScenario", emissionScenario);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	
	/**
	 * Retrieve all the FutureTrendData matching the future trend variable given as parameter
	 * @param variableName: the name of the Future Trend data to match
	 * @param region: the region of the Future Trend data to match
	 * @param year: the year of the Future Trend data to match
	 * @return the list of FutureTrendData that match the given parameter
	 * @throws NoResultException if no FutureTrendData matches the given parameter
	 */
	@Transactional
	public List<ProjectedClimateChangeData> find(Region region, String variableName, Integer year) throws NoResultException {
		
		Variable variable = projectedClimateChangeVariableDao.find(variableName);		
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.variable = :variable AND d.year = :year");
			query.setParameter("region", region);
			query.setParameter("variable", variable);
			query.setParameter("year", year);
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
	private List<ProjectedClimateChangeData> performQueryAndCheckResultList(Query query) {
		try {
			List<ProjectedClimateChangeData> futureTrendData = new ArrayList<ProjectedClimateChangeData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof ProjectedClimateChangeData)
					futureTrendData.add((ProjectedClimateChangeData)(obj));
			}
			return futureTrendData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given Future Trend data into the database, by adding it or updating it
	 * @param futureTrendData: the Future Trend data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public ProjectedClimateChangeData save(ProjectedClimateChangeData futureTrendData) {
		if (futureTrendData.getId() == 0) {
			entityManager.persist(futureTrendData);
			return futureTrendData;
		}
		else {
			entityManager.merge(futureTrendData);
			return futureTrendData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No Projected Climate Change data found corresponding to the specified parameters";
}