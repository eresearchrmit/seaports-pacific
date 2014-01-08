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

import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.model.Seaport;
import edu.rmit.eres.seaports.model.User;
import edu.rmit.eres.seaports.security.UserLoginService;

/**
 * Test class for the user stories DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReportDaoTest {

	User userForTest;
	
	User userWithoutStory;
	
	Report reportForTest;
	
	@Autowired
	private ReportDao reportDao;

	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {		
		userForTest = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		userWithoutStory = new User("testuser4", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser4", "testuser4");
		
		reportForTest = new Report("User 1 WorkBoard", "User story Purpose", "active", "private", userForTest, 
				new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")), null);
	}

	/**
	 * Test for the find method
	 * There should be a story in the test DB matching the fields
	 */
	@Test
	public void userStoryfindTest() {
		Report report = reportDao.find(1);

		Assert.assertNotNull(report);
		
		Assert.assertEquals("User 1 Workboard", report.getName());
		Assert.assertEquals("active", report.getMode());
		Assert.assertEquals("private", report.getAccess());
		Assert.assertEquals("testuser1", report.getOwner().getUsername());
		Assert.assertEquals("Fiji", report.getSeaport().getRegion().getName());
	}
	
	/**
	 * Test for the find method
	 * The method should throw an exception since ID 9999 doesn't exist
	 */
	@Test(expected = NoResultException.class)
	public void userStoryfindUnexistingStoryTest() {
		reportDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for getAllStories method
	 * There should be 6 stories in the Test DB
	 */
	@Test
	public void getAllStoriesTest() {
		List<Report> stories = reportDao.getAllStories();
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(6, stories.size());
	}
	
	/**
	 * Test for getUserStories. It should retrieve a list of 3 PASSIVE user stories
	 */
	@Test
	public void getUserStoriesTest() {
		List<Report> stories = reportDao.getUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(3, stories.size());
	}
	
	/**
	 * Test for getUserStories. It should retrieve a list of 0 report
	 */
	@Test
	public void getUserStoriesNoStoryTest() {
		List<Report> stories = reportDao.getUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPrivateUserStories. It should retrieve a list of 1 PASSIVE PRIVATE user story
	 */
	@Test
	public void getPrivateUserStoriesTest() {
		List<Report> stories = reportDao.getPrivateUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(1, stories.size());
	}
	
	/**
	 * Test for getPrivateUserStories. It should retrieve a list of 0 user story
	 */
	@Test
	public void getPrivateUserStoriesNoStoryTest() {
		List<Report> stories = reportDao.getPrivateUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublicUserStories. It should retrieve a list of 2 PASSIVE PUBLIC user story
	 */
	@Test
	public void getPublicUserStoriesTest() {
		List<Report> stories = reportDao.getPublicUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(2, stories.size());
	}
	
	/**
	 * Test for getPublicUserStories. It should retrieve a list of 0 user story
	 */
	@Test
	public void getPublicUserStoriesNoStoryTest() {
		List<Report> stories = reportDao.getPublicUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 1 PASSIVE PUBLISHED user story
	 */
	@Test
	public void getPublishedUserStoriesTest() {
		List<Report> stories = reportDao.getPublishedUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(1, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 0 user stories
	 */
	@Test
	public void getPublishedUserStoriesNoStoryTest() {
		List<Report> stories = reportDao.getPublishedUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 1 PASSIVE PUBLISHED user story
	 */
	@Test
	public void getWorkBoardTest() {
		Report workboard = reportDao.getWorkboard(userForTest);
		
		Assert.assertNotNull(workboard);
		Assert.assertEquals("User 1 Workboard", workboard.getName());
		Assert.assertEquals("active", workboard.getMode());
		Assert.assertEquals("private", workboard.getAccess());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 0 user stories
	 */
	@Test
	public void getWorkBoardNoWorkboardTest() {
		Report workboard = reportDao.getWorkboard(userWithoutStory);
		
		Assert.assertNull(workboard);
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveNewReportTest() {
		Report savedReport = reportDao.save(new Report("test", "Purpose", "active", "private", userForTest, 
				new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")), null));
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("test", savedReport.getName());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("active", savedReport.getMode());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("private", savedReport.getAccess());
		
		Assert.assertNotNull(savedReport.getOwner());
		Assert.assertEquals("testuser1", savedReport.getOwner().getUsername());
		
		Assert.assertNotNull(savedReport.getSeaport().getRegion());
		Assert.assertEquals("East Coast South", savedReport.getSeaport().getRegion().getName());
		
		Assert.assertNull(savedReport.getElements());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveStoryOnlyNameTest() {
		Report userStory = new Report();
		userStory.setName("test");
		Report savedReport = reportDao.save(userStory);
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("test", savedReport.getName());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("active", savedReport.getMode());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("private", savedReport.getAccess());
		
		Assert.assertNull(savedReport.getOwner());
		Assert.assertNull(savedReport.getSeaport());
		Assert.assertNull(savedReport.getElements());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveExistingReportTest() {
		Report savedReport = reportDao.save(reportForTest);
	
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("User 1 WorkBoard", savedReport.getName());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("active", savedReport.getMode());
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("private", savedReport.getAccess());
	}
	
	/**
	 * Test for save. It should throw an exception since the given user story is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userSaveNullTest() {
		reportDao.save(null);
	}
	
	/**
	 * Test for save. It should throw an exception since the given user story name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userSaveEmptyTest() {
		reportDao.save(new Report());
	}
	
	/**
	 * Test for delete.
	 */
	@Test
	public void userDeleteStoryTest() {
		Report story = reportDao.find(1);
		Assert.assertNotNull(story);
		
		reportDao.delete(story);
	}
	
	/**
	 * Test for delete. It should throw an exception since the given user story is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userDeleteNullTest() {
		reportDao.delete(null);
	}
}
