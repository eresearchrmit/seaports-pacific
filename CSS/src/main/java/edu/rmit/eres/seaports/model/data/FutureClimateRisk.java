/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.model.data;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import edu.rmit.eres.seaports.model.DataElement;

/**
 * Class representing a climate variable
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
public class FutureClimateRisk implements Serializable {
	
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
	 * A list of the allowed likelihood rating
	 */
	public static final String[] likelihoodRatings = new String[] {"Rare", "Unlikely", "Possible", "Probable", "Almost Certain"};

	
	/**
	 * A list of the allowed vulnerability ratings
	 */
	//public static final String[] vulnerabilityRatings = new String[] {"Not vulnerable", "Could be vulnerable", "somewhat vulnerable", "Moderately vulnerable", "Significantly vulnerable"};


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
	 * The area the risk applies to
	 */
	@Column
	private String area;
		
	/**
	 * The description of the future risk
	 */
	@Column
	private String description;
	
	/**
	 * The details of the future risk (current thresholds)
	 */
	@Column
	private String details;
	
	/**
	 * The description of the consequences of the future risk
	 */
	@Column
	private String futureConsequences;
	
	/**
	 * The rating of the consequences of the risk
	 */
	@Column
	private Integer consequencesRating;
	
	/**
	 * The rating of the likelihood of the risk to happen
	 */
	@Column
	private Integer likelihoodRating;
	
	/**
	 * Default constructor of FutureClimateRisk
	 */
	public FutureClimateRisk() {
	}
	
	/**
	 * Constructor of FutureClimateRisk specifying all the fields
	 * @param eventType: the type of event responsible of the risk
	 * @param area: the  area the risk applies to
	 * @param description: description of the future risk
	 * @param details: the details of the future risk (current thresholds)
	 * @param futureConsequences: the description of the consequences of the future risk
	 * @param consequencesRating: the rating of the consequences of the risk
	 * @param likelihoodRating: the rating of the likelihood of the risk to happen
	 */
	public FutureClimateRisk(String eventType, String area, String description, String details, 
			String futureConsequences, Integer consequencesRating, Integer likelihoodRating) throws IllegalArgumentException {
		
		if (!(Arrays.asList(eventTypes).contains(eventType))) 
			throw new IllegalArgumentException(ERR_INVALID_EVENT_TYPE);
		
		if (!(Arrays.asList(riskAreas).contains(area))) 
			throw new IllegalArgumentException(ERR_INVALID_RISK_AREA);
		
		if (consequencesRating < 1 || consequencesRating > 5) 
			throw new IllegalArgumentException(ERR_CONSEQUENCE_RATING_OUT_OF_RANGE);
		
		if (likelihoodRating < 1 || likelihoodRating > 5)
			throw new IllegalArgumentException(ERR_LIKELIHOOD_RATING_OUT_OF_RANGE);
		
		setEventType(eventType);
		setArea(area);
		setDescription(description);
		setDetails(details);
		setFutureConsequences(futureConsequences);
		setConsequencesRating(consequencesRating);
		setLikelihoodRating(likelihoodRating);
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
	 * Getter for the area the risk applies to
	 * @return: the current area the risk applies to
	 */
	public String getArea() {
		return this.area;
	}
	
	/**
	 * Setter for the area the risk applies to
	 * @param area: the new area the risk applies to
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	/**
	 * Getter for the description of the future risk
	 * @return: the current description of the future risk
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Setter for the description of the future risk
	 * @param year: the current description of the future risk
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for the details of the future risk (current thresholds)
	 * @return: the current details of the future risk (current thresholds)
	 */
	public String getDetails() {
		return this.details;
	}
	
	/**
	 * Setter for the details of the future risk (current thresholds)
	 * @param details: the new details of the future risk (current thresholds)
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * Getter for the description of the consequences of the future risk
	 * @return: the current description of the consequences of the future risk
	 */
	public String getFutureConsequences() {
		return this.futureConsequences;
	}
	
	/**
	 * Setter for the description of the consequences of the future risk
	 * @param futureConsequences: the new description of the consequences of the future risk
	 */
	public void setFutureConsequences(String futureConsequences) {
		this.futureConsequences = futureConsequences;
	}	
	
	/**
	 * Getter for the rating of the consequences of the risk
	 * @return: the current rating of the consequences of the risk
	 */
	public Integer getConsequencesRating() {
		return this.consequencesRating;
	}
	
	/**
	 * Setter for the rating of the consequences of the risk
	 * @param consequencesRating: the new rating of the consequences of the risk
	 */
	public void setConsequencesRating(Integer consequencesRating) {
		this.consequencesRating = consequencesRating;
	}
	
	/**
	 * Getter for the rating of the likelihood of the risk to happen
	 * @return: the current rating of the likelihood of the risk to happen
	 */
	public Integer getLikelihoodRating() {
		return this.likelihoodRating;
	}
	
	/**
	 * Setter for the rating of the likelihood of the risk to happen
	 * @param consequencesRating: the new rating of the likelihood of the risk to happen
	 */
	public void setLikelihoodRating(Integer likelihoodRating) {
		this.likelihoodRating = likelihoodRating;
	}
	
	/**
	 * Getter for the priority of of the risk, depending on the consequence rating and the likelihood rating of the risk
	 * @param priority: the priority rating of the risk
	 */
	public String getPriority() {
		int priority = this.getLikelihoodRating() * this.getConsequencesRating();
		
		if (priority < 5)
			return "Low";
		else if (priority >= 5 && priority < 10)
			return "Medium";
		else if (priority >= 10 && priority < 15)
			return "High";
		else
			return "Very High";
	}	
	
	/**
	 * Parses a given string as a consequence rating: 1 = None, 2 = Insignificant, 3 = Moderate, 4 = Major, 5 = Extreme
	 * @param weatherEventConsequenceString: string corresponding to the rating of a weather event consequence
	 * @return the integer corresponding to the rating of the weather event consequence 
	 * @throws NumberFormatException if the given string isn't a Integer
	 * @throws IllegalArgumentException if the given string correspond to a number out of the range 0-5
	 */
	public static Integer parseConsequenceRating(String weatherEventConsequenceString) throws NumberFormatException, IllegalArgumentException {
		Integer weatherEventConsequence = Integer.parseInt(weatherEventConsequenceString);
		
		if (weatherEventConsequence < 0 || weatherEventConsequence > 5)
			throw new IllegalArgumentException(ERR_CONSEQUENCE_RATING_OUT_OF_RANGE);
		
		return weatherEventConsequence;
	}

	/**
	 * String representation of a FutureClimateRisk object
	 * @return the string representation of the future risk
	 */
	@Override
	public String toString() {
		return (this.eventType + " effect on " + this.area + " (Consequences: " + this.consequencesRating + " " + this.likelihoodRating + ")");
	}
	
	// Information, success, warning and error messages 
	public static final String ERR_INVALID_EVENT_TYPE = "Invalid climate risk type";
	public static final String ERR_INVALID_RISK_AREA = "Invalid risk area";
	public static final String ERR_CONSEQUENCE_RATING_OUT_OF_RANGE = "The rating of a consequence must be between 0 (none) and 5 (extreme)";
	public static final String ERR_LIKELIHOOD_RATING_OUT_OF_RANGE = "The rating of the risk likelihood must be between 0 (rare) and 5 (almost certain)";
}