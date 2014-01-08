/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.helpers;

import java.util.Comparator;

import edu.rmit.eres.seaports.model.Element;

/**
 * Implementation of the Comparator interface enabling the comparison of 
 * Element objects in function of the date and time they were created.
 * @author Guillaume Prevost
 */
public class ElementCreationDateComparator implements Comparator<Element> {
	@Override
    public int compare(Element elem1, Element elem2) {
        return -(elem1.getCreationDate().compareTo(elem2.getCreationDate()));
    }
}