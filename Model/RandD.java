/**
 * 
 */
package com.dannyB.EMS.model;

import javax.persistence.Entity;



@Entity
public class RandD extends Department {

	private static final long serialVersionUID = 8063278648917447956L;
	/**
	 * @param depName
	 * @param depHead
	 */
	public RandD(String depName, String depHead) {
		super(depName, depHead);
	}

	/**
	 * @param depName
	 */
	public RandD(String depName) {
		super(depName);
	}
	public RandD() {
		super("Research and Development");
	}

}
