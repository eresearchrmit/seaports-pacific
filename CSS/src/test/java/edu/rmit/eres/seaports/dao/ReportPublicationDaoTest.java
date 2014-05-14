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

import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.model.ReportPublication;
import edu.rmit.eres.seaports.model.User;
import edu.rmit.eres.seaports.security.UserLoginService;

/**
 * Test class for the report publications DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReportPublicationDaoTest {

	User userForTest;
	
	User userWithoutReport;
	
	Report reportForTest;
	
	ReportPublication pubToSave;
	
	@Autowired
	private ReportPublicationDao reportPublicationDao;

	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {		
		userForTest = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		
		userWithoutReport = new User("testuser4", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser4", "testuser4");
		
		String content = "Content Test";
		pubToSave = new ReportPublication("New Publication Test", userForTest, content.getBytes());
	}

	/**
	 * Test for the find method of the ReportPublicationDao class
	 * There should be a report in the test DB matching the fields
	 */
	@Test
	public void findReportPublicationTest() {
		int pubId = 1;
		ReportPublication publishedReport = reportPublicationDao.find(pubId);

		Assert.assertNotNull(publishedReport);
		
		Assert.assertEquals(pubId, publishedReport.getId());
		Assert.assertEquals("User 1 Story 3 (Published)", publishedReport.getName()); // The publication with ID 1 has the name 'User 1 Story 3 (Published)'
		Assert.assertEquals("testuser1", publishedReport.getOwner().getUsername());
	}
	
	/**
	 * Test for the find method of the ElementDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findReportPublicationNullIdTest() {
		reportPublicationDao.find(null);
	}
	
	/**
	 * Test for the find method of the ReportPublicationDao class.
	 * The method should throw an exception since ID 9999 doesn't exist
	 */
	@Test(expected = NoResultException.class)
	public void findReportUnknownIdTest() {
		reportPublicationDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for getAllReportPublications method of the ReportPublicationDao class.
	 * There should be 1 published report in the test database
	 */
	@Test
	public void getAllReportPublicationsTest() {
		List<ReportPublication> pubs = reportPublicationDao.getAllReportPublications();
		
		Assert.assertNotNull(pubs);
		Assert.assertEquals(1, pubs.size()); // 1 published report in the test database
	}
	
	/**
	 * Test for getReportPublications method of the ReportPublicationDao class.
	 * It should retrieve a list of 1 report publication
	 */
	@Test
	public void getUserReportPublications() {
		List<ReportPublication> pubs = reportPublicationDao.getReportPublications(userForTest);
		
		Assert.assertNotNull(pubs);
		Assert.assertEquals(1, pubs.size());
	}
	
	/**
	 * Test for getReportPublications method of the ReportPublicationDao class.
	 * It should retrieve a list of 0 report publication
	 */
	@Test
	public void getUserReportPublicationNoPubTest() {
		List<ReportPublication> pubs = reportPublicationDao.getReportPublications(userWithoutReport);
		
		Assert.assertNotNull(pubs);
		Assert.assertEquals(0, pubs.size());
	}
	
	/**
	 * Test for getReportPublications method of the ReportPublicationDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getUserReportPublicationNullIdTest() {
		reportPublicationDao.getReportPublications(null);
	}
	
	/**
	 * Test for the save method of the ReportPublicationDao class.
	 */
	@Test
	public void saveNewReportPublicationTest() {
		ReportPublication savedPub = reportPublicationDao.save(pubToSave);
		
		Assert.assertNotNull(savedPub.getName());
		Assert.assertEquals(pubToSave.getName(), savedPub.getName());
		
		Assert.assertNotNull(savedPub.getOwner());
		Assert.assertEquals(pubToSave.getOwner().getUsername(), savedPub.getOwner().getUsername());
		
	}
	
	/**
	 * Test for the save method of the ReportPublicationDao class, with a report publication specifying only the name.
	 */
	@Test
	public void saveReportPublicationOnlyNameTest() {
		String pubName = "test";
		ReportPublication pub = new ReportPublication();
		pub.setName(pubName);
		
		ReportPublication savedPub = reportPublicationDao.save(pub);
		
		Assert.assertNotNull(savedPub.getName());
		Assert.assertEquals(pubName, savedPub.getName());
		Assert.assertNull(savedPub.getOwner());
	}
	
	/**
	 * Test for the save method of the ReportPublicationDao class.
	 */
	@Test
	public void saveReportExistingReportTest() {
		// Retrieves the report publication with ID 1 and checks it is valid
		int pubId = 1;
		ReportPublication pub = reportPublicationDao.find(pubId);
		Assert.assertNotNull(pub.getId());
		Assert.assertEquals(pubId, pub.getId());
		
		// Modify fields in the report publication
		String newName = "New Name";
		String newContent = "New Content";
		pub.setName(newName);
		pub.setContent(newContent.getBytes());
		
		// Saves it
		ReportPublication savedPub = reportPublicationDao.save(pub);
		
		// Check the modifications were saved properly
		Assert.assertNotNull(savedPub.getId());
		Assert.assertEquals(pubId, savedPub.getId());
		Assert.assertNotNull(savedPub.getName());
		Assert.assertEquals(newName, savedPub.getName());
		
		String newStringContent = new String(savedPub.getContent());
		Assert.assertNotNull(savedPub.getContent());
		Assert.assertEquals(newContent, newStringContent);
	}
	
	/**
	 * Test for the save method of the ReportPublicationDao class.
	 * It should throw an exception since the given report publication is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveReportNullTest() {
		reportPublicationDao.save(null);
	}
	
	/**
	 * Test for the save method of the ReportPublicationDao class.
	 * It should throw an exception since the given report publication name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveReportEmptyTest() {
		reportPublicationDao.save(new ReportPublication());
	}
	
	/**
	 * Test for the delete method of the ReportPublicationDao class.
	 */
	@Test
	public void deleteReportStoryTest() {
		int pubId = 1;
		ReportPublication pub = reportPublicationDao.find(pubId);
		Assert.assertNotNull(pub);
		Assert.assertEquals(pubId, pub.getId());
		
		reportPublicationDao.delete(pub);
		
		try {
			pub = reportPublicationDao.find(pubId);
			Assert.fail(); // fails if the report wasn't deleted
		}
		catch(NoResultException e) {
			// Supposed to be triggered after the deletion
		}
	}
	
	/**
	 * Test for the delete method of the ReportPublicationDao class.
	 * It should throw an exception since the given report is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteReportNullTest() {
		reportPublicationDao.delete(null);
	}
}
