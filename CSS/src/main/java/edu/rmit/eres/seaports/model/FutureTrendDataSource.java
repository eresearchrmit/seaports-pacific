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

import edu.rmit.eres.seaports.dao.FutureTrendDataDao;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 5th May. 2014
 */
@Entity
@DiscriminatorValue(value="FutureTrend")
public class FutureTrendDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	@Transient
	private FutureTrendDataDao futureTrendDataDao;
	
	/**
	 * Default constructor of data source
	 */
	public FutureTrendDataSource() {
		super();
	}
	
	/**
	 * Constructor of data source specifying all its fields
	 * @param name: the name of the data source
	 * @param displayName: the display name of the data source
	 * @param helpText: the introduction/help text of the data source
	 * @param parameters: the list of parameters for this data source
	 * @param seaports: the list of seaports for which this data source is available
	 */
	public FutureTrendDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public FutureTrendDataSource(DataSource dataSource) {
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
	public FutureTrendDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
		FutureTrendDataDao dataDao = (FutureTrendDataDao)obj;
		this.futureTrendDataDao = (FutureTrendDataDao) dataDao;
	}
	
	@Override
	public void flush() {
		this.futureTrendDataDao = null;
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<FutureTrendData> getData(DataElement dataElement) {
		
		Region region = dataElement.getReport().getSeaport().getRegion();
		
		String variableName = "";
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getName().equals("Variable"))
				variableName = opt.getValue();
		}

		List<FutureTrendData> data = this.futureTrendDataDao.find(region, variableName);
				
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}