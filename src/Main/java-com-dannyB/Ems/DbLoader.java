/**
 * 
 */
package com.dannyB.EMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dannyB.EMS.model.Department;
import com.dannyB.EMS.model.Employee;
import com.dannyB.EMS.model.HR;
import com.dannyB.EMS.model.Leadership;
import com.dannyB.EMS.model.Manufacturing;
import com.dannyB.EMS.model.RandD;
import com.dannyB.EMS.model.Sales;
import com.dannyB.EMS.repo.DepartmentRepository;
import com.dannyB.EMS.repo.EmployeeRepository;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: Jul 10, 2020
 * Last Updated: 
 * Description:
 *
 */



@Component
public class DbLoader implements CommandLineRunner {

	@Autowired
	private final EmployeeRepository empRepo;
	@Autowired
	private final DepartmentRepository depRepo;
	
	@Autowired
	public DbLoader(EmployeeRepository e, DepartmentRepository d) {
		this.empRepo = e;
		this.depRepo = d;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Department hR = new HR();
		Department randD = new RandD();
		Department manufacturing = new Manufacturing();
		Department leadership = new Leadership();
		Department sales = new Sales();
		
		Employee Rich = new Employee("Rich Bowers", leadership.getDEP_ID(), "Director");
		Employee Charlie = new Employee("Charles Dunn", randD.getDEP_ID(), "TA");
		Employee Paula = new Employee("Paula Marchis", sales.getDEP_ID());
		Employee Dan = new Employee("Daniel G Birmingham");
		Employee Joe = new Employee("Joe Is Exotic");
		Employee RB = new Employee("R B");
		
		this.depRepo.save(hR);
		this.depRepo.save(randD);
		this.depRepo.save(manufacturing);
		this.depRepo.save(leadership);
		this.depRepo.save(sales);
		
		this.empRepo.save(Rich);
		this.empRepo.save(Charlie);
		this.empRepo.save(Paula);
		this.empRepo.save(Dan);
		this.empRepo.save(Joe);
		this.empRepo.save(RB);
	}

}
