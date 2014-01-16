/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.util.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import edu.rmit.eres.seaports.dao.*;
import edu.rmit.eres.seaports.helpers.ElementPositionComparator;
import edu.rmit.eres.seaports.helpers.SecurityHelper;
import edu.rmit.eres.seaports.model.*;

@Controller
@RequestMapping("auth/userstory")
public class ReportController {
		
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private ElementDao elementDao;
		
	/*@RequestMapping(value= "/userstory-created", method = RequestMethod.GET)
	public String workboardCreated(@RequestParam(value="id",required=true) Integer userstoryId, Model model) {
		model.addAttribute("userstoryId", userstoryId);
		return "userstoryCreated";
	}*/
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView getReportList(@RequestParam(value="user",required=true) String username, Model model) {
		logger.info("Inside getReportList");
		
		ModelAndView mav = new ModelAndView("reportList");
		try {
			User curentUser = userDao.find(SecurityHelper.getCurrentlyLoggedInUsername());
			mav.addObject("user", curentUser);
			
			// Retrieve user
			User user = userDao.find(username);
			
			if (!(SecurityHelper.IsCurrentUserMatching(user.getUsername()) || SecurityHelper.IsCurrentUserAdmin())) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Retrieve user's Stories
			List<Report> reportsList = reportDao.getReports(user);
			mav.addObject("userStoriesList", reportsList);
			
			// Define a title
	 		model.addAttribute("listingTitle", "My Reports");
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", ERR_RETRIEVE_USERSTORY_LIST);
		}

		return mav;
	}
	
	@ModelAttribute("userstory")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getReport(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside getReport");
		
		Report report = null;
		try {
			report = reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
		}
		catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return ModelForReport(model, report);
	}
	
	@RequestMapping(value= "/view", method = RequestMethod.GET)
	public ModelAndView getReportView(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside getReportView");
		
		ModelAndView mav = getReport(id, model);
		mav.setViewName("reportView");
		return mav;
	}

	@RequestMapping(value= "/lock", method = RequestMethod.GET)
	public String changeReportPrivacy(
			@RequestParam(value="id",required=true) Integer id, 
			@RequestParam(value="lock",required=true) Boolean lock, 
			RedirectAttributes attributes) {
		logger.info("Inside getUserStoriesList !");
		
		try {
			Report userStory= reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(userStory))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			if (lock) // true == locked == private
				userStory.setAccess("private");
			else // false == unlocked == public
				userStory.setAccess("public");
			reportDao.save(userStory);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}

	@RequestMapping(value="/create", method=RequestMethod.GET) 
	public ModelAndView createReport(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside createReport");
		
		Report userStory = null;
		try {
			// Retrieve the user story
			userStory = reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(userStory))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			if (!(userStory.getMode().equals("published"))) {
				userStory.setMode("passive");
				reportDao.save(userStory);
				
				// Initial ordering of the data elements in the user story
				int i = 1;
				for (Element elem : userStory.getElements()) {
					elem.setPosition(i);
					i++;
				}
				reportDao.save(userStory);
				model.addAttribute("userstory", userStory);
				return new ModelAndView("userstoryCreated");
			}
			else
				model.addAttribute("errorMessage", ERR_STORY_ALREADY_PUBLISHED);
		}
		catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return ModelForReport(model, userStory);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST) 
	public String saveReport(
			@RequestParam(value="comments",required=false) String[] updatedTexts, 
			@Valid @ModelAttribute("userstory") Report updatedReport, 
			RedirectAttributes attributes) {
		logger.info("Inside saveReport");
		
		Report userStory = null;
		try {
			// Retrieve the original user story
			userStory = reportDao.find(updatedReport.getId());
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(userStory))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Reorder the data elements in the user story
			for (Element elem : userStory.getElements()) {
				for (Element updatedDataElement : updatedReport.getElements()) {
					if (elem.getId() == updatedDataElement.getId()) {
						elem.setPosition(updatedDataElement.getPosition());
						break;
					}
				}
			}
			// Save the user story after reordering
			reportDao.save(userStory);
			
			Collections.sort(userStory.getElements(), new ElementPositionComparator());
			
			// TODO: Update content of the text data elements if they have been changed
			/*if (updatedTexts != null) {
				int i = 0;
				for (DataElement dataElement : userStory.getDataElements()) {
					if (dataElement.getClass().equals(DataElementText.class)) {
						DataElementText dataElementText = (DataElementText)(dataElement);
						if (updatedTexts.length > i && updatedTexts[i] != null && !updatedTexts[i].equals(dataElementText.getText())) {
							dataElementText.setText(updatedTexts[i]);
							dataElementDao.save(dataElementText);
						}
						i++;
					}
				}
			}*/
			attributes.addFlashAttribute("successMessage", MSG_USERSTORY_SAVED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		
		if (userStory != null)
			return "redirect:/auth/userstory?id=" + userStory.getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET) 
	public String deleteReport(@RequestParam(value="id", required=true) Integer userStoryId, RedirectAttributes attributes) {
		logger.debug("Inside deleteReport");
		
		try {
			Report userStory = reportDao.find(userStoryId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(userStory))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			reportDao.delete(userStory);
			attributes.addFlashAttribute("successMessage", MSG_USERSTORY_DELETED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_DELETE_USERSTORY);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_DELETE_USERSTORY);
		}
		
		return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}	

	@RequestMapping(value="/addText", method=RequestMethod.POST) 
	public String addTextToReport(@RequestParam(value="userStoryId",required=true) Integer id, 
			@RequestParam(value="textContent",required=true) String textContent, 
			@RequestParam(value="textInsertPosition",required=true) String insertTextAfter, RedirectAttributes attributes) {
		logger.info("Inside saveReport");
		
		Report report = null;
		try {
			report = reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			Integer newTextPosition = Integer.parseInt(insertTextAfter) + 1;
			
			// Increment the positions of all data elements after the new one
			int i = 0;
			for (Element elem : report.getElements()) {
				if (elem.getPosition() >= newTextPosition)
					elem.setPosition(elem.getPosition() + 1);
				i++;
			}
			// Save the user story after reordering
			reportDao.save(report);
			
			// TODO: Add Text Data Element
			/*DataElementText newTextItem = new DataElementText(new Date(), "Report Text", true, newTextPosition, DisplayType.PLAIN, userStory, textContent);
			dataElementDao.save(newTextItem);*/
			
			attributes.addFlashAttribute("successMessage", MSG_TEXT_ADDED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_ADD_TEXT);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_ADD_TEXT);
		}
		
		if (report != null)
			return "redirect:/auth/userstory?id=" + report.getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value="/editText", method=RequestMethod.POST) 
	public String editText(@RequestParam(value="dataElementId") Integer elementId,
			@RequestParam(value="textContent") String textContent, RedirectAttributes attributes) {
		logger.info("Inside editText");
		
		Element element = null;
		try {
			// Retrieve the data element
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// TODO: make changes to the content of the text element
			/*if (dataElement instanceof DataElementText) {
				DataElementText deText = (DataElementText)(dataElement);
				deText.setText(textContent);
				dataElementDao.save(deText);
			}*/
			attributes.addFlashAttribute("successMessage", MSG_TEXT_EDITED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_EDIT_TEXT);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_EDIT_TEXT);
		}
		
		if (element != null)
			return "redirect:/auth/userstory?id=" + element.getReport().getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value="/deleteText", method=RequestMethod.GET) 
	public String removeTextFromReport(@RequestParam(value="text",required=true) Integer id, RedirectAttributes attributes) {
		logger.info("Inside removeTextFromReport");
		
		Element element = null;
		try {
			element = elementDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// TODO: Exception if the element isn't a text
			//if (!(dataElement instanceof DataElementText))
				//throw new IllegalArgumentException();
			
			elementDao.delete(element);
			attributes.addFlashAttribute("successMessage", MSG_TEXT_REMOVED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_REMOVE_TEXT);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_REMOVE_TEXT);
		}
		
		if (element != null)
			return "redirect:/auth/userstory?id=" + element.getReport().getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value="/includeDataElement", method=RequestMethod.GET) 
	public String includeDataElementToReport(@RequestParam(value="dataelement",required=true) Integer elementId, RedirectAttributes attributes) {
		logger.info("Inside includeDataElementToReport");
		
		Element element = null;
		try {
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			element.setIncluded(!element.getIncluded());
			elementDao.save(element);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
 		
		if (element != null)
			return "redirect:/auth/userstory?id=" + element.getReport().getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value = "/publish", method=RequestMethod.GET) 
	public String publishReport(@RequestParam(value="id", required=true) Integer reportId, RedirectAttributes attributes) {
		logger.debug("Inside publishReport");
		
		Report report= null;
		try {
			report = reportDao.find(reportId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			if (!report.getMode().equals("published")) {
				report.setAccess("public");
				report.setMode("published");
				report.setPublishDate(new Date());
								
				reportDao.save(report);
				
				attributes.addFlashAttribute("successMessage", MSG_STORY_PUBLISHED);
			}
			else
				attributes.addFlashAttribute("warningMessage", ERR_STORY_ALREADY_PUBLISHED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_DELETE_USERSTORY);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_DELETE_USERSTORY);
		}

		if (report != null)
			return "redirect:/auth/userstory?id=" + report.getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	public ModelAndView ModelForReport(Model model, Report report) {
		logger.debug("Inside ModelForReport");

		model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
		
		if (report != null)
		{
			Collections.sort(report.getElements(), new ElementPositionComparator());
			
			List<Element> elements = report.getElements();
	 		for (Element element : elements) {
	 			// TODO: get values to plot
	 			/*if (dataElement.getClass().equals(DataElementAbs.class)) {
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
	 			}*/
			}
			model.addAttribute("userstory", report);
		}
		return new ModelAndView("userstory");
	}
	
	public static final String ERR_ACCESS_DENIED = "You are not allowed to access this Story";
	
	public static final String ERR_RETRIEVE_USERSTORY_LIST = "Impossible to retrieve the list of your reports";
	public static final String MSG_NO_USER_STORY = "There is no report to display";
	public static final String MSG_USERSTORY_SAVED = "The report has been saved successfully";
	public static final String ERR_SAVE_USERSTORY = "Error saving the report. Please Try Again";
	public static final String MSG_USERSTORY_DELETED = "The report has been deleted successfully";
	public static final String ERR_DELETE_USERSTORY = "Error deleting the report. Please Try Again";
	
	public static final String MSG_TEXT_ADDED = "Text added successfully";
	public static final String ERR_ADD_TEXT = "Error adding text. Please Try Again";
	public static final String MSG_TEXT_EDITED = "Text edited successfully";
	public static final String ERR_EDIT_TEXT = "Error editing the text. Please Try Again";
	public static final String MSG_TEXT_REMOVED = "Text deleted successfully";
	public static final String ERR_REMOVE_TEXT = "Error deleting the text. Please Try Again";
	
	public static final String MSG_STORY_PUBLISHED = "The report is now published. It appears publicly on this portal and will be listed on Reasearch Data Australia search results.";
	public static final String ERR_STORY_ALREADY_PUBLISHED = "This report is already published";
}