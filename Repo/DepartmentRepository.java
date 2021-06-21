

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dannyB.EMS.model.Department;

@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, String> {
	
}
