/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

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
import edu.rmit.eres.seaports.model.Report;

/**
 * 
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PublicControllerTest {
	
	@Autowired
	private PublicController publicController;
	
	@Autowired
	private ReportDao userStoryDao;
	
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
	public void getPublishedUserStoriesListSuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getPublishedUserStoriesList(model);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicList", result.getViewName());
		Assert.assertNull(model.get("errorMessage"));
		Assert.assertEquals("Published Reports", model.get("listingTitle"));
		
		// Check that the result is a list of User Stories and that they are all published
		@SuppressWarnings("unchecked")
		List<Object> resUserStoriesList = (List<Object>)(result.getModelMap().get("userStoriesList"));
		Assert.assertEquals(1, resUserStoriesList.size()); // There should be 1 published story in the test database
		for (Object obj : resUserStoriesList) {
			if (obj instanceof Report) {
				Report report = (Report)obj;
				Assert.assertEquals("published", report.getMode());
			}
			else
				Assert.fail();
		}
	}
	
	/**
	 * Listing should find no published story
	 */
	@Test
	public void getPublishedUserStoriesListNoresultTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		
		Report report = userStoryDao.find(4); // Published user story
		report.setMode("passive");
		userStoryDao.save(report);
		
		ModelAndView result = publicController.getPublishedUserStoriesList(model);
		
		report.setMode("published");
		userStoryDao.save(report);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicList", result.getViewName());
		
		Assert.assertEquals("Published Reports", model.get("listingTitle"));
		Assert.assertNull(model.get("errorMessage"));
		Assert.assertNotNull(model.get("warningMessage"));
		Assert.assertEquals(PublicController.ERR_NO_RESULT, model.get("warningMessage"));
	}
	
	/**
	 * getUserStoryPublicView should fail because the requested User Story ID doesn't exist
	 */
	@Test
	public void getUserStoryPublicViewNoResultTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getUserStoryPublicView(9999, model); // Non-existing ID
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicView", result.getViewName());
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(ReportDao.ERR_NO_SUCH_USERSTORY, model.get("errorMessage"));
		
		Assert.assertNotNull(model.get("publicView"));
		Assert.assertEquals(true, model.get("publicView"));
		
		Assert.assertNull(model.get("userstory"));
	}
	
	/**
	 * getUserStoryPublicView should fail because the requested User Story is not published
	 */
	@Test
	public void getUserStoryPublicViewNotPublishedTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getUserStoryPublicView(1, model); // The User Story exists but isn't published
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicView", result.getViewName());
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(PublicController.ERR_REPORT_NOT_PUBLISHED, model.get("errorMessage"));
		
		Assert.assertNotNull(model.get("publicView"));
		Assert.assertEquals(true, model.get("publicView"));
		
		Assert.assertNull(model.get("userstory"));
	}
	
	/**
	 * getUserStoryPublicViewNotPublishedTest should succeed
	 */
	@Test
	public void getUserStoryPublicViewSuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getUserStoryPublicView(4, model);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasView());
		Assert.assertNull(model.get("errorMessage"));
		
		Assert.assertEquals("userstoryPublicView", result.getViewName());
		Assert.assertNotNull(model.get("publicView"));
		Assert.assertEquals(true, model.get("publicView"));
		
		Assert.assertNotNull(model.get("userstory"));
		Report report = (Report)(model.get("userstory"));
		Assert.assertEquals(4, report.getId()); // The User Story with ID 4 should be published
		Assert.assertEquals("published", report.getMode());
	}
}