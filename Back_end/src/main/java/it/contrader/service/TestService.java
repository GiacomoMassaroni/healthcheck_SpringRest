package it.contrader.service;

import it.contrader.converter.TestConverter;
import it.contrader.converter.TestPostConverter;
import it.contrader.dao.TestRepository;
import it.contrader.dto.TestDTO;
import it.contrader.dto.TestEmailDTO;
import it.contrader.dto.TestPostDTO;
import it.contrader.dto.UserDTO;
import it.contrader.exceptions.CustomException;
import it.contrader.model.User;
import it.contrader.service.UserService;
import it.contrader.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.http.HttpConnectTimeoutException;
import java.util.List;

@Service
public class TestService extends AbstractService<Test, TestDTO> {

    @Autowired
    private TestRepository repository;


    @Autowired
    private TestConverter converter;

    @Autowired
    private TestPostConverter converterPostDto;
    @Autowired
    private UserService serviceUser;

    @Autowired
    private EmailService emailService;




    public List<TestDTO> findAllByDoctorId(Long doctor){
        return converter.toDTOList(repository.findAllByDoctorId(doctor).orElseThrow(() -> new RuntimeException("Dottore non trovato")));
    }

    public List<TestPostDTO> findAllPostByDoctorId(Long doctor){
        return converterPostDto.toDTOList(repository.findAllByDoctorId(doctor).orElseThrow(() -> new RuntimeException("Dottore non trovato")));
    }
    public List<TestDTO> findAllByPatientId(Long patient){
        return converter.toDTOList(repository.findAllByPatientId(patient).orElseThrow(() -> new RuntimeException("Paziente non trovato")));
    }

    public List<TestDTO> findAllByType(Test.TestType type){
        return converter.toDTOList(repository.findAllByType(type).orElseThrow(() -> new RuntimeException("Data non trovato")));
    }

    public List<TestDTO> findAllByDate(String date){
        return converter.toDTOList(repository.findAllByDate(date).orElseThrow(() -> new RuntimeException("Data non trovato")));
    }

    public List<TestDTO> findAllByIsChecked(Boolean isChecked){
        return converter.toDTOList(repository.findAllByIsChecked(isChecked).orElseThrow(() -> new RuntimeException("Data non trovato")));
    }

    public List<TestDTO> findAllPatientCfByDoctorId(Long doctor){
        return converter.toDTOList(repository.findAllPatientCfByDoctorId(doctor).orElseThrow(() -> new RuntimeException("Dottore non trovato")));
    }

    public List<TestPostDTO> findAllPatientIdByDoctorId(Long doctor){
        return converterPostDto.toDTOList(repository.findAllPatientIdByDoctorId(doctor).orElseThrow(() -> new RuntimeException("Dottore non trovato")));
    }
    public List<TestPostDTO> findAllDoctorIdByPatientId(Long patient){
        return converterPostDto.toDTOList(repository.findAllDoctorIdByPatientId(patient).orElseThrow(() -> new RuntimeException("Paziente non trovato")));
    }
    public ResponseEntity<?> validate(Long idTest, Long idUser) {
        try {
            TestPostDTO dto = readPost(idTest);
            UserDTO user = serviceUser.read(idUser);

            if (!user.getUsertype().equals(User.Usertype.USER)) {
                if ((user.getUsertype().equals(User.Usertype.ADMIN))) {
                    if (user.getId().equals(dto.getDoctor())){
                    dto.setIsChecked(true);
                    TestPostDTO validatedDto = updatePost(dto);
                    return ResponseEntity.ok(validatedDto);
                    } else {
                        throw new CustomException("Un dottore non può validare referti di pazienti altrui");
                    }
                } else {
                    dto.setIsChecked(!dto.getIsChecked());
                    TestPostDTO validatedDto = updatePost(dto);
                    return ResponseEntity.ok(validatedDto);
                }
            } else {
                throw new CustomException("Un paziente non può convalidare un referto");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    public TestPostDTO readPost(long id) {
            TestPostDTO dto = converterPostDto.toDTO(repository.findById(id).get());
                return dto;
    }
    public TestPostDTO updatePost(TestPostDTO dto) {
        return converterPostDto.toDTO(repository.save(converterPostDto.toEntity(dto)));
    }
    public List<TestDTO> findAllByDateAndPatientId(String date,Long patient){
        return converter.toDTOList(repository.findAllByDateAndPatientId(date, patient).orElseThrow(() -> new RuntimeException("Data non trovata")));
    }

    public List<TestDTO> findAllByTypeAndPatientId(Test.TestType type, Long patient) {
        return  converter.toDTOList(repository.findAllByTypeAndPatientId(type, patient).orElseThrow(() -> new RuntimeException("Tipo di utente non trovato")));
    }

    public List<TestDTO> findAllByIsCheckedAndPatientId(Boolean isChecked, Long patient) {
        return converter.toDTOList(repository.findAllByIsCheckedAndPatientId(isChecked, patient).orElseThrow(() -> new RuntimeException("Errore nella ricerca")));
    }
    public List<TestDTO> findAllByDateAndDoctorId(String date, Long doctor) {
        return converter.toDTOList(repository.findAllByDateAndDoctorId(date, doctor).orElseThrow(() -> new RuntimeException(("Test non trovato"))));
    }

    public List<TestDTO> findAllByTypeAndDoctorId(Test.TestType type, Long doctor) {
        return converter.toDTOList(repository.findAllByTypeAndDoctorId(type, doctor).orElseThrow(() -> new RuntimeException("Test non trovato")));
    }

    public List<TestDTO> findAllByIsCheckedAndDoctorId(Boolean isChecked, Long doctor) {
        return converter.toDTOList(repository.findAllByIsCheckedAndDoctorId(isChecked, doctor).orElseThrow(() -> new RuntimeException("Validazione non trovata")));

    }


    public TestPostDTO update(TestPostDTO dto) {
        return converterPostDto.toDTO(repository.save(converterPostDto.toEntity(dto)));
    }

    public TestPostDTO insert(TestPostDTO dto) {
       return converterPostDto.toDTO(repository.save(converterPostDto.toEntity(dto)));
    }




}
