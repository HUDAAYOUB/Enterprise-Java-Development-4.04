package Reposotory;

import model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findAll();
    Optional<Patient> findById(Integer id);
    @Query("SELECT p FROM Patient p JOIN p.admittedBy e WHERE e.status = 'OFF'")
    List<Patient> findByAdmittingEmployeeStatusOff();

    List<Patient> findByAdmittedByDepartment(String department);

    List<Patient> findByDateOfBirth(Date date);
}
