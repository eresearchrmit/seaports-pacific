/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.ElementCategory;

/**
 * Test class for the Climate Smart Seaports element categories DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ElementCategoryDaoTest {
	
	ElementCategory elementCategoryForTest;
	
	ElementCategory elementCategoryToSave;
	
	@Autowired
	private ElementCategoryDao elementCategoryDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
		elementCategoryForTest = new ElementCategory("Non-Climate context", "test category description", "test category help text");
		elementCategoryForTest.setId(1);
		
		elementCategoryToSave = new ElementCategory("new category", "new category description", "new category help text");
	}

	/**
	 * Test for the find (by ID) method of the ElementCategoryDao class.
	 * The method should return a ElementCategory object
	 */
	@Test
	public void findElementCategoryIdTest() {
		int elementCategoryeId = 2;
		ElementCategory resElementCategory = elementCategoryDao.find(elementCategoryeId);

		Assert.assertNotNull(resElementCategory);
		Assert.assertEquals(elementCategoryeId, resElementCategory.getId());
		Assert.assertEquals("Future climate &amp; marine", resElementCategory.getName()); // Display type with ID 3 has name 'Future climate & marine'
		Assert.assertNotNull(resElementCategory.getHelpText());
		Assert.assertNotNull(resElementCategory.getDescriptionText());
	}
	
	/**
	 * Test for the find (by ID) method of the ElementCategoryDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findElementCategoryeByIdNullIdTest() {
		elementCategoryDao.find((Integer)null);
	}
	
	/**
	 * Test for the find (by ID) method of the ElementCategoryDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findElementCategoryUnknownIdTest() {
		elementCategoryDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for the find (by Name) method of the ElementCategoryDao class.
	 * The method should return a ElementCategory object
	 */
	@Test
	public void findElementCategoryByNameTest() {
		String elementCategoryName = "Applications";
		ElementCategory resElementCategory = elementCategoryDao.find(elementCategoryName);

		Assert.assertNotNull(resElementCategory);
		Assert.assertEquals(4, resElementCategory.getId()); // 'Applications' has ID 4 in the test database
		Assert.assertEquals(elementCategoryName, resElementCategory.getName());
		Assert.assertNotNull(resElementCategory.getHelpText());
		Assert.assertNotNull(resElementCategory.getDescriptionText());
	}
	
	/**
	 * Test for the find (by Name) method of the ElementCategoryDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findElementCategoryByNameNullNameTest() {
		elementCategoryDao.find((String)null);
	}
	
	/**
	 * Test for the find (by Name) method of the ElementCategoryDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findElementCategoryByNameUnknownNameTest() {
		elementCategoryDao.find("UNKNOWN NAME"); // Non-Existing name
	}
	
	/**
	 * Test for the getAll method of the ElementCategoryDao class.
	 * The method should return a list of ElementCategory objects
	 */
	@Test
	public void getAllElementCategoryTest() {
		List<ElementCategory> resElementCategories = elementCategoryDao.getAll();
		
		Assert.assertNotNull(resElementCategories);
		Assert.assertEquals(4, resElementCategories.size()); // There are 4 element categories in the test database
		for (ElementCategory resElementCategory : resElementCategories) {
			Assert.assertNotNull(resElementCategory);
			Assert.assertEquals(ElementCategory.class, resElementCategory.getClass());
			Assert.assertNotNull(resElementCategory.getHelpText());
			Assert.assertNotNull(resElementCategory.getDescriptionText());
		}
	}
	
	/**
	 * Test for the save method of the ElementCategoryDao class
	 */
	@Test
	public void saveNewElementCategoryTest() {
		ElementCategory savedCategory = elementCategoryDao.save(elementCategoryToSave);
		
		Assert.assertNotNull(savedCategory.getName());
		Assert.assertEquals(elementCategoryToSave.getName(), savedCategory.getName());
		
		Assert.assertNotNull(savedCategory.getDescriptionText());
		Assert.assertEquals(elementCategoryToSave.getDescriptionText(), savedCategory.getDescriptionText());
		
		Assert.assertNotNull(savedCategory.getHelpText());
		Assert.assertEquals(elementCategoryToSave.getHelpText(), savedCategory.getHelpText());
		
		
		Assert.assertNull(savedCategory.getDataSourcesAvailable());
	}
	
	/**
	 * Test for save with a report specifying only the name.
	 */
	@Test
	public void saveElementCategorytOnlyNameTest() {
		String categoryName = "test";
		ElementCategory elementCategory = new ElementCategory(categoryName);
		
		ElementCategory savedCategory = elementCategoryDao.save(elementCategory);
		
		Assert.assertNotNull(savedCategory.getName());
		Assert.assertEquals(categoryName, savedCategory.getName());
		
		Assert.assertNull(savedCategory.getDescriptionText());
		Assert.assertNull(savedCategory.getHelpText());
		
		Assert.assertNull(savedCategory.getDataSourcesAvailable());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void saveElementCategoryTest() {
		ElementCategory savedCategory = elementCategoryDao.save(elementCategoryForTest);
	
		Assert.assertNotNull(savedCategory.getId());
		Assert.assertEquals(elementCategoryForTest.getId(), savedCategory.getId());
		Assert.assertNotNull(savedCategory.getName());
		Assert.assertEquals(elementCategoryForTest.getName(), savedCategory.getName());
		Assert.assertNotNull(savedCategory.getHelpText());
		Assert.assertEquals(elementCategoryForTest.getHelpText(), savedCategory.getHelpText());
		Assert.assertNotNull(savedCategory.getDescriptionText());
		Assert.assertEquals(elementCategoryForTest.getDescriptionText(), savedCategory.getDescriptionText());
		
	}
	
	/**
	 * Test for save. It should throw an exception since the given category is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveElementCategoryNullTest() {
		elementCategoryDao.save(null);
	}
	
	/**
	 * Test for save. It should throw an exception since the given category name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveElementCategoryEmptyTest() {
		elementCategoryDao.save(new ElementCategory());
	}
	
	/**
	 * Test for delete.
	 */
	@Test
	public void deleteElementCategoryTest() {
		// Retrieve the element category with ID 1. If this fails, the test is inconclusive and therefore fails
		int elementCategoryId = 1;
		ElementCategory elementCategory = elementCategoryDao.find(elementCategoryId);
		Assert.assertNotNull(elementCategory);
		Assert.assertEquals(elementCategoryId, elementCategory.getId());
		elementCategoryDao.delete(elementCategory);
		
		try {
			elementCategory = elementCategoryDao.find(elementCategoryId);
			Assert.fail(); // fails if the category wasn't deleted
		}
		catch(NoResultException e) {
			// Supposed to be triggered after the deletion
		}
	}
	
	/**
	 * Test for delete. It should throw an exception since the given report is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteElementCategoryNullTest() {
		elementCategoryDao.delete(null);
	}
}
