/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model ;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import edu.rmit.eres.seaports.model.User;

/**
 * Class representing the publication of a report, like a snapshot of this report at a given time
 * @author Guillaume Prevost
 * @since 16th Jan. 2014
 */
@Entity
@Table(name = "ReportPublication")
public class ReportPublication {
	
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the report publication
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The name of the report publication
	 */
	@Column
	private String name;
		
	/**
	 * The user who created the report publication
	 */
	@ManyToOne
	@JoinColumn(name="owner_login")
	private User owner;
	
	/**
	 * The date when the report publication has been created
	 */
	@Column
	private Date creationDate;
			
    /**
     * The binary content of the report publication
     */
    @Column(columnDefinition = "LONGBLOB")
    protected byte[] content;
    
	/**
	 * Default constructor of report publication
	 */
	public ReportPublication() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of report publication specifying all its fields
	 * @param name: the name of the report publication
	 * @param owner: the user who created the report publication
	 */
	public ReportPublication(String name, User owner, byte[] content) {
		setName(name);
		setOwner(owner);
		setContent(content);
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of report publication based on a report
	 * @param report: the report to create this publication from
	 */
	public ReportPublication(Report report) throws IOException {
		setCreationDate(new Date());
		setName(report.getName());
		setOwner(report.getOwner());
		setContent(this.toByteArray(report));
	}
		
    /** 
     * Write the object to a byte array
     */
    private byte[] toByteArray(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
        return baos.toByteArray();
    }

	/**
	 * Getter for the ID of the report publication
	 * @return The unique ID of the report publication
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the report publication
	 * @return The new unique ID of the report publication
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the name of the report publication
	 * @return The current name of the report publication
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the report publication
	 * @return The new name of the report publication
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the owner of the report publication
	 * @return The current owner of the report publication
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Setter for the owner of the report publication
	 * @return The new owner of the report publication
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Getter for the report publication creation date
	 * @return The date when the report publication was created
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * Setter for the report publication creation date
	 * @param creationDate: the new date when the report publication was created
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Getter for the content of the of the report publication
	 * @return The current content of the of the report publication
	 */
	public byte[] getContent() {
		return this.content;
	}
	
	/**
	 * Setter for the content of the of the report publication
	 * @return The new content of the of the report publication
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public Report getReport() throws IOException, ClassNotFoundException {
		byte[] content = getContent();
		
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(content));
		Object obj  = ois.readObject();
		ois.close();
        
        return (Report)obj;
	}
	
	public void setReport(Report report) throws IOException {
		byte[] content = this.toByteArray(report);
		this.setContent(content);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}