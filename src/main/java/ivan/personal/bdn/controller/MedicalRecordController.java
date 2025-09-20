package ivan.personal.bdn.controller;

import ivan.personal.bdn.exception.AppException;
import ivan.personal.bdn.model.*;
import ivan.personal.bdn.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-record")
@Log
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordCategoryRepository medicalRecordCategoryRepository;
    private final SymptomRepository symptomRepository;
    private final Who2016Repository who2016Repository;
    private final BoneMarrowFibrosisRepository boneMarrowFibrosisRepository;
    private final TherapyLineRepository therapyLineRepository;
    private final TreatmentResponseRepository treatmentResponseRepository;

    @GetMapping("/")
    public ResponseEntity<List<MedicalRecord>> listAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        if (medicalRecords.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info(medicalRecord.toString());
        medicalRecord.setCreatedAt(new Timestamp(new Date().getTime()));
        medicalRecordRepository.save(medicalRecord);
        return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody final MedicalRecord medicalRecord) {
        medicalRecordRepository.saveAndFlush(medicalRecord);
        return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MedicalRecord> deleteMedicalRecord(@PathVariable("id") final Long id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);
        if (!medicalRecord.isPresent()) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "Medical record not found: " + id);
        }
        medicalRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-category/{id}")
    public ResponseEntity<MedicalRecordCategory> findCategoryById(@PathVariable("id") final Long id) {
        Optional<MedicalRecordCategory> medicalRecordCategory = medicalRecordCategoryRepository.findById(id);
        if (!medicalRecordCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicalRecordCategory.get(), HttpStatus.OK);
    }

    @GetMapping("/symptom/")
    public ResponseEntity<List<Symptom>> listAllSymptoms() {
        List<Symptom> symptoms = symptomRepository.findAll();
        if (symptoms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }

    @GetMapping("/who2016/")
    public ResponseEntity<List<Who2016>> listAllWho() {
        List<Who2016> who2016s = who2016Repository.findAll();
        if (who2016s.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(who2016s, HttpStatus.OK);
    }

    @GetMapping("/bone-marrow-fibrosis/")
    public ResponseEntity<List<BoneMarrowFibrosis>> listAllBoneMarrowFibrosis() {
        List<BoneMarrowFibrosis> boneMarrowFibroses = boneMarrowFibrosisRepository.findAll();
        if (boneMarrowFibroses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boneMarrowFibroses, HttpStatus.OK);
    }

    @GetMapping("/therapy-line/")
    public ResponseEntity<List<TherapyLine>> listAllTherapyLine() {
        List<TherapyLine> therapyLines = therapyLineRepository.findAll();
        if (therapyLines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(therapyLines, HttpStatus.OK);
    }

    @GetMapping("/treatment-response/")
    public ResponseEntity<List<TreatmentResponse>> listAllTreatmentResponse() {
        List<TreatmentResponse> treatmentResponses = treatmentResponseRepository.findAll();
        if (treatmentResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(treatmentResponses, HttpStatus.OK);
    }

}
