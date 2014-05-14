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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 17th Dec. 2013
 */
@Entity
@Table(name = "DataSource")
@DiscriminatorColumn(name = "type")
public class DataSource implements IDataSource, Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
    
	/**
	 * The unique ID of the data source
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the data source
	 */
	@Column
    private String name;
	
	/**
	 * The display name of the data source
	 */
	@Column
    private String displayName;
	
	/**
	 * The introduction/help text of the data source
	 */
	@Column(columnDefinition = "TEXT")
    private String helpText;

	/**
	 * A list of the parameters for this data source
	 */
	@OneToMany(targetEntity=DataSourceParameter.class, mappedBy="dataSource", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DataSourceParameter> parameters;
	
	/**
	 * The list of engineering model data contained in this data element
	 */
    @ManyToMany
    @JoinTable(name="data_source_display_types", joinColumns={@JoinColumn(name="data_source_id")}, inverseJoinColumns={@JoinColumn(name="display_type_id")})
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<DisplayType> displayTypes;

    
	/**
	 * The list of seaports for which this data source is available
	 */
    @ManyToMany
    @JoinTable(name="data_source_seaports", joinColumns={@JoinColumn(name="data_source_id")}, inverseJoinColumns={@JoinColumn(name="seaport_code")})
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<Seaport> seaports;
	
	/**
	 * The list of element categories for which this data source is available
	 */
    @ManyToMany
    @JoinTable(name="data_source_element_category", joinColumns={@JoinColumn(name="data_source_id")}, inverseJoinColumns={@JoinColumn(name="element_category_id")})
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<ElementCategory> categories;
	
	/**
	 * Default constructor of data source
	 */
	public DataSource() {
	}
	
	/**
	 * Copy constructor of data source
	 */
	public DataSource(DataSource dataSource) {
		setId(dataSource.getId());
		setName(dataSource.getName());
		setCategories(dataSource.getCategories());
		setDisplayTypes(dataSource.getDisplayTypes());
		setSeaports(dataSource.getSeaports());
	}
	
	/**
	 * Constructor of data source specifying all its fields
	 * @param name: the name of the data source
	 * @param displayName: the display name of the data source
	 * @param helpText: the introduction/help text of the data source
	 * @param parameters: the list of parameters for this data source
	 * @param seaports: the list of seaports for which this data source is available
	 */
	public DataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		setName(name);
		setDisplayName(displayName);
		setHelpText(helpText);
		setParameters(parameters);
		setSeaports(seaports);
	}

	/**
	 * Constructor of data source specifying all its fields
	 * @param name: the name of the data source
	 * @param displayName: the display name of the data source
	 * @param helpText: the introduction/help text of the data source
	 * @param parameters: the list of parameters for this data source
	 * @param seaports: the list of seaports for which this data source is available
	 * @param displayTypes: the display types available for this data source
	 */
	public DataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		setName(name);
		setDisplayName(displayName);
		setHelpText(helpText);
		setParameters(parameters);
		setSeaports(seaports);
		setDisplayTypes(displayTypes);
	}
	
	public void init(Object obj) {
		
	}

	public void flush() {
	
	}
		
	/**
	 * Getter for the unique ID of the data source
	 * @return The unique ID of the data source
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the unique ID of the data source
	 * @param The unique ID of the data source
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the name of the data source
	 * @return the current name of the data source
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name of the data source
	 * @param name: the new name of the data source
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the introduction/help text of the data source
	 * @return the current introduction/help text of the data source
	 */
	public String getHelpText() {
		return helpText;
	}
	
	/**
	 * Setter for the introduction/help text of the data source
	 * @param name: the new introduction/help text of the data source
	 */
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
	
	/**
	 * Getter for the display name of the data source
	 * @return the current display name of the data source
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Setter for the display name of the data source
	 * @param name: the new display name of the data source
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Getter for the parameters of the data source
	 * @return the current parameters of the data source
	 */
	public List<DataSourceParameter> getParameters() {
		return this.parameters;
	}
	
	/**
	 * Setter for the parameters of the data source
	 * @param parameters: the new parameters of the data source
	 */
	public void setParameters(List<DataSourceParameter> parameters) {
		this.parameters = parameters;
	}
	
	
	/**
	 * Getter for the available display types of the data source
	 * @return the current available display types of the data source
	 */
	public List<DisplayType> getDisplayTypes() {
		return this.displayTypes;
	}
	
	/**
	 * Setter for the available display types of the data source
	 * @param displayTypes: the new available display types of the data source
	 */
	public void setDisplayTypes(List<DisplayType> displayTypes) {
		this.displayTypes = displayTypes;
	}
	
	/**
	 * Getter for the list of seaports for which this data source is available
	 * @return the current available list of seaports for which this data source is available
	 */
	public List<Seaport> getSeaports() {
		return this.seaports;
	}
	
	/**
	 * Setter for the list of seaports for which this data source is available
	 * @param displayTypes: the new list of seaports for which this data source is available
	 */
	public void setSeaports(List<Seaport> seaports) {
		this.seaports = seaports;
	}
	
	/**
	 * Getter for the list of element categories for which this data source is available
	 * @return the current available list of element categories for which this data source is available
	 */
	public List<ElementCategory> getCategories() {
		return this.categories;
	}
	
	/**
	 * Setter for the list of element categories for which this data source is available
	 * @param displayTypes: the new list of element categories for which this data source is available
	 */
	public void setCategories(List<ElementCategory> categories) {
		this.categories = categories;
	}
	
	public List<?> getData(DataElement dataElement) {
		return new ArrayList<Object>();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}