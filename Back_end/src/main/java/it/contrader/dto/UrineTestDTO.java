package it.contrader.dto;

import it.contrader.model.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO della classe UrineTest. Ha gli stessi attributi di UrineTest
 *
 * @author Vittorio Valent & Girolamo Murdaca
 *
 *@see it.contrader.model.UrineTest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrineTestDTO {

    private Long id;

    private Float color;

    private Float hemoglobine;

    private Float ph;

    private Float protein;

    private Long idTest;

}