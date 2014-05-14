/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class representing an option of data source parameter
 * @author Guillaume Prevost
 * @since 18th Dec. 2013
 */
@Entity
@Table(name = "DataSourceParameterOption")
public class DataSourceParameterOption implements Serializable {
	
	private static final long serialVersionUID = -838760812583509614L;

	/**
	 * The unique ID of the parameter option
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the parameter option
	 */
	@Column
    private String name;
	
	/**
	 * The value associated to the parameter option
	 */
	@Column
    private String value;
	
	/**
	 * The data source parameter to which this option belongs
	 */
	@ManyToOne
	@JoinColumn(name="data_source_parameter_id")
	private DataSourceParameter parameter;
	
	/**
	 * The position of the option within the parameter options list
	 */
	@Column
	private int position;
	
	/**
	 * The list of data elements using this parameter option
	 */
	@ManyToMany(mappedBy="selectedOptions")
	private List<DataElement> dataElementsList;
	
	/**
	 * Default constructor of data source parameter
	 */
	public DataSourceParameterOption() {
		this.dataElementsList = new ArrayList<DataElement>();
	}
	
	/**
	 * Constructor of data source specifying all its fields
	 * @param name: the name of the data source parameter
	 * @param parameter: the data source parameter to which this option belongs
	 * @param position: the position of the option within the parameter options list
	 */
	public DataSourceParameterOption(String name, String value, DataSourceParameter parameter, int position) {
		setName(name);
		setValue(value);
		setParameter(parameter);
		setPosition(position);
		this.dataElementsList = new ArrayList<DataElement>();
	}
	
	/**
	 * Getter for the unique ID of the parameter option
	 * @return The unique ID of the parameter option
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the unique ID of the parameter option
	 * @param The unique ID of the parameter option
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the name of the parameter option
	 * @return the current name of the parameter option
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the parameter option
	 * @param name: the new name of the parameter option
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the value of the parameter option
	 * @return The value of the parameter option
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Setter for the value of the parameter option
	 * @param The value of the parameter option
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Getter for the value of the parameter option
	 * @return The value of the parameter option
	 */
	public DataSourceParameter getParameter() {
		return this.parameter;
	}
	
	/**
	 * Setter for the value of the parameter option
	 * @param The value of the parameter option
	 */
	public void setParameter(DataSourceParameter parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * Getter for the position of the option within the parameter options list
	 * @return The position of the option within the parameter options list
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Setter for the position of the option within the parameter options list
	 * @param The position of the option within the parameter options list
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Returns the string representation of the parameter's option
	 * @return the string representation of the parameter's option
	 */
	public String toString() {
		return this.name;
	}
}
