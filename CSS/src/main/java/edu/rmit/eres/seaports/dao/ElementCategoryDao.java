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
 * Data Access Object for the element categories
 * @author Guillaume Prevost
 * @since 6th Jan. 2014
 */
@Repository
public class ElementCategoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The name of the table in the database where the element categories are stored
	 */
	public final static String TABLE_NAME = "ElementCategory";
	
	/**
	 * Retrieve the category in the database associated to a unique ID 
	 * @param id: the unique ID of the required category
	 * @return the category associated to the given unique ID
	 * @throws NoResultException if the search didn't return any result
	 */
	public ElementCategory find(Integer id) throws NoResultException {
		ElementCategory categ = entityManager.find(ElementCategory.class, id);
		if (categ == null)
			throw new NoResultException(ERR_NO_SUCH_ELEMENT_CATEGORY);
		return categ;
	}
	
	/**
	 * Retrieve an element category in the Database by its name. It isn't supposed to be 2 element category with the same name, but if it happened to be the case, only one element category would be returned.
	 * @param name: the name of the element category to retrieve
	 * @return the data source matching the given name
	 * @throws NoResultException: if no element category with the given name is found in the database
	 */
	@Transactional
	public ElementCategory find(String name) throws NoResultException {
		try {
			Query query = entityManager.createQuery("SELECT r FROM " + TABLE_NAME + " r WHERE r.name = :name");
			query.setParameter("name", name);
			ElementCategory elementCategory = (ElementCategory)(query.getSingleResult());
			return elementCategory;
		}
		catch (Exception e)
		{
			throw new NoResultException(ERR_NO_SUCH_ELEMENT_CATEGORY);
		}
	}
	
	/**
	 * Get a list of all the element's categories
	 * @return the list of all the categories
	 */
	@Transactional
	public List<ElementCategory> getAll() {
		Query query = entityManager.createQuery("SELECT c FROM " + ElementCategoryDao.TABLE_NAME + " c");
		return performQueryAndCheckResultList(query);		
	}
	
	/**
	 * Saves a given category into the database, by adding it or updating it
	 * @param category: the element to save in the database
	 * @return the saved category
	 */
	@Transactional
	public ElementCategory save(ElementCategory category) throws IllegalArgumentException {
		if (category == null)
			throw new IllegalArgumentException();
		
		if (category.getId() == 0) {
			entityManager.persist(category);
			return category;
		}
		else {
			entityManager.merge(category);
			return category;
		}		
	}
	
	/**
	 * Delete from the database the element category associated to the unique ID
	 * @param id: the unique ID of the element category to delete
	 */
	@Transactional
	public void delete(ElementCategory category) throws IllegalArgumentException {
		if (category == null)
			throw new IllegalArgumentException();
		
		// Delete the data element itself
		ElementCategory categ = entityManager.find(ElementCategory.class, category.getId());
		entityManager.remove(categ);
	}
	
	/**
	 * Perform a query and check the result list is of the right type
	 * @param query: the query to execute
	 * @return the list of categories returned by the query and checked
	 */
	private List<ElementCategory> performQueryAndCheckResultList(Query query) {
		try {
			List<ElementCategory> categories  = new ArrayList<ElementCategory>();
			for (Object obj : query.getResultList()) {
				if (obj instanceof ElementCategory)
					categories.add((ElementCategory)(obj));
			}
			return categories;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	// Information, success, warning and error messages
	public static final String ERR_NO_SUCH_ELEMENT_CATEGORY = "No such element category could be found";
}
