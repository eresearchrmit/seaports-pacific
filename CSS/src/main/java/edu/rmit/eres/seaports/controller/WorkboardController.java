/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import edu.rmit.eres.seaports.helpers.ElementCreationDateComparator;
import edu.rmit.eres.seaports.helpers.ElementPositionComparator;
import edu.rmit.eres.seaports.helpers.EngineeringModelHelper;
import edu.rmit.eres.seaports.helpers.SecurityHelper;
import edu.rmit.eres.seaports.model.*;

@Controller
@RequestMapping("auth/report")
public class WorkboardController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkboardController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private SeaportDao seaportDao;
	
	@Autowired
	private ElementCategoryDao elementCategoryDao;
	
	@Autowired
	private ElementDao elementDao;

	@Autowired
	private AbsVariableDao absVariableDao;
	
	@Autowired
	private AbsDataDao absDataDao;
	
	@Autowired
	private BitreVariableCategoryDao bitreVariableCategoryDao;
	
	@Autowired
	private BitreDataDao bitreDataDao;
	
	@Autowired
	private CsiroDataDao csiroDataDao;
	
	@Autowired
	private CmarDataDao cmarDataDao;
	
	@Autowired
	private CsiroDataBaselineDao csiroDataBaselineDao;
	
	@Autowired
	private PastDataDao pastDataDao;
	
	@Autowired
	private AcornSatStationDao acornSatStationDao;
	
	@Autowired
	private AcornSatDataDao acornSatDataDao;
	
	@Autowired
	private EngineeringModelVariableDao engineeringModelVariableDao;
	
	@Autowired
	private EngineeringModelAssetDao engineeringModelAssetDao;
	
	@Autowired
	private EngineeringModelDataDao engineeringModelDataDao;
	
	@Autowired
	private ClimateEmissionScenarioDao climateEmissionScenarioDao;
	
	@Autowired
	private ClimateModelDao climateModelDao;
	
	@Autowired
	private ClimateParamsDao climateParamsDao;
	
	@Autowired
	private WeatherEventDao weatherEventDao;

	@RequestMapping(value= "/report-created", method = RequestMethod.GET)
	public String reportCreated(Model model) {
		return "reportCreated";
	}
	
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
			mav.addObject("reportList", reportsList);
			
			// Define a title
	 		model.addAttribute("listingTitle", "My Reports");
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
	
	@RequestMapping(value= "/upload", method = RequestMethod.POST)
	public String uploadfileinWorkboard(
			@RequestParam(value="file",required=true) MultipartFile uploadfile,
			@RequestParam(value="id",required=true) Integer reportId, 
			RedirectAttributes attributes) {
		logger.info("Inside uploadfileinWorkBoard");
		
		Report report = null;
        /*try {*/
        	report = reportDao.find(reportId);
        	
        	if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
    			throw new AccessDeniedException(ERR_ACCESS_DENIED);
        	
            int lastIndex = uploadfile.getOriginalFilename().lastIndexOf('.');
        	String fileName = uploadfile.getOriginalFilename().substring(0, lastIndex);
            String fileExtension = uploadfile.getOriginalFilename().substring(lastIndex + 1);
            
            String contentType = uploadfile.getContentType();
            String[] arrPlainTextFiletypes = {"text/plain", "application/txt", "browser/internal", "text/anytext", "widetext/plain", "widetext/paragraph"};
            String[] arrJpegFiletypes = {"image/jpeg", "image/jpg", "image/jp_", "application/jpg", "application/x-jpg", "image/pjpeg", "image/pipeg", "image/vnd.swiftview-jpeg","image/x-xbitmap"};
            String[] arrCsvFiletypes = {"text/comma-separated-values", "text/csv", "application/csv", "application/excel", "application/vnd.ms-excel", "application/vnd.msexcel"};
            if(ArrayUtils.contains(arrPlainTextFiletypes, contentType) || ArrayUtils.contains(arrJpegFiletypes, contentType) || ArrayUtils.contains(arrCsvFiletypes, contentType)) {
            	
            	// TODO: Create a DataElementFile
            	/*DisplayType displayType = DisplayType.PLAIN;
            	if (ArrayUtils.contains(arrJpegFiletypes, contentType)) // File is a JPEG
            		displayType = DisplayType.PICTURE;
            	else if (ArrayUtils.contains(arrCsvFiletypes, contentType)) // File is a CSV
            		displayType = DisplayType.TABLE;
            	DataElementFile dataElement = new DataElementFile(new Date(), fileName, true, 0, displayType, report, fileExtension, uploadfile.getBytes());
            	dataElementDao.save(dataElement);
            	report.getDataElements().add(dataElement);*/
            	
            	attributes.addFlashAttribute("successMessage", MSG_FILE_UPLOAD_SUCCESS);
            }
            else
            	attributes.addFlashAttribute("errorMessage", ERR_INVALID_FILETYPE);
        /*}
        catch (IOException e) {
        	attributes.addFlashAttribute("errorMessage", ERR_FILE_UPLOAD);
        }*/
        
		return "redirect:/auth/report?id=" + reportId + "#tabs-non-climate-context";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST) 
	public ModelAndView createReport(@ModelAttribute("userstory") Report report, Model model) {
		logger.info("Inside createReport");
        
		try {
			User user = userDao.find(SecurityHelper.getCurrentlyLoggedInUsername());
			Report currentReport = reportDao.getWorkboard(user);
			if (currentReport != null) {
				model.addAttribute("warningMessage", WorkboardController.ERR_ALREADY_CURRENT_WORKBOARD);
				return ModelForReport(model, currentReport);
			}
			
			if (report.getSeaport() == null || report.getSeaport().getRegion() == null)
				throw new IllegalArgumentException(WorkboardController.ERR_REGION_NOT_DEFINED);
			
			if (report.getPurpose() == null || report.getPurpose().length() <= 0)
				throw new IllegalArgumentException(WorkboardController.ERR_PURPOSE_NOT_DEFINED);
			
			Seaport seaport = seaportDao.find(report.getSeaport().getCode());
			
			report.setOwner(user);
			report.setSeaport(seaport);
			report.setMode("active");
			report.setAccess("private");
			report.setElements(new ArrayList<Element>());
			report.setCreationDate(new Date());
			reportDao.save(report);
			
			return new ModelAndView("reportCreated");
		}
		catch (AccessDeniedException e) {
			throw e;
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_CREATE_REPORT + ". Details: " + e.getMessage());
		}
		
		return ModelForReportCreation(model, null);
	}

	@RequestMapping(value = "/delete",method=RequestMethod.GET) 
	public String deleteReport(@RequestParam(value="id", required=true) Integer reportId, Model model) {
		logger.debug("Inside deleteReport");
		
		try {
			model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
			
			Report report = reportDao.find(reportId);
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			User user = report.getOwner();
			reportDao.delete(report); // Deletes the Report
			
			return "redirect:/auth/report/list?user=" + user.getUsername(); // returns to the list of reports
			//return ModelForReportCreation(model, user); // Starts the creation of a new report
		}
		catch (NoResultException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return ("report");
	}	
	
	@RequestMapping(value = "/deletedataelement",method=RequestMethod.GET) 
	public String deleteDataElement(@RequestParam(value="dataelementid",required=true) Integer elementId, 
			RedirectAttributes attributes, Model model) {
		logger.info("Inside deleteDataElement");
		
		try {
			Element element = elementDao.find(elementId);
			Report report = element.getReport();
			
			if (!(SecurityHelper.IsCurrentUserAllowedToAccess(report))) // Security: ownership check
				throw new AccessDeniedException(ERR_ACCESS_DENIED);
			
			// Delete the Data Element if it belongs to the user's report
			if (report.getMode().equals("active")) {
				elementDao.delete(element);
				report.getElements().remove(element);
				attributes.addFlashAttribute("successMessage", MSG_DATA_ELEMENT_DELETED);
			}
			else { // If the Data Element belongs to another report, don't delete
				attributes.addFlashAttribute("errorMessage", ERR_DELETE_DATA_ELEMENT);
			}
			// Redirects to the right tab of the report after deletion based on the data element type
			return "redirect:" + redirectToCategory(element);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return ("redirect:/auth/report/list");
	}
	
	@RequestMapping(value= "/lock", method = RequestMethod.GET)
	public String changeReportPrivacy(
			@RequestParam(value="id",required=true) Integer id, 
			@RequestParam(value="lock",required=true) Boolean lock, 
			RedirectAttributes attributes) {
		logger.info("Inside changeReportPrivacy");
		
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
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_REPORT);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_SAVE_REPORT);
		}
		return "redirect:/auth/report/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
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
				
				attributes.addFlashAttribute("successMessage", MSG_REPORT_PUBLISHED);
			}
			else
				attributes.addFlashAttribute("warningMessage", ERR_REPORT_ALREADY_PUBLISHED);
		}
		catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("errorMessage", ERR_PUBLISH_REPORT);
		}
		catch (NoResultException e) {
			attributes.addFlashAttribute("errorMessage", ERR_PUBLISH_REPORT);
		}

		if (report != null)
			return "redirect:/auth/userstory?id=" + report.getId();
		else
			return "redirect:/auth/userstory/list?user=" + SecurityHelper.getCurrentlyLoggedInUsername();
	}
	
	private ModelAndView ModelForReport(Model model, Report report) {
		logger.info("Inside ModelForReport");
		
		model.addAttribute("user", userDao.find(SecurityHelper.getCurrentlyLoggedInUsername()));
		ModelAndView mav = new ModelAndView("report");
		
		if (report != null)
		{
			Collections.sort(report.getElements(), new ElementPositionComparator());
			mav.addObject("report", report);
			
			try {
				// List of all the element categories
		 		List<ElementCategory> allCategories = elementCategoryDao.getAll();
		 		mav.addObject("allCategories", allCategories);
				
				// Prepare the input elements
				List<Element> elements = report.getElements();
		 		for (Element element : elements) {
		 			if (element.getClass().equals(InputElement.class)) {
		 				((InputElement)element).generateStringContent();
		 			}
				}
		 		
		 		mav.addObject("elements", elements);
		 		
		 		// Empty data element to use as a "New Data Element"
		 		mav.addObject(new DataElement());
		 		

		
		 		// Prepares the various Comboboxes for new data element creation
		 		/*List<EngineeringModelVariable> chlorideEngineeringModelVariables = engineeringModelVariableDao.getAll("Chloride"); 
		 		mav.addObject("chlorideEngineeringModelVariables", chlorideEngineeringModelVariables);
		 		List<EngineeringModelVariable> carbonationEngineeringModelVariables = engineeringModelVariableDao.getAll("Carbonation"); 
		 		mav.addObject("carbonationEngineeringModelVariables", carbonationEngineeringModelVariables);*/
		 		
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
		
		// TODO: redirect to the correct category tab
		/*
		if (dataElement.getClass().equals(DataElementAbs.class) || dataElement.getClass().equals(DataElementBitre.class) || dataElement.getClass().equals(DataElementFile.class))
			return "/auth/workboard?user=" + SecurityHelper.getCurrentlyLoggedInUsername() + "#tabs-non-climate-context";
		else if (dataElement.getClass().equals(DataElementPast.class) || dataElement.getClass().equals(DataElementAcornSat.class))
			return "/auth/workboard?user=" + SecurityHelper.getCurrentlyLoggedInUsername() + "#tabs-observed-climate";
		else if (dataElement.getClass().equals(DataElementCsiro.class) || dataElement.getClass().equals(DataElementCmar.class))
			return "/auth/workboard?user=" + SecurityHelper.getCurrentlyLoggedInUsername() + "#tabs-future-climate";
		else if (dataElement.getClass().equals(DataElementEngineeringModel.class) || dataElement.getClass().equals(DataElementVulnerability.class))
			return "/auth/workboard?user=" + SecurityHelper.getCurrentlyLoggedInUsername() + "#tabs-applications";
		*/
		return "/auth/report?id=" + element.getReport().getId() + "#tabs-" + element.getCategory().getName().replace(' ', '-');
	}
	
	public static final String ERR_ACCESS_DENIED = "You are not allowed to access this Report";
	
	public static final String ERR_CREATE_REPORT = "An error happened while creating your report. Please contact an administrator";
	public static final String ERR_RETRIEVE_REPORT_LIST = "Impossible to retrieve the list of your reports";
	public static final String ERR_ALREADY_CURRENT_WORKBOARD = "There is already a current workboard. Delete it or make a User Story before creating a new Workboard";
	public static final String ERR_REGION_NOT_DEFINED = "Please select a region and a seaport for your workboard";
	public static final String ERR_PURPOSE_NOT_DEFINED = "Please describe the activity that leads you to use this application";
	public static final String ERR_RETRIEVE_REPORT = "Could not retrieve the specified report";
	public static final String ERR_DELETE_DATA_ELEMENT = "The Data Element could not be deleted";
	public static final String ERR_FILE_UPLOAD = "Unable to upload the file to your workboard";
	public static final String ERR_INVALID_FILETYPE = "This file format is not handled by the application (only text, csv and jpeg files are allowed).";
	
	public static final String MSG_ABS_DATA_ADDED = "The ABS data has been added successfully to your workboard";
	public static final String MSG_BITRE_DATA_ADDED = "The Ports Australia data has been added successfully to your workboard";
	public static final String MSG_PAST_DATA_ADDED = "The Past data has been added successfully to your workboard";
	public static final String MSG_ACORNSAT_DATA_ADDED = "The BoM - ACORN-SAT data has been added successfully to your workboard";
	public static final String MSG_CSIRO_DATA_ADDED = "The CSIRO data has been added successfully to your workboard";
	public static final String MSG_CMAR_DATA_ADDED = "The CSIRO - CMAR data has been added successfully to your workboard";
	public static final String MSG_VULNERABILITY_DATA_ADDED = "The Vulnerability has been added successfully to your workboard";
	public static final String MSG_DATA_ELEMENT_DELETED = "The Data Element was deleted successfully from your Workboard";
	public static final String MSG_WORKBOARD_SAVED = "Workboard saved";
	public static final String MSG_FILE_UPLOAD_SUCCESS = "The file was uploaded successfully to your workboard";
	public static final String MSG_ENG_DATA_ADDED = "The Engineering Model Data has been added successfully to your workboard";
	
	public static final String ERR_UPLOAD_ENG_MODEL = "Unable to upload the engineering model data to your workboard";
	public static final String ERR_NO_DATA_ENG_MODEL = "No data could be extracted from the provided Excel file";
	public static final String ERR_NO_DATA_ENG_MODEL_EXAMPLE = "No example data found for the required variable";
	public static final String ERR_INVALID_FILE_FORMAT = "Invalid file format. The type of the file you tried to upload is not allowed";
	public static final String ERR_UPLOAD_NO_FILE = "No file received. Please make sure that you have chosen an Excel file to upload, or selected a pre-defined example.";
	
	public static final String MSG_REPORTSAVED = "The report has been saved successfully";
	public static final String ERR_SAVE_REPORT = "Error saving the report. Please Try Again";
	
	public static final String ERR_PUBLISH_REPORT = "Error publishing the report. Please Try Again";
	public static final String MSG_REPORT_PUBLISHED = "The report is now published. It appears publicly on this portal and will be listed on Reasearch Data Australia search results.";
	public static final String ERR_REPORT_ALREADY_PUBLISHED = "This report is already published";
}