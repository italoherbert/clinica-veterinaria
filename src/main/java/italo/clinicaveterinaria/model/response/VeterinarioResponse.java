package italo.clinicaveterinaria.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VeterinarioResponse {

	private Long id;
	
	private PessoaResponse pessoa;
	
}
