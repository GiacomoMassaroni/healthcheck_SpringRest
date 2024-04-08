package it.contrader.converter;

import it.contrader.dto.AddTestDTO;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.TestPostDTO;
import it.contrader.dto.UrineTestDTO;
import org.springframework.stereotype.Component;

@Component
public class AddTestConverter {

    public TestPostDTO toTestPost(AddTestDTO dto) {
        return dto != null ? TestPostDTO.builder()
                .id(dto.getId())
                .doctor(dto.getDoctor())
                .patient(dto.getPatient())
                .isChecked(false)
                .date(dto.getDate())
                .type(dto.getType())
                .build() : null;
    }


    public BloodTestDTO toBloodTest(AddTestDTO dto) {
        return dto != null ? BloodTestDTO.builder()
                .id(dto.getId())
                .redBloodCell(dto.getRedBloodCell())
                .whiteBloodCell(dto.getWhiteBloodCell())
                .platelets(dto.getPlatelets())
                .hemoglobin(dto.getHemoglobin())
                .build() : null;
    }

    public UrineTestDTO toUrineTest(AddTestDTO dto) {
        return dto != null ? UrineTestDTO.builder()
                .id(dto.getId())
                .color(dto.getColor())
                .ph(dto.getPh())
                .protein(dto.getProtein())
                .hemoglobine(dto.getHemoglobine())
                .build() : null;
    }
}