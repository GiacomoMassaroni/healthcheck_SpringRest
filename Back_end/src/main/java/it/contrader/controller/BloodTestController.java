package it.contrader.controller;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.TestPostDTO;
import it.contrader.service.BloodTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bloodtest")
@CrossOrigin(origins = "http://localhost:4200")
public class BloodTestController extends AbstractController<BloodTestDTO> {

    @Autowired
    private BloodTestService service;

    @Autowired
    private PdfController pdfController;

    @Autowired
    private TestController testController;
    @GetMapping("/findByTestFk")
    public BloodTestDTO findByIdTestFk(@RequestParam Long id) { return service.findByIdTestFk(id);}

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestBody BloodTestDTO dto){
        try {
            service.update(dto);
            pdfController.createTestPdf(dto.getIdTest());
            testController.updateTest(dto.getIdTest());
            return ResponseEntity.ok((dto));
        }catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Modifica errata");
        }

    }

    @GetMapping("/findByTeskFk")
    public ResponseEntity<?> findByTestFk(@RequestParam("id") Long id){
        try {
            BloodTestDTO dto = service.findByIdTestFk(id);
            return ResponseEntity.ok((dto));
        }catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Referto non trovato");
        }

    }
}