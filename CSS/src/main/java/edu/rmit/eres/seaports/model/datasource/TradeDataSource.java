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

import edu.rmit.eres.seaports.dao.TradeDataDao;
import edu.rmit.eres.seaports.model.DataElement;
import edu.rmit.eres.seaports.model.DataSource;
import edu.rmit.eres.seaports.model.DataSourceParameter;
import edu.rmit.eres.seaports.model.DataSourceParameterOption;
import edu.rmit.eres.seaports.model.DisplayType;
import edu.rmit.eres.seaports.model.Region;
import edu.rmit.eres.seaports.model.Seaport;
import edu.rmit.eres.seaports.model.data.TradeData;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 29th Apr. 2014
 */
@Entity
@DiscriminatorValue(value="Trade")
public class TradeDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	@Transient
	private TradeDataDao tradeDataDao;
	
	/**
	 * Default constructor of data source
	 */
	public TradeDataSource() {
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
	public TradeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public TradeDataSource(DataSource dataSource) {
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
	public TradeDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
		TradeDataDao dataDao = (TradeDataDao)obj;
		this.tradeDataDao = (TradeDataDao) dataDao;
	}
	
	@Override
	public void flush() {
		this.tradeDataDao = null;
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<TradeData> getData(DataElement dataElement) {
		
		Region region = dataElement.getReport().getSeaport().getRegion();
		
		Boolean imported = true;
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getName().equals("Import/Export")) {
				if (opt.getValue().equals("Import"))
					imported = true;
				else if (opt.getValue().equals("Export"))
					imported = false;
			}
		}
		
		List<TradeData> data = this.tradeDataDao.find(region, imported);
				
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}