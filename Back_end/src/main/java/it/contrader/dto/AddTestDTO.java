package it.contrader.dto;

import it.contrader.model.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO della classe BloodTest. Ha gli stessi attributi di BloodTest
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 *@see it.contrader.model.BloodTest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddTestDTO {

	private Long id;

	private Long doctor;

	private Long patient;

	private Boolean isChecked;

	private String date;

	private Test.TestType type;

	private Float redBloodCell;

	private Float whiteBloodCell;

	private Float platelets;

	private Float hemoglobin;

	private Float color;

	private Float hemoglobine;

	private Float ph;

	private Float protein;

}
