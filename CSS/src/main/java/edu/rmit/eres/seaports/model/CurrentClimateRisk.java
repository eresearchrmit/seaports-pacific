/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class representing a climate variable
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
public class CurrentClimateRisk implements Serializable {
	
	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * A list of allowed climate events types
	 */
	public static final String[] eventTypes = new String[] {"Sea current", "Wave climate", "Sea Surface Temperature", "Sea acidity", "Storm surge", "Cyclone", "Intense rainfall", "Wind speed/direction", "Heat wave", "Drought"};
		
	/**
	 * A list of the allowed risk areas
	 */
	public static final String[] riskAreas = new String[] {"Marine Infrastructure", "Port Infrastructure", "Port Superstructure", "Supply Chain", "Operations", "Workforce", "Financial", "Legal/Regulations", "Environment", "Stakeholders", "Reputation"};
	
	/**
	 * A list of the allowed consequence ratings
	 */
	public static final String[] consequencesRatings = new String[] {"Not vulnerable", "Could be vulnerable", "Somewhat vulnerable", "Moderately vulnerable", "Significantly vulnerable"};
	
	/**
	 * The unique ID of the Weather Event
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * The data element containing the future climate risk
	 */
	@ManyToOne
	@JoinColumn(name="data_element_id")
	private DataElement element;
	
	/**
	 * The type of event responsible of the risk
	 */
	@Column
	private String eventType;
		
	/**
	 * The rating of the consequences of the risk
	 */
	@Column
	private String consequencesRating;
		
	/**
	 * Default constructor of FutureClimateRisk
	 */
	public CurrentClimateRisk() {
	}
	
	/**
	 * Constructor of FutureClimateRisk specifying all the fields
	 * @param eventType: the type of event responsible of the risk
	 * @param consequencesRating: the rating of the consequences of the risk
	 */
	public CurrentClimateRisk(String eventType, String consequencesRating) throws IllegalArgumentException {
		
		if (!(Arrays.asList(eventTypes).contains(eventType))) 
			throw new IllegalArgumentException(ERR_INVALID_EVENT_TYPE);
					
		setEventType(eventType);
		setConsequencesRating(consequencesRating);
	}
	
	/**
	 * Getter for the unique ID of the Weather Event
	 * @return: the current unique ID of the Weather Event
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter for the unique ID of the Weather Event
	 * @param id: the new unique ID of the Weather Event
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter for the type of event responsible of the risk
	 * @return: the current type of event responsible of the risk
	 */
	public String getEventType() {
		return this.eventType;
	}
	
	/**
	 * Setter for the type of event responsible of the risk
	 * @param eventType: the new type of event responsible of the risk
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	/**
	 * Getter for the rating of the consequences of the risk
	 * @return: the current rating of the consequences of the risk
	 */
	public String getConsequencesRating() {
		return this.consequencesRating;
	}
	
	/**
	 * Setter for the rating of the consequences of the risk
	 * @param consequencesRating: the new rating of the consequences of the risk
	 */
	public void setConsequencesRating(String consequencesRating) {
		this.consequencesRating = consequencesRating;
	}
	
	/**
	 * Parses a given string as a consequence rating: 1 = None, 2 = Insignificant, 3 = Moderate, 4 = Major, 5 = Extreme
	 * @param weatherEventConsequenceString: string corresponding to the rating of a weather event consequence
	 * @return the integer corresponding to the rating of the weather event consequence 
	 * @throws NumberFormatException if the given string isn't a Integer
	 * @throws IllegalArgumentException if the given string correspond to a number out of the range 0-5
	 */
	public static Integer parseConsequenceRating(String currentRiskConsequenceString) throws NumberFormatException, IllegalArgumentException {
		Integer currentRiskConsequence = Integer.parseInt(currentRiskConsequenceString);
		
		if (currentRiskConsequence < 0 || currentRiskConsequence > 5)
			throw new IllegalArgumentException(ERR_CONSEQUENCE_RATING_OUT_OF_RANGE);
		
		return currentRiskConsequence;
	}

	/**
	 * String representation of a FutureClimateRisk object
	 * @return the string representation of the future risk
	 */
	@Override
	public String toString() {
		return (this.eventType + " consequences rating: " + this.consequencesRating);
	}
	
	// Information, success, warning and error messages 
	public static final String ERR_INVALID_EVENT_TYPE = "Invalid climate risk type";
	public static final String ERR_CONSEQUENCE_RATING_OUT_OF_RANGE = "The rating of a consequence must be between 0 (none) and 5 (extreme)";
}