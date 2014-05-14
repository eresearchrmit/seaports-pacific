/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import edu.rmit.eres.seaports.dao.*;
import edu.rmit.eres.seaports.helpers.ElementPositionComparator;
import edu.rmit.eres.seaports.helpers.FileTypeHelper;
import edu.rmit.eres.seaports.helpers.SecurityHelper;
import edu.rmit.eres.seaports.model.*;
import edu.rmit.eres.seaports.model.datasource.DemographicsDataSource;
import edu.rmit.eres.seaports.model.datasource.FutureExtremeDataSource;
import edu.rmit.eres.seaports.model.datasource.FutureTrendDataSource;
import edu.rmit.eres.seaports.model.datasource.ObservedExtremeDataSource;
import edu.rmit.eres.seaports.model.datasource.ObservedTrendDataSource;
import edu.rmit.eres.seaports.model.datasource.TradeDataSource;

@Controller
@RequestMapping("auth/report")
public class ReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private ReportPublicationDao reportPublicationDao;
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private SeaportDao seaportDao;
	
	@Autowired
	private ElementCategoryDao elementCategoryDao;
	
	@Autowired
	private DisplayTypeDao displayTypeDao;
	
	@Autowired
	private DataSourceDao dataSourceDao;
	
	@Autowired
	private DataSourceParameterOptionDao dataSourceParameterOptionDao;
	
	@Autowired
	private ElementDao elementDao;
	
	@Autowired
	private ObservedTrendDataDao observedTrendDataDao;
	
	@Autowired
	private FutureTrendDataDao futureTrendDataDao;
	
	@Autowired
	private ExtremeDataDao extremeDataDao;
	
	@Autowired
	private TradeDataDao tradeDataDao;
	
	@Autowired
	private DemographicsDataDao demographicsDataDao;
	
	@RequestMapping(value= "/my-reports", method = RequestMethod.GET)
	public String myReports(Model model) {
		logger.info("Inside myReports");
		
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
			  userDetails = (UserDetails) principal;
			}
			
			return "redirect:/auth/report/list?user=" + userDetails.getUsername();
		}
		catch (NullPointerException ex) {
			return "redirect:/accessDenied";
		}
	}
		
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView getReportList(@RequestParam(value="user",required=true) String username, Model model) {
		logger.info("Inside getReportListForUser");
		
		ModelAndView mav = new ModelAndView("reportList");
		try {
			User currentUser = userDao.find(SecurityHelper.getCurrentlyLoggedInUsername());
			mav.addObject("user", currentUser);
			
			// Retrieve user
			User user = userDao.find(username);
			
			if (!(SecurityHelper.IsCurrentUserMatching(user.getUsername()) || SecurityHelper.IsCurrentUserAdmin())) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Retrieve user's Stories
			List<Report> reportsList = reportDao.getUserReports(user);
			mav.addObject("reportList", reportsList);
			
			// Retrieve user's published reports
			List<ReportPublication> publishedReports = reportPublicationDao.getReportPublications(user);
			mav.addObject("publishedReports", publishedReports);
			
			// Define a title
			if (user.getUsername().equals(currentUser.getUsername()))
				model.addAttribute("listingTitle", "My Reports");
			else
				model.addAttribute("listingTitle", user.getFirstname() + user.getLastname() + "'s Reports");
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", ERR_RETRIEVE_REPORT_LIST);
		}

		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getReport(@RequestParam(value="id",required=true) int id, Model model) {
		logger.info("Inside getReport");
		
		Report report = null;
		try {
			report = reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			ModelAndView mav = ModelForReport(model, report);

			// Adds empty elements to fill when user creates a new element
			DataElement newDataElement = new DataElement();
			mav.addObject("newdataelement", newDataElement);
			InputElement newInputElement = new InputElement();
			mav.addObject("newinputelement", newInputElement);
			
			return mav;
		}
		catch (IllegalArgumentException | NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return ModelForReport(model, null);
	}
	
	@RequestMapping(value= "/view", method = RequestMethod.GET)
	public ModelAndView viewReport(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside viewReport");
		
		Report report = null;
		try {
			report = reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
		}
		catch (IllegalArgumentException | NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		ModelAndView mav = ModelForReport(model, report);
		mav.setViewName("reportView");
		
		return mav;
	}
		
	@RequestMapping(value="/create", method=RequestMethod.GET) 
	public ModelAndView createReport(Model model) {
		logger.info("Inside createReport");

		try {		
			User user = userDao.find(SecurityHelper.getCurrentlyLoggedInUsername());
			return ModelForReportCreation(model, user);
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return ModelForReportCreation(model, null);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createReport(@ModelAttribute("report") Report report, @RequestParam(value="prefillReport",required=false) boolean prefillReport, Model model) {
		logger.info("Inside createReport - POST");
        
		try {
			User user = userDao.find(SecurityHelper.getCurrentlyLoggedInUsername());
			model.addAttribute("user", user);
			/*Report currentReport = reportDao.getWorkboard(user);
			if (currentReport != null) {
				model.addAttribute("warningMessage", WorkboardController.ERR_ALREADY_CURRENT_WORKBOARD);
				return ModelForReport(model, currentReport);
			}*/
			
			if (report.getSeaport() == null || report.getSeaport().getRegion() == null)
				throw new IllegalArgumentException(ReportController.ERR_REGION_NOT_DEFINED);
			
			if (report.getPurpose() == null || report.getPurpose().length() <= 0)
				throw new IllegalArgumentException(ReportController.ERR_PURPOSE_NOT_DEFINED);
			
			Seaport seaport = seaportDao.find(report.getSeaport().getCode());
			
			report.setOwner(user);
			report.setSeaport(seaport);
			report.setAccess("private");
			report.setElements(new ArrayList<Element>());
			report.setCreationDate(new Date());
			reportDao.save(report);
			
			if (prefillReport == true)  {
				List<ElementCategory> categories = elementCategoryDao.getAll();
				for (ElementCategory cat : categories) {
					String content = "<p><span style=\"text-decoration: underline; font-size: x-large;\">" + cat.getName() + "</span></p>";
				    InputElement heading = new InputElement(new Date(), "Category Heading", cat, report, true, 1, "text/plain", content.getBytes(), true, false);
				    elementDao.save(heading);
				}
			}
			
			model.addAttribute("report", report);
			
			return new ModelAndView("redirect:/auth/report?id=" + report.getId());
		}
		catch (AccessDeniedException e) {
			throw e;
		}
		catch (IllegalArgumentException | NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_CREATE_REPORT + ". Details: " + e.getMessage());
		}
		
		return ModelForReportCreation(model, null);
	}

	@RequestMapping(value="/save", method=RequestMethod.POST) 
	public String saveReport(@Valid @ModelAttribute("report") Report updatedReport, RedirectAttributes attributes) {
		logger.info("Inside saveReport");
		
		Element elementForRedirection = null;
		try {
			// Retrieve the original user story
			Report report = reportDao.find(updatedReport.getId());
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			
			// Reorder the data elements in the user story
			for (Element elem : report.getElements()) {
				for (Element updatedDataElement : updatedReport.getElements()) {
					if (elem.getId() == updatedDataElement.getId()) {
						elem.setPosition(updatedDataElement.getPosition());
						elementForRedirection = elem;
						break;
					}
				}
			}
			// Save the user story after reordering
			reportDao.save(report);
			
			Collections.sort(report.getElements(), new ElementPositionComparator());
			
			attributes.addFlashAttribute("successMessage", MSG_REPORT_SAVED);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_REPORT);
		}
		
		if (elementForRedirection != null)
			return "redirect:" + redirectToCategory(elementForRedirection);
			//return "redirect:/auth/report?id=" + report.getId() + "#tabs-summary";
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}

	@RequestMapping(value = "/delete",method=RequestMethod.GET) 
	public String deleteReport(@RequestParam(value="id", required=true) Integer reportId, RedirectAttributes attributes) {
		logger.debug("Inside deleteReport");
				
		try {
			//attributes.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
			
			Report report = reportDao.find(reportId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			String ownerUsername = report.getOwner().getUsername();
			reportDao.delete(report); // Deletes the Report
			
			return "redirect:/auth/report/list?user=" + ownerUsername; // returns to the list of reports
			//return ModelForReportCreation(model, user); // Starts the creation of a new report
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ReportController.ERR_DELETE_REPORT);
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername(); // returns to the list of reports
		}
	}
	
	@RequestMapping(value = "/create-data-element", method=RequestMethod.POST) 
	public String createDataElement(@ModelAttribute("newdataelement") DataElement dataElement, 
			RedirectAttributes attributes, Model model) {
		logger.info("Inside createDataElement");
		
		Report report = null;
		try {
			report = reportDao.find(dataElement.getReport().getId());
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			DisplayType displayType = displayTypeDao.find(dataElement.getDisplayType().getName());
			ElementCategory category = elementCategoryDao.find(dataElement.getCategory().getName());
			DataSource dataSource = dataSourceDao.find(dataElement.getDataSource().getName());
			List<DataSourceParameterOption> options = new ArrayList<DataSourceParameterOption>();
			for (DataSourceParameterOption selectedOption : dataElement.getSelectedOptions()) {	
				if (selectedOption.getId() > 0)
					options.add(dataSourceParameterOptionDao.find(selectedOption.getId()));
			}
			
			String title = dataSource.getDisplayName() + " Data Element";
			int insertPosition = dataElement.getPosition() + 1;
			
			reorderReportElementsAfterPosition(report, insertPosition);
			
			DataElement newElement = new DataElement(new Date(), title, category, report, true, insertPosition,  dataSource, options, displayType, true, false);
			newElement.setInputs(dataElement.getInputs());
			elementDao.save(newElement);
			
			// Redirects to the right tab of the report after creating the element, based on its category
			attributes.addFlashAttribute("successMessage", MSG_ELEMENT_CREATED);
			return "redirect:" + redirectToCategory(newElement);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		if (report != null)
			return "redirect:/auth/report?id=" + report.getId();
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
		
	@RequestMapping(value = "/create-text-element",method=RequestMethod.POST) 
	public String createTextElement(@ModelAttribute("newinputelement") InputElement inputElement, 
			@RequestParam(value="textContent",required=true) String textContent, 
			RedirectAttributes attributes) {
		logger.info("Inside addInputElement");
		
		Report report = null;
		try {
			report = reportDao.find(inputElement.getReport().getId());
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			ElementCategory category = elementCategoryDao.find(inputElement.getCategory().getName());
			String title = "Analysis Text";
			byte[] content = textContent.getBytes();
			int insertPosition = inputElement.getPosition() + 1;
			
			reorderReportElementsAfterPosition(report, insertPosition);
			
			InputElement newElement = new InputElement(new Date(), title, category, report, true, insertPosition, "text/plain", content, true, false);
			elementDao.save(newElement);
			
			return "redirect:" + redirectToCategory(newElement);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		if (report != null)
			return "redirect:/auth/report?id=" + report.getId();
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value= "/create-file-element", method = RequestMethod.POST)
	public String createFileElement(@ModelAttribute("newinputelement") InputElement inputElement, 
			@RequestParam(value="file",required=false) MultipartFile uploadfile,
			RedirectAttributes attributes) {
		logger.info("Inside uploadfileinWorkBoard");
		
		Report report = null;
        try {
        	report = reportDao.find(inputElement.getReport().getId());
        	
        	if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
        	
        	if (uploadfile == null || uploadfile.isEmpty())
        		throw new FileUploadException();
        	
            int lastIndex = uploadfile.getOriginalFilename().lastIndexOf('.');
        	String fileName = uploadfile.getOriginalFilename().substring(0, lastIndex);
            String fileExtension = uploadfile.getOriginalFilename().substring(lastIndex + 1);
            
            if(FileTypeHelper.IsFileContentTypeAllowed(uploadfile)) {
            	
            	ElementCategory category = elementCategoryDao.find(inputElement.getCategory().getName());
            	String title = fileName + "." + fileExtension;
    			int insertPosition = inputElement.getPosition() + 1;
    			
    			reorderReportElementsAfterPosition(report, insertPosition);
    			
            	InputElement newElement = new InputElement(new Date(), title, category, report, true, insertPosition, uploadfile.getContentType(), uploadfile.getBytes(), true, false);
    			elementDao.save(newElement);
            	
            	attributes.addFlashAttribute("successMessage", MSG_FILE_UPLOAD_SUCCESS);
            	
            	return "redirect:" + redirectToCategory(newElement);
            }
            else
            	attributes.addFlashAttribute("errorMessage", ERR_INVALID_FILETYPE);
        }
        catch (IOException | FileUploadException e) {
        	attributes.addFlashAttribute("errorMessage", ERR_FILE_UPLOAD);
        }
        catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
        
		if (report != null)
			return "redirect:/auth/report?id=" + report.getId();
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value="/edit-display-type", method=RequestMethod.POST) 
	public String editDisplayType(@RequestParam(value="elementId") Integer elementId,
			@RequestParam(value="displayType") String displayTypeName, RedirectAttributes attributes) {
		logger.info("Inside editDisplayType");
		
		Element element = null;
		try {
			// Retrieve the data element
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Check that the element is an DataElement
			if (element instanceof DataElement) {
				DataElement dataElement = (DataElement)element;
				
				DisplayType displayType = displayTypeDao.find(displayTypeName);
				dataElement.setDisplayType(displayType);
				elementDao.save(dataElement);
				
				attributes.addFlashAttribute("successMessage", MSG_DISPLAY_TYPE_EDITED);
			}
			else
				throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_EDIT_DISPLAY_TYPE);
		}
		
		if (element != null)
			return "redirect:" + redirectToCategory(element);
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value="/edit-element-display", method=RequestMethod.POST) 
	public String editFullWidth(@RequestParam(value="elementId") Integer elementId,
			@RequestParam(value="fullWidth") boolean fullWidth, 
			@RequestParam(value="pageBreakAfter", required=false) boolean pageBreakAfter, 
			RedirectAttributes attributes) {
		logger.info("Inside editElementDisplay");
		
		Element element = null;
		try {
			// Retrieve the data element
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);

			element.setFullWidth(fullWidth);
			element.setPageBreakAfter(pageBreakAfter);
			elementDao.save(element);
				
			attributes.addFlashAttribute("successMessage", MSG_ELEMENT_EDITED);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_EDIT_ELEMENT);
		}
		
		if (element != null)
			return "redirect:" + redirectToCategory(element);
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	private void reorderReportElementsAfterPosition(Report report, int insertPosition) {
		// Increment the positions of all the elements after the new one
		for (Element elem : report.getElements()) {
			if (elem.getPosition() >= insertPosition)
				elem.setPosition(elem.getPosition() + 1);
		}
		// Save the report after reordering
		reportDao.save(report);
	}
	
	@RequestMapping(value="/edit-text-element", method=RequestMethod.POST) 
	public String editTextElement(@RequestParam(value="elementId") Integer elementId,
			@RequestParam(value="textContent") String textContent, RedirectAttributes attributes) {
		logger.info("Inside editTextElement");
		
		Element element = null;
		try {
			// Retrieve the data element
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Check that the element is an InputElement
			if (element instanceof InputElement) {
				InputElement inputElement = (InputElement)element;
				// Check that the content type is plain text
				if (FileTypeHelper.IsContentTypePlaintext(inputElement.getContentType())) {
					inputElement.setStringContent(textContent);
					elementDao.save(inputElement);
					attributes.addFlashAttribute("successMessage", MSG_TEXT_EDITED);
				}
				else
					throw new IllegalArgumentException();
			}
			else
				throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_EDIT_TEXT);
		}
		
		if (element != null)
			return "redirect:" + redirectToCategory(element);
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
		
	@RequestMapping(value = "/delete-element",method=RequestMethod.GET) 
	public String deleteElement(@RequestParam(value="id",required=true) Integer elementId, RedirectAttributes attributes, Model model) {
		logger.info("Inside deleteElement");
		
		try {
			Element element = elementDao.find(elementId);
			Report report = element.getReport();
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			try {	
				elementDao.delete(element);
				
				// Reorder the element in the report to keep consistent positions
				report.getElements().remove(element);
				int removedPosition = element.getPosition();
				for (Element elem : report.getElements()) {
					if (elem.getPosition() >= removedPosition)
						elem.setPosition(elem.getPosition() - 1);
				}
				reportDao.save(report);
				
				attributes.addFlashAttribute("successMessage", MSG_ELEMENT_DELETED);
			}
			catch (IllegalArgumentException e) {
				attributes.addFlashAttribute("errorMessage", ERR_DELETE_ELEMENT);
			}
			// Redirects to the right tab of the report after deletion based on the data element type
			return "redirect:" + redirectToCategory(element);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return ("redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername());
	}
	
	@RequestMapping(value="/include-element", method=RequestMethod.GET) 
	public String includeElementToReport(@RequestParam(value="id",required=true) Integer elementId, @RequestParam(value="included",required=true) Boolean included, RedirectAttributes attributes) {
		logger.info("Inside includeElementToReport");
		
		Element element = null;
		try {
			element = elementDao.find(elementId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(element.getReport()))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			element.setIncluded(included);
			elementDao.save(element);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
 		
		if (element != null)
			return "redirect:" + redirectToCategory(element);
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value= "/lock", method = RequestMethod.GET)
	public String changeReportPrivacy(
			@RequestParam(value="id",required=true) Integer id, 
			@RequestParam(value="lock",required=true) Boolean lock, 
			RedirectAttributes attributes) {
		logger.info("Inside changeReportPrivacy");
		
		try {
			Report report= reportDao.find(id);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			if (lock) // true == locked == private
				report.setAccess("private");
			else // false == unlocked == public
				report.setAccess("public");
			reportDao.save(report);
		}
		catch (IllegalArgumentException | NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_REPORT);
		}
		
		return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}

	@RequestMapping(value = "/publish", method=RequestMethod.GET) 
	public String publishReport(@RequestParam(value="id", required=true) Integer reportId, RedirectAttributes attributes) {
		logger.debug("Inside publishReport");
		
		ReportPublication pub = null;
		try {
			Report report = reportDao.find(reportId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			report = this.prepareReportData(report);
			
			pub = new ReportPublication(report);
			pub = reportPublicationDao.save(pub);
			
			attributes.addFlashAttribute("successMessage", MSG_REPORT_PUBLISHED);
		}
		catch (IllegalArgumentException | NoResultException | IOException e) {
			attributes.addFlashAttribute("errorMessage", ERR_PUBLISH_REPORT + " (" + e.getMessage() + ")");
		}

		if (pub != null)
			return "redirect:/public/published-report/view?id=" + pub.getId();
		else
			return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	@RequestMapping(value = "/unpublish", method=RequestMethod.GET) 
	public String unpublishReport(@RequestParam(value="id", required=true) Integer reportPublicationId, RedirectAttributes attributes) {
		logger.debug("Inside unpublishReport");
		
		try {
			ReportPublication publishedReport = reportPublicationDao.find(reportPublicationId);
			Report report = publishedReport.getReport();
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			reportPublicationDao.delete(publishedReport);
			
			attributes.addFlashAttribute("successMessage", MSG_REPORT_UNPUBLISHED);
		}
		catch (IllegalArgumentException | NoResultException | IOException | ClassNotFoundException e) {
			attributes.addFlashAttribute("errorMessage", ERR_UNPUBLISH_REPORT + " (" + e.getMessage() + ")");
		}

		return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	private Report prepareReportData(Report report) {
		List<Element> elements = report.getElements(); 		
		if (elements != null) {
 			for (Element element : elements) {
	 			element = prepareElementData(element);
	 		}
 		}
 		return report;
	}
	
	private Element prepareElementData(Element element) {
		if (element.getClass().equals(InputElement.class)) {
			InputElement ie = ((InputElement)element);
			ie.generateStringContent();
			return ie;
		}
		else if (element.getClass().equals(DataElement.class)) {
			DataElement de = ((DataElement)element);
			
			try {
				// Instantiate the data source of the sub-type specified by the data source name
				String className = "edu.rmit.eres.seaports.model.datasource." + WordUtils.capitalize(de.getDataSource().getName()) + "DataSource";
				Constructor<?> constructor = Class.forName(className).getDeclaredConstructor(DataSource.class);
				constructor.setAccessible(true);
				
				// Cast to the correct specific data source type
				Class<? extends DataSource> dataSourceClass = Class.forName(className).asSubclass(DataSource.class);
			    DataSource ds = dataSourceClass.cast(constructor.newInstance(new Object[] { de.getDataSource() }));
			    
			    // Initiate the data source with an Autowired DAO if needed
			    if (ds instanceof ObservedTrendDataSource)
			    	ds.init(observedTrendDataDao);
			    if (ds instanceof ObservedExtremeDataSource || ds instanceof FutureExtremeDataSource)
			    	ds.init(extremeDataDao);
			    if (ds instanceof FutureTrendDataSource)
			    	ds.init(futureTrendDataDao);
			    if (ds instanceof TradeDataSource)
			    	ds.init(tradeDataDao);
			    if (ds instanceof DemographicsDataSource)
			    	ds.init(demographicsDataDao);
			    
			    // Retrieves the data and set the element's data field
			    try {
			    	List<?> data = ds.getData(de);
			    	de.setData(data);
			    }
			    catch (NoResultException e) {
			    	e.printStackTrace();
			    }
			    
			    // Flushes the datasource (dispose of resources not needed such as DAOs)
			    ds.flush();
			    return de;
			}
			catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException 
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return element;
	}
	
	public ModelAndView ModelForReport(Model model, Report report) {
		logger.info("Inside ModelForReport");
		
		// Try/Catch this so no exception is thrown is no user is logged in (allow calling from PublicController) 
		try { model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername())); }
		catch (NoResultException e) {}
		
		ModelAndView mav = new ModelAndView("report");
		
		if (report != null)
		{
			report = this.prepareReportData(report);
			
			Collections.sort(report.getElements(), new ElementPositionComparator());
			mav.addObject("report", report);
			
			try {
				// List of all the element categories
		 		List<ElementCategory> allCategories = elementCategoryDao.getAll();
		 		mav.addObject("allCategories", allCategories);
		 		
		 		//mav.addObject("elements", report.getElements());
		 		
		 		// Empty data element to use as a "New Data Element"
		 		mav.addObject(new DataElement());
		 		
		 		// List of seaports in the workboard's region
		 		List<Seaport> regionSeaports = seaportDao.getAllInRegion(report.getSeaport().getRegion());
		 		mav.addObject("regionSeaports", regionSeaports);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
			}
		}
		
		return mav;
	}

	private ModelAndView ModelForReportCreation(Model model, User user) {
		logger.info("Inside ModelForReportCreation");
		
		ModelAndView mav = new ModelAndView("reportCreation");
		try {
			model.addAttribute("user", user);
			
			Report report = new Report();
			report.setOwner(user);
			report.setSeaport(new Seaport("", "", new Region("", "")));
			mav.addObject("report", report);
			
			// List of all the seaports
	 		List<Seaport> allSeaports = seaportDao.getAll();
	 		mav.addObject("allSeaports", allSeaports);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return mav;
	}
	
	/**
	 * Redirects to the right tab of the workboard after deletion based on the data element type
	 * @param dataElement: the data element
	 * @return the address to which the page should be redirected
	 */
	private String redirectToCategory(Element element) {
		return "/auth/report?id=" + element.getReport().getId() + "#tabs-" + element.getCategory().getDisplayName();
	}
	
	public static final String ERR_ACCESS_DENIED = "You are not allowed to access this Report";
	
	public static final String ERR_CREATE_REPORT = "An error happened while creating your report. Please try again";
	public static final String ERR_DELETE_REPORT = "An error happened while deleting your report. Please try again";
	public static final String ERR_RETRIEVE_REPORT = "Could not retrieve the specified report";
	public static final String ERR_RETRIEVE_REPORT_LIST = "Impossible to retrieve the list of your reports";
	public static final String ERR_REGION_NOT_DEFINED = "Please select a region and a seaport for your report";
	public static final String ERR_PURPOSE_NOT_DEFINED = "Please describe the activity that leads you to use this application";
	
	public static final String ERR_DELETE_ELEMENT = "The element could not be deleted";
	public static final String ERR_FILE_UPLOAD = "Unable to upload the file to the report";
	public static final String ERR_UPLOAD_NO_FILE = "No file received. Please make sure that you have chosen an Excel file to upload, or selected a pre-defined example.";
	public static final String ERR_INVALID_FILETYPE = "This file format is not handled by the application (only text, csv and jpeg files are allowed).";
	public static final String ERR_INVALID_FILE_FORMAT = "Invalid file format. The type of the file you tried to upload is not allowed";
	
	public static final String MSG_ELEMENT_CREATED = "The element has been added successfully to your report";
	public static final String MSG_ELEMENT_EDITED = "Element edited successfully";
	public static final String ERR_EDIT_ELEMENT = "Error editing the element. Please Try Again";
	public static final String MSG_ELEMENT_DELETED = "The element was deleted successfully from the report";	
	public static final String MSG_FILE_UPLOAD_SUCCESS = "The file was uploaded successfully to the report";
	
	public static final String MSG_TEXT_EDITED = "Text edited successfully";
	public static final String ERR_EDIT_TEXT = "Error editing the text. Please Try Again";
	
	public static final String MSG_DISPLAY_TYPE_EDITED = "Display type changed successfully";
	public static final String ERR_EDIT_DISPLAY_TYPE = "Error changing the display type. Please Try Again";
	
	public static final String MSG_REPORT_SAVED = "The report has been saved successfully";
	public static final String ERR_SAVE_REPORT = "Error saving the report. Please Try Again";
	
	public static final String MSG_REPORT_PUBLISHED = "The report is now published and appears publicly on this portal";
	public static final String ERR_PUBLISH_REPORT = "Error publishing the report. Please Try Again";
	public static final String MSG_REPORT_UNPUBLISHED = "The report was unpublished successfully";
	public static final String ERR_UNPUBLISH_REPORT = "Error unpublishing the report. Please Try Again";
}