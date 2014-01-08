/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import edu.rmit.eres.seaports.controller.ReportController;
import edu.rmit.eres.seaports.dao.ElementDao;
import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.model.Element;
//import edu.rmit.eres.seaports.model.DataElementText;
import edu.rmit.eres.seaports.model.User;
import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.security.UserLoginService;;

/**
 * This test class holds the unit tests regarding the reports (user stories) retrieval, privacy, and actions
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ReportControllerTest {
	
	@Autowired
	private ReportController reportController;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private ElementDao elementDao;
	
	User loggedInUser;
	User loggedInUserNoWB;
	User loggedInAdmin;
	SecurityContext securityContextUserLoggedIn;
	SecurityContext securityContextUserLoggedInNoWB;
	SecurityContext securityContextAdminLoggedIn;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL); // Optional
				
		loggedInUser = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		Authentication userAuth = new edu.rmit.eres.seaports.controller.ConcreteAuthentication(loggedInUser);
		securityContextUserLoggedIn = new SecurityContextImpl();
		securityContextUserLoggedIn.setAuthentication(userAuth);
		
		loggedInUserNoWB = new User("testuser3", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser3", "testuser3");
		Authentication userAuthNoWB = new edu.rmit.eres.seaports.controller.ConcreteAuthentication(loggedInUserNoWB);
		securityContextUserLoggedInNoWB = new SecurityContextImpl();
		securityContextUserLoggedInNoWB.setAuthentication(userAuthNoWB);
		
		loggedInAdmin = new User("testadmin1", "password", true, true, UserLoginService.ROLE_ADMINISTRATOR, "email@company.com", "testadmin1", "testadmin1");
		Authentication adminAuth = new edu.rmit.eres.seaports.controller.ConcreteAuthentication(loggedInAdmin);
		securityContextAdminLoggedIn = new SecurityContextImpl();
		securityContextAdminLoggedIn.setAuthentication(adminAuth);
	}

	
	/* --------------------------------------------------------------------- */
	/* --------------------------- createReport ------------------------- */
	/* --------------------------------------------------------------------- */
	
	/**
	 * createReport should succeed
	 */
	/*@Test
	@Transactional
	public void createReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 1;
		
		// Check that the story is in Active mode before
		Report refUserstory = reportDao.find(id);
		Assert.assertEquals("active", refUserstory.getMode());
		
		// Create the story. It should switch to active mode
		reportController.createReport(id, model);
		Assert.assertNull(model.get("errorMessage"));
		Report changedUserstory = reportDao.find(id);
		Assert.assertEquals("passive", changedUserstory.getMode());
		
		// Recreate with now a story which is passive. It should succeed as well.
		reportController.createReport(id, model);
		Assert.assertNull(model.get("errorMessage"));
		changedUserstory = reportDao.find(id);
		Assert.assertEquals("passive", changedUserstory.getMode());
	}*/
	
	/**
	 * createReport should fail since the story is already published
	 */
	/*@Test
	@Transactional
	public void createReportAlreadyPublishedTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 4;
		
		Report refUserstory = reportDao.find(id);
		Assert.assertEquals("published", refUserstory.getMode());
		
		reportController.createReport(id, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(ReportController.ERR_STORY_ALREADY_PUBLISHED, model.get("errorMessage"));
		
		Report changedUserstory = reportDao.find(id);
		Assert.assertEquals("published", changedUserstory.getMode());
	}*/
	
	/* --------------------------------------------------------------------- */
	/* ---------------------------- saveReport -------------------------- */
	/* --------------------------------------------------------------------- */
	
	/**
	 * saveReport should succeed
	 */
	@Test
	public void saveReportUpdateTextsTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 2;
		Report refUserstory = reportDao.find(id);
		Assert.assertEquals(5, refUserstory.getElements().size());
		
		String[] updatedTexts = new String[] {"Updated Text 1", "Updated Text 2"};
		
		reportController.saveReport(updatedTexts, refUserstory, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		
		Report changedUserstory = reportDao.find(id);
		Assert.assertEquals(5, changedUserstory.getElements().size());
		
		// TODO: create test texts to update before saving the report
		/*DataElementText textItem = (DataElementText)(changedUserstory.getElements().get(1));
		Assert.assertEquals(updatedTexts[0], textItem.getText());
		DataElementText textItem2 = (DataElementText)(changedUserstory.getElements().get(3));
		Assert.assertEquals(updatedTexts[1], textItem2.getText());*/
		
		// Set the texts back to what they were before the test
		String[] refTexts = new String[] {"This is a text comment", "This is a second text comment"};
		reportController.saveReport(refTexts, changedUserstory, redirectAttributes);
		
	}
	
	/**
	 * saveReport should succeed
	 */
	@Test
	public void saveReportUpdateOrderTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 2;
		Report refUserstory = reportDao.find(id);
		Assert.assertEquals(5, refUserstory.getElements().size());
		
		// Unchanged texts
		String[] refTexts = new String[] {"This is a text comment", "This is a second text comment"};
		
		// Re-order the data elements
		Report updatedReport = reportDao.find(id);
		updatedReport.getElements().get(0).setPosition(4);
		updatedReport.getElements().get(1).setPosition(3);
		updatedReport.getElements().get(2).setPosition(1);
		updatedReport.getElements().get(3).setPosition(2);
		
		reportController.saveReport(refTexts, updatedReport, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		// Check that the data element order has been correctly changed
		Report changedUserstory = reportDao.find(id);
		Assert.assertEquals(5, changedUserstory.getElements().size());
		Assert.assertEquals(4, changedUserstory.getElements().get(0).getPosition());
		Assert.assertEquals(3, changedUserstory.getElements().get(1).getPosition());
		Assert.assertEquals(1, changedUserstory.getElements().get(2).getPosition());
		Assert.assertEquals(2, changedUserstory.getElements().get(3).getPosition());
		
		// Reset the order as i was before the test
		updatedReport.getElements().get(0).setPosition(1);
		updatedReport.getElements().get(1).setPosition(2);
		updatedReport.getElements().get(2).setPosition(3);
		updatedReport.getElements().get(3).setPosition(4);
		reportController.saveReport(refTexts, updatedReport, redirectAttributes);
	}
	
	/* --------------------------------------------------------------------- */
	/* --------------------------- deleteReport ------------------------- */
	/* --------------------------------------------------------------------- */
	
	/**
	 * deleteReport should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 2;
		
		Report refReport = reportDao.find(id);
		
		String result = reportController.deleteReport(id, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + refReport.getOwner().getUsername(), result);
		
		/*try {
			reportDao.find(id);
			Assert.fail("At this point, the story shouldn't exist anymore. If it is found, the test have failed."); 
		}
		catch (NoResultException e) {
			reportDao.save(refReport, );
		}*/
	}
	
	/**
	 * deleteReport should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteReportNullIdTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		
		String result = reportController.deleteReport(null, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + securityContextUserLoggedIn.getAuthentication().getName(), result);
		Assert.assertEquals(ReportController.ERR_DELETE_USERSTORY, redirectAttributes.getFlashAttributes().get("errorMessage"));
	}
	
	/**
	 * deleteReport should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteReportUnknownIdTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		String result = reportController.deleteReport(id, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + securityContextUserLoggedIn.getAuthentication().getName(), result);
		Assert.assertEquals(ReportController.ERR_DELETE_USERSTORY, redirectAttributes.getFlashAttributes().get("errorMessage"));
	}
	
	/* --------------------------------------------------------------------- */
	/* ------------------------- addTextToReport ------------------------ */
	/* --------------------------------------------------------------------- */
	
	/**
	 * addTextToReport should succeed
	 */
	@Test
	@Transactional
	public void addTextToReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 3;
		
		//Report refReport = reportDao.find(id);
		
		reportController.addTextToReport(id, "Content", "0", redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		
		//Report changedReport = reportDao.find(id);
		//Assert.assertEquals(refReport.getElements().size() + 1, changedReport.getElements().size());
	}
	
	/**
	 * addTextToReport should fails since the story ID doesn't exist
	 */
	@Test
	@Transactional
	public void addTextToReportUnknownIDTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		reportController.addTextToReport(id, "Content", "0", redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals(ReportController.ERR_ADD_TEXT, redirectAttributes.getFlashAttributes().get("errorMessage"));
	}
	
	/* --------------------------------------------------------------------- */
	/* ----------------------- removeTextFromReport --------------------- */
	/* --------------------------------------------------------------------- */
	
	/**
	 * removeTextFromReport should succeed
	 */
	@Test
	@Transactional
	public void removeTextFromReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 5;
		
		//Report refReport = reportDao.find(id);
		
		reportController.removeTextFromReport(id, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		
		//Report changedReport = reportDao.find(id);
		//Assert.assertEquals(refReport.getElements().size() - 1, changedReport.getElements().size());
	}
	
	/**
	 * removeTextFromReport should fails since the story ID doesn't exist
	 */
	@Test
	@Transactional
	public void removeTextFromReportUnknownIDTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		reportController.removeTextFromReport(id, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals(ReportController.ERR_REMOVE_TEXT, redirectAttributes.getFlashAttributes().get("errorMessage"));
	}
	
	/* --------------------------------------------------------------------- */
	/* -------------------- includeDataElementToReport ------------------ */
	/* --------------------------------------------------------------------- */
	
	/**
	 * includeDataElementToReport should succeed
	 */
	@Test
	public void includeDataElementToReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer elementId = 4;
		
		// Check that the data element is included at the beginning of the test
		Element dataElementRef = elementDao.find(elementId);
		Assert.assertEquals(true, dataElementRef.getIncluded());
		
		// Exclude the data element from the user story
		reportController.includeDataElementToReport(elementId, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Element dataElementChanged = elementDao.find(elementId);
		Assert.assertEquals(false, dataElementChanged.getIncluded());
		
		// Re-include the data element to the user story
		reportController.includeDataElementToReport(elementId, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Element dataElementChanged2 = elementDao.find(elementId);
		Assert.assertEquals(true, dataElementChanged2.getIncluded());
	}
	
	/**
	 * includeDataElementToReport should fail since the data element ID doesn't exist
	 */
	@Test
	public void includeDataElementToReportUnknownIdTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer dataElementId = 9999; // NON-EXISTING ID
				
		reportController.includeDataElementToReport(dataElementId, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals(ElementDao.ERR_NO_SUCH_ELEMENT, redirectAttributes.getFlashAttributes().get("errorMessage"));
		
	}
	
	/* --------------------------------------------------------------------- */
	/* -------------------------- publishReport ------------------------- */
	/* --------------------------------------------------------------------- */
	
	/**
	 * publishReport should succeed
	 */
	@Test
	public void publishReportSuccessTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 2;
		Report refReport = reportDao.find(id);
		
		// Check that the user story is private and in in 'passive' mode before the test
		Assert.assertEquals("private", refReport.getAccess());
		Assert.assertEquals("passive", refReport.getMode());
		Assert.assertNull(refReport.getPublishDate());
		
		String result = reportController.publishReport(id, redirectAttributes);
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertNull(redirectAttributes.getFlashAttributes().get("warningMessage"));
		Assert.assertEquals("redirect:/auth/userstory?id=" + id, result);
		
		// Check the the User story is now public and published
		refReport = reportDao.find(id);
		Assert.assertEquals("public", refReport.getAccess());
		Assert.assertEquals("published", refReport.getMode());
		Assert.assertNotNull(refReport.getPublishDate());
		
		// Reset the access to 'private' and the mode to 'passive' after the test
		refReport.setAccess("private");
		refReport.setMode("passive");
		refReport.setPublishDate(null);
		reportDao.save(refReport);
	}

	/**
	 * publishReport should fails since the story ID doesn't exist
	 */
	@Test
	@Transactional
	public void publishReportUnknownIDTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		String result = reportController.publishReport(id, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals(ReportController.ERR_DELETE_USERSTORY, redirectAttributes.getFlashAttributes().get("errorMessage"));
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + securityContextUserLoggedIn.getAuthentication().getName(), result);
	}
	
	/**
	 * publishReport should fails since the story is already published
	 */
	@Test
	@Transactional
	public void publishReportAlreadyPublishedTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Integer id = 4; // STORY ALREADY PUBLISHED
		
		String result = reportController.publishReport(id, redirectAttributes);
		Assert.assertNotNull(redirectAttributes.getFlashAttributes().get("warningMessage"));
		Assert.assertEquals(ReportController.ERR_STORY_ALREADY_PUBLISHED, redirectAttributes.getFlashAttributes().get("warningMessage"));
		Assert.assertEquals("redirect:/auth/userstory?id=" + id, result);
		
		// Check that the User Story's published date hasn't changed
		Report refReport = reportDao.find(id);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Assert.assertEquals("2013-04-10", dateFormatter.format(refReport.getPublishDate()));
	}
}