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
 * Data Access Object for the data elements
 * @author Guillaume Prevost
 */
@Repository
public class ElementDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the Elements are stored
	 */
	public final static String TABLE_NAME = "Element";
	
	/**
	 * Retrieve the element in the database associated to a unique ID 
	 * @param id: the unique ID of the required element
	 * @return the element associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public Element find(Integer id) throws NoResultException {
		Element elem = entityManager.find(Element.class, id);
		if (elem == null)
			throw new NoResultException(ERR_NO_SUCH_ELEMENT);
		return elem;
	}
	
	/**
	 * Get a list of all the elements belonging to a report
	 * @param userStory: the report to retrieve the elements of
	 * @return the list of elements of the given report
	 */
	@Transactional
	public List<Element> getElements(Report report) {		
		Query query = entityManager.createQuery("SELECT elem FROM " + ElementDao.TABLE_NAME + " elem WHERE elem.report = :report ");
		query.setParameter("report", report);
		
		return performQueryAndCheckResultList(query);		
	}
	
	/**
	 * Saves a given element into the database, by adding it or updating it
	 * @param element: the element to save in the database
	 * @return the saved element
	 */
	@Transactional
	public Element save(Element element) throws IllegalArgumentException {
		if (element == null)
			throw new IllegalArgumentException();
		
		if (element.getId() == 0) {
			entityManager.persist(element);
			return element;
		}
		else {
			entityManager.merge(element);
			return element;
		}		
	}
	
	/**
	 * Saves a given data element into the database, by adding it or updating it
	 * @param element: the data element to save in the database
	 * @return the saved data element
	 */
	@Transactional
	public Element save(DataElement dataElement) throws IllegalArgumentException {
		if (dataElement == null)
			throw new IllegalArgumentException();
		
		if (dataElement.getId() == 0) {
			entityManager.persist(dataElement);
			return dataElement;
		}
		else {
			entityManager.merge(dataElement);
			return dataElement;
		}
	}
	
	/**
	 * Delete from the database the data element associated to the unique ID
	 * @param id: the unique ID of the data element to delete
	 */
	@Transactional
	public void delete(Element element) throws IllegalArgumentException {
		if (element == null)
			throw new IllegalArgumentException();	
		
		Element elem = entityManager.find(Element.class, element.getId());
		entityManager.remove(elem);
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of data elements returned by the query and checked
	 */
	private List<Element> performQueryAndCheckResultList(Query query) {
		try {
			List<Element> elements  = new ArrayList<Element>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof Element)
					elements.add((Element)(obj));
			}
			return elements;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_SUCH_ELEMENT = "No such element could be found";
}
