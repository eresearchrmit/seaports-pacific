/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.data.WeatherEvent;

/**
 * Data Access Object for the weather events (vulnerabilty assessment) 
 * @author Guillaume Prevost
 */
@Repository
public class WeatherEventDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * The name of the table in the database where the CSIRO Data are stored
	 */
	public final static String TABLE_NAME = "WeatherEvent";
	
	/**
	 * Retrieve a Weather Event in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required Weather Event
	 * @return the Weather Event object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public WeatherEvent find(Integer id) throws NoResultException {
		WeatherEvent weatherEvent = entityManager.find(WeatherEvent.class, id);
		if (weatherEvent == null)
			throw new NoResultException(ERR_NO_RESULT);
		return weatherEvent;
	}
	
	/**
	 * Retrieve a region in the Database by its name. It isn't supposed to be 2 regions with the same name, but if it happened to be the case, only one region would be returned.
	 * @param name: the name of the region to retrieve
	 * @return the region matching the given name
	 * @throws NoResultException: if no region with the given name is found in the database
	 */
	@Transactional
	public WeatherEvent findByElem(int elemId) throws NoResultException {
		if (elemId <= 0)
			throw new IllegalArgumentException();
		
		try {
			Query query = entityManager.createQuery("SELECT e FROM " + TABLE_NAME + " e WHERE e.name = :elemId");
			query.setParameter("elemId", elemId);
			WeatherEvent weatherEvent = (WeatherEvent)(query.getSingleResult());
			return weatherEvent;
		}
		catch (Exception e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Saves a given Weather Event into the database, by adding it or updating it
	 * @param weatherEvent: the Weather Event to save in the database
	 * @return the saved weather event
	 */
	@Transactional
	public WeatherEvent save(WeatherEvent weatherEvent) {
		if (weatherEvent.getId() == 0) {
			entityManager.persist(weatherEvent);
			return weatherEvent;
		}
		else {
			entityManager.merge(weatherEvent);
			return weatherEvent;
		}
	}
	
	/**
	 * Delete the weather event associated to the unique ID from the database
	 * @param id: the unique ID of the weather event to delete
	 */
	@Transactional
	public void delete(int id) {
		Query query = entityManager.createQuery("DELETE FROM " + WeatherEventDao.TABLE_NAME + " d WHERE d.id = :id");
		query.setParameter("id", id).executeUpdate();
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No weather event found corresponding to the specified parameters";
}