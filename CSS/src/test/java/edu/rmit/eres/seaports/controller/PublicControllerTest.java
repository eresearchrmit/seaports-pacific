/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

import edu.rmit.eres.seaports.controller.PublicController;
import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.dao.ReportPublicationDao;
import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.model.ReportPublication;

/**
 * This test class holds the unit tests regarding the publicly available pages and actions
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PublicControllerTest {
	
	@Autowired
	private PublicController publicController;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	ReportPublicationDao reportPublicationDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
	}
	
	/**
	 * Listing should succeed even though no login is given
	 */
	@Test
	public void getPublishedReportListSuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getPublishedReportList(model);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("publishedReportList", result.getViewName());
		Assert.assertNull(model.get("errorMessage"));
		Assert.assertEquals("Published Reports", model.get("listingTitle"));
		
		// Check that the result is a list of User Stories and that they are all published
		@SuppressWarnings("unchecked")
		List<Object> resPublishedReports = (List<Object>)(result.getModelMap().get("publishedReports"));
		Assert.assertEquals(1, resPublishedReports.size()); // There should be 1 published report in the test database
		for (Object obj : resPublishedReports) {
			if (obj instanceof ReportPublication) {
				ReportPublication publishedReport = (ReportPublication)obj;
				try {
					Report report = publishedReport.getReport();
					Assert.assertEquals(4, report.getId()); // The ID of the referenced report in the test DB is 4
				} catch (ClassNotFoundException | IOException e) {
					Assert.fail();
				}
			}
			else
				Assert.fail();
		}
	}
	
	/**
	 * Listing should find no published story
	 */
	@Test
	@Transactional
	public void getPublishedReportListNoresultTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		
		ReportPublication publishedReport = reportPublicationDao.find(1); // Published report
		reportPublicationDao.delete(publishedReport);
		
		ModelAndView result = publicController.getPublishedReportList(model);
		
		reportPublicationDao.save(publishedReport);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("publishedReportList", result.getViewName());
		
		Assert.assertEquals("Published Reports", model.get("listingTitle"));
		Assert.assertNull(model.get("errorMessage"));
		Assert.assertNotNull(model.get("warningMessage"));
		Assert.assertEquals(PublicController.ERR_NO_RESULT, model.get("warningMessage"));
	}
	
	/**
	 * getPublishedReportView should fail because the requested User Story ID doesn't exist
	 */
	@Test
	public void getPublishedReportViewNoResultTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.viewPublishedReport(9999, model); // Non-existing ID
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("reportView", result.getViewName());
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(ReportPublicationDao.ERR_NO_SUCH_PUBLICATION, model.get("errorMessage"));
		
		Assert.assertNotNull(model.get("publicView"));
		Assert.assertEquals(true, model.get("publicView"));
		
		Assert.assertNull(model.get("report"));
	}

	/**
	 * getPublishedReportViewSuccessTest should succeed
	 */
	@Test
	public void getPublishedReportViewSuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.viewPublishedReport(1, model);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertNull(model.get("errorMessage"));
		
		Assert.assertEquals("reportView", result.getViewName());
		Assert.assertNotNull(model.get("publicView"));
		Assert.assertEquals(true, model.get("publicView"));
		
		Assert.assertNotNull(result.getModelMap().get("report"));
		Report report = (Report)(result.getModelMap().get("report"));
		Assert.assertEquals(4, report.getId()); // The report with ID 4 should be published
	}
}