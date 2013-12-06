package com.mobatia.mibblio.model;

import java.io.Serializable;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class GistsResponseModel.Comparable<EventModel>,Comparator<EventModel>
 */
public class GistsResponseModel implements Serializable,Comparable<GistsResponseModel>{

	
	/** The git id. */
	private  String gitId="";
	
	/** The description. */
	private  String description="";
	
	/** The date. */
	private String date ="";
	
	/** The language. */
	private  String language[];
	
	/** The type. */
	private  String type[];
	
	/** The raw_url. */
	private  String raw_url[];
	
	/** The filename. */
	private  String filename[];
	
	private  String fileSize[];
	
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	public String[] getFileSize() {
		return fileSize;
	}

	public void setFileSize(String[] filenameSize) {
		this.fileSize = filenameSize;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String[] getLanguage() {
		return language;
	}
	
	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String[] language) {
		this.language = language;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String[] getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String[] type) {
		this.type = type;
	}
	
	/**
	 * Gets the raw_url.
	 *
	 * @return the raw_url
	 */
	public String[] getRaw_url() {
		return raw_url;
	}
	
	/**
	 * Sets the raw_url.
	 *
	 * @param raw_url the new raw_url
	 */
	public void setRaw_url(String[] raw_url) {
		this.raw_url = raw_url;
	}
	
	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String[] getFilename() {
		return filename;
	}
	
	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String[] filename) {
		this.filename = filename;
	}
	
	/**
	 * Gets the git id.
	 *
	 * @return the git id
	 */
	public String getGitId() {
		return gitId;
	}
	
	/**
	 * Sets the git id.
	 *
	 * @param gitId the new git id
	 */
	public void setGitId(String gitId) {
		this.gitId = gitId;
	}

	

	@Override
	public int compareTo(GistsResponseModel another) {
		// TODO Auto-generated method stub
		return this.description.compareToIgnoreCase(another.description);
	}

	
	
	
	
}
