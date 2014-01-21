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
import edu.rmit.eres.seaports.dao.ElementDao;
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
	private ReportController workboardController;

	@Autowired
	private ReportController reportController;
	
	@Autowired
	private AdminController adminController;
	
	@Autowired
	private  ReportDao userStoryDao;
	
	@Autowired
	private  ElementDao elementDao;
	
	InputElement inputElement;
	InputElement textElement;
	
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
		
		// Text Element to test text edition methods
		textElement = (InputElement) elementDao.find(2);
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
	public void getPublishedReportListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.getPublishedReportList(model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("publishedReportList", result.getViewName());
	}
	
	@Test
	public void viewPublishedReportTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = publicController.viewPublishedReport(1, model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("reportView", result.getViewName());
	}
	
	/* --------------------------------------------------------------------- */
	/* ----------------------------- Report ----------------------------- */
	/* --------------------------------------------------------------------- */
	
	@Test(expected = AccessDeniedException.class)
	public void getReportListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		reportController.getReportList("testuser1", model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void getReportTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		workboardController.getReport(1, model);
	}
		
	@Test(expected = AccessDeniedException.class)
	public void createReportTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Report report = new Report();
		report.setName("addWorkBoardTest");
		report.setSeaport(new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")));
		workboardController.createReport(report, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void createFileElementTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		MockMultipartFile mockMultipartFileText = new MockMultipartFile("content", "test.css", "text/css", "Hello World".getBytes());
		workboardController.createFileElement(inputElement, mockMultipartFileText, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void createTextElementTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.createTextElement(this.textElement, "Content", redirectAttributes);
	}

	@Test(expected = AccessDeniedException.class)
	public void deleteReportTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.deleteReport(1, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void deleteElementTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		workboardController.deleteElement(1, redirectAttributes, model);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void changeReportPrivacyTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.changeReportPrivacy(2, true, redirectAttributes);
	}
		
	@Test(expected = AccessDeniedException.class)
	public void saveReportTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		Report refUserstory = userStoryDao.find(2);
		
		reportController.saveReport(refUserstory, redirectAttributes);
	}
		
	@Test(expected = AccessDeniedException.class)
	public void includeElementToReportTest() {
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		reportController.includeElementToReport(4, true, redirectAttributes);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void publishReportTest() {
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