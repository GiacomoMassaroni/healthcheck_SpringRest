package it.contrader.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import it.contrader.dto.*;
import it.contrader.model.Test;
import it.contrader.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "http://localhost:4200")
public class PdfController {

    @Autowired
    PdfService pdfService;
    @Autowired
    RegistryService service;
    @Autowired
    BloodTestService bloodService;
    @Autowired
    TestService testService;
    @Autowired
    UrineTestService urineService;

    @PostMapping(value = "/createPdf")
    public ResponseEntity<String> createTestPdf(@RequestParam Long idTest) {

        try {

            TestDTO test = testService.read(idTest);
            BloodTestDTO testDto = new BloodTestDTO();
            UrineTestDTO urineTestDTO = new UrineTestDTO();
            TestPostDTO testPostDTO;

            if (test.getType().equals(Test.TestType.BLOODTEST)) {

                testDto = bloodService.findByIdTestFk(idTest);
                testPostDTO = testService.readPost(testDto.getIdTest());

            } else {

                urineTestDTO = urineService.findByIdTestFk(idTest);
                testPostDTO = testService.readPost(urineTestDTO.getIdTest());
            }

            RegistryDTO registry = service.read(testPostDTO.getPatient());
            pdfService.createTest(testDto, urineTestDTO, registry, test);

            return ResponseEntity.ok("PDF creato con successo.");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema nella creazione del PDF: " + e.getMessage());

        }

    }

    @GetMapping("/viewPdf")
    public ResponseEntity<?> viewPdf(@RequestParam String cf, @RequestParam Long id ) throws IOException {
        String name = cf + "-" + id;
        File pdfFile = new File(name);
        if (!pdfFile.exists()) {
            createTestPdf(id);
        }
        byte[] imageData=pdfService.viewPdf(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("pdf/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ name + ".pdf\"")
                .body(imageData);
    }


}
