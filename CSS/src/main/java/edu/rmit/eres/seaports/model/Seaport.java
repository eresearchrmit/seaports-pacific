/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class representing an Australian seaport. It is related to the NRM region where it is located.
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
@Entity
@Table(name = "Seaport")
public class Seaport implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The code of the seaport
	 */
	@Id
	private String code;
	
	/**
	 * The name of the seaport
	 */
	@Column
	private String name;
	
	/**
	 * The latitude component of the GPS coordinates of the seaport
	 */
	@Column
	private Double latitude;
	
	/**
	 * The longitude component of the GPS coordinates of the seaport
	 */
	@Column
	private Double longitude;
	
	/**
	 * The region where the seaport is located
	 */
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;
	
	/**
	 * The urban center the seaport belongs to, if any
	 */
	@Column
	private String urbanCenter;
	
	/**
	 * The list of available data sources for this seaport
	 */
	@ManyToMany(mappedBy="seaports")
	private List<DataSource> dataSourcesAvailable;
	
	/**
	 * Default constructor of Seaport
	 */
	public Seaport() {
	}
	
	/**
	 * Constructor of Seaport specifying the code, name and region
	 * @param code: the unique code of the seaport
	 * @param name: the name of the seaport
	 * @param region: the region where the seaport is located
	 */
	public Seaport(String code, String name, Region region) {
		setCode(code);
		setName(name);
		setRegion(region);
	}
	
	/**
	 * Constructor of Seaport specifying the code, name, region and urban center
	 * @param code: the unique code of the seaport
	 * @param name: the name of the seaport
	 * @param latitude: the latitude component of the GPS coordinates of the seaport
	 * @param longitude: the longitude component of the GPS coordinates of the seaport
	 * @param region: the region where the seaport is located
	 * @param urbanCenter: the name of the urban center near the seaport
	 */
	public Seaport(String code, String name, Double latitude, Double longitude, Region region, String urbanCenter) {
		setCode(code);
		setName(name);
		setLatitude(latitude);
		setLongitude(longitude);
		setRegion(region);
		setUrbanCenter(urbanCenter);
	}
	
	/**
	 * Getter for the code of the seaport
	 * @return the current code of the seaport
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Setter for the code for the seaport
	 * @param code: the new code of the seaport
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Getter for the name of the seaport
	 * @return the name of the seaport
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for the name of the seaport
	 * @param name: the new name of the seaport
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the latitude component of the GPS coordinates of the seaport
	 * @return the latitude of the seaport
	 */
	public Double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Setter for the latitude component of the GPS coordinates of the seaport
	 * @param latitude: the new latitude of the seaport
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Getter for the longitude component of the GPS coordinates of the seaport
	 * @return the longitude of the seaport
	 */
	public Double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Setter for the longitude component of the GPS coordinates of the seaport
	 * @param location: the new longitude of the seaport
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Getter for the seaport's region
	 * @return the region where the seaport is located
	 */
	public Region getRegion() {
		return this.region;
	}
	
	/**
	 * Setter for the seaport's region
	 * @param region: the new region where the seaport is located
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * Getter for the name of the urban center near the seaport
	 * @return the current name of the urban center near the seaport
	 */
	public String getUrbanCenter() {
		return this.urbanCenter;
	}
	
	/**
	 * Setter for the name of the urban center near the seaport
	 * @param name: the new name of the urban center near the seaport
	 */
	public void setUrbanCenter(String urbanCenter) {
		this.urbanCenter = urbanCenter;
	}
	
	/**
	 * Getter for the list of available data sources for this seaport
	 * @return the list of available data sources for this seaport
	 */
	public List<DataSource> getDataSourcesAvailable() {
		return this.dataSourcesAvailable;
	}
	
	/**
	 * Setter for the list of available data sources for this seaport
	 * @param dataSourcesAvailable: the new list of available data sources for this seaport
	 */
	public void setDataSourcesAvailable(List<DataSource> dataSourcesAvailable) {
		this.dataSourcesAvailable = dataSourcesAvailable;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}