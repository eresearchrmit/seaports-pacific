/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import edu.rmit.eres.seaports.controller.AdminController;
import edu.rmit.eres.seaports.controller.PublicController;
import edu.rmit.eres.seaports.controller.ReportController;
import edu.rmit.eres.seaports.controller.WorkboardController;
import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.model.ElementCategory;
import edu.rmit.eres.seaports.model.InputElement;
import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Seaport;
import edu.rmit.eres.seaports.model.Report;

/**
 * This test class holds tests against all the protected controllers methods performed by an anonymous user
 * Most of the tests are expecting to get an "Access Denied" response.
 * @author Guillaume Prevost
 * @since 19th Mar. 2013
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnonymousControllerTest {

	@Autowired
	private PublicController publicController;
	
	@Autowired
	private WorkboardController workboardController;

	@Autowired
	private ReportController reportController;
	
	@Autowired
	private AdminController adminController;
	
	@Autowired
	private  ReportDao userStoryDao;
	
	InputElement inputElement;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {
		SecurityContextHolder.getContext().setAuthentication(null);
		
		// Dummy Input Element to pass for file upload test
		Report report = new Report();
		report.setId(1);
		inputElement = new InputElement(new Date(), null, new ElementCategory("Observed climate"), report, true, 1, null);
		inputElement.setId(0);
	}

	/* --------------------------------------------------------------------- */
	/* ------------------------------ Public ------------------------------- */
	/* --------------------------------------------------------------------- */
	
	@Test
	public void homeTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.home(model);
		
		Assert.assertEquals("home", result.getViewName());
	}
	
	@Test
	public void copyrightTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.copyright(model);
		
		Assert.assertEquals("copyright", result.getViewName());
	}
	
	@Test
	public void getPublishedUserStoriesListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getPublishedUserStoriesList(model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicList", result.getViewName());
	}
	
	@Test
	public void getUserStoryPublicViewTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getUserStoryPublicView(1, model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("userstoryPublicView", result.getViewName());
	}
	
	/* --------------------------------------------------------------------- */
	/* ----------------------------- Workboard ----------------------------- */
	/* --------------------------------------------------------------------- */
	
	/*@Test
	public void getWorkboardTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		workboardController.getWorkboard(model);
	}*/
	
	@Test(expected = AccessDeniedException.class)
	public void getReportUserTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		workboardController.getReport(1, model);
	}
	
	/*@Test(expected = AccessDeniedException.class)
	public void addAbsDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addAbsDataToWorkboard(1, 1, "AUSYD", "graph", redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addBitreSatDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addBitreDataToWorkboard(1, 1, "AUSYD", "graph", redirectAttributes);
	}*/
	
	@Test(expected = AccessDeniedException.class)
	public void uploadfileinWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		MockMultipartFile mockMultipartFileText = new MockMultipartFile("content", "test.css", "text/css", "Hello World".getBytes());
		workboardController.createFileElement(inputElement, mockMultipartFileText, redirectAttributes);
	}

	/*@Test(expected = AccessDeniedException.class)
	public void addPastDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addPastDataToWorkboard(1, 1, redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addAcornSatDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addAcornSatDataToWorkboard(1, "extreme", redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addCsiroDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addCsiroDataToWorkboard(1, "Temperature", "A1B", "2030", "", redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addCmarDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addCmarDataToWorkboard(1, "2030", "on", redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addEngineeringDataToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		MockMultipartFile mockMultipartFileText = new MockMultipartFile("content", "test.css", "text/css", "Hello World".getBytes());
		workboardController.addEngineeringDataToWorkboard(mockMultipartFileText, "upload", "Crack propagation time", "Chloride", 1, "graph", redirectAttributes);
	}*/
	
	/*@Test(expected = AccessDeniedException.class)
	public void addVulnerabilityAssessmentToWorkboardTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.addVulnerabilityAssessmentToWorkboard(1, "Storm", "2005", "direct", "Impact text", "0", "1", "2", "3", "4", "2", "4", "1", "0", "3", "4", "2", "Other consequences", "adequate", "Changes imlepemented", redirectAttributes);
	}*/
	
	@Test(expected = AccessDeniedException.class)
	public void addWorkboardTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Report report = new Report();
		report.setName("addWorkBoardTest");
		report.setSeaport(new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")));
		workboardController.createReport(report, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void deleteWorkboardTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		workboardController.deleteReport(1, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void deleteDataElementFromUserStoryTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.deleteElement(1, redirectAttributes, model);
	}
	
	
	
	/* --------------------------------------------------------------------- */
	/* ----------------------------- User Story ---------------------------- */
	/* --------------------------------------------------------------------- */
	
	@Test(expected = AccessDeniedException.class)
	public void getUserStoriesListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		reportController.getReportList("testuser1", model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void getUserStoryTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		reportController.getReport(2, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void getUserStoryViewTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		reportController.getReportView(2, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void changeUserStoryPrivacyTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.changeReportPrivacy(2, true, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void createUserStoryTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		reportController.createReport(2, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void saveUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Report refUserstory = userStoryDao.find(2);
		String[] updatedTexts = new String[] {"Updated Text 1", "Updated Text 2"};
		
		reportController.saveReport(updatedTexts, refUserstory, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void deleteUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.deleteReport(2, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void addTextToUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.addTextToReport(2, "Content", "0", redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void removeTextFromUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.removeTextFromReport(2, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void includeDataElementToUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.includeDataElementToReport(4, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void publishUserStoryTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.publishReport(4, redirectAttributes);
	}

	/* --------------------------------------------------------------------- */
	/* --------------------------- Administration -------------------------- */
	/* --------------------------------------------------------------------- */
	
	@Test(expected = AccessDeniedException.class)
	public void userListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		adminController.userList(model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void userEnableDisableTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		adminController.userEnableDisable("enable", "testuser1", model);
	}
}