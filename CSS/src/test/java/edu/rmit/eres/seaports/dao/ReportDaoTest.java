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
 * Test class for the user reports DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReportDaoTest {

	User userForTest;
	
	User userWithoutReport;
	
	Report reportForTest;
	
	Report reportToSave;
	
	@Autowired
	private ReportDao reportDao;

	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {		
		userForTest = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		
		userWithoutReport = new User("testuser4", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser4", "testuser4");
		
		reportForTest = new Report("User 1 WorkBoard", "User story Purpose", "private", userForTest, 
				new Seaport("FJLEV", "Levuka", new Region("Fiji", "")), null);
		
		reportToSave = new Report("test", "Purpose", "private", userForTest, 
				new Seaport("FJLEV", "Levuka", new Region("Fiji", "")), null);
	}

	/**
	 * Test for the find method
	 * There should be a report in the test DB matching the fields
	 */
	@Test
	public void findReportTest() {
		Report report = reportDao.find(1);

		Assert.assertNotNull(report);
		
		Assert.assertEquals("User 1 Workboard", report.getName());
		Assert.assertEquals("private", report.getAccess());
		Assert.assertEquals("testuser1", report.getOwner().getUsername());
		Assert.assertEquals("Region 1", report.getSeaport().getRegion().getName());
	}
	
	/**
	 * Test for find method of the ReportDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findReportNullIdTest() {
		reportDao.find(null);
	}
	
	/**
	 * Test for the find method
	 * The method should throw an exception since ID 9999 doesn't exist
	 */
	@Test(expected = NoResultException.class)
	public void findReportUnknownIdTest() {
		reportDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for getAllReports method
	 * There should be 6 reports in the Test DB
	 */
	@Test
	public void getAllReportsTest() {
		List<Report> reports = reportDao.getAllReports();
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(6, reports.size());
	}
	
	/**
	 * Test for getReports. It should retrieve a list of 3 reports
	 */
	@Test
	public void getUserReportsTest() {
		List<Report> reports = reportDao.getUserReports(userForTest);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(4, reports.size());
	}
	
	/**
	 * Test for getUserReports. It should retrieve a list of 0 report
	 */
	@Test
	public void getUserReportsNoStoryTest() {
		List<Report> reports = reportDao.getUserReports(userWithoutReport);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(0, reports.size());
	}
	
	/**
	 * Test for getUserReports method of the ReportDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getUserReportsNullUserTest() {
		reportDao.getUserReports(null);
	}
	
	/**
	 * Test for getPrivateUserReports. It should retrieve a list of 1 PRIVATE report
	 */
	@Test
	public void getPrivateUserReportsTest() {
		List<Report> reports = reportDao.getPrivateUserReports(userForTest);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(2, reports.size());
	}
	
	/**
	 * Test for getPrivateUserReports. It should retrieve a list of 0 report
	 */
	@Test
	public void getPrivateUserReportsNoStoryTest() {
		List<Report> reports = reportDao.getPrivateUserReports(userWithoutReport);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(0, reports.size());
	}
	
	/**
	 * Test for getPrivateUserReports method of the ReportDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getPrivateUserReportsNullUserTest() {
		reportDao.getPrivateUserReports(null);
	}
	
	/**
	 * Test for getPublicUserReports. It should retrieve a list of 2 PUBLIC reports
	 */
	@Test
	public void getPublicUserReportsTest() {
		List<Report> reports = reportDao.getPublicUserReports(userForTest);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(2, reports.size());
	}
	
	/**
	 * Test for getPublicUserReports. It should retrieve a list of 0 user story
	 */
	@Test
	public void getPublicUserReportsNoReportTest() {
		List<Report> reports = reportDao.getPublicUserReports(userWithoutReport);
		
		Assert.assertNotNull(reports);
		Assert.assertEquals(0, reports.size());
	}
	
	/**
	 * Test for getPublicUserReports method of the ReportDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getPublicUserReportsNullUserTest() {
		reportDao.getPublicUserReports(null);
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void saveNewReportTest() {
		Report savedReport = reportDao.save(reportToSave);
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals(reportToSave.getName(), savedReport.getName());
		Assert.assertEquals(reportToSave.getAccess(), savedReport.getAccess());
		
		Assert.assertNotNull(savedReport.getOwner());
		Assert.assertEquals(reportToSave.getOwner().getUsername(), savedReport.getOwner().getUsername());
		
		Assert.assertNotNull(savedReport.getSeaport());
		Assert.assertEquals(reportToSave.getSeaport().getCode(), savedReport.getSeaport().getCode());
		Assert.assertNotNull(savedReport.getSeaport().getRegion());
		Assert.assertEquals(reportToSave.getSeaport().getRegion().getName(), savedReport.getSeaport().getRegion().getName());
		
		Assert.assertNull(savedReport.getElements());
	}
	
	/**
	 * Test for save with a report specifying only the name.
	 */
	@Test
	public void saveReportOnlyNameTest() {
		String reportName = "test";
		Report report = new Report();
		report.setName(reportName);
		
		Report savedReport = reportDao.save(report);
		
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals(reportName, savedReport.getName());
		Assert.assertEquals("private", savedReport.getAccess());
		Assert.assertNull(savedReport.getOwner());
		Assert.assertNull(savedReport.getSeaport());
		Assert.assertNull(savedReport.getElements());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void saveReportExistingReportTest() {
		Report savedReport = reportDao.save(reportForTest);
	
		Assert.assertNotNull(savedReport.getName());
		Assert.assertEquals("User 1 WorkBoard", savedReport.getName());
		
		Assert.assertNotNull(savedReport.getAccess());
		Assert.assertEquals("private", savedReport.getAccess());
	}
	
	/**
	 * Test for save. It should throw an exception since the given report is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveReportNullTest() {
		reportDao.save(null);
	}
	
	/**
	 * Test for save. It should throw an exception since the given report name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveReportEmptyTest() {
		reportDao.save(new Report());
	}
	
	/**
	 * Test for delete.
	 */
	@Test
	public void deleteReportStoryTest() {
		int reportId = 1;
		Report report = reportDao.find(reportId);
		Assert.assertNotNull(report);
		Assert.assertEquals(reportId, report.getId());
		
		reportDao.delete(report);
		
		try {
			report = reportDao.find(reportId);
			Assert.fail(); // fails if the report wasn't deleted
		}
		catch(NoResultException e) {
			// Supposed to be triggered after the deletion
		}
	}
	
	/**
	 * Test for delete. It should throw an exception since the given report is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteReportNullTest() {
		reportDao.delete(null);
	}
}
