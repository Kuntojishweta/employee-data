/**
 * 
 */
package com.dannyB.EMS.model;

import javax.persistence.Entity;

@Entity
public class Sales extends Department {

	private static final long serialVersionUID = 4990761532182024838L;
	/**
	 * @param depName
	 * @param depHead
	 */
	public Sales(String depName, String depHead) {
		super(depName, depHead);
	}
	/**
	 * @param depName
	 */
	public Sales(String depName) {
		super(depName);
	}
	public Sales() {
		super("Sales");
	}
}
