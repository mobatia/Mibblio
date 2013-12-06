package com.mobatia.mibblio.constants;

// TODO: Auto-generated Javadoc
/**
 * Author name :Elwin.
 */
public interface Constants {

	/** The action bar color. */
	public String ACTION_BAR_COLOR = "#42C0FB";
	
/*	integer constant for get 30 gist */
	public int COUNT =30;

	// API constants

	/** The String constant for the api header . */
	public String BASE_URL = "https://api.github.com/gists/public";
	// public String BASE_URL="https://api.github.com/users/samrain/gists";


	/** The String constant for gist id jsontag. */
	public String GIT_ID = "id";

	/** The String constant for Gist description jsontag. */
	public String GIT_TYPE_DESCRIPTION = "description";

	/** The String constant for file jsontag. */
	public String FILE = "files";


	/** The String constant for file last update date jsontag. */
	public String GIT_DATE = "updated_at";


	/** The String constant for file name jsontag. */
	public String GIT_FILE_NAME = "filename";


	/** The String constant for file language jsontag. */
	public String GIT_LANGUAGE = "language";


	/** The String constant for file url jsontag. */
	public String GIT_RAW_URL = "raw_url";


	/** The String constant for file type jsontag. */
	public String GIT_TYPE = "type";

	/** The String constant for file size jsontag. */
	public String GIT_FILE_SIZE ="size";

}
