/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class representing a future trend variable
 * @author Guillaume Prevost
 * @since 5th May. 2014
 */
@Entity
@Table(name = "FutureTrendVariable")
public class FutureTrendVariable extends Variable {
	
	private static final long serialVersionUID = -2491294078433221153L;

	/**
	 * Default constructor of FutureTrendVariable
	 */
	public FutureTrendVariable() {
	}
	
	/**
	 * Constructor of FutureTrendVariable
	 * @param name: the name of the variable
	 * @param shortName: the short name for the variable
	 * @param description: the description of the variable
	 * @param uom: the unit of measure of the variable
	 */
	public FutureTrendVariable(String name, String shortName, String description, String uom) {
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.uom = uom;
		this.uomVariation = "%";
	}
	
	/**
	 * Constructor of FutureTrendVariable
	 * @param name: the name of the variable
	 * @param shortName: the short name for the variable
	 * @param description: the description of the variable
	 * @param uom: the unit of measure of the variable
	 * @param uomVariation: the unit of measure of the variation of the variable
	 */
	public FutureTrendVariable(String name, String shortName, String description, String uom, String uomVariation) {
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.uom = uom;
		this.uomVariation = uomVariation;
	}
}