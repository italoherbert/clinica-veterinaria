package italo.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.clinicaveterinaria.exception.AnimalNaoEncontradoException;
import italo.clinicaveterinaria.exception.DataAnimalFormatoException;
import italo.clinicaveterinaria.exception.EspecieNaoEncontradaException;
import italo.clinicaveterinaria.model.request.SaveAnimalRequest;
import italo.clinicaveterinaria.model.response.AnimalResponse;
import italo.clinicaveterinaria.model.response.ErroResponse;
import italo.clinicaveterinaria.service.AnimalService;
import italo.clinicaveterinaria.util.EnumUtil;

@RestController
@RequestMapping(value="/api/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private EnumUtil enumUtil;
	
	@PostMapping(value="/registra")
	public ResponseEntity<Object> registraAnimal( @RequestBody SaveAnimalRequest request ) {
		if ( request.getNome() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_ANIMAL_REQUERIDO ) );
		if ( request.getNome().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_ANIMAL_REQUERIDO ) );

		if ( request.getEspecie() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ESPECIE_ANIMAL_REQUERIDA ) );
		if ( request.getEspecie().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ESPECIE_ANIMAL_REQUERIDA ) );
		
		if ( request.getRaca() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.RACA_ANIMAL_REQUERIDA ) );
		if ( request.getRaca().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.RACA_ANIMAL_REQUERIDA ) );
		
		if ( request.getDataNascimento() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.DATA_NASC_ANIMAL_REQUERIDA ) );
		if ( request.getDataNascimento().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.DATA_NASC_ANIMAL_REQUERIDA ) );
		
		try {
			animalService.registraAnimal( request );
			return ResponseEntity.ok().build();
		} catch ( DataAnimalFormatoException e ) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.DATA_NASC_ANIMAL_INVALIDA ) );
		} catch (EspecieNaoEncontradaException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ESPECIE_NAO_ENCONTRADA ) );
		}
	}
	
	@GetMapping(value="/filtra/{nomeIni}")
	public ResponseEntity<Object> filtraAnimais( @PathVariable String nomeIni ) {				
		List<AnimalResponse> lista;
		if ( nomeIni.equals( "*" ) ) {
			lista = animalService.buscaTodosAnimais();
		} else {
			lista = animalService.filtraAnimais( nomeIni );
		}
		return ResponseEntity.ok( lista );
	}
	
	@GetMapping(value="/get/{animalId}")
	public ResponseEntity<Object> buscaAnimal( @PathVariable Long animalId ) {
		try {
			AnimalResponse resp = animalService.buscaAnimal( animalId );
			return ResponseEntity.ok( resp );
		} catch (AnimalNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ANIMAL_NAO_ENCONTRADO ) );
		}
	}
	
	@GetMapping(value="/lista/especies")
	public ResponseEntity<Object> listaEspecies() {						
		return ResponseEntity.ok( enumUtil.getEspeciesTipos() );
	}
	
	@DeleteMapping("/deleta/{animalId}")
	public ResponseEntity<Object> deletaAnimal( @PathVariable Long animalId ) {
		try {
			animalService.deletaAnimal( animalId );
			return ResponseEntity.ok().build();
		} catch (AnimalNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ANIMAL_NAO_ENCONTRADO ) );
		}
		
	}
	
}
