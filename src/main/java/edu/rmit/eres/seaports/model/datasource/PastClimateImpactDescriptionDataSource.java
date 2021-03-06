/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.model.datasource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import edu.rmit.eres.seaports.model.DataElement;
import edu.rmit.eres.seaports.model.DataSource;
import edu.rmit.eres.seaports.model.DataSourceParameter;
import edu.rmit.eres.seaports.model.DataSourceParameterOption;
import edu.rmit.eres.seaports.model.DisplayType;
import edu.rmit.eres.seaports.model.Seaport;
import edu.rmit.eres.seaports.model.data.WeatherEvent;

/**
 * Class representing a source of data
 * @author Guillaume Prevost
 * @since 04th Mar. 2014
 */
@Entity
@DiscriminatorValue(value="PastClimateImpactDescription")
public class PastClimateImpactDescriptionDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
		
	/**
	 * Default constructor of data source
	 */
	public PastClimateImpactDescriptionDataSource() {
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
	public PastClimateImpactDescriptionDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public PastClimateImpactDescriptionDataSource(DataSource dataSource) {
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
	public PastClimateImpactDescriptionDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
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
	public List<WeatherEvent> getData(DataElement dataElement) {
		
		String type = "";
		int year = 0;
		Boolean direct = false;
		String impact = "";
		String consequencesRating = "";
		String consequencesOther = "";
		Boolean responseAdequate = false;
		String changes = "";
		int i = 0;
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getDisplay().equals(DataSourceParameter.Display.TEXT)) {
				opt.setValue(dataElement.getInputs().get(i));
				i++;
			}
			
			if (opt.getParameter().getName().equals("Weather Event"))
				type = opt.getValue();
			else if (opt.getParameter().getName().equals("Date"))
				year = Integer.parseInt(opt.getValue());
			else if (opt.getParameter().getName().equals("Direct or indirect impact"))
				direct = opt.getValue().equals("1") ? true : false;
			else if (opt.getParameter().getName().equals("Impact")) {
				impact = opt.getValue();
			}
			else if (opt.getParameter().getName().equals("Other business consequences"))
				consequencesOther = opt.getValue();
			else if (opt.getParameter().getName().equals("Would you say your response was adequate?"))
				responseAdequate = opt.getValue().equals("1") ? true : false;
			else if (opt.getParameter().getName().equals("What were the changes implemented as a result of this event?"))
				changes = opt.getValue();
			else
				consequencesRating += Integer.parseInt(opt.getValue()) + ",";
		}		
		
		List<WeatherEvent> data = new ArrayList<WeatherEvent>();
		//data.add(this.weatherEventDao.findByElem(dataElement.getId()));
		WeatherEvent event = new WeatherEvent(type, year, direct, impact, 
				consequencesRating, consequencesOther, responseAdequate, changes);
		data.add(event);
		
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}