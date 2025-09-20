package ivan.personal.bdn.controller;

import ivan.personal.bdn.exception.AppException;
import ivan.personal.bdn.model.FollowUp;
import ivan.personal.bdn.model.Hospital;
import ivan.personal.bdn.model.MyeloproliferativeDiagnosis;
import ivan.personal.bdn.model.MyeloproliferativeTherapy;
import ivan.personal.bdn.repository.FollowUpRepository;
import ivan.personal.bdn.repository.HospitalRepository;
import ivan.personal.bdn.repository.MyeloproliferativeDiagnosisRepository;
import ivan.personal.bdn.repository.MyeloproliferativeTherapyRepository;
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
@RequestMapping("/myeloproliferative")
@RequiredArgsConstructor
@Log
public class MyeloproliferativeController {
    private final MyeloproliferativeDiagnosisRepository myeloproliferativeDiagnosisRepository;
    private final MyeloproliferativeTherapyRepository myeloproliferativeTherapyRepository;
    private final HospitalRepository hospitalRepository;
    private final FollowUpRepository followUpRepository;

    @GetMapping("/diagnosis/")
    public ResponseEntity<List<MyeloproliferativeDiagnosis>> listAllMyeloproliferativeDiagnosis() {
        List<MyeloproliferativeDiagnosis> myeloproliferativeDiagnosis = myeloproliferativeDiagnosisRepository.findAll();
        if (myeloproliferativeDiagnosis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(myeloproliferativeDiagnosis, HttpStatus.OK);
    }

    @PostMapping(value = "/diagnosis/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyeloproliferativeDiagnosis> addMyeloproliferativeDiagnosis(@RequestBody MyeloproliferativeDiagnosis myeloproliferativeDiagnosis) {
        log.info(myeloproliferativeDiagnosis.toString());

        Hospital hospital = hospitalRepository.findByCode(myeloproliferativeDiagnosis.getHospitalCode());
        if (hospital == null) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "Hospital not found with code: " + myeloproliferativeDiagnosis.getHospitalCode());
        }
        myeloproliferativeDiagnosis.setHospital(hospital);
        myeloproliferativeDiagnosisRepository.save(myeloproliferativeDiagnosis);
        return new ResponseEntity<>(myeloproliferativeDiagnosis, HttpStatus.CREATED);
    }

    @PostMapping(value = "/diagnosis/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyeloproliferativeDiagnosis> updateMyeloproliferativeDiagnosis(@RequestBody final MyeloproliferativeDiagnosis myeloproliferativeDiagnosis) {
        myeloproliferativeDiagnosisRepository.saveAndFlush(myeloproliferativeDiagnosis);
        return new ResponseEntity<>(myeloproliferativeDiagnosis, HttpStatus.OK);
    }

    @DeleteMapping("/diagnosis/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MyeloproliferativeDiagnosis> deleteMyeloproliferativeDiagnosis(@PathVariable("id") final Long id) {
        Optional<MyeloproliferativeDiagnosis> myeloproliferativeDiagnosis = myeloproliferativeDiagnosisRepository.findById(id);
        if (!myeloproliferativeDiagnosis.isPresent()) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "Diagnosis not found with code: " + id);
        }
        myeloproliferativeDiagnosisRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/therapy/")
    public ResponseEntity<List<MyeloproliferativeTherapy>> listAllMyeloproliferativeTherapy() {
        List<MyeloproliferativeTherapy> myeloTherapyProliferative = myeloproliferativeTherapyRepository.findAll();
        if (myeloTherapyProliferative.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(myeloTherapyProliferative, HttpStatus.OK);
    }

    @PostMapping(value = "/therapy/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyeloproliferativeTherapy> addMyeloproliferativeTherapy(@RequestBody MyeloproliferativeTherapy myeloTherapyProliferativa) {
        myeloproliferativeTherapyRepository.save(myeloTherapyProliferativa);
        return new ResponseEntity<>(myeloTherapyProliferativa, HttpStatus.CREATED);
    }

    @PostMapping(value = "/therapy/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyeloproliferativeTherapy> updateMyeloproliferativeTherapy(@RequestBody final MyeloproliferativeTherapy myeloTherapyProliferativa) {
        myeloproliferativeTherapyRepository.saveAndFlush(myeloTherapyProliferativa);
        return new ResponseEntity<>(myeloTherapyProliferativa, HttpStatus.OK);
    }

    @DeleteMapping("/therapy/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MyeloproliferativeTherapy> deleteMyeloproliferativeTherapy(@PathVariable("id") final Long id) {
        Optional<MyeloproliferativeTherapy> myeloproliferativeTherapy = myeloproliferativeTherapyRepository.findById(id);
        if (!myeloproliferativeTherapy.isPresent()) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "Therapy not found with code: " + id);
        }
        myeloproliferativeTherapyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/followup/")
    public ResponseEntity<List<FollowUp>> listAllMyeloproliferativeFollowUp() {
        List<FollowUp> followUp = followUpRepository.findAll();
        if (followUp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(followUp, HttpStatus.OK);
    }

    @PostMapping(value = "/followup/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FollowUp> addMyeloproliferativeFollowUP(@RequestBody FollowUp followUp) {
        followUpRepository.save(followUp);
        return new ResponseEntity<>(followUp, HttpStatus.CREATED);
    }

    @PostMapping(value = "/followup/addAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FollowUp>> addMyeloproliferativeFollowUPAll(@RequestBody List<FollowUp> followUps) {
        List<FollowUp> savedFollowups = followUpRepository.saveAll(followUps);
        return new ResponseEntity<>(savedFollowups, HttpStatus.CREATED);
    }

    @PostMapping(value = "/followup/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FollowUp> updateMyeloproliferativeFollowUp(@RequestBody final FollowUp followup) {
        followUpRepository.saveAndFlush(followup);
        return new ResponseEntity<>(followup, HttpStatus.OK);
    }

    @DeleteMapping("/followup/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FollowUp> deleteMyeloproliferativeFollowUp(@PathVariable("id") final Long id) {
        Optional<FollowUp> followup = followUpRepository.findById(id);
        if (!followup.isPresent()) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "Followup not found with code: " + id);
        }
        followUpRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
