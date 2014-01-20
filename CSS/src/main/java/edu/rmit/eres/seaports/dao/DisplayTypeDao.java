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

import edu.rmit.eres.seaports.model.*;

/**
 * Data Access Object for the display types
 * @author Guillaume Prevost
 */
@Repository
public class DisplayTypeDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the Display Types are stored
	 */
	private final static String TABLE_NAME = "DisplayType";
	
	/**
	 * Retrieve the region in the database associated to a unique ID 
	 * @param id: the unique ID of the required region
	 * @return the region associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public DisplayType find(Integer id) throws NoResultException {
		DisplayType displayType = entityManager.find(DisplayType.class, id);
		if (displayType == null)
			throw new NoResultException(ERR_NO_RESULT);
		return displayType;
	}
	
	/**
	 * Retrieve a display type in the Database by its name. It isn't supposed to be 2 display types with the same name, but if it happened to be the case, only one display type would be returned.
	 * @param name: the name of the display type to retrieve
	 * @return the display type matching the given name
	 * @throws NoResultException: if no display type with the given name is found in the database
	 */
	@Transactional
	public DisplayType find(String name) throws NoResultException {
		try {
			Query query = entityManager.createQuery("SELECT r FROM " + TABLE_NAME + " r WHERE r.name = :name");
			query.setParameter("name", name);
			DisplayType displayType = (DisplayType)(query.getSingleResult());
			return displayType;
		}
		catch (Exception e)
		{
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve a list of all the display types in the database
	 * @return the list of all the display types in the database
	 */
	@Transactional
	public List<DisplayType> getAll() {
		Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d");
	    return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of display types returned by the query and checked
	 */
	private List<DisplayType> performQueryAndCheckResultList(Query query) {
		try {
			List<DisplayType> displayTypes = new ArrayList<DisplayType>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof DisplayType)
					displayTypes.add((DisplayType)(obj));
			}
			return displayTypes;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No display type found corresponding to the specified name";
}
