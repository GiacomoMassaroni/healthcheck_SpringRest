package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Non esiste il model associato, serve solo a trasferire un oggetto dal frontend 
 * che contenga le infrmazioni di username e password.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

	@NotBlank(message = "Il campo email non può essere vuoto")
	private String email;

	@NotBlank(message = "Il campo password non può essere vuoto")
	private String password;
}
