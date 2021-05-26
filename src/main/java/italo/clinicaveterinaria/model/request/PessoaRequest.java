package italo.clinicaveterinaria.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PessoaRequest {
		
	private String nome;
	
	private String telefone;
	
	private String email;
	
}
