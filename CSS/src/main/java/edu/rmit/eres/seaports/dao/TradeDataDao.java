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
import edu.rmit.eres.seaports.model.data.TradeData;

/**
 * Data Access Object for the Extreme data
 * @author Guillaume Prevost
 */
@Repository
public class TradeDataDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TradeVariableDao tradeVariableDao;
	
	/**
	 * The name of the table in the database where the Extreme Data are stored
	 */
	public final static String TABLE_NAME = "TradeData";
	
	/**
	 * Retrieve an TradeData in the CSS Database from it's unique ID
	 * @param id: the unique ID of the required TradeData
	 * @return the TradeData object corresponding to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public TradeData find(Integer id) throws NoResultException {
		TradeData data = entityManager.find(TradeData.class, id);
		if (data == null)
			throw new NoResultException(ERR_NO_RESULT);
		return data;
	}
	
	/**
	 * Retrieve all the TradeData matching the region and whether the data relates to imported goods or not, given as parameter
	 * @param region: the region of the Trade data to match
	 * @param imported: whether the trade data relates to import (true) or export (false)
	 * @return the list of TradeData that match the given parameter
	 * @throws NoResultException if no TradeData matches the given parameter
	 */
	@Transactional
	public List<TradeData> find(Region region, Boolean imported) throws NoResultException {
			
		try {
			Query query = entityManager.createQuery("SELECT d FROM " + TABLE_NAME + " d WHERE d.region = :region AND d.imported = :imported");
			query.setParameter("region", region);
			query.setParameter("imported", imported);
			return performQueryAndCheckResultList(query);
		}
		catch (NoResultException e) {
			throw new NoResultException(ERR_NO_RESULT);
		}
	}
	
	/**
	 * Retrieve all the TradeData matching the region and the trade variable given as parameter
	 * @param variableName: the name of the Trade data to match
	 * @param region: the region of the Trade data to match
	 * @return the list of TradeData that match the given parameter
	 * @throws NoResultException if no TradeData matches the given parameter
	 */
	@Transactional
	public List<TradeData> find(Region region, String variableName) throws NoResultException {
		
		Variable variable = tradeVariableDao.find(variableName);		
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
	 * @return the list of TradeData returned by the query and checked
	 */
	private List<TradeData> performQueryAndCheckResultList(Query query) {
		try {
			List<TradeData> tradeData = new ArrayList<TradeData>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof TradeData)
					tradeData.add((TradeData)(obj));
			}
			return tradeData;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Saves a given Trade data into the database, by adding it or updating it
	 * @param tradeData: the Trade data to save in the database
	 * @return the saved data
	 */
	@Transactional
	public TradeData save(TradeData tradeData) {
		if (tradeData.getId() == 0) {
			entityManager.persist(tradeData);
			return tradeData;
		}
		else {
			entityManager.merge(tradeData);
			return tradeData;
		}		
	} 
	
	// Information, success, warning and error messages
	public static final String ERR_NO_RESULT = "No trade data found corresponding to the specified parameters";
}