package it.contrader.dto;

import it.contrader.model.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateDTO {

    private Long idTest;
    private Long idUser;

}
