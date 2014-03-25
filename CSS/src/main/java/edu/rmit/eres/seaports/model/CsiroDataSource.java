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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import edu.rmit.eres.seaports.dao.CsiroDataDao;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 17th Dec. 2013
 */
@Entity
@DiscriminatorValue(value="Csiro")
public class CsiroDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	@Transient
	private CsiroDataDao csiroDataDao;
	
	/**
	 * Default constructor of data source
	 */
	public CsiroDataSource() {
		super();
	}
	
	/**
	 * Constructor of data source specifying all its fields but the display type
	 * @param name: the name of the data source
	 * @param displayName: the display name of the data source
	 * @param helpText: the introduction/help text of the data source
	 * @param parameters: the list of parameters for this data source
	 * @param seaports: the list of seaports for which this data source is available
	 */
	public CsiroDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public CsiroDataSource(DataSource dataSource) {
		super(dataSource);
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
	public CsiroDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
		CsiroDataDao dataDao = (CsiroDataDao)obj;
		this.csiroDataDao = (CsiroDataDao) dataDao;
	}
	
	@Override
	public void flush() {
		this.csiroDataDao = null;
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<CsiroData> getData(DataElement dataElement) {
		
		String variable = "";
		String emissionScenario = "";
		int year = 0;
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getName().equals("Emission Scenario"))
				emissionScenario = opt.getValue();
			if (opt.getParameter().getName().equals("Variable"))
				variable = opt.getValue();
			if (opt.getParameter().getName().equals("Year"))
				year = Integer.parseInt(opt.getValue());
		}
		String regionName = dataElement.getReport().getSeaport().getRegion().getName();

		List<CsiroData> data = this.csiroDataDao.find(regionName, emissionScenario, year, variable);
				
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}