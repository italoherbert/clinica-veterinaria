package italo.clinicaveterinaria.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaFiltroRequest {

	private String dataConsulta;
	
	private String status;
	
}
