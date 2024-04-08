package it.contrader.controller;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.service.BloodTestService;
import it.contrader.service.UrineTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urinetest")
@CrossOrigin(origins = "http://localhost:4200")
public class UrineTestController extends AbstractController<UrineTestDTO>{
    @Autowired
    private UrineTestService urineTestService;

    @Autowired
    private PdfController pdfController;

    @Autowired
    private TestController testController;
    @GetMapping("/findByTestFk")
    public UrineTestDTO findByIdTestFk(@RequestParam Long id) { return urineTestService.findByIdTestFk(id);}

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestBody UrineTestDTO dto){
        try {
            urineTestService.update(dto);
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
            UrineTestDTO dto = urineTestService.findByIdTestFk(id);
            return ResponseEntity.ok((dto));
        }catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Referto non trovato");
        }

    }
}

