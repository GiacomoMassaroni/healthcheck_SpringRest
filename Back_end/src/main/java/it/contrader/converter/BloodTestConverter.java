package it.contrader.converter;

import it.contrader.dto.BloodTestDTO;
import it.contrader.model.BloodTest;
import it.contrader.model.Test;
import org.springframework.stereotype.Component;

@Component
public class BloodTestConverter extends AbstractConverter<BloodTest, BloodTestDTO> {

    @Override
    public BloodTest toEntity(BloodTestDTO bloodTestDTO) {
        Test test = new Test();
        test.setId(bloodTestDTO.getIdTest());
        return bloodTestDTO != null ? BloodTest.builder()
                .id(bloodTestDTO.getId())
                .redBloodCell(bloodTestDTO.getRedBloodCell())
                .whiteBloodCell(bloodTestDTO.getWhiteBloodCell())
                .platelets(bloodTestDTO.getPlatelets())
                .hemoglobin(bloodTestDTO.getHemoglobin())
                .testFk(test)
                .build() : null;
    }

    @Override
    public BloodTestDTO toDTO(BloodTest bloodTest) {
        return bloodTest != null ? BloodTestDTO.builder()
                .id(bloodTest.getId())
                .redBloodCell(bloodTest.getRedBloodCell())
                .whiteBloodCell(bloodTest.getWhiteBloodCell())
                .platelets(bloodTest.getPlatelets())
                .hemoglobin(bloodTest.getHemoglobin())
                .idTest(bloodTest.getTestFk().getId())
                .build() : null;
    }
}