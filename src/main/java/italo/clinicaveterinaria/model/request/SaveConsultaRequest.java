package italo.clinicaveterinaria.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SaveConsultaRequest {
	
	private String dataConsulta;
		
	private Long veterinarioId;
	
	private Long animalId;
	
}
