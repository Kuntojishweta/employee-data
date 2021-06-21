
/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: May 11, 2020
 * Last Updated: 
 * Description: Runner class to do some prelim testing showing the implementation
 * of the offline model
 *
 */
public class EMSRunner {
	public static void main(String[] args) {
		//Note: Department and Employee variable assignment should
		//not be used in final implementation
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
		
		System.out.println(EMS.getDepartmentMap().values().toString().replaceAll("(ID=[A-Z]+],)" , "$1\n")+"\n");
		//Test writing to and reading from external EMS state file
		EMS.writeStateToFile();
		EMS.getDepartmentMap().clear();
		EMS.getEmployeeMap().clear();
		System.out.println(EMS.getEmployeeMap().toString().replaceAll("(Title=[A-Za-z ]+],)" , "$1\n")+"\n"); //make output pretty
		
		//Now read previously generated state from state file
		EMS.readStateFromFile();
		System.out.println(EMS.getDepartmentMap().get("UNASSIGNED").getEmp_IDSet());
		System.out.println(EMS.getEmployeeMap().toString().replaceAll("(Title=[A-Za-z ]+],)" , "$1\n")+"\n"); //make output pretty
		
		//Remove an employee Rich
		System.out.println("Expect output [rb1]: " + EMS.getDepartmentMap().get("LEADERSHIP").getEmp_IDSet()); //expected rb1
		EMS.removeEmployee("rb1");
		System.out.println("Expect empty set output: " + EMS.getDepartmentMap().get("LEADERSHIP").getEmp_IDSet()); //expected []
		System.out.println("\n");

	}
}
