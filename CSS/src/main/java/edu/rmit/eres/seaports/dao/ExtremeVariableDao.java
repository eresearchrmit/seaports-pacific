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

import edu.rmit.eres.seaports.model.Variable;

/**
 * Data Access Object for the Observed Extreme Variables
 * @author Guillaume Prevost
 */
@Repository
public class ExtremeVariableDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the Observed Extreme Variables are stored
	 */
	public final static String TABLE_NAME = "ExtremeVariable";
	
	/**
	 * Retrieve the variable in the database associated to a unique ID
	 * @param id: the unique ID of the required variable
	 * @return the variable associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public Variable find(Integer id) {
		Variable variable = entityManager.find(Variable.class, id);
		if (variable == null)
			throw new NoResultException(ERR_NO_RESULT);
		return variable;
	}
	
	/**
	 * Retrieve the (unique) variable matching a name
	 * @param variableName: the name of the variable to match
	 * @return the Extreme variable from the database
	 * @throws NoResultException if no variable matches the given name 
	 */
	@Transactional
	public Variable find(String variableName) throws NoResultException {
		try {
			Query query = entityManager.createQuery("SELECT v FROM " + TABLE_NAME + " v WHERE v.name = :variableName");
			query.setParameter("variableName", variableName);
			return (Variable)(query.getSingleResult());
		}
		catch (Exception e)
		{
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve a list of all the different climate variables in the Database
	 * @return the list of all the different climate variables in the Database
	 */
	@Transactional
	public List<Variable> getAll() {
		Query query = entityManager.createQuery("SELECT v FROM " + TABLE_NAME + " v");
		
		List<Variable> results = new ArrayList<Variable>();
		for (Object obj : query.getResultList()) {
			if (obj instanceof Variable)
			results.add((Variable)(obj));
		}
		
		return results;
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No observed extreme variable found corresponding to the specified name";
}