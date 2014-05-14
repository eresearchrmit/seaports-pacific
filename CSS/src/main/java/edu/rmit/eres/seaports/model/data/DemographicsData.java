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
 * Class representing trade data
 * @author Guillaume Prevost
 * @since 14th May. 2014
 */
@Entity
@Table(name = "DemographicsData")
public class DemographicsData implements Serializable
{
	private static final long serialVersionUID = -7141765080300638805L;

	/**
	 * The unique ID of the trade Data
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
	 * The year to which this data relates to
	 */
	private Integer year;
		
	/**
	 * The variable that this data represents
	 */
	@ManyToOne
	@JoinColumn(name="variable_id")
	private DemographicsVariable variable;
		
	/**
	 * The numerical value of the data
	 */
	@Column
	private Double value;
	
	/**
	 * The name of the source of the data
	 */
	@Column
	private String sourceName;
	
	/**
	 * Default constructor of TradeData
	 */
	public DemographicsData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of TradeData
	 * @param creationDate: the date when this data has been created
	 * @param region: the region to which the data is related
	 * @param year: the year to which this data relates
	 * @param variable: variable that this data represents
	 * @param value: numerical value of the data
	 * @param sourceName: the name of the source of the data
	 */
	public DemographicsData(Date creationDate, Region region, Integer year, DemographicsVariable variable, Double value, String sourceName) {
		setCreationDate(creationDate);
		setRegion(region);
		setYear(year);
		setVariable(variable);
		setValue(value);
		setSourceName(sourceName);
	}

	/**
	 * Getter for the unique ID of the Trade Data
	 * @return: the unique ID of the Trade Data
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
	 * Getter for the year to which this data relates to
	 * @return the current year to which this data relates to
	 */
	public Integer getYear() {
		return this.year;
	}
	
	/**
	 * Setter for the year to which this data relates to
	 * @param year: the new year to which this data relates to
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
		
	/**
	 * Getter for the variable that this Data represent
	 * @return: the variable represented by this data
	 */
	public DemographicsVariable getVariable() {
		return variable;
	}
	
	/**
	 * Setter for the variable that this Data represent
	 * @param variable: the new variable represented by this data
	 */
	public void setVariable(DemographicsVariable variable) {
		this.variable = variable;
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