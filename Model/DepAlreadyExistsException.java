/**
 * 
 */
package com.dannyB.EMS.model;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: May 11, 2020
 * Last Updated: 
 * Description:
 *
 */
public class DepAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = 640416680364436323L;
	
	private String existingDEP_ID;
	private static String formatMessage(String s) {
		return String.format("A department with the same department ID %s already"
				+ "exits. Please choose a unique name when naming a new"
				+ "department.", s);
	}


	/**
	 */
	public DepAlreadyExistsException(String DEP_ID) {
		super(formatMessage(DEP_ID));
		this.existingDEP_ID = DEP_ID;
	}


	/**
	 * @param cause
	 */
	public DepAlreadyExistsException(String DEP_ID, Throwable cause) {
		super(formatMessage(DEP_ID), cause);
		this.existingDEP_ID = DEP_ID;
	}

	/**
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DepAlreadyExistsException(String DEP_ID, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(formatMessage(DEP_ID), cause, enableSuppression, writableStackTrace);
		this.existingDEP_ID = DEP_ID;
	}
	
	public String getExistingDepartment() {
		return this.existingDEP_ID;
	}

}
