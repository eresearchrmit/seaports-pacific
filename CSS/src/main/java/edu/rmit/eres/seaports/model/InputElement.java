/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://bitbucket.org/eresearchrmit/seaports-pacific.git
 */
package edu.rmit.eres.seaports.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Base64;

import edu.rmit.eres.seaports.helpers.FileTypeHelper;

/**
 * Class representing an input element of a report
 * @author Guillaume Prevost
 * @since 11th Jan. 2013
 */
@Entity
public class InputElement extends Element implements Serializable {

	private static final long serialVersionUID = 8121004437941556525L;

	/**
	 * The type of the input element (considered as plain text if not recognized)
	 */
	@Column
	protected String contentType;
	
    /**
     * The binary content of the input element
     */
    @Column(columnDefinition = "LONGBLOB")
    protected byte[] content;
	
    @Transient
    protected String stringContent;
    
	/**
	 * Default constructor of input element
	 */
	public InputElement() {
		setCreationDate(new Date());
	}
	
	/**
	 * Constructor of input element specifying all the fields except the content
	 * @param creationDate: the date when the input element was created
	 * @param name: the name of the input element
	 * @param category: the category of the element
	 * @param report: the report to which this element belongs
	 * @param included: whether the input element is included or not in the publication of its parent Report.
	 * @param position: the position of the input element in the report it belongs to
	 * @param contentType: the type of the input content (considered as plain text if not recognized)
	 * @param fullWidth: whether the element takes 2 column (full width) or only one column (half width)
	 * @param pageBreakAfter: whether there should be a page break after the element
	 */
	public InputElement(Date creationDate, String name, ElementCategory category, Report report, boolean included, 
			int position, String contentType, boolean fullWidth, boolean pageBreakAfter) {
		super(creationDate, name, category, report, included, position, fullWidth, pageBreakAfter);
		setContentType(contentType);
	}
	
	/**
	 * Constructor of input element specifying all the fields
	 * @param creationDate: the date when the input element was created
	 * @param name: the name of the input element
	 * @param category: the category of the element
	 * @param report: the report to which this element belongs
	 * @param included: whether the input element is included or not in the publication of its parent Report.
	 * @param position: the position of the input element in the report it belongs to
	 * @param contentType: the type of the input content (considered as plain text if not recognized)
	 * @param content: the content of the input element
	 * @param fullWidth: whether the element takes 2 column (full width) or only one column (half width)
	 * @param pageBreakAfter: whether there should be a page break after the element
	 */
	public InputElement(Date creationDate, String name, ElementCategory category, Report report, boolean included, 
			int position, String contentType, byte[] content, boolean fullWidth, boolean pageBreakAfter) {
		super(creationDate, name, category, report, included, position, fullWidth, pageBreakAfter);
		setContentType(contentType);
		setContent(content);
	}
	
	/**
	 * Getter for the type of the input content
	 * @return the current type of the input content
	 */
	public String getContentType() {
		return this.contentType;
	}
	
	/**
	 * Setter for the type of the input content
	 * @param type: the new type of the input content
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Getter for the binary content of the input content
	 * @return The current binary content of the input content
	 */
	public byte[] getContent() {
		if (this.content == null)
			generateBinaryContent();
		return content;
	}

	/**
	 * Setter for the binary content of the input content. 
	 * It also sets the property stringContent by converting the binary content into a String.
	 * @param content: the new binary content of the input content
	 */
	public void setContent(byte[] content) {
		this.content = content;
		generateStringContent();
	}
	
	/**
	 * Getter for the string conversion of the input content
	 * @return The current string conversion of the input content
	 */
	public String getStringContent() {
		if (this.stringContent == null)
			generateStringContent();
		return this.stringContent;
	}
	
	/**
	 * Getter for an excaped version of the string content of the element
	 * Removes the "\r" and "\n" characters and replaces "'" by "\'"
	 * @return an escaped version of the string content of the element
	 */
	public String getEscapedStringContent() {
		return getStringContent().replaceAll("(\r\n|\r|\n|\n\r)", "").replace("'", "\\\'");
	}
	
	/**
	 * Setter for the string content of the input content. 
	 * It also sets the property binaryContent by converting the String content into binary.
	 * @param content: the new string content of the input content
	 */
	public void setStringContent(String stringContent) {
		this.stringContent = stringContent;
		generateBinaryContent();
	}
	
	/**
	 * Converts the binary content into a String and assign it to the stringContent property.
	 */
	public void generateStringContent() {
		if (this.content != null) {
			if (this.contentType != null && FileTypeHelper.IsContentTypeJpeg(this.contentType)) {
				this.stringContent = Base64.encodeBase64String(this.content);
			}
			else if (this.contentType != null && FileTypeHelper.IsContentTypeCsv(this.contentType)) {
				String result = "<table class=\"data display datatable\">";
				
				String fileContent = new String(this.content);
				String[] rows = fileContent.split("\n");
				
				int i = 0;
				for (String row : rows) {
					result += "<tr class=\"" + ((i % 2 == 0) ? "even" : "odd") + "\">";
					String[] cells = row.split(",");
					for (String cell : cells) {
						result += "<td class=\"top\">" + cell + "</td>";
					}
					result += "</tr>";
					i++;
				}
				result += "</table>";
				this.stringContent = result;
			}
			else if (this.contentType != null && FileTypeHelper.IsContentTypePlaintext(this.contentType))
				this.stringContent = new String(this.content);
		}
		else {
			this.stringContent = null;
		}
	}
	
	/**
	 * Converts the string content into binary and assign it to the content property.
	 */
	public void generateBinaryContent() {
		if (this.stringContent != null) {
			if (this.contentType != null && FileTypeHelper.IsContentTypeJpeg(this.contentType))
				this.content = Base64.decodeBase64(this.stringContent);
			else
				this.content = this.stringContent.getBytes();
		}
		else {
			this.content = null;
		}
	}
}