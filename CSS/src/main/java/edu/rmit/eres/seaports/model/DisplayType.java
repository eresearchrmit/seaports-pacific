/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Class representing a possible type of display for a data element.
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
@Table(name = "DisplayType")
public class DisplayType implements Serializable {
	
	private static final long serialVersionUID = 3161691084099898374L;

	/**
	 * The unique ID of the Display Type
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the display type
	 */
	@Column
    private String name;

	/**
	 * The list of data sources using this display type
	 */
	@ManyToMany(mappedBy="displayTypes")
	private List<DataSource> dataSourcesList;
	
	/**
	 *  A list of data elements using this display type
	 */
	/*@OneToMany(targetEntity=DataElement.class, mappedBy="displayType",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DataElement> dataElementsList;*/
	
	/**
	 * Default constructor of display type
	 */
	public DisplayType() {
		this.dataSourcesList = new ArrayList<DataSource>();
		//this.dataElementsList = new ArrayList<DataElement>();
	}

	/**
	 * Constructor of display type specifying its name
	 * @param name: the name of the display type
	 */
	public DisplayType(String name) {
		setName(name);
		this.dataSourcesList = new ArrayList<DataSource>();
		//this.dataElementsList = new ArrayList<DataElement>();
	}
	
	/**
	 * Constructor of display type specifying all its fields
	 * @param name: the name of the display type
	 * @param dataSourcesList: the list of data sources using this display type
	 * @param dataElementsList: the list of data elements using this display type
	 */
	/*public DisplayType(String name, List<DataSource> dataSourcesList, List<DataElement> dataElementsList) {
		setName(name);
		setDataSourcesList(dataSourcesList);
		setDataElementsList(dataElementsList);
	}*/
	
	/**
	 * Getter for the unique ID of the display type
	 * @return The unique ID of the display type
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the display type
	 * @param The unique ID of the display type
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the name of the display type
	 * @return the current name of the display type
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the display type
	 * @param name: the new name of the display type
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the list of data sources using this display type
	 * @return the current list of data sources using this display type
	 */
	public List<DataSource> getDataSourcesList() {
		return this.dataSourcesList;
	}
	
	/**
	 * Setter for the list of data sources using this display type
	 * @param name: the new list of data sources using this display type
	 */
	public void setDataSourcesList(List<DataSource> dataSourcesList) {
		this.dataSourcesList = dataSourcesList;
	}
	
	/**
	 * Getter for the list of data elements using this display type
	 * @return the current list of data elements using this display type
	 */
	/*public List<DataElement> getDataElementsList() {
		return this.dataElementsList;
	}*/
	
	/**
	 * Setter for the list of data elements using this display type
	 * @param name: the new list of data elements using this display type
	 */
	/*public void setDataElementsList(List<DataElement> dataElementsList) {
		this.dataElementsList = dataElementsList;
	}*/
	
	/**
	 * String representation of the Display Type
	 * @return the string representation of the display type
	 */
	public String toString() {
		return this.name;
	}

	public boolean equals(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}
}