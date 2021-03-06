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

import edu.rmit.eres.seaports.model.DataSourceParameterOption;

/**
 * Test class for the Climate Smart Seaports data source parameter options DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DataSourceParameterOptionDaoTest {
	
	@Autowired
	private DataSourceParameterOptionDao dataSourceParameterOptionDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}

	/**
	 * Test for the find (by ID) method of the DataSourceParameterOptionDao class.
	 * The method should return a DataSource object
	 */
	@Test
	public void findDataSourceParameterByIdTest() {
		int optionId = 1;
		DataSourceParameterOption resOption = dataSourceParameterOptionDao.find(optionId);

		Assert.assertNotNull(resOption);
		Assert.assertEquals(optionId, resOption.getId());
		Assert.assertEquals("Minimum Temperatures", resOption.getName()); // 'Minimum Temperatures' has ID 1
	}
	
	/**
	 * Test for the find (by ID) method of the DataSourceParameterOptionDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findDataSourceParameterIdNullIdTest() {
		dataSourceParameterOptionDao.find((Integer)null);
	}
	
	/**
	 * Test for the find (by ID) method of the DataSourceParameterOptionDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findDataSourceParameterUnknownIdTest() {
		dataSourceParameterOptionDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceParameterOptionDao class.
	 * The method should return a DataSource object
	 */
	@Test
	public void findDataSourceParameterByNameTest() {
		String optionName = "Maximum Temperatures";
		DataSourceParameterOption resOption  = dataSourceParameterOptionDao.find(optionName);

		Assert.assertNotNull(resOption);
		Assert.assertEquals(2, resOption.getId()); // 'Maximum Temperatures' has ID 2
		Assert.assertEquals(optionName, resOption.getName());
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceParameterOptionDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findDataSourceByNameNullNameTest() {
		dataSourceParameterOptionDao.find((String)null);
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceParameterOptionDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findDataSourceByNameUnknownNameTest() {
		dataSourceParameterOptionDao.find("UNKNOWN NAME"); // Non-Existing name
	}
	
	/**
	 * Test for the getAll method of the DataSourceDao class.
	 * The method should return a list of 1 DataSource objects
	 */
	@Test
	public void getAllDataSourceTest() {
		List<DataSourceParameterOption> resOptions = dataSourceParameterOptionDao.getAll();
		
		Assert.assertNotNull(resOptions);
		//Assert.assertEquals(227, resOptions.size()); // 227 options in the test database
		for (DataSourceParameterOption resOption : resOptions) {
			Assert.assertNotNull(resOption);
			Assert.assertEquals(DataSourceParameterOption.class, resOption.getClass());
		}
	}
}
