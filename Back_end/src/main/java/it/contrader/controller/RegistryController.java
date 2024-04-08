package it.contrader.controller;

import it.contrader.dto.*;
import it.contrader.model.User;
import it.contrader.service.RegistryService;
import it.contrader.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/registry")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistryController extends AbstractController<RegistryDTO> {
     @Autowired
     private RegistryService service;

     @Autowired
     private TestService testService;
     @GetMapping("/findByUsertype")
     public List<RegistryDTO> findByUsertype(@RequestParam User.Usertype usertype) { return service.findByUsertype(usertype);}
     @GetMapping("/findByUserFk")
     public RegistryDTO findByUserFkId(@RequestParam Long id) { return service.findByUserFk(id);}
     @GetMapping("/findByUserFkEmail")
     public RegistryDTO findByUserFkEmail(@RequestParam String email) { return service.findByUserFkEmail(email);}


     @GetMapping("/getAllPatientByDoctorId")
     public List<RegistryDTO> getAllPatientByDoctorId(@RequestParam Long idDoctor) {
          List<TestPostDTO> testList = testService.findAllPatientIdByDoctorId(idDoctor);
          Set<Long> idSet = new HashSet<>();
          List<Long> idList = new ArrayList<>();

          for (TestPostDTO testDTO : testList) {
               idSet.add(testDTO.getPatient());
          }

          idList.addAll(idSet);

          List<RegistryDTO> resultList = new ArrayList<>();

          for (Long id : idList) {
               RegistryDTO dto = service.read(id);
               resultList.add(dto);
          }
          return resultList;
     }

     @GetMapping("/getAllDoctorByPatientId")
     public List<RegistryDTO> getAllDoctorByPatientId(@RequestParam Long idPatient) {
          List<TestPostDTO> testList = testService.findAllDoctorIdByPatientId(idPatient);
          Set<Long> idSet = new HashSet<>();
          List<Long> idList = new ArrayList<>();

          for (TestPostDTO testDTO : testList) {
               idSet.add(testDTO.getDoctor());
          }

          idList.addAll(idSet);

          List<RegistryDTO> resultList = new ArrayList<>();

          for (Long id : idList) {
               RegistryDTO dto = service.read(id);
               resultList.add(dto);
          }
          return resultList;
     }

}
