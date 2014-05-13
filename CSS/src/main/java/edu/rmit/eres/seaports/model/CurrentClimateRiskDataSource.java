/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 04th Mar. 2014
 */
@Entity
@DiscriminatorValue(value="FutureClimateRisk")
public class CurrentClimateRiskDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * Default constructor of data source
	 */
	public CurrentClimateRiskDataSource() {
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
	public CurrentClimateRiskDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public CurrentClimateRiskDataSource(DataSource dataSource) {
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
	public CurrentClimateRiskDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
		super(name, displayName, helpText, parameters, seaports, displayTypes);
	}
	
	@Override
	public void init(Object obj) {
	}
	
	@Override
	public void flush() {
	}
	
	/**
	 * Retrieves the data according to the given parameters
	 */
	@Override
	public List<CurrentClimateRisk> getData(DataElement dataElement) {
		
		String type = "";
		String consequencesRating = "";
		int i = 0;
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getDisplay().equals(DataSourceParameter.Display.TEXT)) {
				opt.setValue(dataElement.getInputs().get(i));
				i++;
			}
			
			if (opt.getParameter().getName().equals("Event Type"))
				type = opt.getValue();
			else
				consequencesRating += Integer.parseInt(opt.getValue()) + ",";
		}
		
		List<CurrentClimateRisk> data = new ArrayList<CurrentClimateRisk>();
		CurrentClimateRisk risk = new CurrentClimateRisk(type, consequencesRating);
		data.add(risk);
		
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}