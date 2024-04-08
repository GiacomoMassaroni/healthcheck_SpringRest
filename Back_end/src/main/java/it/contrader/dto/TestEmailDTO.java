package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestEmailDTO {

    String emailDoctor;

    String nameDoctor;

    String surnameDoctor;

    String emailPatient;

    String namePatient;

    String surnamePatient;

    String fileName;

}
