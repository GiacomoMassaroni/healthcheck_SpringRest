package it.contrader.dto;

import it.contrader.model.Registry;
import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistryDTO {

    private Long id;

    private String name;

    private String surname;

    private Registry.Gender gender;

    private String birthDate;

    private String birthPlace;

    private String nationality;

    private String city;

    private String address;

    private String bg;

    private String cf;

    private Long idUser;
}
