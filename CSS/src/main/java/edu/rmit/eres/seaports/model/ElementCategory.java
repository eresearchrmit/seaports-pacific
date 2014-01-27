/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing an category of elements
 * @author Guillaume Prevost
 * @since 18th Dec. 2013
 */
@Entity
@Table(name = "ElementCategory")
public class ElementCategory implements Serializable {
	
	private static final long serialVersionUID = -6504084261239898621L;

	/**
	 * The unique ID of the category
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	/**
	 * The name of the category
	 */
	@Column
	protected String name;
	
	/**
	 * The description and introduction of the category (HTML)
	 */
	@Column(columnDefinition="TEXT")
	protected String descriptionText;
	
	/**
	 * The help text of the category (HTML)
	 */
	@Column(columnDefinition="TEXT")
	protected String helpText;
	
	/**
	 * The list of available data sources for this category
	 */
	@ManyToMany(targetEntity=DataSource.class, mappedBy="categories")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<DataSource> dataSourcesAvailable;
	
	/**
	 * Default constructor of element category
	 */
	public ElementCategory() {
	}
	
	/**
	 * Constructor of element category specifying the name of the element category
	 * @param name: the name of the element category
	 */
	public ElementCategory(String name) {
		setName(name);
	}
	
	/**
	 * Constructor of element category specifying the name of the element category
	 * @param name: the name of the element category
	 * @param descriptionText: description and introduction of the category (HTML)
	 * @param helpText: help text of the category (HTML)
	 */
	public ElementCategory(String name, String descriptionText, String helpText) {
		setName(name);
		setDescriptionText(descriptionText);
		setHelpText(helpText);
	}
	
	/**
	 * Getter for the unique ID of the category
	 * @return The unique ID of the category
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the category
	 * @param The unique ID of the category
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the name of the category
	 * @return the current name of the category
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the category
	 * @param name: the new name of the category
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the description and introduction of the category (HTML)
	 * @return the current description and introduction of the category (HTML)
	 */
	public String getDescriptionText() {
		return this.descriptionText;
	}
	
	/**
	 * Setter for the description and introduction of the category (HTML)
	 * @param name: the new description and introduction of the category (HTML)
	 */
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	/**
	 * Getter for the help text of the category (HTML)
	 * @return the current help text of the category (HTML)
	 */
	public String getHelpText() {
		return this.helpText;
	}
	
	/**
	 * Setter for the help text of the category (HTML)
	 * @param name: the new help text of the category (HTML)
	 */
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
	
	/**
	 * Getter for the list of available data sources for the category
	 * @return the list of available data sources for the category
	 */
	public List<DataSource> getDataSourcesAvailable() {
		return this.dataSourcesAvailable;
	}
	
	/**
	 * Setter for the list of available data sources for the category
	 * @param dataSourcesAvailable: the new list of available data sources for the category
	 */
	public void setDataSourcesAvailable(List<DataSource> dataSourcesAvailable) {
		this.dataSourcesAvailable = dataSourcesAvailable;
	}
}
