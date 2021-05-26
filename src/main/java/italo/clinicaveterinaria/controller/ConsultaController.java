package italo.clinicaveterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import italo.clinicaveterinaria.exception.AnimalNaoEncontradoException;
import italo.clinicaveterinaria.exception.ConsultaNaoEncontradaException;
import italo.clinicaveterinaria.exception.DataConsultaFormatoException;
import italo.clinicaveterinaria.exception.StatusConsultaNaoEncontrado;
import italo.clinicaveterinaria.exception.VeterinarioNaoEncontradoException;
import italo.clinicaveterinaria.model.request.ConsultaFiltroRequest;
import italo.clinicaveterinaria.model.request.SaveConsultaRequest;
import italo.clinicaveterinaria.model.response.ConsultaResponse;
import italo.clinicaveterinaria.model.response.ErroResponse;
import italo.clinicaveterinaria.service.ConsultaService;
import italo.clinicaveterinaria.util.EnumUtil;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private EnumUtil enumUtil;
	
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody SaveConsultaRequest request ) {
		if ( request.getDataConsulta() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_DATA_CONSULTA_REQUERIDA ) );
		if ( request.getDataConsulta().isEmpty() )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_DATA_CONSULTA_REQUERIDA ) );
		
		if ( request.getAnimalId() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_ID_ANIMAL_REQUERIDO ) );

		if ( request.getVeterinarioId() == null )
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_ID_VETERINARIO_REQUERIDO ) );
		
		try {
			consultaService.registraConsulta( request );
			return ResponseEntity.ok().build();
		} catch ( DataConsultaFormatoException e ) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.DATA_CONSULTA_INVALIDA ) );
		} catch (AnimalNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.ANIMAL_NAO_ENCONTRADO ) );
		} catch (VeterinarioNaoEncontradoException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.VETERINARIO_NAO_ENCONTRADO ) );
		} 
	}
	
	@PatchMapping("/salva/status/{consultaId}")
	public ResponseEntity<Object> salvaStatus( @PathVariable Long consultaId, @RequestBody String status ) {		
		try {
			consultaService.salvaConsultaStatus( consultaId, status );
			return ResponseEntity.ok().build();
		} catch (StatusConsultaNaoEncontrado e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_STATUS_NAO_ENCONTRADO ) );
		} catch (ConsultaNaoEncontradaException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_NAO_ENCONTRADA ) );
		}		 
	}
	
	@ResponseBody
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody ConsultaFiltroRequest request ) {
		try {
			List<ConsultaResponse> lista = consultaService.filtraConsultas( request );
			return ResponseEntity.ok( lista );
		} catch ( DataConsultaFormatoException e ) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.DATA_CONSULTA_INVALIDA ) );
		} catch (StatusConsultaNaoEncontrado e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_STATUS_NAO_ENCONTRADO ) );
		} 		
	}	
	
	@ResponseBody
	@GetMapping("/get/{consultaId}")
	public ResponseEntity<Object> get( @PathVariable Long consultaId ) {
		try {
			ConsultaResponse resp = consultaService.getConsulta( consultaId );
			return ResponseEntity.ok( resp );
		} catch ( ConsultaNaoEncontradaException e ) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_NAO_ENCONTRADA ) );
		} 		
	}	
	
	@GetMapping(value="/lista/statuss")
	public ResponseEntity<Object> listaStatuss() {						
		return ResponseEntity.ok( enumUtil.getConsultaStatusTipos() );
	}
	
	@DeleteMapping("/delete/{consultaId}")
	public ResponseEntity<Object> deleta( @PathVariable Long consultaId ) {
		try {
			consultaService.deletaConsulta( consultaId );
			return ResponseEntity.ok().build();	
		} catch (ConsultaNaoEncontradaException e) {
			return ResponseEntity.badRequest().body( new ErroResponse( ErroResponse.CONSULTA_NAO_ENCONTRADA ) );
		}
	}
	
}
