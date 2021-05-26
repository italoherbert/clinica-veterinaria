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
import italo.clinicaveterinaria.exception.TutorNaoEncontradoException;
import italo.clinicaveterinaria.model.request.SaveTutorRequest;
import italo.clinicaveterinaria.model.response.ErroResponse;
import italo.clinicaveterinaria.model.response.TutorResponse;
import italo.clinicaveterinaria.service.TutorService;

@RestController
@RequestMapping(value="/api/tutor")
public class TutorController {

	@Autowired
	private TutorService tutorService;
			
	@PostMapping(value="/registra/{animalId}")
	public ResponseEntity<Object> registraTutor( @PathVariable Long animalId, @RequestBody SaveTutorRequest request ) {
		if ( request.getPessoa() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_TUTOR_REQUERIDO ) );		
		
		if ( request.getPessoa().getNome() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_TUTOR_REQUERIDO ) );
		if ( request.getPessoa().getNome().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_TUTOR_REQUERIDO ) );

		if ( request.getPessoa().getTelefone() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TELEFONE_TUTOR_REQUERIDO ) );
		if ( request.getPessoa().getTelefone().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TELEFONE_TUTOR_REQUERIDO ) );
		
		if ( request.getPessoa().getEmail() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.EMAIL_TUTOR_REQUERIDO ) );
		if ( request.getPessoa().getEmail().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.EMAIL_TUTOR_REQUERIDO ) );
		
		try {
			tutorService.registraTutor( animalId, request );
			return ResponseEntity.ok().build();
		} catch (AnimalNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ANIMAL_NAO_ENCONTRADO ) );
		}
	}
	
	@GetMapping(value="/filtra/{animalId}/{nomeIni}")
	public ResponseEntity<Object> buscaTutor( @PathVariable Long animalId, @PathVariable String nomeIni ) {
		try {
			List<TutorResponse> lista;
			if ( nomeIni.equals( "*" ) ) {
				lista = tutorService.listaTodosTutoresPorAnimal( animalId );
			} else {
				lista = tutorService.filtraTutoresPorAnimal( animalId, nomeIni );
			}
			
			return ResponseEntity.ok( lista );
		} catch ( AnimalNaoEncontradoException e ) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ANIMAL_NAO_ENCONTRADO ) );
		}
	}
	
	@GetMapping(value="/get/{tutorId}")
	public ResponseEntity<Object> buscaTutor( @PathVariable Long tutorId ) {
		try {
			TutorResponse resp = tutorService.buscaTutor( tutorId );
			return ResponseEntity.ok( resp );
		} catch (TutorNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TUTOR_NAO_ENCONTRADO ) );
		}
	}
	
	@DeleteMapping("/deleta/{tutorId}")
	public ResponseEntity<Object> deletaTutor( @PathVariable Long tutorId ) {
		try {
			tutorService.deletaTutor( tutorId );
			return ResponseEntity.ok().build();
		} catch (TutorNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TUTOR_NAO_ENCONTRADO ) );
		}
		
	}
	
}
