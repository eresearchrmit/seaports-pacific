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
 * Data Access Object for the parameter's options
 * @author Guillaume Prevost
 * @since 10th Jan. 2014
 */
@Repository
public class DataSourceParameterOptionDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the element categories are stored
	 */
	public final static String TABLE_NAME = "DataSourceParameterOption";
	
	/**
	 * Retrieve the parameter's option in the database associated to a unique ID 
	 * @param id: the unique ID of the required parameter's option
	 * @return the parameter's option associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public DataSourceParameterOption find(Integer id) throws NoResultException {
		DataSourceParameterOption option = entityManager.find(DataSourceParameterOption.class, id);
		if (option == null)
			throw new NoResultException(ERR_NO_SUCH_OPTION);
		return option;
	}
	
	/**
	 * Retrieve an parameter's option in the Database by its name. It isn't supposed to be 2 parameter's options 
	 * with the same name, but if it happened to be the case, only one parameter's option would be returned.
	 * @param name: the name of the parameter's option to retrieve
	 * @return the parameter's option matching the given name
	 * @throws NoResultException: if no parameter's optionwith the given name is found in the database
	 */
	@Transactional
	public DataSourceParameterOption find(String name) throws NoResultException {
		if (name == null)
			throw new IllegalArgumentException();
		
		try {
			Query query = entityManager.createQuery("SELECT r FROM " + DataSourceParameterOptionDao.TABLE_NAME + " r WHERE r.name = :name");
			query.setParameter("name", name);
			DataSourceParameterOption option = (DataSourceParameterOption)(query.getSingleResult());
			return option;
		}
		catch (Exception e) {
			throw new NoResultException(ERR_NO_SUCH_OPTION);
		}
	}
	
	/**
	 * Get a list of all the parameter's options
	 * @return the list of all the parameter's options
	 */
	@Transactional
	public List<DataSourceParameterOption> getAll() {
		Query query = entityManager.createQuery("SELECT c FROM " + DataSourceParameterOptionDao.TABLE_NAME + " c");
		return performQueryAndCheckResultList(query);		
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of parameter's options returned by the query and checked
	 */
	private List<DataSourceParameterOption> performQueryAndCheckResultList(Query query) {
		try {
			List<DataSourceParameterOption> options  = new ArrayList<DataSourceParameterOption>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof DataSourceParameterOption)
					options.add((DataSourceParameterOption)(obj));
			}
			return options;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_SUCH_OPTION = "No such parameter's option could be found";
}
