/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Class representing a parameter of a data source
 * @author Guillaume Prevost
 * @since 18th Dec. 2013
 */
@Entity
@Table(name = "DataSourceParameter")
public class DataSourceParameter implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the parameter
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the parameter
	 */
	@Column
    private String name;
	
	/**
	 * The description of the parameter
	 */
	@Column(columnDefinition="TEXT")
    private String description;
	
	/**
	 * The data source containing this parameter
	 */
	@ManyToOne
	@JoinColumn(name="datasource_id")
	private DataSource dataSource;
	
	/**
	 * A list of the seaports located in the region
	 */
	@OneToMany(targetEntity=DataSourceParameterOption.class, mappedBy="parameter",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DataSourceParameterOption> options;
	
    /**
     * The way the data element should be displayed
     */
	@Enumerated(EnumType.STRING)
	private Display display;
	
	/**
	 * Default constructor of data source parameter
	 */
	public DataSourceParameter() {
	}
	
	/**
	 * Constructor of data source specifying all its fields
	 * @param name: the name of the data source parameter
	 */
	public DataSourceParameter(String name, String description, DataSource dataSource, List<DataSourceParameterOption> options, Display display) {
		setName(name);
		setDescription(description);
		setDataSource(dataSource);
		setOptions(options);
		setDisplay(display);
	}
	
	/**
	 * Getter for the unique ID of the parameter
	 * @return The unique ID of the parameter
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the unique ID of the parameter
	 * @param The unique ID of the parameter
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the name of the parameter
	 * @return the current name of the parameter
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name of the parameter
	 * @param name: the new name of the parameter
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the description of the parameter
	 * @return the current description of the parameter
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for the description of the parameter
	 * @param name: the new description of the parameter
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for the data source containing the parameter
	 * @return the current data source containing the parameter
	 */
	public DataSource getDataSource() {
		return this.dataSource; 
	}
	
	/**
	 * Setter for the data source containing the parameter
	 * @param position: the new data source containing the parameter
	 */
	public void setDataSource (DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Getter for the options of the parameter
	 * @return the current options of the parameter
	 */
	public List<DataSourceParameterOption> getOptions() {
		return this.options; 
	}
	
	/**
	 * Setter for the options of the parameter
	 * @param position: the new options of the parameter
	 */
	public void setOptions (List<DataSourceParameterOption> options) {
		this.options = options;
	}
	
	/**
	 * Getter for the display of the parameter and its options
	 * @return the current display of the parameter and its options
	 */
	public Display getDisplay() {
		return this.display;
	}
	
	/**
	 * Setter for the display of the parameter and its options
	 * @param position: the new display of the parameter and its options
	 */
	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * The possible types of display for a Parameter. Default is UNDEFINED
	 * @author Guillaume Prevost
	 * @since 18th Apr. 2013
	 */
	public enum Display {
		UNDEFINED("undefined"),
		DROPDOWN("dropdown"),
		RADIO("radio"),
		TEXT("text");
		
		private String text;

		Display(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public static Display fromString(String text) {
			if (text != null) {
				for (Display b : Display.values()) {
					if (text.equalsIgnoreCase(b.text)) {
						return b;
					}
				}
			}
			return Display.UNDEFINED;
		}
		
		public String toString() {
			return text;
		}

		public boolean equals(Display otherDisplay) {
			return (otherDisplay == null) ? false : text.equals(otherDisplay.getText());
		}
		
		public boolean equals(String otherText) {
			return (otherText == null) ? false : text.equals(otherText);
		}
	}
}
