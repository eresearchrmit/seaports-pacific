package war.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class representing an Australian seaport. It is related to the NRM region where it is located.
 * @author Guillaume Prevost
 * @since 20th Dec. 2012
 */
@Entity
@Table(name = "Seaport")
public class Seaport {

	private static final long serialVersionUID = -1308795024262635690L;
	
	/**
	 * The unique ID of the seaport
	 */
	private int id;
	
	/**
	 * The name of the seaport
	 */
	private String name;
	
	/**
	 * The region where the seaport is located
	 */
	private Region region;
	
	
	/**
	 * Getter for the unique ID of the parameters combination
	 * @return the unique ID of the parameters
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for the name for the seaport
	 * @return the name for the seaport
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name for the seaport
	 * @param name: the new name of the seaport
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the seaport's region
	 * @return the region where the seaport is located
	 */
	@ManyToOne
	@JoinColumn(name="region_id")
	public Region getRegion() {
		return region;
	}
	
	/**
	 * Setter for the seaport's region
	 * @param region: the new region where the seaport is located
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
}
