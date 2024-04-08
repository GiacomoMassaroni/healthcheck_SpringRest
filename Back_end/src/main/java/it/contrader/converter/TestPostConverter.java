package it.contrader.converter;

import it.contrader.dto.TestPostDTO;
import it.contrader.model.Registry;
import it.contrader.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestPostConverter extends AbstractConverter<Test, TestPostDTO> {

    @Override
    public Test toEntity(TestPostDTO testDTO) {
        Registry registryDoc = new Registry();
        Registry registryPat = new Registry();
        registryDoc.setId(testDTO.getDoctor());
        registryPat.setId(testDTO.getPatient());

        return testDTO!= null ? Test.builder()
                .id(testDTO.getId())
                .doctor(registryDoc)
                .patient(registryPat)
                .isChecked(testDTO.getIsChecked())
                .date(testDTO.getDate())
                .type(testDTO.getType())
                .build() : null;

    }

    @Override
    public TestPostDTO toDTO(Test test) {
        // Non sappiamo se funziona
        return test!= null ? TestPostDTO.builder()
                .id(test.getId())
                .doctor(test.getDoctor().getId())
                .patient(test.getPatient().getId())
                .isChecked(test.getIsChecked())
                .date(test.getDate())
                .type(test.getType())
                .build() : null;
    }

}
