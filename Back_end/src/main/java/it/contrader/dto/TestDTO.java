package it.contrader.dto;

import it.contrader.model.Test;
import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDTO {

    private Long id;

    private String doctor;

    private String patient;

    private Boolean isChecked;

    private String date;

    private Test.TestType type;

}
