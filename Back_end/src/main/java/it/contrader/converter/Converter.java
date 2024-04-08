package it.contrader.converter;

import java.util.List;

public interface Converter<Entity, DTO> {

    public Entity toEntity(DTO dto);

    public DTO toDTO(Entity entity);

    public List<DTO> toDTOList(List<Entity> entityList);

    public List<Entity> toEntityList(List<DTO> listDTO);

}