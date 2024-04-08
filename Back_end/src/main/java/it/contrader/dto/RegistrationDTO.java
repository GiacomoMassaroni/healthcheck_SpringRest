package it.contrader.dto;

import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO extends UserDTO {

    @NotBlank(message = "Il campo email non può essere vuoto")
    private String email;

    @NotBlank(message = "Il campo password non può essere vuoto")
    private String password;

    private User.Usertype usertype = User.Usertype.USER;
}

