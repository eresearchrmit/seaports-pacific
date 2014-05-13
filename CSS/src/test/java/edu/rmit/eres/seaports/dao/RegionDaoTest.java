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

import edu.rmit.eres.seaports.model.Region;

/**
 * Test class for the Climate Smart Seaports regions DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegionDaoTest {
	
	@Autowired
	private RegionDao regionDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}

	/**
	 * Test for the find (by ID) method of the RegionDao class.
	 * The method should return a Region object
	 */
	@Test
	public void findRegionByIdTest() {
		int regionId = 1;
		Region resRegion = regionDao.find(regionId);

		Assert.assertNotNull(resRegion);
		Assert.assertEquals(regionId, resRegion.getId());
		Assert.assertEquals("Region 1", resRegion.getName()); // 'Region 1' has ID 2
	}
	
	/**
	 * Test for the find (by ID) method of the RegionDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findRegionByIdNullIdTest() {
		regionDao.find((Integer)null);
	}
	
	/**
	 * Test for the find (by ID) method of the RegionDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findRegionUnknownIdTest() {
		regionDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for the find (by Name) method of the RegionDao class.
	 * The method should return a Region object
	 */
	@Test
	public void findRegionByNameTest() {
		String regionName = "Region 2";
		Region resRegion = regionDao.find(regionName);

		Assert.assertNotNull(resRegion);
		Assert.assertEquals(2, resRegion.getId()); // 'Region 2' has ID 2
		Assert.assertEquals(regionName, resRegion.getName());
	}
	
	/**
	 * Test for the find (by Name) method of the RegionDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findRegionByNameNullNameTest() {
		regionDao.find((String)null);
	}
	
	/**
	 * Test for the find (by Name) method of the RegionDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findRegionByNameUnknownNameTest() {
		regionDao.find("UNKNOWN NAME"); // Non-Existing name
	}
	
	/**
	 * Test for the getAll method of the RegionDao class.
	 * The method should return a list of 2 Region objects
	 */
	@Test
	public void getAllRegionTest() {
		List<Region> resRegions = regionDao.getAll();
		
		Assert.assertNotNull(resRegions);
		Assert.assertEquals(2, resRegions.size()); // Two regions in the test database
		for (Region resRegion : resRegions) {
			Assert.assertNotNull(resRegion);
			Assert.assertEquals(Region.class, resRegion.getClass());
		}
	}
}
