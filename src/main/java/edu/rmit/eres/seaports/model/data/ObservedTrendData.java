/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
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
 * Class representing the observed trend data
 * @author Guillaume Prevost
 * @since 29th Apr. 2014
 */
@Entity
@Table(name = "ObservedTrendData")
public class ObservedTrendData implements Serializable
{
	private static final long serialVersionUID = -7141765080300638805L;

	/**
	 * The unique ID of the Observed Trend Data
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
	 * The start date of the period this data relates to
	 */
	private Date periodStart;
	
	/**
	 * The end date of the period this data relates to
	 */
	private Date periodEnd;
	
	/**
	 * The variable that this data represents
	 */
	@ManyToOne
	@JoinColumn(name="variable_id")
	private ObservedTrendVariable variable;

	/**
	 * The season when the data is measured (annual, warm season, cool season)
	 */
	@Column
	private String measureSeason;
	
	/**
	 * The numerical baseline value of the data
	 */
	@Column
	private Double baselineValue;
	
	/**
	 * The numerical value of the change
	 */
	@Column
	private Double value;
	
	/**
	 * The value in term of percentage
	 */
	@Column
	private Double percentageValue;
	
	/**
	 * The name of the source of the data
	 */
	@Column
	private String sourceName;
	
	/**
	 * Default constructor of ObservedTrendData
	 */
	public ObservedTrendData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of ObservedTrendData
	 * @param creationDate: the date when this data has been created
	 * @param region: the region to which the data is related
	 * @param periodStart: the start date of the period this data relates to
	 * @param periodEnd: the end date of the period this data relates to
	 * @param variable: variable that this data represents
	 * @param measureSeason: season when the data is measured (annual, warm season, cool season)
	 * @param baselineValue: numerical baseline value of the data
	 * @param value: numerical value of the data
	 * @param percentageValue: value in term of percentage
	 * @param picture: the picture representing the data
	 * @param sourceName: the name of the source of the data
	 */
	public ObservedTrendData(Date creationDate, Region region, Date periodStart, Date periodEnd, ObservedTrendVariable variable, String measureSeason, Double baselineValue, Double value, Double percentageValue, String sourceName) {
		setCreationDate(creationDate);
		setRegion(region);
		setPeriodStart(periodStart);
		setPeriodEnd(periodEnd);
		setVariable(variable);
		setMeasureSeason(measureSeason);
		setBaselineValue(baselineValue);
		setValue(value);
		setPercentageValue(percentageValue);
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
	 * Getter for the start date of the period this data relates to
	 * @return the start date of the period this data relates to
	 */
	public Date getPeriodStart() {
		return this.periodStart;
	}
	
	/**
	 * Setter for the start date of the period this data relates to
	 * @param year: the new start date of the period this data relates to
	 */
	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
	
	/**
	 * Getter for the end date of the period this data relates to
	 * @return the end date of the period this data relates to
	 */
	public Date getPeriodEnd() {
		return this.periodEnd;
	}
	
	/**
	 * Setter for the end date of the period this data relates to
	 * @param year: the new end date of the period this data relates to
	 */
	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}
	
	/**
	 * Getter for the variable that this Data represent
	 * @return: the variable represented by this data
	 */
	public ObservedTrendVariable getVariable() {
		return variable;
	}
	
	/**
	 * Setter for the variable that this Data represent
	 * @param variable: the new variable represented by this data
	 */
	public void setVariable(ObservedTrendVariable variable) {
		this.variable = variable;
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
	 * Getter for the numerical baseline value of the data
	 * @return: the current numerical baseline value of the data
	 */
	public Double getBaselineValue() {
		return this.baselineValue;
	}
	
	/**
	 * Getter for the numerical baseline value of the data
	 * @param baselineValue: the new numerical baseline value of the data
	 */
	public void setBaselineValue(Double baselineValue) {
		this.baselineValue = baselineValue;
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
	 * Getter for the value in term of percentage
	 * @return: the current value in term of percentage
	 */
	public Double getPercentageValue() {
		return percentageValue;
	}
	
	/**
	 * Getter for the value in term of percentage
	 * @param data: the new value in term of percentage
	 */
	public void setPercentageValue(Double percentageValue) {
		this.percentageValue = percentageValue;
	}
	
	/**
	 * Getter for the URL of the picture
	 * @return: the current URL of the picture
	 */
	public String getPicture() {
		char oldChar = ' ';
		char newChar = '-';
		String variableName = this.variable.getShortName().toLowerCase().replace(oldChar, newChar);
		String regionName = this.region.getName().toLowerCase().replace(oldChar, newChar);
		return String.format("trend-%s-%s", variableName, regionName);
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