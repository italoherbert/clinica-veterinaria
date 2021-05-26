package italo.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.clinicaveterinaria.component.CarregadorComponent;
import italo.clinicaveterinaria.exception.AnimalNaoEncontradoException;
import italo.clinicaveterinaria.exception.TutorNaoEncontradoException;
import italo.clinicaveterinaria.model.Animal;
import italo.clinicaveterinaria.model.Pessoa;
import italo.clinicaveterinaria.model.Tutor;
import italo.clinicaveterinaria.model.request.SaveTutorRequest;
import italo.clinicaveterinaria.model.response.TutorResponse;
import italo.clinicaveterinaria.repository.AnimalRepository;
import italo.clinicaveterinaria.repository.TutorRepository;

@Service
public class TutorService {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
		
	@Autowired
	private CarregadorComponent carregador;
	
	public void registraTutor( Long animalId, SaveTutorRequest request ) throws AnimalNaoEncontradoException {
		boolean existeAnimal = animalRepository.existsById( animalId );		
		if ( !existeAnimal )
			throw new AnimalNaoEncontradoException();
		
		Animal a = new Animal();
		a.setId( animalId );
		
		Pessoa p = new Pessoa();
		p.setNome( request.getPessoa().getNome() );
		p.setEmail( request.getPessoa().getEmail() );
		p.setTelefone( request.getPessoa().getTelefone() );
		
		Tutor t = new Tutor();
		t.setPessoa( p ); 
		t.setAnimal( a );
		
		tutorRepository.save( t );
	}

	public List<TutorResponse> listaTodosTutoresPorAnimal( Long animalId ) throws AnimalNaoEncontradoException {
		Animal animal = animalRepository.findById( animalId ).orElseThrow( AnimalNaoEncontradoException::new );
		
		List<TutorResponse> lista = new ArrayList<>();
		for( Tutor t : animal.getTutores() ) {
			TutorResponse resp = new TutorResponse();
			carregador.carregaTutor( resp, t );
			
			lista.add( resp );
		}		
		
		return lista;
	}
	
	public List<TutorResponse> filtraTutoresPorAnimal( Long animalId, String nomeIni ) throws AnimalNaoEncontradoException {
		Animal animal = animalRepository.findById( animalId ).orElseThrow( AnimalNaoEncontradoException::new );
		
		List<TutorResponse> lista = new ArrayList<>();
		for( Tutor t : animal.getTutores() ) {
			if ( !t.getPessoa().getNome().toLowerCase().startsWith( nomeIni.toLowerCase() ) )
				continue;
			
			TutorResponse resp = new TutorResponse();
			carregador.carregaTutor( resp, t );
			
			lista.add( resp );
		}		
		
		return lista;
	}
	
	public TutorResponse buscaTutor( Long id ) throws TutorNaoEncontradoException {
		Tutor t = tutorRepository.findById( id ).orElseThrow( TutorNaoEncontradoException::new );
		
		TutorResponse resp = new TutorResponse();
		carregador.carregaTutor( resp, t );
		
		return resp;
	}
	
	public void deletaTutor( Long id ) throws TutorNaoEncontradoException {
		boolean existe = tutorRepository.existsById( id );
		if ( !existe )
			throw new TutorNaoEncontradoException();
		
		tutorRepository.deleteById( id ); 
	}
	
}
