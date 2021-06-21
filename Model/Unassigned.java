/**
 * 
 */
package com.dannyB.EMS.model;

import javax.persistence.Entity;

@Entity
public final class Unassigned extends Department {

	private static final long serialVersionUID = 3380795860170929845L;

	public Unassigned() {
		super("Unassigned");
	}

}
