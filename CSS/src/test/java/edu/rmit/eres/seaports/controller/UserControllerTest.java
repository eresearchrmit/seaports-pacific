/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.controller;
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

import edu.rmit.eres.seaports.controller.UserController;
import edu.rmit.eres.seaports.dao.UserDao;
import edu.rmit.eres.seaports.model.User;
import edu.rmit.eres.seaports.security.UserLoginService;

/**
 * This test class holds the unit tests regarding the registration and authentication of users
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserControllerTest {
	
	@Autowired
	private UserController userController;

	@Autowired
	private UserDao userDao;
	
	User userForTest;
	SecurityContext securityContextUserLoggedIn;
	
	@Before
	public void prepareData() {
		userForTest = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com.au", "testuser1", "testuser1");
		
		Authentication userAuth = new ConcreteAuthentication(userForTest);
		securityContextUserLoggedIn = new SecurityContextImpl();
		securityContextUserLoggedIn.setAuthentication(userAuth);
		
		SecurityContextHolder.clearContext();
	}
	
	/**
	 * Test for the getUser method of the UserController class
	 * Should return an empty User object
	 */
	@Test
	public void getUserTest() {
		User user = userController.getUser();
		Assert.assertNotNull(user);
		Assert.assertNull(user.getPassword());
		Assert.assertNull(user.getUsername());
	}
	
	/**
	 * Test for the loginFailed method of the UserController class
	 * Should return a 'login' view name and set 'error' to true in the model
	 */
	@Test
	public void loginFailedTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String result = userController.loginFailed(model);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("login", result);
		
		Assert.assertNotNull(model.get("error"));
		Assert.assertEquals(true, model.get("error"));
	}
	
	/**
	 * Test for the accessDenied method of the UserController class
	 * Should return a 'accessDenied' view name
	 */
	@Test
	public void accessDeniedTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String result = userController.accessDenied(model);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("accessDenied", result);
	}
	
	/**
	 * Test for the accessDenied method of the UserController class
	 * Should return a 'accessDenied' view name
	 */
	@Test
	public void accessDeniedLoggedInTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		ExtendedModelMap model = new ExtendedModelMap();
		String result = userController.accessDenied(model);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("accessDenied", result);
		
		// Check that the logged in user 'testuser1' has been retrieved in 'user'
		Assert.assertNotNull(model.get("user"));
		Assert.assertTrue(model.get("user") instanceof User);
		User user = (User)model.get("user");
		Assert.assertEquals(userForTest.getUsername(), user.getUsername());
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should succeed
	 */
	@Test
	public void registerNewUserSuccessTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String result = userController.registerNewUser(userForTest, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("introduction", result);
		
		User newUser = userDao.find(userForTest.getUsername());
		Assert.assertNotNull(newUser);
		Assert.assertEquals(userForTest.getUsername(), newUser.getUsername());
		Assert.assertEquals(userForTest.getPassword(), newUser.getPassword());
		Assert.assertEquals(userForTest.getFirstname(), newUser.getFirstname());
		Assert.assertEquals(userForTest.getLastname(), newUser.getLastname());
		Assert.assertEquals(userForTest.getRoles(), newUser.getRoles());
		Assert.assertEquals(userForTest.getNlaNumber(), newUser.getNlaNumber());
		Assert.assertEquals(userForTest.getEnabled(), newUser.getEnabled());
		Assert.assertEquals(userForTest.getNonLocked(), newUser.getNonLocked());
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the login is null
	 */
	@Test
	public void registerNewUserNullLoginTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setUsername(null);
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_LOGIN, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the login is empty
	 */
	@Test
	public void registerNewUserEmptyLoginTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setUsername("");
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_LOGIN, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the password is null
	 */
	@Test
	public void registerNewUserNullPasswordTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setPassword(null);
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_PASSWORD, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the password is less than 5 characters
	 */
	@Test
	public void registerNewUserEmptyPasswordTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setPassword("abcd");
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_PASSWORD, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the first name is null
	 */
	@Test
	public void registerNewUserNullFirstnameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setFirstname(null);
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_FIRSTNAME, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the first name is empty
	 */
	@Test
	public void registerNewUserEmptyFirstnameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setFirstname("");
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_FIRSTNAME, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the last name is null
	 */
	@Test
	public void registerNewUserNullLastNameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setLastname(null);
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_LASTNAME, model.get("errorMessage"));
	}
	
	/**
	 * Registration should fail because the last name is empty
	 */
	@Test
	public void registerNewUserEmptyLastnameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setLastname("");
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_LASTNAME, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the email is null
	 */
	@Test
	public void registerNewUserNullEmailTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setEmail(null);
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("register", result);
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_EMAIL, model.get("errorMessage"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Registration should fail because the email is invalid
	 */
	@Test
	public void registerNewUserInvalidEmailTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		User user = userForTest;
		user.setEmail("");
		String result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(result);		
		Assert.assertEquals("register", result);
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_EMAIL, model.get("errorMessage"));
		
		model = new ExtendedModelMap();
		user.setEmail("foo@bar");
		result = userController.registerNewUser(user, model);
		
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserController.ERR_SIGNUP_INVALID_EMAIL, model.get("errorMessage"));
	}

	/**
	 * Test for the registerNewUser method of the UserController class
	 * Anonymous access to the method should succeed but leave the 'user' field empty
	 */
	@Test
	public void userProfileTest() {
		
		String usernameToFind = "testuser1";
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userController.userProfile(usernameToFind, model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("profile", result.getViewName());
		
		// Check that the profile of 'testuser2' has been retrieved in 'userProfile'
		Assert.assertNotNull(model.get("userProfile"));
		Assert.assertTrue(model.get("userProfile") instanceof User);
		User userProfile = (User)model.get("userProfile");
		Assert.assertEquals(usernameToFind, userProfile.getUsername());
		
		// Check that 'user' is not set because no user is logged in
		Assert.assertNull(model.get("user"));
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Logged in access should succeed and fill in both 'user' and 'userProfile'
	 */
	@Test
	public void userProfileLoggedInTest() {
		SecurityContextHolder.setContext(securityContextUserLoggedIn);
		
		String usernameToFind = "testuser2";
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userController.userProfile(usernameToFind, model);
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("profile", result.getViewName());
		
		// Check that the profile of 'testuser2' has been retrieved in 'userProfile'
		Assert.assertNotNull(model.get("userProfile"));
		Assert.assertTrue(model.get("userProfile") instanceof User);
		User userProfile = (User)model.get("userProfile");
		Assert.assertEquals(usernameToFind, userProfile.getUsername());
		
		// Check that the logged in user 'testuser1' has been retrieved in 'user'
		Assert.assertNotNull(model.get("user"));
		Assert.assertTrue(model.get("user") instanceof User);
		User user = (User)model.get("user");
		Assert.assertEquals(userForTest.getUsername(), user.getUsername());
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 *Should throw an exception since the required username is null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void userProfileNullUsernameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		userController.userProfile(null, model);
	}
	
	/**
	 * Test for the registerNewUser method of the UserController class
	 * Should fail and set an error message since the required username doesn't exist
	 */
	@Test
	public void userProfileUnknownUsernameTest() {
		ExtendedModelMap model = new ExtendedModelMap();
		ModelAndView result = userController.userProfile("UNKNOWN USERNAME", model); // Non-existing username
		
		Assert.assertTrue(result.hasView());
		Assert.assertEquals("profile", result.getViewName());
		
		Assert.assertNull(model.get("userProfile"));
		Assert.assertNotNull(model.get("errorMessage"));
		Assert.assertEquals(UserDao.ERR_NO_SUCH_USER, model.get("errorMessage"));
	}
}