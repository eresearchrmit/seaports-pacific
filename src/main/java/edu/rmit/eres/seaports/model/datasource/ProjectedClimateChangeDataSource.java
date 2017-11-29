/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.model.datasource;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import edu.rmit.eres.seaports.dao.ProjectedClimateChangeDataDao;
import edu.rmit.eres.seaports.model.DataElement;
import edu.rmit.eres.seaports.model.DataSource;
import edu.rmit.eres.seaports.model.DataSourceParameter;
import edu.rmit.eres.seaports.model.DataSourceParameterOption;
import edu.rmit.eres.seaports.model.DisplayType;
import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Seaport;
import edu.rmit.eres.seaports.model.data.ProjectedClimateChangeData;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 5th May. 2014
 */
@Entity
@DiscriminatorValue(value="ProjectedClimateChange")
public class ProjectedClimateChangeDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	@Transient
	private ProjectedClimateChangeDataDao projectedClimateChangeDataDao;
	
	/**
	 * Default constructor of data source
	 */
	public ProjectedClimateChangeDataSource() {
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
	public ProjectedClimateChangeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public ProjectedClimateChangeDataSource(DataSource dataSource) {
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
	public ProjectedClimateChangeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
		ProjectedClimateChangeDataDao dataDao = (ProjectedClimateChangeDataDao)obj;
		this.projectedClimateChangeDataDao = (ProjectedClimateChangeDataDao) dataDao;
	}
	
	@Override
	public void flush() {
		this.projectedClimateChangeDataDao = null;
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<ProjectedClimateChangeData> getData(DataElement dataElement) {
		
		Region region = dataElement.getReport().getSeaport().getRegion();
		
		String variableName = "";
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getName().equals("Variable"))
				variableName = opt.getValue();
		}

		List<ProjectedClimateChangeData> data = this.projectedClimateChangeDataDao.find(region, variableName);
				
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}