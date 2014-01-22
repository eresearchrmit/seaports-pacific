/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.rmit.eres.seaports.model.Element;
import edu.rmit.eres.seaports.model.ElementCategory;
import edu.rmit.eres.seaports.model.Report;

/**
 * Test class for the Climate Smart Seaports report elements DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ElementDaoTest {
		
	Element elementToSave;
	
	@Autowired
	private ElementDao elementDao;

	@Autowired
	private ReportDao reportDao;
	
	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {		
		elementToSave = new Element(new Date(), "New Element Test", new ElementCategory(), new Report(), true, 1);
	}

	/**
	 * Test for the find method of the ElementDao class.
	 * The method should return an Element object
	 */
	@Test
	public void findElementTest() {
		int refElementId = 1;
		Element resElement = elementDao.find(refElementId);

		Assert.assertNotNull(resElement);
		Assert.assertEquals(refElementId, resElement.getId());
		Assert.assertEquals("Test 1", resElement.getName()); // Port with ID 1 has name 'Test 1' in the test database
	}
	
	/**
	 * Test for the find method of the ElementDao class.
	 * The method should throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findSeaportNullCodeTest() {
		elementDao.find(null);
	}
	
	/**
	 * Test for the find method of the ElementDao class.
	 * The method should throw a NoResultException
	 */
	@Test(expected = NoResultException.class)
	public void findElementUnknownIdTest() {
		elementDao.find(9999); // Non-Existing ID
	}
	
	/**
	 * Test for the getElements method of the ElementDao class.
	 * The method should return a list of 5 Element objects
	 */
	@Test
	public void getElementsFromReportTest() {
		Report report = new Report();
		report.setId(2);
		List<Element> resElements = elementDao.getElements(report);
		
		Assert.assertNotNull(resElements);
		Assert.assertEquals(5, resElements.size()); // There are 5 elements in the report with ID 2
		for (Element resElement : resElements) {
			Assert.assertNotNull(resElement);
			Assert.assertEquals(Element.class, resElement.getClass().getSuperclass());
		}
	}
	
	/**
	 * Test for the getElements method of the ElementDao class with a null report
	 * The method should return an empty list of Element objects
	 */
	@Test
	public void getElementsFromNullReportTest() {
		List<Element> resElements = elementDao.getElements(null);
		
		Assert.assertNotNull(resElements);
		Assert.assertEquals(0, resElements.size());
	}

	/**
	 * Test for the getElements method of the ElementDao class with an empty report
	 * The method should return an empty list of Element objects
	 */
	@Test(expected=NoResultException.class)
	public void getElementsFromReportInvalidIdTest() {
		Report report = reportDao.find(0);
		report.setId(0);
		elementDao.getElements(report);
	}
	
	

	/**
	 * Test for the save method of the ElementDao class
	 */
	@Test
	public void saveNewElementTest() {
		Element savedElement = elementDao.save(elementToSave);
		
		Assert.assertNotNull(savedElement.getName());
		Assert.assertEquals(elementToSave.getName(), savedElement.getName());

		Assert.assertNotNull(savedElement.getIncluded());
		Assert.assertEquals(elementToSave.getIncluded(), savedElement.getIncluded());
		
		Assert.assertNotNull(savedElement.getPosition());
		Assert.assertEquals(elementToSave.getPosition(), savedElement.getPosition());
		
		Assert.assertNotNull(savedElement.getCreationDate());		
		Assert.assertNotNull(savedElement.getCategory());
		Assert.assertNotNull(savedElement.getReport());
	}
	

	/**
	 * Test for the save method of the ElementDao class, with an empty element.
	 */
	@Test
	public void saveEmptyElementTest() {
		Element savedElement = elementDao.save(new Element());
		
		Assert.assertNotNull(savedElement);
		Assert.assertNotNull(savedElement.getId());
	}
	
	/**
	 * Test for the save method of the ElementDao class, with an existing element
	 */
	@Test
	public void saveExistingElementTest() {
		
		// Retrieve the element with ID 1. If this fails, the test is inconclusive and therefore fails
		int elementId = 1;
		Element element = elementDao.find(elementId);
		Assert.assertNotNull(element);
		Assert.assertEquals(elementId, element.getId());
		
		// Modify the element and save the changes
		String newName = "Changed Test Element";
		element.setName(newName);
		Element savedElement = elementDao.save(element);
	
		Assert.assertNotNull(savedElement);
		Assert.assertNotNull(savedElement.getId());
		Assert.assertEquals(elementId, savedElement.getId());
		Assert.assertEquals(newName, savedElement.getName());
	}
	
	/**
	 * Test for save. It should throw an exception since the given element is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveElementNullTest() {
		elementDao.save(null);
	}
	
	/**
	 * Test for delete.
	 */
	@Test
	public void deleteElementCategoryTest() {
		// Retrieve the element with ID 1. If this fails, the test is inconclusive and therefore fails
		int elementId = 1;
		Element element = elementDao.find(elementId);
		Assert.assertNotNull(element);
		Assert.assertEquals(elementId, element.getId());
		
		elementDao.delete(element);
		
		try {
			element = elementDao.find(elementId);
			Assert.fail(); // fails if the element wasn't deleted
		}
		catch(NoResultException e) {
			// Supposed to be triggered after the deletion
		}
	}
	
	/**
	 * Test for delete. It should throw an exception since the given report is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteElementCategoryNullTest() {
		elementDao.delete(null);
	}
}
