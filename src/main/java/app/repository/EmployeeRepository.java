package app.repository;

import app.dl.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    Employee findByEmployeeCnp(String employeeCnp);
    Employee findByEmployeeName(String employeeName);
}
