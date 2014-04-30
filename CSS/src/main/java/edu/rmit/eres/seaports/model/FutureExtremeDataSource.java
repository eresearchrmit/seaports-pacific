/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import edu.rmit.eres.seaports.dao.ExtremeDataDao;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 29th Apr. 2014
 */
@Entity
@DiscriminatorValue(value="FutureExtreme")
public class FutureExtremeDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	@Transient
	private ExtremeDataDao extremeDataDao;
	
	/**
	 * Default constructor of data source
	 */
	public FutureExtremeDataSource() {
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
	public FutureExtremeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public FutureExtremeDataSource(DataSource dataSource) {
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
	public FutureExtremeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
		ExtremeDataDao dataDao = (ExtremeDataDao)obj;
		this.extremeDataDao = (ExtremeDataDao) dataDao;
	}
	
	@Override
	public void flush() {
		this.extremeDataDao = null;
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<ExtremeData> getData(DataElement dataElement) {
		
		Region region = dataElement.getReport().getSeaport().getRegion();
		
		String variableName = "";
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getName().equals("Variable"))
				variableName = opt.getValue();
		}

		List<ExtremeData> data = this.extremeDataDao.find(region, variableName);
		Collections.sort(data, new ExtremeDataComparator());
		
		return data;
	}
	
	public static class ExtremeDataComparator implements Comparator<ExtremeData> {
	      @Override
	      public int compare(ExtremeData s, ExtremeData t) {
	         int f = s.getLocation().compareTo(t.getLocation());
	         return (f != 0) ? f : s.getYear().compareTo(t.getYear());
	      }
	  }
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}