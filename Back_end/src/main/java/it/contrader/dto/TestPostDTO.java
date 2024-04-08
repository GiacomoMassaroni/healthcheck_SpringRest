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
public class TestPostDTO {

    private Long id;

    private Long doctor;

    private Long patient;

    private Boolean isChecked;

    private String date;

    private Test.TestType type;

}
