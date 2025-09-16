package it.ivan.bdn.controller;

import java.util.List;

import it.ivan.bdn.exception.HospitalException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.ivan.bdn.model.Hospital;
import it.ivan.bdn.repository.HospitalRepository;

@RestController
@RequestMapping("/hospital")
@Log
@AllArgsConstructor
public class HospitalController 
{
	HospitalRepository hospitalRepository;

	@GetMapping("/")
	public ResponseEntity<List<Hospital>> getAllHospitals()
	{
		List<Hospital> listHospitals = hospitalRepository.findAll();
	    return new ResponseEntity<>(listHospitals, HttpStatus.OK);
	}
	
	@PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hospital> createHospital(@RequestBody final Hospital hospital) 
    {
		Hospital hosp = hospitalRepository.findByCode(hospital.getCode());

		if (hosp != null) {
			throw new HospitalException(HttpStatus.CONFLICT,
					"Impossible register the hospital. The code "
							+ hospital.getCode() + " is already present");
		}

 	    hospitalRepository.save(hospital);
 	    return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

	@PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hospital> updateHospital(@RequestBody final Hospital hospital){
		hospitalRepository.saveAndFlush(hospital);
		return new ResponseEntity<>(hospital, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Hospital> deleteHospital(@PathVariable("id") final Long id){
		Hospital hospital = hospitalRepository.findById(id).orElseThrow(() ->
				new HospitalException(HttpStatus.NOT_FOUND,
				String.format("Hospital not found - {%d}", id)));
		hospitalRepository.deleteById(id);
		return new ResponseEntity<>(hospital, HttpStatus.OK);
	}
}