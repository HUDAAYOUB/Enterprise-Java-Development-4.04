package Reposotory;

import model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();
    List<Employee> getEmployeesByStatus(String status);

    List<Employee> getEmployeesByDepartment(String department);

    Optional<Employee> findByEmployeeId(Long employeeId);
}