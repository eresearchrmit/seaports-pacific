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

import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Seaport;

/**
 * Test class for the Climate Smart Seaports seaports DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SeaportDaoTest {
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private SeaportDao seaportDao;

	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}

	/**
	 * Test for the find method of the SeaportDao class.
	 * The method should return a Seaport object
	 */
	@Test
	public void findSeaportTest() {
		String refSeaportCode = "CODE1";
		Seaport resSeaport = seaportDao.find(refSeaportCode);

		Assert.assertNotNull(resSeaport);
		Assert.assertEquals(refSeaportCode, resSeaport.getCode());
		Assert.assertEquals("Port 1", resSeaport.getName()); // Port with code 'CODE1' has name 'Port 1'
	}
	
	/**
	 * Test for the find method of the SeaportDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findSeaportNullCodeTest() {
		seaportDao.find(null);
	}
	
	/**
	 * Test for the find method of the SeaportDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findSeaportUnknownCodeTest() {
		seaportDao.find("UNKNOWN CODE"); // Non-Existing code
	}
	
	/**
	 * Test for the getAll method of the SeaportDao class.
	 * The method should return a list of 2 Seaport objects
	 */
	@Test
	public void getAllSeaportTest() {
		List<Seaport> resSeaports = seaportDao.getAll();
		
		Assert.assertNotNull(resSeaports);
		Assert.assertEquals(3, resSeaports.size()); // There are 3 Seaports in the test database
		for (Seaport resSeaport : resSeaports) {
			Assert.assertNotNull(resSeaport);
			Assert.assertEquals(Seaport.class, resSeaport.getClass());
		}
	}
	
	/**
	 * Test for the getAllInRegion method of the SeaportDao class.
	 * The method should return a list of 2 Seaport objects
	 */
	@Test
	public void getAllSeaportInRegionTest() {
		int regionId = 1;
		Region region = regionDao.find(regionId);
		if (region == null)
			Assert.fail("Could not get the region with ID " + regionId + " to test getAllInRegion(Region) method");
		
		List<Seaport> resSeaports = seaportDao.getAllInRegion(region);
		
		Assert.assertNotNull(resSeaports);
		Assert.assertEquals(3, resSeaports.size()); // There are two seaports in region with ID 1
		for (Seaport resSeaport : resSeaports) {
			Assert.assertNotNull(resSeaport);
			Assert.assertEquals(Seaport.class, resSeaport.getClass());
			Assert.assertNotNull(resSeaport.getRegion());
			Assert.assertEquals(regionId, resSeaport.getRegion().getId());
		}
	}
}
