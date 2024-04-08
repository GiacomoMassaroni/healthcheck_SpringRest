package it.contrader.converter;

import it.contrader.dto.TestDTO;
import it.contrader.model.Registry;
import it.contrader.model.Test;
import it.contrader.model.User;
import org.springframework.stereotype.Component;

@Component
public class TestConverter extends AbstractConverter<Test, TestDTO> {

    @Override
    public Test toEntity(TestDTO testDTO) {
        // Non sappiamo se effettivamente funziona
        Registry registryDoc = new Registry();
        Registry registryPat = new Registry();
        registryDoc.setSurname(testDTO.getDoctor());
        registryPat.setCf(testDTO.getPatient());

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
    public TestDTO toDTO(Test test) {
        return test!= null ? TestDTO.builder()
                .id(test.getId())
                .doctor(test.getDoctor().getSurname())
                .patient(test.getPatient().getCf())
                .isChecked(test.getIsChecked())
                .date(test.getDate())
                .type(test.getType())
                .build() : null;
    }
}
