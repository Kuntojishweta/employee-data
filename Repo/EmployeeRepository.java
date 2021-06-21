

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dannyB.EMS.model.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
}
