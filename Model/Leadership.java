/**
 * 
 */
package com.dannyB.EMS.model;
    import javax.persistence.Entity;
  

  @Entity
import javax.persistence.Entity;
private static final long serialVersionUID = 1474997418003913406L;
	/**
	 * @param depName
	 * @param depHead
	 */
	public Leadership(String depName, String depHead) {
		super(depName, depHead);
	}
	/**
	 * @param depName
	 */
	public Leadership(String depName) {
		super(depName);
	}
	public Leadership() {
		super("Leadership");
	}

}
