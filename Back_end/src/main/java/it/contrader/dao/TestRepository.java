package it.contrader.dao;

import it.contrader.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<List<Test>> findAllByDoctorId(Long doctor);
    Optional<List<Test>> findAllByPatientId(Long patient);

    Optional<List<Test>> findAllByType(Test.TestType type);

    Optional<List<Test>> findAllByDate(String date);
    Optional<List<Test>> findAllByIsChecked(Boolean isChecked);

    Optional<Test> findAllByPatient(Long patient);

    Optional<List<Test>> findAllPatientCfByDoctorId(Long doctor);

    Optional<List<Test>> findAllPatientIdByDoctorId(Long doctor);

    Optional<List<Test>> findAllDoctorIdByPatientId(Long doctor);

    Optional<List<Test>> findAllByDateAndPatientId(String date,Long patient);

    Optional<List<Test>> findAllByTypeAndPatientId(Test.TestType type, Long patient);

    Optional<List<Test>> findAllByIsCheckedAndPatientId(Boolean isChecked,Long patient);

    Optional<List<Test>> findAllByDateAndDoctorId(String data, Long doctor);
    Optional<List<Test>> findAllByTypeAndDoctorId(Test.TestType type, Long doctor);
    Optional<List<Test>> findAllByIsCheckedAndDoctorId(Boolean isChecked, Long doctor);


}