/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
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

import edu.rmit.eres.seaports.model.DisplayType;

/**
 * Test class for the Climate Smart Seaports display types DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DisplayTypeDaoTest {
		
	@Autowired
	private DisplayTypeDao displayTypeDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}

	/**
	 * Test for the find (by ID) method of the DisplayTypeDao class.
	 * The method should return a DisplayType object
	 */
	@Test
	public void findDisplayTypeByIdTest() {
		int displayTypeId = 1;
		DisplayType resDisplayType = displayTypeDao.find(displayTypeId);

		Assert.assertNotNull(resDisplayType);
		Assert.assertEquals(displayTypeId, resDisplayType.getId());
		Assert.assertEquals("Text", resDisplayType.getName()); // Display type with ID 1 has name 'Text'
	}
	
	/**
	 * Test for the find (by ID) method of the DisplayTypeDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findDisplayTypeByIdNullIdTest() {
		displayTypeDao.find((Integer)null);
	}
	
	/**
	 * Test for the find (by ID) method of the DisplayTypeDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findDisplayTypeUnknownIdTest() {
		displayTypeDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for the find (by Name) method of the DisplayTypeDao class.
	 * The method should return a DisplayType object
	 */
	@Test
	public void findDisplayTypeByNameTest() {
		String displayTypeName = "Table";
		DisplayType resDisplayType = displayTypeDao.find(displayTypeName);

		Assert.assertNotNull(resDisplayType);
		Assert.assertEquals(3, resDisplayType.getId()); // 'Table' has ID 3 in the test database
		Assert.assertEquals(displayTypeName, resDisplayType.getName());
	}
	
	/**
	 * Test for the find (by Name) method of the DisplayTypeDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findDisplayTypeByNameNullNameTest() {
		displayTypeDao.find((String)null);
	}
	
	/**
	 * Test for the find (by Name) method of the DisplayTypeDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findDisplayTypeByNameUnknownNameTest() {
		displayTypeDao.find("UNKNOWN NAME"); // Non-Existing name
	}
	
	/**
	 * Test for the getAll method of the DisplayTypeDao class.
	 * The method should return a list of 5 DisplayType objects
	 */
	@Test
	public void getAllDisplayTypeTest() {
		List<DisplayType> resDisplayTypes = displayTypeDao.getAll();
		
		Assert.assertNotNull(resDisplayTypes);
		Assert.assertEquals(5, resDisplayTypes.size()); // There are 5 display types in the test database
		for (DisplayType resDisplayType : resDisplayTypes) {
			Assert.assertNotNull(resDisplayType);
			Assert.assertEquals(DisplayType.class, resDisplayType.getClass());
		}
	}
}
