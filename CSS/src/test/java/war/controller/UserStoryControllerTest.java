package war.controller;

import java.util.List;

import junit.framework.Assert;

import services.UserLoginService;
import war.dao.DataElementDao;
import war.dao.UserDao;
import war.dao.UserStoryDao;
import war.model.UserAuthority;
import war.model.DataElement;
import war.model.DataElementText;
import war.model.User;
import war.model.UserStory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserStoryControllerTest {
	
	@Autowired
	private UserStoryController userStoryController;
	
	@Autowired
	private UserStoryDao userStoryDao;
	
	@Autowired
	private DataElementDao dataElementDao;
	
	/**
	 * getUserStory should fail because there is no User Story with this ID
	 */
	@Test
	@Transactional
	public void getUserStoryUnknownIdTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userStoryController.getUserStory(99999, model);
		
		// Check the error message
		Assert.assertNotNull(result);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserStoryDao.ERR_NO_SUCH_USERSTORY, model.get("errorMessage"));
		
 		// Check the view name
 		Assert.assertEquals("userstory", result.getViewName());
	}
	
	/**
	 * getUserStory should succeed
	 */
	@Test
	@Transactional
	public void getUserStoryTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User refUser = new User("testuser1", "password", "enabled", UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		int id = 1; // ID of a User Story owned by testuser1
		ModelAndView result = userStoryController.getUserStory(id, model);
		
		// Check the error message
		Assert.assertNotNull(result);
		Assert.assertNull(model.get("errorMessage"));
		
 		// Check the view name
 		Assert.assertEquals("userstory", result.getViewName());
 		
		// Check the User set in the ModelAndView's ModelMap
		Assert.assertTrue(model.get("user").getClass().equals(User.class));
		User resUser = (User)(model.get("user"));
		Assert.assertEquals(refUser, resUser);
		
		// Check that the result is a list of Data Elements and that they all belong to the user story
		UserStory resUserStory = (UserStory)(model.get("userstory"));
		Assert.assertTrue(resUserStory.getDataElements().size() > 0);
		for (DataElement de : resUserStory.getDataElements()) {
			Assert.assertEquals(id, de.getUserStory().getId());
			Assert.assertEquals(refUser.getUsername(), de.getUserStory().getOwner().getUsername());
		}
	}
	
	/**
	 * getUserStoriesList should fail to find a user, but still get the user stories listing view
	 */
	@Test
	@Transactional
	public void getUserStoriesListUnknownUserTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userStoryController.getUserStoriesList("UNKNOWN USER", model);
		
		// Check the error message
		Assert.assertNotNull(result);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserDao.ERR_NO_SUCH_USER, model.get("errorMessage"));
		
		// Check the view name
		Assert.assertEquals("userstoryList", result.getViewName());
	}
	
	/**
	 * getUserStoriesList should succeed but return an empty list as the user has no story
	 */
	@Test
	@Transactional
	public void getUserStoriesListNoStoryTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User refUser = new User("testuser3", "password", "enabled", UserLoginService.ROLE_USER, "email@company.com", "testuser3", "testuser3");
		ModelAndView result = userStoryController.getUserStoriesList(refUser.getUsername(), model);
		
		// Check there is no error
		Assert.assertNotNull(result);
		Assert.assertNull(model.get("errorMessage"));
		
		// Check the view name
		Assert.assertEquals("userstoryList", result.getViewName());
		
		// Check the UserStory set in the ModelAndView's ModelMap
		Assert.assertTrue(result.getModelMap().get("user").getClass().equals(User.class));
		User resUser = (User)(result.getModelMap().get("user"));
		Assert.assertEquals(refUser, resUser);
		
		Assert.assertEquals("My User Stories", model.get("listingTitle"));
		
		// Check that the result is a list of User Stories and that they are all passive
		@SuppressWarnings("unchecked")
		List<Object> resUserStoriesList = (List<Object>)(result.getModelMap().get("userStoriesList"));
		Assert.assertEquals(0, resUserStoriesList.size());
	}
	
	/**
	 * getUserStoriesList should succeed and return a list of the user's stories
	 */
	@Test
	@Transactional
	public void getUserStoriesListTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User refUser = new User("testuser1", "password", "enabled", UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		ModelAndView result = userStoryController.getUserStoriesList(refUser.getUsername(), model);
		
		// Check there is no error
		Assert.assertNotNull(result);
		Assert.assertNull(model.get("errorMessage"));
		
		// Check the view name
		Assert.assertEquals("userstoryList", result.getViewName());
		
		// Check the User set in the ModelAndView's ModelMap
		Assert.assertTrue(result.getModelMap().get("user").getClass().equals(User.class));
		User resUser = (User)(result.getModelMap().get("user"));
		Assert.assertEquals(refUser, resUser);
		
		Assert.assertEquals("My User Stories", model.get("listingTitle"));
		
		// Check that the result is a list of User Stories and that they are all passive
		@SuppressWarnings("unchecked")
		List<Object> resUserStoriesList = (List<Object>)(result.getModelMap().get("userStoriesList"));
		for (Object obj : resUserStoriesList) {
			if (obj instanceof UserStory) {
				UserStory us = (UserStory)obj;
				Assert.assertTrue(us.getMode().equals("passive") || us.getMode().equals("published"));
			}	
			else
				Assert.fail();
		}
	}
	
	/**
	 * getUserStoryView should fail because there is no User Story with this ID
	 */
	@Test
	@Transactional
	public void getUserStoryViewUnknownIdTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userStoryController.getUserStoryView(99999, model);
		
		// Check the error message
		Assert.assertNotNull(result);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserStoryDao.ERR_NO_SUCH_USERSTORY, model.get("errorMessage"));
		
 		// Check the view name
 		Assert.assertEquals("userstoryView", result.getViewName());
	}
	
	/**
	 * getUserStoryView should succeed
	 */
	@Test
	@Transactional
	public void getUserStoryViewTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User refUser = new User("testuser1", "password", "enabled", UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		int id = 1; // ID of a User Story owned by testuser1
		ModelAndView result = userStoryController.getUserStoryView(id, model);
		
		// Check the error message
		Assert.assertNotNull(result);
		Assert.assertNull(model.get("errorMessage"));
		
 		// Check the view name
 		Assert.assertEquals("userstoryView", result.getViewName());
 		
		// Check the User set in the ModelAndView's ModelMap
		Assert.assertTrue(model.get("user").getClass().equals(User.class));
		User resUser = (User)(model.get("user"));
		Assert.assertEquals(refUser, resUser);
		
		// Check that the result is a list of Data Elements and that they all belong to the user story
		UserStory resUserStory = (UserStory)(model.get("userstory"));
		Assert.assertTrue(resUserStory.getDataElements().size() > 0);
		for (DataElement de : resUserStory.getDataElements()) {
			Assert.assertEquals(id, de.getUserStory().getId());
			Assert.assertEquals(refUser.getUsername(), de.getUserStory().getOwner().getUsername());
		}
	}
	
	/**
	 * changeUserStoryPrivacy should succeed
	 */
	@Test
	@Transactional
	public void changeUserStoryPrivacyPublicTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String login = "testuser1";
		Integer id = 1;
		
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals("private", refUserstory.getAccess());
		
		String result = userStoryController.changeUserStoryPrivacy(login, id, false, model);
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + login, result);
		Assert.assertNull(model.get("errorMessage"));
		
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals("public", changedUserstory.getAccess());
	}
	
	/**
	 * changeUserStoryPrivacy should succeed
	 */
	@Test
	@Transactional
	public void changeUserStoryPrivacyPrivateTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String login = "testuser1";
		Integer id = 3;
		
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals("public", refUserstory.getAccess());
		
		String result = userStoryController.changeUserStoryPrivacy(login, id, true, model);
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + login, result);
		Assert.assertNull(model.get("errorMessage"));
		
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals("private", changedUserstory.getAccess());
	}
	
	/**
	 * createUserStory should succeed
	 */
	@Test
	@Transactional
	public void createUserStorySuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 1;
		
		// Check that the story is in Active mode before
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals("active", refUserstory.getMode());
		
		// Create the story. It should switch to active mode
		userStoryController.createUserStory(id, model);
		Assert.assertNull(model.get("errorMessage"));
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals("passive", changedUserstory.getMode());
		
		// Recreate with now a story which is passive. It should succeed as well.
		userStoryController.createUserStory(id, model);
		Assert.assertNull(model.get("errorMessage"));
		changedUserstory = userStoryDao.find(id);
		Assert.assertEquals("passive", changedUserstory.getMode());
	}
	
	/**
	 * createUserStory should fail since the story is already published
	 */
	@Test
	@Transactional
	public void createUserStoryAlreadyPublishedTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 4;
		
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals("published", refUserstory.getMode());
		
		userStoryController.createUserStory(id, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserStoryController.ERR_STORY_ALREADY_PUBLISHED, model.get("errorMessage"));
		
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals("published", changedUserstory.getMode());
	}
	
	/**
	 * saveUserStory should succeed
	 */
	@Test
	public void saveUserStoryUpdateTextsTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 2;
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals(4, refUserstory.getDataElements().size());
		
		String[] updatedTexts = new String[] {"Updated Text 1", "Updated Text 2"};
		
		userStoryController.saveUserStory(updatedTexts, refUserstory, model);
		Assert.assertNull(model.get("errorMessage"));
		
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals(4, changedUserstory.getDataElements().size());
		
		DataElementText textItem = (DataElementText)(changedUserstory.getDataElements().get(1));
		Assert.assertEquals(updatedTexts[0], textItem.getText());
		DataElementText textItem2 = (DataElementText)(changedUserstory.getDataElements().get(3));
		Assert.assertEquals(updatedTexts[1], textItem2.getText());
		
		// Set the texts back to what they were before the test
		String[] refTexts = new String[] {"This is a text comment", "This is a second text comment"};
		userStoryController.saveUserStory(refTexts, changedUserstory, model);
		
	}
	
	/**
	 * saveUserStory should succeed
	 */
	@Test
	public void saveUserStoryUpdateOrderTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 2;
		UserStory refUserstory = userStoryDao.find(id);
		Assert.assertEquals(4, refUserstory.getDataElements().size());
		
		// Unchanged texts
		String[] refTexts = new String[] {"This is a text comment", "This is a second text comment"};
		
		// Re-order the data elements
		UserStory updatedUserStory = userStoryDao.find(id);
		updatedUserStory.getDataElements().get(0).setPosition(4);
		updatedUserStory.getDataElements().get(1).setPosition(3);
		updatedUserStory.getDataElements().get(2).setPosition(1);
		updatedUserStory.getDataElements().get(3).setPosition(2);
		
		userStoryController.saveUserStory(refTexts, updatedUserStory, model);
		Assert.assertNull(model.get("errorMessage"));
		// Check that the data element order has been correctly changed
		UserStory changedUserstory = userStoryDao.find(id);
		Assert.assertEquals(4, changedUserstory.getDataElements().size());
		Assert.assertEquals(4, changedUserstory.getDataElements().get(0).getPosition());
		Assert.assertEquals(3, changedUserstory.getDataElements().get(1).getPosition());
		Assert.assertEquals(1, changedUserstory.getDataElements().get(2).getPosition());
		Assert.assertEquals(2, changedUserstory.getDataElements().get(3).getPosition());
		
		// Reset the order as i was before the test
		updatedUserStory.getDataElements().get(0).setPosition(1);
		updatedUserStory.getDataElements().get(1).setPosition(2);
		updatedUserStory.getDataElements().get(2).setPosition(3);
		updatedUserStory.getDataElements().get(3).setPosition(4);
		userStoryController.saveUserStory(refTexts, updatedUserStory, model);
	}
	
	/**
	 * deleteUserStory should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteUserStorySuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 2;
		
		UserStory refUserStory = userStoryDao.find(id);
		
		String result = userStoryController.deleteUserStory(id, model);
		Assert.assertNull(model.get("errorMessage"));
		Assert.assertEquals("redirect:/auth/userstory/list?user=" + refUserStory.getOwner().getUsername(), result);
		
		/*try {
			userStoryDao.find(id);
			Assert.fail("At this point, the story shouldn't exist anymore. If it is found, the test have failed."); 
		}
		catch (NoResultException e) {
			userStoryDao.save(refUserStory, );
		}*/
	}
	
	/**
	 * deleteUserStory should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteUserStoryNullIdTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		
		String result = userStoryController.deleteUserStory(null, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals("listUS", result);
		Assert.assertEquals(UserStoryController.ERR_DELETE_USERSTORY, model.get("errorMessage"));
	}
	
	/**
	 * deleteUserStory should fail since the story is already published
	 */
	@Test
	@Transactional
	public void deleteUserStoryUnknownIdTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		String result = userStoryController.deleteUserStory(id, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals("listUS", result);
		Assert.assertEquals(UserStoryController.ERR_DELETE_USERSTORY, model.get("errorMessage"));
	}
	
	/**
	 * addTextToUserStory should succeed
	 */
	@Test
	@Transactional
	public void addTextToUserStorySuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 3;
		
		//UserStory refUserStory = userStoryDao.find(id);
		
		userStoryController.addTextToUserStory(id, model);
		Assert.assertNull(model.get("errorMessage"));
		
		//UserStory changedUserStory = userStoryDao.find(id);
		//Assert.assertEquals(refUserStory.getDataElements().size() + 1, changedUserStory.getDataElements().size());
	}
	
	/**
	 * addTextToUserStory should fails since the story ID doesn't exist
	 */
	@Test
	@Transactional
	public void addTextToUserStoryUnknownIDTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		userStoryController.addTextToUserStory(id, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserStoryController.ERR_SAVE_USERSTORY, model.get("errorMessage"));
	}
	
	/**
	 * removeTextFromUserStory should succeed
	 */
	@Test
	@Transactional
	public void removeTextFromUserStorySuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 3;
		
		//UserStory refUserStory = userStoryDao.find(id);
		
		userStoryController.removeTextFromUserStory(id, model);
		Assert.assertNull(model.get("errorMessage"));
		
		//UserStory changedUserStory = userStoryDao.find(id);
		//Assert.assertEquals(refUserStory.getDataElements().size() - 1, changedUserStory.getDataElements().size());
	}
	
	/**
	 * removeTextFromUserStory should fails since the story ID doesn't exist
	 */
	@Test
	@Transactional
	public void removeTextFromUserStoryUnknownIDTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer id = 9999; // NON-EXISTING ID
		
		userStoryController.removeTextFromUserStory(id, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserStoryController.ERR_REMOVE_TEXT, model.get("errorMessage"));
	}
	
	/**
	 * includeDataElementToUserStory should succeed
	 */
	@Test
	public void includeDataElementToUserStorySuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer dataElementId = 4;
		
		// Check that the data element is included at the beginning of the test
		DataElement dataElementRef = dataElementDao.find(dataElementId);
		Assert.assertEquals(true, dataElementRef.getIncluded());
		
		// Exclude the data element from the user story
		userStoryController.includeDataElementToUserStory(dataElementId, model);
		Assert.assertNull(model.get("errorMessage"));
		DataElement dataElementChanged = dataElementDao.find(dataElementId);
		Assert.assertEquals(false, dataElementChanged.getIncluded());
		
		// Re-include the data element to the user story
		userStoryController.includeDataElementToUserStory(dataElementId, model);
		Assert.assertNull(model.get("errorMessage"));
		DataElement dataElementChanged2 = dataElementDao.find(dataElementId);
		Assert.assertEquals(true, dataElementChanged2.getIncluded());
	}
	
	/**
	 * includeDataElementToUserStory should fail since the data element ID doesn't exist
	 */
	@Test
	public void includeDataElementToUserStoryUnknownIdTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		Integer dataElementId = 9999; // NON-EXISTING ID
				
		userStoryController.includeDataElementToUserStory(dataElementId, model);
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(DataElementDao.ERR_NO_SUCH_DATA_ELEMENT, model.get("errorMessage"));
		
	}
	
}