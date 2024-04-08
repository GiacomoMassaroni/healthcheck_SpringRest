package it.contrader.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity, DTO> implements Converter<Entity, DTO> {

    public List<Entity> toEntityList(List<DTO> listDTO) {
        return listDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<DTO> toDTOList(List<Entity> listEntity) {
        return listEntity.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
