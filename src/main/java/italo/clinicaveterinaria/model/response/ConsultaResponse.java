package italo.clinicaveterinaria.model.response;

import italo.clinicaveterinaria.model.ConsultaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponse {

	private Long id;
	
	private String dataConsulta;
	
	private ConsultaStatus status;
	
	private AnimalResponse animal;
	
	private VeterinarioResponse veterinario;
	
}
