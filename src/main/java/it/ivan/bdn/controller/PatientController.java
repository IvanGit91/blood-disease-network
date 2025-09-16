package it.ivan.bdn.controller;

import it.ivan.bdn.exception.AppException;
import it.ivan.bdn.model.Patient;
import it.ivan.bdn.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@Log
@RequiredArgsConstructor
public class PatientController
{
	private final PatientRepository patientRepository;

	// method to get list of hospital
	@GetMapping("/")
	public ResponseEntity<List<Patient>> listAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		if (patients.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> addPatients(@RequestBody final Patient patient)
	{
		patientRepository.save(patient);
		return new ResponseEntity<>(patient, HttpStatus.CREATED);
	}

	@PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> updatePatients(@RequestBody final Patient patient){
		patientRepository.saveAndFlush(patient);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Patient> deletePatients(@PathVariable("id") final Long id){
		Optional<Patient> patient = patientRepository.findById(id);
		if (!patient.isPresent()){
			throw new AppException(HttpStatus.NOT_FOUND,
					"Patient not found with code: " + id);
		}
		patientRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
