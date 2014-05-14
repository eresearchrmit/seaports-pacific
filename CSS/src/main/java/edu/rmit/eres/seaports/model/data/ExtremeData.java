/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model.data;

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

import edu.rmit.eres.seaports.model.Region;

/**
 * Class representing Future Extreme data.
 * @author Guillaume Prevost
 * @since 30th Apr. 2014
 */
@Entity
@Table(name = "ExtremeData")
public class ExtremeData implements Serializable
{
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the Future Extreme Data
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The location to which the data is related
	 */
	private String location;
	
	/**
	 * The region to which the data is related
	 */
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;
	
	/**
	 * The date when this data has been created
	 */
	private Date creationDate;
	
	/**
	 * The variable that this data represents
	 */
	@ManyToOne
	@JoinColumn(name="extreme_variable_id")
	private ExtremeVariable variable;

	/**
	 * The year for which the value is projected
	 */
	@Column
	private Integer year;
	
	/**
	 * The value of the variation of the variable
	 */
	@Column
	private Double value;

	/**
	 * The name of the source of the data
	 */
	@Column
	private String sourceName;
	
	/**
	 * Default constructor of ExtremeData
	 */
	public ExtremeData() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of ExtremeData
	 * @param creationDate: the date when this data has been created
	 * @param location: the location to which the data is related
	 * @param variable: the variable that this data represents
	 * @param year: the year for which the value is projected
	 * @param value: the value of the data
	 */
	public ExtremeData(Date creationDate, Region region, String location, ExtremeVariable variable, Integer year, Double value, String sourceName) {
		setCreationDate(creationDate);
		setRegion(region);
		setLocation(location);
		setVariable(variable);
		setYear(year);
		setValue(value);
		setSourceName(sourceName);
	}

	/**
	 * Getter for the unique ID of the extreme Data
	 * @return: the unique ID of the extreme Data
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
	 * Getter for the data's location
	 * @return the location to which this data is related
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Setter for the data's location
	 * @param region: the new location to which this data is related
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Getter for the variable that this Data represent
	 * @return: the variable represented by this data
	 */
	public ExtremeVariable getVariable() {
		return variable;
	}
	
	/**
	 * Setter for the variable that this Data represent
	 * @param variable: the new variable represented by this data
	 */
	public void setVariable(ExtremeVariable variable) {
		this.variable = variable;
	}
		
	/**
	 * Getter for year for which the value is projected
	 * @return: the current year for which the value is projected
	 */
	public Integer getYear() {
		return year;
	}
	
	/**
	 * Getter for the year for which the value is projected
	 * @param year: the new year for which the value is projected
	 */
	public void setYear(Integer year) {
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
	
	public String toString() {
		return "Extreme Data: " + this.location + ", " + this.year + ", " + this.value;
	}
}