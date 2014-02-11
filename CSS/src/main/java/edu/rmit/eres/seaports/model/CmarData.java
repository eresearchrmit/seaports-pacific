/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.awt.Point;
import java.io.Serializable;

import org.hibernate.annotations.Type;

/**
 * Class representing the sea level rise data extracted from CMAR
 * @author Guillaume Prevost
 * @since 12th Mar. 2013
 */
@Entity
@Table(name = "CmarData")
public class CmarData implements Serializable
{
	private static final long serialVersionUID = -7141765080300638805L;

	/**
	 * The unique ID of the CMAR Data
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    
	/**
	 * The region to which the data is related
	 */
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;
	
	/**
	 * The climate model used to compute the data
	 */
	@ManyToOne
	@JoinColumn(name="climate_model_id")
	private ClimateModel model;
	
	/**
	 * The name of the climate model
	 */
	@Column
	private String modelName;
	
	/**
	 * The CO2 emissions scenario
	 */
	@ManyToOne
	@JoinColumn(name="climate_emission_scenario_id")
	private ClimateEmissionScenario emissionScenario;
	
	/**
	 * The date when this data has been created
	 */
	private Date creationDate;
	
	/**
	 * The variable that this data represents
	 */
	@ManyToOne
	@JoinColumn(name="climate_variable_id")
	private CsiroVariable variable;

	/**
	 * The year for which year the data is computed
	 */
	@Column
	private int year;
	
	/**
	 * The values of the sea level rise change for various locations
	 * String formated as follows: X1,Y1,Value1;X2,Y2,Value2;...;XN,YN,ValueN
	 */
	@Column
	@Type(type="text")
	private String value;
		
	/**
	 * Default constructor of CmarData
	 */
	public CmarData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of CmarData
	 * @param creationDate: the date when this data has been created
	 * @param region: the region to which the data is related
	 * @param model: the climate model used to compute the data
	 * @param modelName: the name of the climate model
	 * @param emissionScenario: the CO2 emissions scenario
	 * @param variable: the variable that this data represents
	 * @param year: the year for which the data is computed
	 * @param value: the value of the data
	 * @param picture: the picture representing the value
	 */
	public CmarData(Date creationDate, Region region, ClimateModel model, String modelName, ClimateEmissionScenario emissionScenario, CsiroVariable variable, int year, String value) {
		setCreationDate(creationDate);
		setRegion(region);
		setModel(model);
		setModelName(modelName);
		setEmissionScenario(emissionScenario);
		setVariable(variable);
		setYear(year);
		setValue(value);
	}

	/**
	 * Getter for the unique ID of the CMAR Data
	 * @return: the unique ID of the CMAR Data
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for the creation date of the Data
	 * @return: the creation date of the data
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Setter for the creation date of the Data
	 * @param value: the new creation date of the Data
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Getter for the data's region
	 * @return the region to which this data is related
	 */
	public Region getRegion() {
		return this.region;
	}
	
	/**
	 * Setter for the data's region
	 * @param region: the new region to which this data is related
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * Getter for the climate model used to compute the data
	 * @return the climate model used to compute the data
	 */
	public ClimateModel getModel() {
		return this.model;
	}
	
	/**
	 * Setter for the climate model used to compute the data
	 * @param modelName: the new climate model used to compute the data
	 */
	public void setModel(ClimateModel model) {
		this.model = model;
	}
	
	/**
	 * Getter for the name of the climate model within the region
	 * @return the name of the climate model within the region
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * Setter for the name of the climate model within the region
	 * @param modelName: the new name of the climate model within the region 
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * Getter for the emission scenario
	 * @return the emission scenario
	 */
	public ClimateEmissionScenario getEmissionScenario() {
		return this.emissionScenario;
	}
	
	/**
	 * Setter for the emission scenario
	 * @param emissionScenario: the new emission scenario
	 */
	public void setEmissionScenario(ClimateEmissionScenario emissionScenario) {
		this.emissionScenario = emissionScenario;
	}
	
	/**
	 * Getter for the variable that this Data represent
	 * @return: the variable represented by this data
	 */
	public CsiroVariable getVariable() {
		return variable;
	}
	
	/**
	 * Setter for the variable that this Data represent
	 * @param variable: the new variable represented by this data
	 */
	public void setVariable(CsiroVariable variable) {
		this.variable = variable;
	}
	
	 /**
	 * Getter for the year for which year the data is computed
	 * @return the year for which year the data is computed
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * Setter for the year for which year the data is computed
	 * @param year: the new year for which year the data is computed
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Getter for the value of the variable for various positions
	 * @return: the current value of the variation of the variable
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Getter for the value of the variable for various positions
	 * @return: the current value of the variation of the variable
	 */
	public Map<Point,Double> getValues() {
		Map<Point,Double> values = new HashMap<Point,Double>();
		
		String[] split = this.value.split(";");
		for (String str : split) {
			try {
				String[] tmp = str.split(",");
				Point location = new Point();
				location.setLocation(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
				values.put(location, Double.parseDouble(tmp[2]));
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// If 1 value fails, we continue anyways to retrieve all the other values
			}
		}
		return values;
	}
	
	/**
	 * Getter for the value of the variable for various positions
	 * @param value: the new value of the variable for various positions
	 */
	public void setValue(String value) {
		this.value = value;
	}
}