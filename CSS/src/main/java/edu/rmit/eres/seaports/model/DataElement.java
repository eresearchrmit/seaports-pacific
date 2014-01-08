/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing a data element of a report
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
public class DataElement extends Element {

	/**
	 * The source providing data for this data element
	 */
	@ManyToOne
	@JoinColumn(name="data_source_id")
	protected DataSource dataSource;
	
	/**
	 * The selected options for the data source of this element
	 */
    @ManyToMany
    @JoinTable(name="data_element_option", joinColumns={@JoinColumn(name="data_element_id")}, inverseJoinColumns={@JoinColumn(name="data_source_parameter_option_id")})
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<DataSourceParameterOption> selectedOptions;
	
    /**
     * The selected display type for this data element
     */
	@ManyToOne
	@JoinColumn(name="display_type_id")
	protected DisplayType displayType;
	
	/**
	 * Default constructor of data element
	 */
	public DataElement() {
		super();
	}
	
	/**
	 * Constructor of data element specifying all the fields
	 * @param creationDate: the date when the data element was created
	 * @param name: the name of the data element
	 * @param category: the category of the element
	 * @param report: the report to which this element belongs
	 * @param included: whether the data element is included or not in the publication of its parent Report.
	 * @param position: the position of the data element in the report it belongs to
	 * @param dataSource: the source of the data for this data element
	 * @param selectedOptions: the selected options for the data source of this element
	 */
	public DataElement(Date creationDate, String name, ElementCategory category, Report report, boolean included, int position, 
			DataSource dataSource, List<DataSourceParameterOption> selectedOptions) {
		super(creationDate, name, category, report, included, position);
		setDataSource(dataSource);
		setSelectedOptions(selectedOptions);
	}
	
	/**
	 * Constructor of data element specifying all the fields
	 * @param creationDate: the date when the data element was created
	 * @param name: the name of the data element
	 * @param category: the category of the element
	 * @param report: the report to which this element belongs
	 * @param included: whether the data element is included or not in the publication of its parent Report.
	 * @param position: the position of the data element in the report it belongs to
	 * @param dataSource: the source of the data for this data element
	 * @param selectedOptions: the selected options for the data source of this element
	 * @param displayType: the way the data element should be displayed
	 */
	public DataElement(Date creationDate, String name, ElementCategory category, Report report, boolean included, int position, 
			DataSource dataSource, List<DataSourceParameterOption> selectedOptions, DisplayType displayType) {
		super(creationDate, name, category, report, included, position);
		setDataSource(dataSource);
		setSelectedOptions(selectedOptions);
		setDisplayType(displayType);
	}
	

	/**
	 * Getter for the data source of the element
	 * @return the data source of the element
	 */
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	/**
	 * Setter for the data source of the element
	 * @param position: the new data source of the element
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Getter for the selected options for the data source of this element
	 * @return the current selected options for the data source of this element
	 */
	public List<DataSourceParameterOption> getSelectedOptions() {
		return this.selectedOptions;
	}
	
	/**
	 * Setter for the selected options for the data source of this element
	 * @param position: the new selected options for the data source of this element
	 */
	public void setSelectedOptions(List<DataSourceParameterOption> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
	
	/**
	 * Getter for the display type of the element in its report
	 * @return the current display type of the element
	 */
	public DisplayType getDisplayType() {
		return this.displayType;
	}
	
	/**
	 * Setter for the display type of the element in its report
	 * @param position: the new display type of the element
	 */
	public void setDisplayType(DisplayType displayType) {
		this.displayType = displayType;
	}
}