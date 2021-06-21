/**
 * 
 */
package com.dannyB.EMS.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: May 8, 2020
 * Last Updated: 
 * Description:
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="department")
public class Department implements Serializable {
	
	private static final long serialVersionUID = -6431775210848340538L;
	private @Id String DEP_ID;
	private String depName;
	private String depHead;
	
	@Transient
	private Set<String> emp_IDSet = new HashSet<String>();
	
	/**
	 * @param depName: id of the department. see method depBuilder for details
	 * @param depHead employee id of the department head. 0 if not given
	 */
	public Department(String depName, String depHead) {
		buildDep(depName,depHead);
	}
	public Department(String depName) {
		buildDep(depName,"NONE");
	}
	
	public Department() {
		this.DEP_ID = null;
		this.depName = null;
		this.depHead = null;
	}
	
	//helper function used in class construction
	private void buildDep(String depName, String depHead) {
		this.depName = depName;
		this.depHead = depHead;
		this.DEP_ID = depName.replaceAll("[^A-Za-z]+", "").toUpperCase();
		try {
			addDepartmentToEMSMap(this.DEP_ID);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	private boolean departmentAlreadyExists(String ID){
		return EMS.getDepartmentMap().containsKey(ID);
	}

	//TODO: Clean up this helper after testing
	private void addDepartmentToEMSMap(String ID) throws DepAlreadyExistsException {
		if (departmentAlreadyExists(ID)) {
			//throw new DepAlreadyExistsException(ID);
		}
		EMS.addDepartment(ID, this);
//		for (Department dep : EMS.getDepartmentMap().values()) {
//			System.out.println(String.format("Department %s has employees with ids %s", dep.toString(), dep.getEmp_IDSet().toString()));
//		}
	}
	
	/**
	 * @param EMP_ID: id of employee to be added to this departments set of EMP_IDs
	 */
	protected void addToEmpIdSet(String EMP_ID) {
		this.emp_IDSet.add(EMP_ID);
	}
	
	/**
	 * @param EMP_ID: id of employee to be removed from this departments set of EMP_IDs
	 */
	protected void removeFromEmpIdSet(String EMP_ID) {
		this.emp_IDSet.remove(EMP_ID);
	}
	
	@Override
	public String toString() {
		return "Department [depName=" + depName + ", depHead=" + depHead + ", DEP_ID=" + DEP_ID + "]";
	}

	//Getters and Setters
	
	@Override
	public int hashCode() {
		return Objects.hash(DEP_ID, depHead, depName, emp_IDSet);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(DEP_ID, other.DEP_ID) && Objects.equals(depHead, other.depHead)
				&& Objects.equals(depName, other.depName) && Objects.equals(emp_IDSet, other.emp_IDSet);
	}
	/**
	 * @return the emp_IDSet
	 */
	public synchronized Set<String> getEmp_IDSet() {
		return emp_IDSet;
	}
	
	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}
	
	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * @return the depHead
	 */
	public String getDepHead() {
		return this.depHead;
	}

	/**
	 * @param depHead the depHead to set
	 */
	public void setDepHead(String depHead) {
		this.depHead = depHead;
	}
	/**
	 * @return the dEP_ID
	 */
	public synchronized String getDEP_ID() {
		return DEP_ID;
	}
	/**
	 * @param dEP_ID the dEP_ID to set
	 */
	public synchronized void setDEP_ID(String dEP_ID) {
		DEP_ID = dEP_ID;
	}
}
