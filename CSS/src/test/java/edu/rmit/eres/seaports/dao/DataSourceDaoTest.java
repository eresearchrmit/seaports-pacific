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

import edu.rmit.eres.seaports.model.DataSource;

/**
 * Test class for the Climate Smart Seaports data sources DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DataSourceDaoTest {
	
	@Autowired
	private DataSourceDao dataSourceDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}

	/**
	 * Test for the find (by ID) method of the DataSourceDao class.
	 * The method should return a DataSource object
	 */
	@Test
	public void findDataSourceyIdTest() {
		int dataSourceId = 1;
		DataSource resDataSource = dataSourceDao.find(dataSourceId);

		Assert.assertNotNull(resDataSource);
		Assert.assertEquals(dataSourceId, resDataSource.getId());
		Assert.assertEquals("CSIRO", resDataSource.getName()); // 'CSIRO' has ID 1
	}
	
	/**
	 * Test for the find (by ID) method of the DataSourceDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findRegionByIdNullIdTest() {
		dataSourceDao.find((Integer)null);
	}
	
	/**
	 * Test for the find (by ID) method of the DataSourceDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findRegionUnknownIdTest() {
		dataSourceDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceDao class.
	 * The method should return a DataSource object
	 */
	@Test
	public void findDataSourceByNameTest() {
		String dataSourceName = "CSIRO";
		DataSource resDataSource  = dataSourceDao.find(dataSourceName);

		Assert.assertNotNull(resDataSource);
		Assert.assertEquals(1, resDataSource.getId()); // 'CSIRO' has ID 1
		Assert.assertEquals(dataSourceName, resDataSource.getName());
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findDataSourceByNameNullNameTest() {
		dataSourceDao.find((String)null);
	}
	
	/**
	 * Test for the find (by Name) method of the DataSourceDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findDataSourceByNameUnknownNameTest() {
		dataSourceDao.find("UNKNOWN NAME"); // Non-Existing name
	}
	
	/**
	 * Test for the getAll method of the DataSourceDao class.
	 * The method should return a list of 1 DataSource objects
	 */
	@Test
	public void getAllDataSourceTest() {
		List<DataSource> resDataSources = dataSourceDao.getAll();
		
		Assert.assertNotNull(resDataSources);
		Assert.assertEquals(1, resDataSources.size()); // 1 data source in the test database
		for (DataSource resDataSource : resDataSources) {
			Assert.assertNotNull(resDataSource);
			Assert.assertEquals(DataSource.class, resDataSource.getClass().getSuperclass());
		}
	}
}
