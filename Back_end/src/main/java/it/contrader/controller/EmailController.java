package it.contrader.controller;


import it.contrader.dto.TestEmailDTO;

import it.contrader.service.EmailService;
import it.contrader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private EmailService service;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody TestEmailDTO dto) {
        try {
            String subject = "Nuovo test inserito";
            service.sendEmailTest(dto, subject);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'e' stato un problema con l'invio della mail.");
        }
    }

    @PostMapping(value = "/sendValidateEmail")
    public void sendPatientEmailValidate(@RequestBody TestEmailDTO dto) {
        String subject = "Test validato";
        service.sendPatientEmailValidate(dto, subject);
    }

    @PostMapping(value = "/sendRegistrationEmail")
    public void sendRegistrationEmail(String email) {
        String subject = "Registrazione avvenuta con successo";
        service.sendRegistrationEmail(email, subject);
    }

    @PostMapping(value = "/sendUpdateEmail")
    public void sendUpdateEmail(TestEmailDTO dto) {
        String subject = "Modifica referto avvenuta con successo";
        service.sendUpdateEmail(dto, subject);
    }

    @PostMapping(value = "/resetPasswordEmail")
    public void resetPasswordEmail(@RequestBody String email, String password) {
        try {
            String subject = "Password Provvisoria";
            service.sendResetPassMail(email, subject,password);
            ResponseEntity.ok(email);
        } catch (RuntimeException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'e' stato un problema con l'invio della mail.");
        }
    }

    }


