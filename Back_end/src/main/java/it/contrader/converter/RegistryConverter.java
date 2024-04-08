package it.contrader.converter;

import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;
import it.contrader.model.User;
import org.springframework.stereotype.Component;

@Component
public class RegistryConverter extends AbstractConverter<Registry, RegistryDTO> {


    @Override
    public Registry toEntity(RegistryDTO registryDTO) {
        User user = new User();
        user.setId(registryDTO.getIdUser());
        return registryDTO != null ? Registry.builder()
                .id(registryDTO.getId())
                .name(capitalizeFirstLetter(registryDTO.getName()))
                .surname(capitalizeFirstLetter(registryDTO.getSurname()))
                .gender(registryDTO.getGender())
                .birthDate(registryDTO.getBirthDate())
                .birthPlace(capitalizeFirstLetter(registryDTO.getBirthPlace()))
                .nationality(registryDTO.getNationality().toUpperCase())
                .city(capitalizeFirstLetter(registryDTO.getCity()))
                .address(registryDTO.getAddress())
                .bg(registryDTO.getBg())
                .cf(registryDTO.getCf().toUpperCase())
                .userFk(user)
                .build() : null;
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }


    @Override
    public RegistryDTO toDTO(Registry registry) {
        return registry != null ? RegistryDTO.builder()
                .id(registry.getId())
                .name(registry.getName())
                .surname(registry.getSurname())
                .gender(registry.getGender())
                .birthDate(registry.getBirthDate())
                .birthPlace(registry.getBirthPlace())
                .nationality(registry.getNationality())
                .city(registry.getCity())
                .address(registry.getAddress())
                .bg(registry.getBg())
                .cf(registry.getCf())
                .idUser(registry.getUserFk().getId())
                .build(): null;
    }

}
