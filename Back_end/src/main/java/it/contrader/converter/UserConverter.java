package it.contrader.converter;

import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {

    @Override
    public User toEntity(UserDTO userDTO) {

        return userDTO != null ? User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .pathImage(userDTO.getPathImage())
                .usertype(userDTO.getUsertype())
                .build() : null;
    }
    public User toPicEntity(UserDTO userDTO, String filePath) {

        return userDTO != null ? User.builder()
                .id(userDTO.getId())
                .email(userDTO.getPassword())
                .password(userDTO.getPassword())
                .usertype(userDTO.getUsertype())
                .pathImage(filePath)
                .build() : null;
    }

    @Override
    public UserDTO toDTO(User user) {
        return user != null ? UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .usertype(user.getUsertype())
                .pathImage(user.getPathImage())
                .build() : null;
    }
}