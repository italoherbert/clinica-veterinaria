package italo.clinicaveterinaria.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SaveAnimalRequest {
	
	private String nome;
	
	private String especie;
	
	private String raca;
	
	private String dataNascimento;
	
}
