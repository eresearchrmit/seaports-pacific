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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.*;

/**
 * Data Access Object for the display types
 * @author Guillaume Prevost
 */
@Repository
public class DataSourceDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the data sources are stored
	 */
	private final static String TABLE_NAME = "DataSource";
	
	/**
	 * Retrieve the data source in the database associated to a unique ID 
	 * @param id: the unique ID of the required region
	 * @return the data source associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public DataSource find(Integer id) throws NoResultException {
		DataSource dataSource = entityManager.find(DataSource.class, id);
		if (dataSource == null)
			throw new NoResultException(ERR_NO_RESULT);
		return dataSource;
	}
	
	/**
	 * Retrieve a data source in the Database by its name. It isn't supposed to be 2 data source with the same name, but if it happened to be the case, only one data source would be returned.
	 * @param name: the name of the data source to retrieve
	 * @return the data source matching the given name
	 * @throws NoResultException: if no data source with the given name is found in the database
	 */
	@Transactional
	public DataSource find(String name) throws NoResultException {
		if (name == null)
			throw new IllegalArgumentException();
		
		try {
			Query query = entityManager.createQuery("SELECT r FROM " + TABLE_NAME + " r WHERE r.name = :name");
			query.setParameter("name", name);
			DataSource dataSource = (DataSource)(query.getSingleResult());
			return dataSource;
		}
		catch (Exception e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve a list of all the display types in the database
	 * @return the list of all the display types in the database
	 */
	@Transactional
	public List<DataSource> getAll() {
		Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d");
	    return performQueryAndCheckResultList(query);
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of display types returned by the query and checked
	 */
	private List<DataSource> performQueryAndCheckResultList(Query query) {
		try {
			List<DataSource> dataSources = new ArrayList<DataSource>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof DataSource)
					dataSources.add((DataSource)(obj));
			}
			return dataSources;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No data source found corresponding to the specified name";
}
