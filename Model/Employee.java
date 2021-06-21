/**
 * 
 */
package com.dannyB.EMS.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: May 8, 2020
 * Last Updated: 
 * Description: Class to store  and manage information about an employee in the EMS
 *
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 4498346644792345109L;
	//Employee parameters
	private @Id String EMP_ID; //would make this final if didnt cause issues with building later
	private String fullName;
	private String dep;
	private double yearlySalary;
	private String jobTitle;  
	
	/**
	 * @param fullName: full name of this employee
	 * @param dep: department id this employee is in. Unassigned if not specified
	 * @param yearlySalary annual salary of this employee. 0.0 if specified
	 * @param jobTitle specific job title for this employee. "Not yet assigned" if not specified
	 */
	public Employee(String fullName, String dep, String jobTitle, double yearlySalary) {
		buildEmp(fullName,dep,jobTitle,yearlySalary);
	}
	
	public Employee(String fullName, String dep, String jobTitle) {
		buildEmp(fullName,dep,jobTitle,0.0);
	}
	
	public Employee(String fullName, String dep) {
		buildEmp(fullName,dep,"Not Yet Assigned",0.0);
	}
	
	public Employee(String fullName) {
		buildEmp(fullName, "UNASSIGNED","Not Yet Assigned",0.0);
	}
	
	public Employee() {
		this.fullName = null;
		this.dep = null;
		this.jobTitle = null;
		this.yearlySalary = 0.0;
		this.EMP_ID = null;
	}
	
	//TODO: sanitize name and Dep input
	private void buildEmp(String fullName, String dep, String jobTitle, double yearlySalary) {
		this.fullName = fullName;
		this.dep = dep;
		this.jobTitle = jobTitle;
		this.yearlySalary = yearlySalary;
		this.EMP_ID = genEmployeeID(this.fullName);
		addEmployeeToEMSMap();
		addEmployeeToDepSet();
	}
	
	private String genEmployeeID(String fullName) {
		StringBuilder newID = new StringBuilder();
		String[] fns = fullName.split(" ");
		if (fns.length <= 2) {
			newID.append(fns[0].charAt(0)).append(fns[fns.length-1].charAt(0));
		}
		else {
			newID.append(fns[0].charAt(0)).append(fns[1].charAt(0)).append(fns[fns.length-1].charAt(0));
		}
		
		for (int i = 0; i < newID.length(); i++) {
			   char c = newID.charAt(i);
			   newID.setCharAt(i, Character.toLowerCase(c));
			}
		int count = 1;
		newID.append(count);
		while(EMS.getEmployeeMap().containsKey(newID.toString())){
			count++;
			newID = newID.deleteCharAt(newID.length()-1).append(count);
		}
		return newID.toString();
	}
	
	private void addEmployeeToEMSMap() {
		EMS.addEmployee(this.EMP_ID, this);
	}
	
	private void addEmployeeToDepSet() {
		EMS.getDepartmentMap().get(this.dep).addToEmpIdSet(this.EMP_ID);
	}
	
	@Override
	public String toString() {
		return "Employee [fullName=" + fullName + ", EMP_ID=" + EMP_ID + ", dep=" + dep + ", jobTitle=" + jobTitle
				+ "]";
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(EMP_ID, dep, fullName, jobTitle, yearlySalary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(EMP_ID, other.EMP_ID) && Objects.equals(dep, other.dep)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(jobTitle, other.jobTitle)
				&& Double.doubleToLongBits(yearlySalary) == Double.doubleToLongBits(other.yearlySalary);
	}

	//Getters and Setters
	public String getFullName() {
		return fullName;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public double getYearlySalary() { // should this be something other than public?
		return yearlySalary;
	}

	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String title) {
		this.jobTitle = title;
	}

	public String getEmpID() {
		return EMP_ID;
	}

}
