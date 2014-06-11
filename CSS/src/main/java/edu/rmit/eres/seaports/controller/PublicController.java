/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.controller;

import java.io.IOException;
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

import edu.rmit.eres.seaports.dao.ReportPublicationDao;
import edu.rmit.eres.seaports.dao.UserDao;
import edu.rmit.eres.seaports.dao.ReportDao;
import edu.rmit.eres.seaports.helpers.ElementPositionComparator;
import edu.rmit.eres.seaports.helpers.SecurityHelper;
import edu.rmit.eres.seaports.model.Report;
import edu.rmit.eres.seaports.model.ReportPublication;
/**
 * Controller for the public section of the application
 */
@Controller
public class PublicController {

	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private ReportPublicationDao reportPublicationDao;
	
	@Autowired
	ReportController reportController;
	
	private void tryGetLoggedInUser(Model model) {
		try {
			model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
		}
		catch (Exception e) {
			// User is not logged in. Not a big deal on the public pages
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
	@RequestMapping(value = {"/public/help"}, method = RequestMethod.GET)
	public ModelAndView help(Model model) {		
		tryGetLoggedInUser(model);
		return new ModelAndView("help");
	}
	
	/**
	 * Listing of all the published reports
	 */
	@RequestMapping(value = {"/public/published-report/list"}, method = RequestMethod.GET)
	public ModelAndView getPublishedReportList(Model model) {
		logger.info("Inside getPublishedReportList");

		tryGetLoggedInUser(model);
		
		ModelAndView mav = new ModelAndView("publishedReportList");
		try {
			// Retrieve all published reports
			List<ReportPublication> publishedReports = reportPublicationDao.getAllReportPublications();
			mav.addObject("publishedReports", publishedReports);
				
			model.addAttribute("listingTitle", "Published Reports");
			if (publishedReports.size() == 0)
				model.addAttribute("warningMessage", ERR_NO_RESULT);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_RETRIEVE_PUBLISHED_REPORT_LIST);
		}

		return mav;
	}
	
	@RequestMapping(value= "/public/published-report/view", method = RequestMethod.GET)
	public ModelAndView viewPublishedReport(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside viewPublishedReport");
		
		tryGetLoggedInUser(model);
		
		Report report = null;
		try {
			ReportPublication pub = reportPublicationDao.find(id);
			report = pub.getReport();
		}
		catch (NoResultException | ClassNotFoundException | IOException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		model.addAttribute("publicView", true);
		
		ModelAndView mav = reportController.ModelForReport(model, report);
		
		mav.setViewName("reportView");
		
		if (report != null)
		{
			Collections.sort(report.getElements(), new ElementPositionComparator());
			mav.addObject("report", report);
		}
		return mav;
	}
	
	public static final String ERR_NO_RESULT = "No report has been published yet";
	public static final String ERR_RETRIEVE_PUBLISHED_REPORT_LIST = "Impossible to retrieve the list of the published reports";
	public static final String ERR_REPORT_NOT_PUBLISHED = "The report that you are looking for was not published by its owner";
}
