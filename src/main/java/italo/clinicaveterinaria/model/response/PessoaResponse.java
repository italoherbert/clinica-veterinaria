package italo.clinicaveterinaria.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PessoaResponse {

	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
}
