/**
 * 
 */
package com.dannyB.EMS.model;

import javax.persistence.Entity;


@Entity
public class Manufacturing extends Department {

	private static final long serialVersionUID = -8190010746911616265L;
	/**
	 * @param depName
	 * @param depHead
	 */
	public Manufacturing(String depName, String depHead) {
		super(depName, depHead);
	}
	/**
	 * @param depName
	 */
	public Manufacturing(String depName) {
		super(depName);
	}
	public Manufacturing() {
		super("Manufacturing");
	}

}
