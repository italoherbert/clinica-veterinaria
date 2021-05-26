package italo.clinicaveterinaria.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErroResponse {
	
	public final static int CONSULTA_NAO_ENCONTRADA = 100;
	public final static int DATA_CONSULTA_INVALIDA = 101;
	public final static int CONSULTA_STATUS_NAO_ENCONTRADO = 102;
	public final static int CONSULTA_DATA_CONSULTA_REQUERIDA = 103;
	public final static int CONSULTA_ID_VETERINARIO_REQUERIDO = 104;
	public final static int CONSULTA_ID_ANIMAL_REQUERIDO = 105;

	public final static int ANIMAL_NAO_ENCONTRADO = 200;
	public final static int DATA_NASC_ANIMAL_INVALIDA = 201;
	public final static int NOME_ANIMAL_REQUERIDO = 202;
	public final static int ESPECIE_ANIMAL_REQUERIDA = 203;
	public final static int RACA_ANIMAL_REQUERIDA= 204;
	public final static int DATA_NASC_ANIMAL_REQUERIDA= 205;
	public final static int ESPECIE_NAO_ENCONTRADA = 206;
	
	public final static int VETERINARIO_NAO_ENCONTRADO = 300;
	public final static int NOME_VETERINARIO_REQUERIDO = 301;
	public final static int TELEFONE_VETERINARIO_REQUERIDO = 302;
	public final static int EMAIL_VETERINARIO_REQUERIDO = 303;
	
	public final static int TUTOR_NAO_ENCONTRADO = 400;
	public final static int NOME_TUTOR_REQUERIDO = 401;
	public final static int TELEFONE_TUTOR_REQUERIDO = 402;
	public final static int EMAIL_TUTOR_REQUERIDO = 404;			

	private int codigo;
	
}
