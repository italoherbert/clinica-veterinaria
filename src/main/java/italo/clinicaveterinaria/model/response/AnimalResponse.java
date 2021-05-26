package italo.clinicaveterinaria.model.response;

import java.util.List;

import italo.clinicaveterinaria.model.Especie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnimalResponse {

	private Long id; 
	
	private String nome;
	
	private Especie especie;
	
	private String raca;
	
	private String dataNascimento;
	
	private List<TutorResponse> tutores;
		
}
