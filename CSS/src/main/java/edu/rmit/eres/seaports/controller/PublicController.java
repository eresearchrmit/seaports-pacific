/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.rmit.eres.seaports.dao.CsiroDataBaselineDao;
import edu.rmit.eres.seaports.dao.UserDao;
import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.helpers.ElementPositionComparator;
import edu.rmit.eres.seaports.helpers.SecurityHelper;
import edu.rmit.eres.seaports.model.AbsData;
import edu.rmit.eres.seaports.model.BitreData;
import edu.rmit.eres.seaports.model.CsiroData;
import edu.rmit.eres.seaports.model.DataElement;
import edu.rmit.eres.seaports.model.Element;
/*import edu.rmit.eres.seaports.model.DataElementAbs;
import edu.rmit.eres.seaports.model.DataElementBitre;
import edu.rmit.eres.seaports.model.DataElementCsiro;
import edu.rmit.eres.seaports.model.DataElementEngineeringModel;
import edu.rmit.eres.seaports.model.DataElementFile;*/
import edu.rmit.eres.seaports.model.EngineeringModelData;
import edu.rmit.eres.seaports.model.Report;

/**
 * Controller for the public section of the application
 */
@Controller
public class PublicController {

	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportDao userStoryDao;
	
	@Autowired
	private CsiroDataBaselineDao csiroDataBaselineDao;
	
	private void tryGetLoggedInUser(Model model) {
		try {
			model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
		}
		catch (Exception e) {
			// User is not logged in. Not a big deal on the home page
		}
	}
	
	/**
	 * Displays the home page view
	 */
	@RequestMapping(value = {"/", "/public"}, method = RequestMethod.GET)
	public ModelAndView home(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("home");
	}
		
	/**
	 * Displays the "About" page
	 */
	@RequestMapping(value = "/public/intro", method = RequestMethod.GET)
	public ModelAndView intro(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("introduction");
	}
	
	/**
	 * Displays the "About" page
	 */
	@RequestMapping(value = "/public/about", method = RequestMethod.GET)
	public ModelAndView about(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("about");
	}
	
	/**
	 * Displays the "Terms of Service" page
	 */
	@RequestMapping(value = {"/public/terms-of-service"}, method = RequestMethod.GET)
	public ModelAndView copyright(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("copyright");
	}
	
	/**
	 * Displays the Guidelines page
	 */
	@RequestMapping(value = {"/public/guidelines"}, method = RequestMethod.GET)
	public ModelAndView guidelines(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("guidelines");
	}
	
	/**
	 * Listing of all the published reports
	 */
	@RequestMapping(value = {"/public/reports/list"}, method = RequestMethod.GET)
	public ModelAndView getPublishedUserStoriesList(Model model) {
		logger.info("Inside getPublishedUserStoriesList");

		tryGetLoggedInUser(model);
		
		ModelAndView mav = new ModelAndView("userstoryPublicList");
		try {
			// Retrieve all published reports
			List<Report> reportsList = userStoryDao.getAllPublishedStories();
			mav.addObject("userStoriesList", reportsList);
				
			model.addAttribute("listingTitle", "Published Reports");
			if (reportsList.size() == 0)
				model.addAttribute("warningMessage", ERR_NO_RESULT);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_RETRIEVE_PUBLISHED_REPORT_LIST);
		}

		return mav;
	}
	
	@RequestMapping(value= "/public/reports/view", method = RequestMethod.GET)
	public ModelAndView getUserStoryPublicView(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside getUserStoryPublicView");
		
		tryGetLoggedInUser(model);
		
		Report report = null;
		try {			
			report = userStoryDao.find(id);
			if (!report.getMode().equals("published")) {
				model.addAttribute("errorMessage", ERR_REPORT_NOT_PUBLISHED);
			}
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		if (report != null && report.getMode().equals("published"))
		{
			Collections.sort(report.getElements(), new ElementPositionComparator());
			
			List<Element> elements = report.getElements();
	 		
			// TODO: Prepare data to be displayed
			/*for (DataElement dataElement : dataElements) {
	 			if (dataElement.getClass().equals(DataElementAbs.class)) {
	 				List<AbsData> absDataList = ((DataElementAbs)dataElement).getAbsDataList();
	 				for (AbsData data : absDataList) {
	 					data.generateValues();
	 				}
	 			}
	 			else if (dataElement.getClass().equals(DataElementBitre.class)) {
	 				List<BitreData> bitreDataList = ((DataElementBitre)dataElement).getBitreDataList();
	 				for (BitreData data : bitreDataList) {
	 					data.generateValues();
	 				}
	 			}
	 			else if (dataElement.getClass().equals(DataElementFile.class)) {
	 				((DataElementFile)dataElement).generateStringContent();
	 			}
	 			else if (dataElement.getClass().equals(DataElementCsiro.class)) {
	 				for (CsiroData data : ((DataElementCsiro)dataElement).getCsiroDataList()) {
	 					data.setBaseline(csiroDataBaselineDao.find(data.getParameters().getRegion(), data.getVariable()));
	 				}
	 			}
	 			else if (dataElement.getClass().equals(DataElementEngineeringModel.class)) {
	 				List<EngineeringModelData> engineeringModelDataList = ((DataElementEngineeringModel)dataElement).getEngineeringModelDataList();
	 				for (EngineeringModelData data : engineeringModelDataList) {
	 					data.generateValues();
	 				}
	 			}
			}*/
			model.addAttribute("userstory", report);
		}
		
		model.addAttribute("publicView", true);
		
		return new ModelAndView("userstoryPublicView");
	}
	
	public static final String ERR_NO_RESULT = "No report has been published yet";
	public static final String ERR_RETRIEVE_PUBLISHED_REPORT_LIST = "Impossible to retrieve the list of the published reports";
	public static final String ERR_REPORT_NOT_PUBLISHED = "The report that you are looking for was not published by its owner";
}
