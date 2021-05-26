package italo.clinicaveterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.clinicaveterinaria.component.CarregadorComponent;
import italo.clinicaveterinaria.exception.VeterinarioNaoEncontradoException;
import italo.clinicaveterinaria.model.Pessoa;
import italo.clinicaveterinaria.model.Veterinario;
import italo.clinicaveterinaria.model.request.SaveVeterinarioRequest;
import italo.clinicaveterinaria.model.response.VeterinarioResponse;
import italo.clinicaveterinaria.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@Autowired
	private CarregadorComponent carregador;
	
	public void registraVeterinario( SaveVeterinarioRequest request ) {
		Pessoa p = new Pessoa();
		p.setNome( request.getPessoa().getNome() );
		p.setTelefone( request.getPessoa().getTelefone() );
		p.setEmail( request.getPessoa().getEmail() );
		
		Veterinario v = new Veterinario();
		v.setPessoa( p );		
				
		veterinarioRepository.save( v );
	}
	
	public List<VeterinarioResponse> filtraVeterinarios( String nomeIni ) {
		List<Veterinario> veterinarios = veterinarioRepository.findByNomeIni( nomeIni );
		
		List<VeterinarioResponse> lista = new ArrayList<>();
		for( Veterinario v : veterinarios ) {									
			VeterinarioResponse resp = new VeterinarioResponse();
			carregador.carregaVeterinario( resp, v );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public List<VeterinarioResponse> buscaTodosVeterinarios() {
		List<Veterinario> veterinarios = veterinarioRepository.findAll();
		
		List<VeterinarioResponse> lista = new ArrayList<>();
		for( Veterinario v : veterinarios ) {
			VeterinarioResponse resp = new VeterinarioResponse();
			carregador.carregaVeterinario( resp, v ); 
			
			lista.add( resp );
		}
		return lista;
	}
	
	public VeterinarioResponse getVeterinario( Long id ) throws VeterinarioNaoEncontradoException {
		Veterinario v = veterinarioRepository.findById( id ).orElseThrow( VeterinarioNaoEncontradoException::new );
		
		VeterinarioResponse resp = new VeterinarioResponse();
		carregador.carregaVeterinario( resp, v );
		
		return resp;
	}
	
	public void deletaVeterinario( Long id ) throws VeterinarioNaoEncontradoException {
		boolean existe = veterinarioRepository.existsById( id );
		if ( !existe )
			throw new VeterinarioNaoEncontradoException();
		
		veterinarioRepository.deleteById( id ); 
	}
	
}
