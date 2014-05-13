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
public class FutureClimateRiskDataSource extends DataSource implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * Default constructor of data source
	 */
	public FutureClimateRiskDataSource() {
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
	public FutureClimateRiskDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports) {
		super(name, displayName, helpText, parameters, seaports);
	}
	
	/**
	 * Copy constructor of data source
	 */
	public FutureClimateRiskDataSource(DataSource dataSource) {
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
	public FutureClimateRiskDataSource(String name, String displayName, String helpText, List<DataSourceParameter> parameters, List<Seaport> seaports, List<DisplayType> displayTypes) {
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
	public List<FutureClimateRisk> getData(DataElement dataElement) {
		
		String type = "";
		String riskArea = "";
		String description = "";
		String details = "";
		String futureConsequences = "";
		Integer consequenceRating = 0;
		Integer likelyhoodRating = 0;
		int i = 0;
		for (DataSourceParameterOption opt : dataElement.getSelectedOptions())
		{
			if (opt.getParameter().getDisplay().equals(DataSourceParameter.Display.TEXT)) {
				opt.setValue(dataElement.getInputs().get(i));
				i++;
			}
			
			if (opt.getParameter().getName().equals("Event Type"))
				type = opt.getValue();
			else if (opt.getParameter().getName().equals("Risk Area"))
				riskArea = opt.getValue();
			else if (opt.getParameter().getName().equals("Description"))
				description = opt.getValue();
			else if (opt.getParameter().getName().equals("Details of Risk"))
				details = opt.getValue();
			else if (opt.getParameter().getName().equals("Future Consequences"))
				futureConsequences = opt.getValue();
			else if (opt.getParameter().getName().equals("Consequence Rating"))
				consequenceRating += Integer.parseInt(opt.getValue());
			else if (opt.getParameter().getName().equals("Likelihood"))
				likelyhoodRating += Integer.parseInt(opt.getValue());
		}		
		
		List<FutureClimateRisk> data = new ArrayList<FutureClimateRisk>();
		FutureClimateRisk risk = new FutureClimateRisk(type, riskArea, description, details, futureConsequences, consequenceRating, likelyhoodRating);
		data.add(risk);
		
		return data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}