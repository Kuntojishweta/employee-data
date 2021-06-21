
/**
 * 
 */
package com.dannyB.EMS.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Map;

import java.util.HashMap;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: May 8, 2020
 * Last Updated: May 21, 2020
 * 
 * Description: Employee Management System (EMS) designed for easy management of company employees.
 * Demonstrates use of employee management system before h2 db implementation.
 *
 */

//TODO: truncate lines to 80 characters in length and finish class descriptions

public final class EMS {
	
	//ems employee map
	private static volatile Map<String, Employee> employeeIDMap = new HashMap<String, Employee>();
	
	//ems department map
	private static volatile Map<String, Department> departmentIDMap = new HashMap<String, Department>();
	
	private static final String COMPANY_NAME = "Washington Red Tails";
	private static final String EMAIL_SUFFIX;
			
	
	static {
		//add unassigned department to departmentIDMap during instantiation
		new Unassigned();
		EMAIL_SUFFIX = String.format("@%s.com", COMPANY_NAME.replaceAll("[\s']", "").toLowerCase());
	}

	//get, add, remove helper wrapper functions for emp and dep maps
	/**
	 * @return the employeeIDMap
	 */
	protected static Map<String, Employee> getEmployeeMap() {
		return employeeIDMap;
	}

	/**
	 * @param EMP_ID: employee id for employee e
	 * @param e: employee to be added to employeeIDMap
	 */
	protected static synchronized void addEmployee(String EMP_ID, Employee e) {
		EMS.employeeIDMap.put(EMP_ID, e);
	}
	
	/**
	 * @param EMP_ID: employee id for employee to be removed from employeeIDMap
	 */
	protected static synchronized void removeEmployee(String EMP_ID) {
		EMS.departmentIDMap.get(EMS.employeeIDMap.get(EMP_ID)
				.getDep()).removeFromEmpIdSet(EMP_ID); //remove employee from department id set
		EMS.employeeIDMap.remove(EMP_ID);
		
	}

	/**
	 * @return the departmentIDMap
	 */
	protected static synchronized Map<String, Department> getDepartmentMap() {
		return departmentIDMap;
	}

	/**
	 * @param DEP_ID: employee id for Department d
	 * @param d: department to be added to departmentIDMap
	 */
	protected static synchronized void addDepartment(String DEP_ID, Department d) {
		EMS.departmentIDMap.put(DEP_ID, d);
	}
	
	/**
	 * @param DEP_ID: department id for department to be removed from departmentIDMap
	 */
	
	protected static synchronized void removeDepartment(String DEP_ID) {
		EMS.departmentIDMap.remove(DEP_ID);
	}
	
	//Question: do i want to make wrappers to create and destroy departments 
	//and employees within EMS framework? purpose would be to keep bad actor
	//from operating on EMS mechanics by assigning variable to employee or dep
	
	
	  /////////////////////////////////////////
	 ///////// File Streaming ////////////////
	/////////////////////////////////////////
	
	
	protected static synchronized void writeStateToFile() {
		//Prepare output file
		File outFile = new File(Paths.get("EMSstate.txt").toAbsolutePath().toString());
		
	   try {
		   //make streams
		   FileOutputStream fos = new FileOutputStream(outFile);
		   ObjectOutputStream oos = new ObjectOutputStream(fos);
		   
		   //write map states
		   oos.writeObject(employeeIDMap);
	       oos.flush();
	       oos.writeObject(departmentIDMap);
	       oos.flush();
	       
	       //close streams
	       oos.close();
	       fos.close();
	    }
	   catch (Exception e) {
		   System.out.println("There was an error exporting the EMS state:");
		   System.out.println(e.getMessage());
		   e.printStackTrace();
	   }
	}
	
	@SuppressWarnings("unchecked")
	protected static void readStateFromFile() {
		File inFile = new File(Paths.get("EMSstate.txt").toAbsolutePath().toString());
		
       try {
    	   FileInputStream fis=new FileInputStream(inFile);
           ObjectInputStream ois=new ObjectInputStream(fis);

           employeeIDMap=(HashMap<String,Employee>)ois.readObject();
           departmentIDMap=(HashMap<String, Department>)ois.readObject();

           ois.close();
           fis.close();
       }
       catch (Exception e) {
		   System.out.println("There was an error importing the EMS state:");
		   System.out.println(e.getMessage());
		   e.printStackTrace();
	   }
	}
	
	
}
