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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing a data element of a report
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
public class DataElement extends Element implements Serializable {

	/**
	 * The source providing data for this data element
	 */
	@ManyToOne
	@JoinColumn(name="data_source_id")
	protected DataSource dataSource;
	
	@Transient
	protected List<?> data;
	
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
	
	public void setData(List<?> data) {
		this.data = data;
	}
	
	public List<?> getData() {
		return this.data;	
		
		/*try {
		// Instantiate the data type of data source based on the name
		String className = "edu.rmit.eres.seaports.model." + this.getDataSource().getName() + "DataSource";
		//DataSource ds = (DataSource)(Class.forName(className).newInstance());
			
		Constructor<?> constructor = Class.forName(className).getDeclaredConstructor(this.getDataSource().getClass());
		constructor.setAccessible(true);
		DataSource ds = (DataSource) constructor.newInstance(new Object[] { this.getDataSource() });

		
		//CSIRODataSource ds = new CSIRODataSource(this.dataSource.getName(), this.dataSource.getParameters(), this.dataSource.getSeaports(), this.dataSource.getDisplayTypes());
		List<?> data = ds.getData(this);*/
			
		/*for (Object row : data) {
			CsiroData csiroData = (CsiroData)row;
			String str = csiroData.getValue().toString();
		}*/
		/*
		//CSIRODataSource ds = new CSIRODataSource(this.dataSource.getName(), this.dataSource.getParameters(), this.dataSource.getSeaports(), this.dataSource.getDisplayTypes());
		//List<?> data = ds.getData(this);
	
		// Retrieves the data
		//List<?> data = ds.getData(this.selectedOptions);
		
		/*for (Object row : data) {
			CsiroData csiroData = (CsiroData)row;
			String str = csiroData.getValue().toString();
		}*/
/*
			return data;
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException |
				InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public String getHtml() {
		
		String res = "";
		// Recap of selected options
		for (DataSourceParameterOption opt : selectedOptions)
			res += opt.getParameter().getName() + ": " + opt.getName() + opt.getValue() + "<br />";
				
		try {
			// Instantiate the data type of data source based on the name
			Class<?> cls = Class.forName("edu.rmit.eres.seaports.model." + this.dataSource.getName() + "DataSource");
			Object instance = cls.newInstance();
			DataSource ds = (DataSource)instance;
			
			// Retrieves the data
			/*List<?> data = ds.getData(this.selectedOptions);
			
			// Format the data as a table
			res += "<table>";
			for (Object row : data) {
				String str = row.toString();
				res += "<tr><td>" + str + "</td></tr>";
			}
			res += "</table>";*/
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}