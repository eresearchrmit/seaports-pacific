/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class representing variation data from the dataset available from CSIRO. 
 * It associates values to the climate variables and climate parameters.
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
@Entity
@Table(name = "CsiroData")
public class CsiroData implements Serializable
{
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the CSIRO Data
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
	@JoinColumn(name="csiro_variable_id")
	private CsiroVariable variable;

	/**
	 * The baseline value of the variable
	 */
	@Column
	private Double baseline;
	
	/**
	 * The year for which year the data is computed
	 */
	@Column
	private int year;
	
	/**
	 * The value of the variation of the variable
	 */
	@Column
	private Double value;

	/**
	 * Default constructor of CsiroData
	 */
	public CsiroData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of CsiroData
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
	public CsiroData(Date creationDate, Region region, ClimateModel model, String modelName, ClimateEmissionScenario emissionScenario, CsiroVariable variable, int year, Double baseline, Double value) {
		setCreationDate(creationDate);
		setRegion(region);
		setModel(model);
		setModelName(modelName);
		setEmissionScenario(emissionScenario);
		setVariable(variable);
		setYear(year);
		setBaseline(baseline);
		setValue(value);
	}

	/**
	 * Getter for the unique ID of the CSIRO Data
	 * @return: the unique ID of the CSIRO Data
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
	 * Getter for the baseline value of the variable
	 * @return: the current baseline value of the variable
	 */
	public Double getBaseline() {
		return baseline;
	}
	
	/**
	 * Getter for the baseline value of the variable
	 * @param value: the new baseline value of the variable
	 */
	public void setBaseline(Double baseline) {
		this.baseline = baseline;
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
	 * Getter for the value of the variation of the variable
	 * @return: the current value of the variation of the variable
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * Getter for the value of the variation of the variable
	 * @param value: the new value of the variation of the variable
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
	public String toString() {
		return "Csiro Data: " + this.region.getName() + ", "+ this.modelName + "(" + this.model.getName() + "), " + this.emissionScenario.getName() + ", " + this.year + ", " + this.value;
	}
}