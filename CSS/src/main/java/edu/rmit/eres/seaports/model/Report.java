/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import edu.rmit.eres.seaports.controller.RIFCSController;
import edu.rmit.eres.seaports.model.User;

/**
 * Class representing
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
@Table(name = "Report")
public class Report implements Serializable {
	
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the report
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the report
	 */
	@Column
	private String name;
	
	/**
	 * The purpose of the report
	 */
	@Column
	private String purpose;
	
	/**
	 * The mode of the report. It can be 'active', 'passive' or 'published'.
	 * Active: there is only one active report per user, it is considered as its 'Workboard'. The user can add and remove data elements form the report.
	 * Passive: the report is being edited by the user
	 * Published: nothing can be modified by the user as the story is published. 
	 */
	@Column
	private String mode;
	
	/**
	 * The level of privacy for this report. It can be private or public.
	 * Private: only the owner can view the report
	 * Public: everyone can view the report
	 */
	@Column
	private String access;
	
	/**
	 * The user who created the report
	 */
	@ManyToOne
	@JoinColumn(name="owner_login")
	private User owner;
	
	/**
	 * The seaport to which the report is related
	 */
	@ManyToOne
	@JoinColumn(name="seaport_id")
	private Seaport seaport;
	
	/**
	 * The list of data elements contained in the report
	 */
	@OneToMany(targetEntity=Element.class, mappedBy="report", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Element> elements;
	
	/**
	 * The date when the report has been created
	 */
	@Column
	private Date creationDate;
	
	/**
	 * The date when the report has been published
	 */
	@Column
	private Date publishDate;
	
	/**
	 * Default constructor of report
	 */
	public Report() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of report specifying all its fields
	 * @param name: the name of the report
	 * @param purpose: the purpose of the report
	 * @param mode: the mode of the report
	 * @param access: the level of privacy for this report
	 * @param owner: the user who created the report
	 * @param seaport: the seaport to which the user stories is related
	 */
	public Report(String name, String purpose, String mode, String access, User owner, Seaport seaport) {
		setName(name);
		setPurpose(purpose);
		setMode(mode);
		setAccess(access);
		setOwner(owner);
		setSeaport(seaport);
		setElements(new ArrayList<Element>());
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of report specifying all its fields
	 * @param name: the name of the report
	 * @param purpose: the purpose of the report
	 * @param mode: the mode of the report
	 * @param access: the level of privacy for this report
	 * @param owner: the user who created the report
	 * @param seaport: the seaport to which the user stories is related
	 * @param elements: the list of data elements contained in the report
	 */
	public Report(String name, String purpose, String mode, String access, User owner, Seaport seaport, List<Element> elements) {
		setName(name);
		setPurpose(purpose);
		setMode(mode);
		setAccess(access);
		setOwner(owner);
		setSeaport(seaport);
		setElements(elements);
		setCreationDate(new Date());
	}

	/**
	 * Getter for the ID of the report
	 * @return The unique ID of the report
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the report
	 * @return The new unique ID of the report
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the name of the report
	 * @return The current name of the report
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the purpose of the report
	 * @return The new purpose of the report
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	/**
	 * Getter for the purpose of the report
	 * @return The current purpose of the report
	 */
	public String getPurpose() {
		return this.purpose;
	}
	
	/**
	 * Setter for the name of the report
	 * @return The new name of the report
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the owner of the report
	 * @return The current owner of the report
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Setter for the owner of the report
	 * @return The new owner of the report
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * Getter for the mode of the report
	 * @return The current mode of the report
	 */
	public String getMode() {
		return this.mode;
	}
	
	/**
	 * Setter for the mode of the report
	 * Active: there is only one active report per user, it is considered as its 'Workboard'. The user can add and remove data elements form the report.
	 * Passive: the report is being edited by the user
	 * Published: nothing can be modified by the user as the story is published. 
	 * @param mode: The new mode of the report
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
	 * Getter for the privacy level of the report
	 * @return The current privacy level of the report
	 */
	public String getAccess() {
		return this.access;
	}
	
	/**
	 * Setter for the privacy level of the report
	 * Private: only the owner can view the report
	 * Public: everyone can view the report
	 * @return The new privacy level of the report
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * Getter for the elements list of the report
	 * @return The current elements list of the report
	 */
	public List<Element> getElements() {
		return this.elements;
	}
	
	/**
	 * Setter for the elements list of the report
	 * @return The new elements list of the report
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	/**
	 * Getter for the seaport to which the report is related
	 * @return The seaport to which the report is related
	 */
	public Seaport getSeaport() {
		return this.seaport;
	}
	
	/**
	 * Setter for the seaport to which the report is related
	 * @param seaport: the new seaport to which the report is related
	 */
	public void setSeaport(Seaport seaport) {
		this.seaport = seaport;
	}

	/**
	 * Getter for the report creation date
	 * @return The date when the report was created
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * Setter for the report creation date
	 * @param creationDate: the new date when the report was created
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Getter for the report publish date
	 * @return The date when the report was published
	 */
	public Date getPublishDate() {
		return this.publishDate;
	}
	
	/**
	 * Setter for the report publish date
	 * @param publishDate: the new date when the report was published
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	/**
	 * Returns a short summary of the report, based on several of the report's properties
	 * @return a short summary of the report
	 */
	public String getShortDescription() {
		return "Report about climate change in the " + seaport.getRegion().getName() 
		+ " NRM region of Australia, focused on " + seaport.getName() + ".";
	}
	
	/**
	 * Returns a full summary of the report, based on several of the report's properties.
	 * @return a full summary of the report
	 */
	public String getFullDescription() {
		
		// Generates a string with the name the different data sources that have been added to the report
		String dataSourcesUsed = "";
		for (Element element : this.elements) {        	
 			if (element.getClass().equals(DataElement.class)) {
 				DataElement e = (DataElement)element;
 				String dataSourceName = e.getDataSource().getName() + " data";
 				if (!dataSourcesUsed.contains(dataSourceName))
 					dataSourcesUsed += dataSourceName + ", ";
 			}
        }
        
        if (!dataSourcesUsed.isEmpty())
        	dataSourcesUsed += " and ";
        dataSourcesUsed += owner.getFirstname() + " " + owner.getLastname() + "'s personal analysis.";
        
        return "This report was created in reference to " + seaport.getName() + " (" + seaport.getCode() 
        + "), located in the " + seaport.getRegion().getName() + " region. "
		+ "The report is composed of " + dataSourcesUsed + ". " + "It has been created by " + owner.getFirstname() 
		+ " " + owner.getLastname() + " using the Climate Smart Seaports tool available at " 
		+ RIFCSController.CSS_URL + ".";
	}
	
	/**
	 * Returns the HTML version of the report
	 * @return an HTML version of the report
	 */
	public String getHtmlcontent() {
		return "plop";
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}