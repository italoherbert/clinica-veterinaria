package italo.clinicaveterinaria.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import italo.clinicaveterinaria.component.CarregadorComponent;
import italo.clinicaveterinaria.exception.AnimalNaoEncontradoException;
import italo.clinicaveterinaria.exception.ConsultaNaoEncontradaException;
import italo.clinicaveterinaria.exception.DataConsultaFormatoException;
import italo.clinicaveterinaria.exception.StatusConsultaNaoEncontrado;
import italo.clinicaveterinaria.exception.VeterinarioNaoEncontradoException;
import italo.clinicaveterinaria.model.Animal;
import italo.clinicaveterinaria.model.Consulta;
import italo.clinicaveterinaria.model.ConsultaStatus;
import italo.clinicaveterinaria.model.Veterinario;
import italo.clinicaveterinaria.model.request.ConsultaFiltroRequest;
import italo.clinicaveterinaria.model.request.SaveConsultaRequest;
import italo.clinicaveterinaria.model.response.ConsultaResponse;
import italo.clinicaveterinaria.repository.AnimalRepository;
import italo.clinicaveterinaria.repository.ConsultaRepository;
import italo.clinicaveterinaria.repository.VeterinarioRepository;
import italo.clinicaveterinaria.util.DataUtil;
import italo.clinicaveterinaria.util.EnumUtil;

@Service
public class ConsultaService {
		
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private DataUtil dataUtil;
	
	@Autowired
	private EnumUtil enumUtil;
	
	@Autowired
	private CarregadorComponent carregador;
	
	public void registraConsulta( SaveConsultaRequest request )	throws DataConsultaFormatoException,  
				AnimalNaoEncontradoException, VeterinarioNaoEncontradoException {
		
		boolean existeAnimal = animalRepository.existsById( request.getAnimalId() );
		if ( !existeAnimal )
			throw new AnimalNaoEncontradoException();
		
		boolean existeVeterinario = veterinarioRepository.existsById( request.getVeterinarioId() );
		if ( !existeVeterinario )
			throw new VeterinarioNaoEncontradoException();
		
		Animal animal = new Animal();
		animal.setId( request.getAnimalId() );
		
		Veterinario veterinario = new Veterinario();
		veterinario.setId( request.getVeterinarioId() );
		
		Consulta c = new Consulta(); 
		c.setStatus( ConsultaStatus.AGENDADA );
		c.setAnimal( animal );
		c.setVeterinario( veterinario );
 		
		try {
			c.setDataConsulta( dataUtil.paraData( request.getDataConsulta() ) );
		} catch ( ParseException e ) {
			throw new DataConsultaFormatoException();
		}
		
		consultaRepository.save( c );
	}

	public void salvaConsultaStatus( Long consultaId, String status ) throws ConsultaNaoEncontradaException, StatusConsultaNaoEncontrado {
		Consulta c = consultaRepository.findById( consultaId ).orElseThrow( ConsultaNaoEncontradaException::new );
		
		ConsultaStatus cstatus = enumUtil.paraConsultaStatus( status );
		if ( status == null )
			throw new StatusConsultaNaoEncontrado();
		
		c.setStatus( cstatus );
		
		consultaRepository.save( c );
	}
	
	public List<ConsultaResponse> filtraConsultas( ConsultaFiltroRequest request ) throws DataConsultaFormatoException, StatusConsultaNaoEncontrado {
		Consulta c = new Consulta();				
		
		ExampleMatcher em = ExampleMatcher.matching();		
		
		if ( !request.getStatus().equals( "*" ) ) {
			ConsultaStatus status = enumUtil.paraConsultaStatus( request.getStatus() );
			if ( status == null )
				throw new StatusConsultaNaoEncontrado();
			
			c.setStatus( status );
			em = em.withMatcher( "status", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase() );
		}
		
		if ( !request.getDataConsulta().equals( "*" ) ) {
			try {
				c.setDataConsulta( dataUtil.paraData( request.getDataConsulta() ) );
			} catch ( ParseException e ) {
				throw new DataConsultaFormatoException();
			}
			em = em.withMatcher( "dataConsulta", ExampleMatcher.GenericPropertyMatchers.exact() );
		}
		
		Example<Consulta> ex = Example.of( c, em );
		
		List<Consulta> consultas = consultaRepository.findAll( ex );
		List<ConsultaResponse> lista = new ArrayList<>();
		for( Consulta cons : consultas ) {
			ConsultaResponse resp = new ConsultaResponse();
			carregador.carregaConsulta( resp, cons );
			
			lista.add( resp );
		}
		return lista;
	}	
	
	public ConsultaResponse getConsulta( Long id ) throws ConsultaNaoEncontradaException {
		Consulta c = consultaRepository.findById( id ).orElseThrow( ConsultaNaoEncontradaException::new );
		
		ConsultaResponse resp = new ConsultaResponse();
		carregador.carregaConsulta( resp, c );
		
		return resp;
	}
	
	public void deletaConsulta( Long id ) throws ConsultaNaoEncontradaException {
		if ( !consultaRepository.existsById( id ) )
			throw new ConsultaNaoEncontradaException();
		
		consultaRepository.deleteById( id ); 
	}
	
}
