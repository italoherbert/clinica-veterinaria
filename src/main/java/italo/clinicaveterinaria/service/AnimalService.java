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
import italo.clinicaveterinaria.exception.DataAnimalFormatoException;
import italo.clinicaveterinaria.exception.EspecieNaoEncontradaException;
import italo.clinicaveterinaria.model.Animal;
import italo.clinicaveterinaria.model.Especie;
import italo.clinicaveterinaria.model.request.SaveAnimalRequest;
import italo.clinicaveterinaria.model.response.AnimalResponse;
import italo.clinicaveterinaria.repository.AnimalRepository;
import italo.clinicaveterinaria.util.DataUtil;
import italo.clinicaveterinaria.util.EnumUtil;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private CarregadorComponent carregador;
	
	@Autowired
	private DataUtil dataUtil;
	
	@Autowired
	private EnumUtil enumUtil;
	
	public void registraAnimal( SaveAnimalRequest request ) throws DataAnimalFormatoException, EspecieNaoEncontradaException {
		Especie especie = enumUtil.paraEspecie( request.getEspecie() );
		if ( especie == null )
			throw new EspecieNaoEncontradaException();
		
		Animal a = new Animal();
		a.setNome( request.getNome() );
		a.setRaca( request.getRaca() );
		a.setEspecie( especie );
		try {
			a.setDataNascimento( dataUtil.paraData( request.getDataNascimento() ) );
		} catch (ParseException e) {
			throw new DataAnimalFormatoException();
		}
		
		animalRepository.save( a );
	}
	
	public List<AnimalResponse> filtraAnimais( String nomeIni ) {
		Animal a = new Animal();
		a.setNome( nomeIni );
		
		ExampleMatcher em = ExampleMatcher.matching()
				.withMatcher( "nome", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase() );
		
		Example<Animal> ex = Example.of( a, em );
		
		List<Animal> animais = animalRepository.findAll( ex );
		
		List<AnimalResponse> lista = new ArrayList<>();
		for( Animal animal : animais ) {
			AnimalResponse resp = new AnimalResponse();
			carregador.carregaAnimal( resp, animal );
									
			lista.add( resp );
		}
		
		return lista;
	}
	
	public List<AnimalResponse> buscaTodosAnimais() {
		List<Animal> animais = animalRepository.findAll();
		
		List<AnimalResponse> lista = new ArrayList<>();
		for( Animal a : animais ) {
			AnimalResponse resp = new AnimalResponse();
			carregador.carregaAnimal( resp, a );						
			
			lista.add( resp );
		}
		return lista;
	}
	
	public AnimalResponse buscaAnimal( Long id ) throws AnimalNaoEncontradoException {
		Animal a = animalRepository.findById( id ).orElseThrow( AnimalNaoEncontradoException::new );
		
		AnimalResponse resp = new AnimalResponse();
		carregador.carregaAnimal( resp, a );				
		
		return resp;
	}
	
	public void deletaAnimal( Long id ) throws AnimalNaoEncontradoException {
		boolean existe = animalRepository.existsById( id );
		if ( !existe )
			throw new AnimalNaoEncontradoException();
		
		animalRepository.deleteById( id ); 
	}
	
}
