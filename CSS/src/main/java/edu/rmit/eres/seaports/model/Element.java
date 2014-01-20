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

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.rmit.eres.seaports.model.Report;

/**
 * Class representing an element of a report
 * @author Guillaume Prevost
 * @since 17th Dec. 2013
 */
@Entity
@Table(name = "Element")
@DiscriminatorColumn(name = "type")
public class Element implements Serializable {

	protected static final long serialVersionUID = -1308795024262635690L;
    
	/**
	 * The unique ID of the Element
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;

	/**
	 * The date when this element has been created
	 */
	protected Date creationDate;
	
	/**
	 * The name of the element
	 */
	@Column
	protected String name;

	/**
	 * The category of the element
	 */
	@ManyToOne
	@JoinColumn(name="category_id")
	protected ElementCategory category;
	
    /**
     * The report to which this element belongs
     */
	@ManyToOne
	@JoinColumn(name="report_id")
	protected Report report;
	
	/**
	 * Whether the element is included or not in the publication of its parent Report.
	 */
    @Column
    protected boolean included;
	
	/**
	 * The position of the element in the report it belongs to
	 */
    @Column
    protected int position;

	/**
	 * Default constructor of element
	 */
	public Element() {
		setCreationDate(new Date());
	}

	/**
	 * Constructor of element specifying all the fields
	 * @param creationDate: the date when the element was created
	 * @param name: the name of the element
	 * @param category: the category of the element
	 * @param report: the report to which this element belongs
	 * @param included: whether the element is included or not in the publication of its parent Report.
	 * @param position: the position of the element in the report it belongs to
	 * @param displayType: the way the element should be displayed
	 */
	public Element(Date creationDate, String name, ElementCategory category, Report report, boolean included, int position) {
		setCreationDate(creationDate);
		setName(name);
		setCategory(category);
		setReport(report);
		setIncluded(included);
		setPosition(position);
	}
    
	/**
	 * Getter for the unique ID of the element
	 * @return The unique ID of the element
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the unique ID of the element
	 * @param The unique ID of the element
	 */
	public void setId(int id) {
		this.id = id ;
	}
	
	/**
	 * Getter for the creation date of the element
	 * @return: the creation date of the element
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Setter for the creation date of the element
	 * @param value: the new creation date of the element
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Getter for the name of the element
	 * @return the current name of the element
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name of the element
	 * @param name: the new name of the element
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the category of the element
	 * @return The current category of the element
	 */
	public ElementCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Setter for the category of the element
	 * @param report: The new category of the element
	 */
	public void setCategory(ElementCategory category) {
		this.category = category;
	}
	
	/**
	 * Getter for the report containing this element
	 * @return The report currently containing this element
	 */
	public Report getReport() {
		return this.report;
	}
	
	/**
	 * Setter for the report containing this element
	 * @param report: The new report currently containing this element
	 */
	public void setReport(Report report) {
		this.report = report;
	}

	/**
	 * Getter for the inclusion of the element in its parent's publication
	 * @return the current inclusion of the element
	 */
	public boolean getIncluded() {
		return this.included;
	}
	
	/**
	 * Setter for the inclusion of the element in its parent's publication
	 * @param position: the new inclusion of the element
	 */
	public void setIncluded(boolean included) {
		this.included = included;
	}
	
	/**
	 * Getter for the position of the element in its report
	 * @return the current position of the element
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Setter for the position of the element in its report
	 * @param position: the new position of the element
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}