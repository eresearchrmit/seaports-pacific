package war.controller;

import java.util.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import war.dao.*;
import war.model.*;
import war.ui.UserStoryUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/userstory")
public class UserStoryController {
		
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserStoryDao userStoryDao;
	
	@Autowired
	private DataElementDao dataElementDao;
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView getUserStoriesList(@RequestParam(value="user",required=true) String login, Model model) {
		logger.info("Inside getUserStoriesList !");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listUS");
		
		try {			
			// Retrieve user
			User user = userDao.find(login);
			mav.addObject("user", user);
			
			// Retrieve user's Stories
			List<UserStory> userStoriesList = userStoryDao.getUserStories(user);
			mav.addObject("userStoriesList", userStoriesList);
			
			// Define a title
	 		model.addAttribute("listingTitle", "My User Stories");

		}
		catch (NullPointerException e) {
			model.addAttribute("errorMessage", ERR_RETRIEVE_USERSTORY_LIST);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return mav;
	}
	
	
	@RequestMapping(value= "/lock", method = RequestMethod.GET)
	public String changeUserStoryPrivacy(@RequestParam(value="user",required=true) String login, @RequestParam(value="id",required=true) Integer id, @RequestParam(value="lock",required=true) Boolean lock, Model model) {
		logger.info("Inside getUserStoriesList !");
		try {
			UserStory userStory= userStoryDao.find(id);
			if (lock)
				userStory.setAccess("private");
			else
				userStory.setAccess("public");
			userStoryDao.save(userStory);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return "redirect:/spring/userstory/list?user=" + login;
	}
	
	
	@ModelAttribute("userstory")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUserStory(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside getUserStoryFromUser");
		
		UserStory userStory = null;
		
		try {
			userStory = userStoryDao.find(id);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return modelForUserStoryView(model, userStory);
	}

	
	@RequestMapping(value="/create", method=RequestMethod.GET) 
	public ModelAndView createUserStory(@RequestParam(value="id",required=true) Integer id, Model model) {
		logger.info("Inside includeDataElementToUserStory");
		
		try {
			// Retrieve the user story
			UserStory userstory = userStoryDao.find(id);
			userstory.setMode("passive");
			userStoryDao.save(userstory);
			
			return modelForUserStoryView(model, userstory);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return modelForUserStoryView(model, null);
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST) 
	public ModelAndView saveUserStory(@RequestParam(value="story",required=true) Integer id, 
			@Valid @ModelAttribute("userstory") UserStory updatedUserStory, 
			Model model) {
		logger.info("Inside saveUserStory");
		
		try {
			// Retrieve the original user story
			UserStory userStory = userStoryDao.find(id);
			
			// Reorder the data elements in the user story
			for (DataElement de : userStory.getDataElements()) {
				for (DataElement updatedDataElement : updatedUserStory.getDataElements()) {
					if (de.getId() == updatedDataElement.getId()) {
						de.setPosition(updatedDataElement.getPosition());
						break;
					}
				}
			}
			// Save the user story after reordering
			userStoryDao.save(userStory);
			
			// Update content of the text item
			/*for (DataElementText updatedTextItem : userStory.getTextItems()) {
				if (updatedTextItem != null) {
					DataElementText textItem = (DataElementText) dataElementDao.find(updatedTextItem.getId());
					textItem.setText(updatedTextItem.getText());
					dataElementDao.save(textItem);
				}
			}*/
			
			model.addAttribute("successMessage", MSG_USERSTORY_SAVED);
			return modelForUserStoryView(model, userStory);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return modelForUserStoryView(model, null);
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET) 
	public String deleteUserStory(@RequestParam(value="id", required=true) Integer userStoryId, Model model) {
		logger.debug("Inside deleteUserStory");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listUS");
		
		try {
			UserStory userStory = userStoryDao.find(userStoryId);
			mav.addObject("user", userStory.getOwner().getLogin());
			String ownerLogin = userStory.getOwner().getLogin();
			userStoryDao.delete(userStory);
			return "redirect:/spring/userstory/list?user=" + ownerLogin;
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "listUS";
	}	

	
	@RequestMapping(value="/addText", method=RequestMethod.GET) 
	public ModelAndView addTextToUserStory(@RequestParam(value="story",required=true) Integer id, Model model) {
		logger.info("Inside saveUserStory");
		
		UserStory userStory = null;
		try {
			// Retrieve the original user story
			userStory = userStoryDao.find(id);
			
			DataElementText newTextItem = new DataElementText(new Date(), "Story Text", true, 0, userStory, "Add some text here...");
			dataElementDao.save(newTextItem);
			
			userStory = userStoryDao.find(id);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_SAVE_USERSTORY);
		}
		return modelForUserStoryView(model, userStory);
	}
	
	@RequestMapping(value="/deleteText", method=RequestMethod.GET) 
	public ModelAndView removeTextFromUserStory(@RequestParam(value="text",required=true) Integer id, Model model) {
		logger.info("Inside saveUserStory");
		
		UserStory userStory = null;
		try {
			DataElement textItem = dataElementDao.find(id);
			dataElementDao.delete(textItem);
			
			userStory = userStoryDao.find(textItem.getUserStory().getId());
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", ERR_REMOVE_TEXT);
		}
		return modelForUserStoryView(model, userStory);
	}
	
	@RequestMapping(value="/includeDataElement", method=RequestMethod.GET) 
	public ModelAndView includeDataElementToUserStory(
			@RequestParam(value="dataelement",required=true) Integer dataElementId, 
			@RequestParam(value="story",required=true) Integer id, 
			Model model) {
		logger.info("Inside includeDataElementToUserStory");
		
		UserStory userStory = null;
		try {
			DataElement dataElement = dataElementDao.find(dataElementId);
			dataElement.setIncluded(!dataElement.getIncluded());
			dataElementDao.save(dataElement);
			
			userStory = userStoryDao.find(id);
		}
 		catch (NoResultException e) {
 			model.addAttribute("errorMessage", e);
		}
 		
 		
 		return modelForUserStoryView(model, userStory);
	}
	
	
	private ModelAndView modelForUserStoryView(Model model, UserStory userStory) {
		logger.debug("Inside modelForUserStoryView");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("passiveUS");
		
		if (userStory != null)
		{
			Collections.sort(userStory.getDataElements(), new DateElementPositionComparator());
			
			/*int i = 0;
			for (DataElement de : userStory.getDataElements()) {
				if (de instanceof DataElementText) {
					userStory.getDataElements().set(i, (DataElementText)de);
				}
				i++;
			}*/
			
			model.addAttribute("userstory", userStory);
			model.addAttribute("user", userStory.getOwner());
		}
		return mav;
	}
	
	
	public static final String ERR_RETRIEVE_USERSTORY_LIST = "Impossible to retrieve the list of your stories";
	public static final String MSG_NO_USER_STORY = "There is no story to display";
	public static final String MSG_USERSTORY_SAVED = "The story has been saved successfully";
	public static final String ERR_SAVE_USERSTORY = "Error saving the story. Please Try Again";
	public static final String ERR_REMOVE_TEXT = "Error removing the text. Please Try Again";
}