package it.contrader.controller;


import it.contrader.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.service.ServiceDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * In questa classe sono implementati tutti i metodi di CRUD dei Controller, paramentrizzati dal tipo
 * generico. Nella classe viene dichiarata l'interfaccia ServiceDTO<DTO>.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 *@param <DTO>
 *

 * 
 * @see ServiceDTO<DTO>
 */
public abstract class AbstractController <DTO>{

	@Autowired
	private ServiceDTO<DTO> service;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		try {
			Iterable<DTO> records = service.getAll();
			List<DTO> recordsList = StreamSupport.stream(records.spliterator(), false)
					.collect(Collectors.toList());
			if (recordsList.isEmpty()) {
				throw new CustomException("Nessun record trovato nel database.");
			}
			return ResponseEntity.ok(recordsList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cancellazione non riuscita");
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody DTO dto){
		try {
			service.update(dto);
			return ResponseEntity.ok((dto));
		}catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Modifica errata");
		}

	}
	
	@PostMapping("/insert")
	public boolean insert (@RequestBody DTO dto) {
		try{
			service.insert(dto);
//			return ResponseEntity.ok((dto));
			return true;

		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inserimento errato");
			return false;
		}
	}
	
	@GetMapping("/read")
	public ResponseEntity<?> read(long id) {
		try {
			DTO dto = service.read(id);
			return ResponseEntity.ok((dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Non Trovato");
		}
	}
}