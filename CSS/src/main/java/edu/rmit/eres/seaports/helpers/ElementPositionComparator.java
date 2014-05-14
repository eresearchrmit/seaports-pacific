/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.helpers;

import java.util.Comparator;

import edu.rmit.eres.seaports.model.Element;

/**
 * Implementation of the Comparator interface enabling the comparison of 
 * Element objects in function of their position in the Report 
 * they belong to.
 * @author Guillaume Prevost
 */
public class ElementPositionComparator implements Comparator<Element> {
	@Override
    public int compare(Element elem1, Element elem2) {
        return Integer.compare(elem1.getPosition(), elem2.getPosition());
    }
}