package it.contrader.service;

import it.contrader.converter.TestConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dto.RegistrationDTO;
import it.contrader.dto.ResetPasswordDTO;
import it.contrader.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService extends AbstractService<User,UserDTO> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter converter;





	private final String FOLDER_PATH= System.getProperty("user.dir")+"/src/main/java/photo/";

	private static final String CARATTERI = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
	private static final SecureRandom random = new SecureRandom();





	public UserDTO findByEmailAndPassword(String email, String password) {
		return converter.toDTO(((UserRepository)repository).findByEmailAndPassword(email, password)
				.orElseThrow(() -> new InvalidCredentialsException("Credenziali Errate")));
	}

	public UserDTO findByEmail(String email) {
		return converter.toDTO(((UserRepository)repository).findByEmail(email)
				.orElseThrow(() -> new InvalidCredentialsException("Credenziali Errate")));
	}



	public void resetPassword(RegistrationDTO registrationDTO) {
		User user = userRepository.findByEmail(registrationDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));
		user.setPassword(registrationDTO.getPassword());
		userRepository.save(user);
	}

	public String uploadImageToFileSystem(MultipartFile file, String email) throws IOException {
		String filePath=file.getOriginalFilename();
		UserDTO userDTO = converter.toDTO(userRepository.findByEmail(email).orElseThrow());
		User fileData=userRepository.save(converter.toPicEntity(userDTO, filePath));
		file.transferTo(new File(FOLDER_PATH + filePath));
		if (fileData != null) {
			return "Upload immagine del profilo riuscito";
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(Long id) throws IOException {
		Optional<User> fileData = userRepository.findPathImageById(id);
		String filePath;
		if (fileData.get().getPathImage() != null) {
			filePath = FOLDER_PATH + fileData.get().getPathImage();
		} else {
			filePath = FOLDER_PATH + "default.png";
		}
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}

	public String resetPasswordByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));
		String newPassword = generateRandomPassword();
		user.setPassword(newPassword);
		userRepository.save(user);
		return newPassword;
	}

	public static String generateRandomPassword() {
		StringBuilder password = new StringBuilder();


		password.append((char) (random.nextInt(26) + 'A'));


		password.append(random.nextInt(10));


		password.append(CARATTERI.charAt(52 + random.nextInt(14)));

		for (int i = 0; i < 6; i++) {
			password.append((char) (random.nextInt(26) + 'a'));
		}

		return password.toString();
	}


	public void resetPass(String password, String email){

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));
		user.setPassword(password);
		userRepository.save(user);
	}

	}






