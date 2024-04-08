package it.contrader.converter;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.model.BloodTest;
import it.contrader.model.Test;
import it.contrader.model.UrineTest;
import org.springframework.stereotype.Component;

@Component
public class UrineTestConverter extends AbstractConverter<UrineTest, UrineTestDTO> {

    @Override
    public UrineTest toEntity(UrineTestDTO urineTestDTO) {
        Test test = new Test();
        test.setId(urineTestDTO.getIdTest());
        return urineTestDTO != null ? UrineTest.builder()
                .id(urineTestDTO.getId())
                .color(urineTestDTO.getColor())
                .hemoglobine(urineTestDTO.getHemoglobine())
                .ph(urineTestDTO.getPh())
                .protein(urineTestDTO.getProtein())
                .testFk(test)
                .build() : null;
    }

    @Override
    public UrineTestDTO toDTO(UrineTest urineTest) {
        return urineTest != null ? UrineTestDTO.builder()
                .id(urineTest.getId())
                .color(urineTest.getColor())
                .hemoglobine(urineTest.getHemoglobine())
                .ph(urineTest.getPh())
                .protein(urineTest.getProtein())
                .idTest(urineTest.getTestFk().getId())
                .build() : null;
    }
}