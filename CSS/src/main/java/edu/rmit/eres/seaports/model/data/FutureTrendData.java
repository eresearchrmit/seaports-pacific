/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.rmit.eres.seaports.model.Region;

import java.io.Serializable;

/**
 * Class representing the future trend data
 * @author Guillaume Prevost
 * @since 5th May. 2014
 */
@Entity
@Table(name = "FutureTrendData")
public class FutureTrendData implements Serializable
{
	private static final long serialVersionUID = -7141765080300638805L;

	/**
	 * The unique ID of the Future Trend Data
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The date when this data has been created
	 */
	private Date creationDate;
	
	/**
	 * The region to which the data is related
	 */
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;
	
	/**
	 * The year for which this data is projected
	 */
	private Integer year;
	
	/**
	 * The variable that this data represents
	 */
	@ManyToOne
	@JoinColumn(name="variable_id")
	private FutureTrendVariable variable;

	/**
	 * The CO2 emissions scenario
	 */
	@ManyToOne
	@JoinColumn(name="climate_emission_scenario_id")
	private ClimateEmissionScenario emissionScenario;
	
	/**
	 * The season when the data is measured (annual, warm season, cool season)
	 */
	@Column
	private String measureSeason;
	
	/**
	 * The projected value of the data
	 */
	@Column
	private Double value;
	
	/**
	 * The possible variation (error margin) of the data due to uncertainty of projections
	 */
	@Column
	private Double variation;
	
	/**
	 * The name of the source of the data
	 */
	@Column
	private String sourceName;
	
	/**
	 * Default constructor of ObservedTrendData
	 */
	public FutureTrendData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of FutureTrendData
	 * @param creationDate: the date when this data has been created
	 * @param region: the region to which the data is related
	 * @param year: the year for which this data is projected
	 * @param variable: variable that this data represents
	 * @param emissionScenario: CO2 emissions scenario
	 * @param measureSeason: season when the data is measured (annual, warm season, cool season)
	 * @param value: numerical value of the data
	 * @param variation: possible variation (error margin) of the data due to uncertainty of projections
	 * @param picture: the picture representing the data
	 * @param sourceName: the name of the source of the data
	 */
	public FutureTrendData(Date creationDate, Region region, Integer year, FutureTrendVariable variable, ClimateEmissionScenario emissionScenario, String measureSeason, Double value, Double variation, String sourceName) {
		setCreationDate(creationDate);
		setRegion(region);
		setYear(year);
		setVariable(variable);
		setEmissionScenario(emissionScenario);
		setMeasureSeason(measureSeason);
		setValue(value);
		setVariation(variation);
		setSourceName(sourceName);
	}

	/**
	 * Getter for the unique ID of the Observed Trend Data
	 * @return: the unique ID of the Observed Trend Data
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
	 * Getter for the creation date of the Data
	 * @return: the creation date of the data
	 */
	public Region getRegion() {
		return region;
	}
	
	/**
	 * Setter for the creation date of the Data
	 * @param value: the new creation date of the Data
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * Getter for the year for which this data is projected
	 * @return the start year for which this data is projected
	 */
	public Integer getYear() {
		return this.year;
	}
	
	/**
	 * Setter for the year for which this data is projected
	 * @param year: the new year for which this data is projected
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * Getter for the variable that this Data represent
	 * @return: the current variable represented by this data
	 */
	public FutureTrendVariable getVariable() {
		return variable;
	}
	
	/**
	 * Setter for the variable that this Data represent
	 * @param variable: the new variable represented by this data
	 */
	public void setVariable(FutureTrendVariable variable) {
		this.variable = variable;
	}
	
	/**
	 * Getter for the CO2 emissions scenario
	 * @return: the current CO2 emissions scenario
	 */
	public ClimateEmissionScenario getEmissionScenario() {
		return emissionScenario;
	}
	
	/**
	 * Setter for the CO2 emissions scenario
	 * @param variable: the new CO2 emissions scenario
	 */
	public void setEmissionScenario(ClimateEmissionScenario emissionScenario) {
		this.emissionScenario = emissionScenario;
	}
	
	/**
	 * Getter for the season when the data is measured (annual, warm season, cool season)
	 * @return: the current season when the data is measured
	 */
	public String getMeasureSeason() {
		return measureSeason;
	}
	
	/**
	 * Getter for the season when the data is measured (annual, warm season, cool season)
	 * @param value: the new season when the data is measured
	 */
	public void setMeasureSeason(String measureSeason) {
		this.measureSeason = measureSeason;
	}
	
	/**
	 * Getter for the numerical value of the data
	 * @return: the current numerical value of the data
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * Getter for the numerical value of the data
	 * @param value: the new numerical value of the data
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
	/**
	 * Getter for the possible variation (error margin) of the data due to uncertainty of projections
	 * @return: the current possible variation
	 */
	public Double getVariation() {
		return variation;
	}
	
	/**
	 * Getter for the possible variation (error margin) of the data due to uncertainty of projections
	 * @param data: the new possible variation
	 */
	public void setVariation(Double variation) {
		this.variation = variation;
	}
	
	/**
	 * Getter for the URL of the picture
	 * @return: the current URL of the picture
	 */
	public String getPicture() {
		char oldChar = ' ';
		char newChar = '-';
		String variableName = this.variable.getShortName().toLowerCase().replace(oldChar, newChar);
		String emissionScenario = this.emissionScenario.getName().toLowerCase().replace(oldChar, newChar);
		return String.format("%s-%s-%d", variableName, emissionScenario, this.year);
	}
	
	/**
	 * Getter for the name of the source of the data
	 * @return: the current name of the source of the data
	 */
	public String getSourceName() {
		return sourceName;
	}
	
	/**
	 * Getter for the name of the source of the data
	 * @param sourceUrl: the new name of the source of the data
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}