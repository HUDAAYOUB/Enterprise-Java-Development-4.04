package Controller;

import Reposotory.PatientRepository;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/patients")
    public class PatientController {

        @Autowired
        PatientRepository patientRepository;

        @GetMapping("/patient")
        public ResponseEntity<List<Patient>> getAllPatients() {
            List<Patient> patients = patientRepository.findAll();
            return ResponseEntity.ok(patients);
        }

        @PostMapping("/add/new")
        public void addPatient(@RequestBody Patient patient) {
            Patient patientNew = new Patient();
            patientNew.setup(patient);
            patientRepository.save(patientNew);
        }

        @GetMapping("/{patientId}")
        public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Integer id) {
            Optional<Patient> patientOptional = patientRepository.findById(id);
            return ResponseEntity.ok(patientOptional);
        }

        @GetMapping("/dob/{date}")
        public ResponseEntity<List<Patient>> getPatientsByDOB(
                @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
            List<Patient> patients = patientRepository.findByDateOfBirth(date);
            return ResponseEntity.ok(patients);
        }

        @GetMapping("/admitting-employee/{department}")
        public ResponseEntity<List<Patient>> getPatientsByAdmittingEmployeeDepartment(@PathVariable String department) {
            List<Patient> patients = patientRepository.findByAdmittedByDepartment(department);
            return ResponseEntity.ok(patients);
        }

        @GetMapping("/employees/status/OFF")
        public ResponseEntity<List<Patient>> getPatientsWithEmployeeStatusOff() {
            List<Patient> patients = patientRepository.findByAdmittingEmployeeStatusOff();
            return ResponseEntity.ok(patients);
        }
    }



