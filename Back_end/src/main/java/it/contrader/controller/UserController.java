package it.contrader.controller;

import it.contrader.dto.*;
import it.contrader.model.User;
import it.contrader.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import it.contrader.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import javax.validation.Valid;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserDTO> {

	@Autowired
	private UserService userService;
	private UserDTO userDTO;
	@Autowired
	private EmailController emailController;

	@Autowired
	private TestController testController;




	@PostMapping(value = "/findbyemailandpassword")
	public ResponseEntity<UserDTO> findbyemailandpassword(@RequestBody @Valid LoginDTO loginDTO) {
		return new ResponseEntity<>(userService.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()), HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
		return new ResponseEntity<>(userService.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()), HttpStatus.OK);
	}

	@PostMapping(value = "/registration")
	public ResponseEntity<UserDTO> registration(@RequestBody RegistrationDTO registrationDTO) {
		registrationDTO.setUsertype(User.Usertype.USER);
		emailController.sendRegistrationEmail(registrationDTO.getEmail());
		return new ResponseEntity<>(userService.insert(registrationDTO), HttpStatus.OK);
	}

	@PutMapping(value = "/resetPassword")
	public ResponseEntity<UserDTO> resetPassword(@RequestBody @Valid RegistrationDTO registrationDTO) {
		userService.resetPassword(registrationDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/uploadProPic")
	public void uploadImageToFIleSystem(@RequestParam("image") MultipartFile file, @RequestParam("email") String email) throws IOException {
		String uploadImage = userService.uploadImageToFileSystem(file, email);
	}

	@GetMapping("/viewProPic")
	public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam Long id) throws IOException {
		byte[] imageData = userService.downloadImageFromFileSystem(id);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	@GetMapping(value = "/resetPasswordEmail")
	public void  resetPasswordEmail(@RequestParam("email") String email) {

			String password = userService.resetPasswordByEmail(email);
			emailController.resetPasswordEmail(email, password);

	}

	@GetMapping(value = "/findByEmail")
	public ResponseEntity<UserDTO> findByEmail(@RequestParam("email") String email) {
		return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
	}

	@PostMapping("/resetUserPassword")
	public void resetUserPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		UserDTO userDto = userService.findByEmail(resetPasswordDTO.getEmail());
		if (userDto.getPassword().equals(resetPasswordDTO.getOldPassword())) {
			userService.resetPass(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getEmail());
		}

	}

	@DeleteMapping("/erase")
	public ResponseEntity<?> delete(@RequestParam("id") long id) {
		try {
			if (userService.read(id).getUsertype().equals(User.Usertype.ADMIN)) {
				testController.changeDoctor(id);
			}
			userService.delete(id);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cancellazione non riuscita");
		}
	}

}