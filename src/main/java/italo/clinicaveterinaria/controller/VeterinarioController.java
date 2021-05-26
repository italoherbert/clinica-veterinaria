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

import italo.clinicaveterinaria.exception.VeterinarioNaoEncontradoException;
import italo.clinicaveterinaria.model.request.SaveVeterinarioRequest;
import italo.clinicaveterinaria.model.response.ErroResponse;
import italo.clinicaveterinaria.model.response.VeterinarioResponse;
import italo.clinicaveterinaria.service.VeterinarioService;

@RestController
@RequestMapping(value="/api/veterinario")
public class VeterinarioController {

	@Autowired
	private VeterinarioService veterinarioService;
	
	@PostMapping(value="/registra")
	public ResponseEntity<Object> registraVeterinario( @RequestBody SaveVeterinarioRequest request ) {
		if ( request.getPessoa() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_VETERINARIO_REQUERIDO ) );		
		
		if ( request.getPessoa().getNome() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_VETERINARIO_REQUERIDO ) );
		if ( request.getPessoa().getNome().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.NOME_VETERINARIO_REQUERIDO ) );

		if ( request.getPessoa().getTelefone() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TELEFONE_VETERINARIO_REQUERIDO ) );
		if ( request.getPessoa().getTelefone().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.TELEFONE_VETERINARIO_REQUERIDO ) );
		
		if ( request.getPessoa().getEmail() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.EMAIL_VETERINARIO_REQUERIDO ) );
		if ( request.getPessoa().getEmail().trim().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.EMAIL_VETERINARIO_REQUERIDO ) );
		
		
		veterinarioService.registraVeterinario( request );
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/filtra/{nomeIni}")
	public ResponseEntity<Object> filtraVeterinario( @PathVariable String nomeIni ) {
		List<VeterinarioResponse> lista;
		if ( nomeIni.equals( "*" ) ) {
			lista = veterinarioService.buscaTodosVeterinarios();
		} else {
			lista = veterinarioService.filtraVeterinarios( nomeIni );
		}
		return ResponseEntity.ok( lista );
	}
		
	@GetMapping(value="/get/{veterinarioId}")
	public ResponseEntity<Object> getVeterinario( @PathVariable Long veterinarioId ) {
		try {
			VeterinarioResponse resp = veterinarioService.getVeterinario( veterinarioId );
			return ResponseEntity.ok( resp );
		} catch (VeterinarioNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.VETERINARIO_NAO_ENCONTRADO ) );
		}
	}
	
	@DeleteMapping(value="/deleta/{veterinarioId}")
	public ResponseEntity<Object> deleteVeterinario( @PathVariable Long veterinarioId ) {
		try {
			veterinarioService.deletaVeterinario( veterinarioId );
			return ResponseEntity.ok().build();
		} catch (VeterinarioNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.VETERINARIO_NAO_ENCONTRADO ) );
		}
	}
	
}
