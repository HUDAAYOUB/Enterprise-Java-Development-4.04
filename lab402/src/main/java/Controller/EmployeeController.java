package Controller;

import Reposotory.EmployeeRepository;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/employee")
    public class EmployeeController {

        @Autowired
        EmployeeRepository employeeRepository;
        @GetMapping("/employees")
        public List<Employee> getAllEmployees() {
            List<Employee> employees = employeeRepository.findAll();
            return ResponseEntity.ok(employees).getBody();
        }

        @GetMapping("/{employeeId}")
        public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long employeeId) {
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
            return ResponseEntity.ok(employeeOptional);
        }

        @GetMapping("/status/{status}")
        public List<Employee> getEmployeesByStatus(@PathVariable("status") String status) {
            List<Employee> employees = employeeRepository.getEmployeesByStatus(status);
            return ResponseEntity.ok(employees).getBody();
        }

        @GetMapping("/department/{department}")
        public List<Employee> getEmployeesByDepartment(@PathVariable("department") String department) {
            List<Employee> employees = employeeRepository.getEmployeesByDepartment(department);
            return ResponseEntity.ok(employees).getBody();
        }

        @PostMapping("/add/new")
        public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        }

        @PutMapping("/{employeeId}/status")
        public ResponseEntity<Void> changeEmployeeStatus(
                @PathVariable Long employeeId,
                @RequestParam("status") String status) {
            Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setStatus(status);
                employeeRepository.save(employee);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{employeeId}/department")
        public ResponseEntity<Void> updateEmployeeDepartment(
                @PathVariable Long employeeId,
                @RequestParam("department") String department) {
            Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setDepartment(department);
                employeeRepository.save(employee);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/update/{employeeId}")
        public ResponseEntity<Employee> updateEmployeeInformation(
                @PathVariable Integer employeeId,
                @RequestBody Employee updatedEmployee) {

            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                employee.setStatus(updatedEmployee.getStatus());
                Employee savedEmployee = employeeRepository.save(employee);
                return ResponseEntity.ok(savedEmployee);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }
