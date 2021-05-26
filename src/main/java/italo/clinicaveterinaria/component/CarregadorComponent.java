package italo.clinicaveterinaria.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.clinicaveterinaria.model.Animal;
import italo.clinicaveterinaria.model.Consulta;
import italo.clinicaveterinaria.model.Pessoa;
import italo.clinicaveterinaria.model.Tutor;
import italo.clinicaveterinaria.model.Veterinario;
import italo.clinicaveterinaria.model.response.AnimalResponse;
import italo.clinicaveterinaria.model.response.ConsultaResponse;
import italo.clinicaveterinaria.model.response.PessoaResponse;
import italo.clinicaveterinaria.model.response.TutorResponse;
import italo.clinicaveterinaria.model.response.VeterinarioResponse;
import italo.clinicaveterinaria.util.DataUtil;

@Component
public class CarregadorComponent {

	@Autowired
	private DataUtil dataUtil;
	
	public void carregaConsulta( ConsultaResponse resp, Consulta c ) {
		AnimalResponse aresp = new AnimalResponse();
		this.carregaAnimal( aresp, c.getAnimal() );
		
		VeterinarioResponse vresp = new VeterinarioResponse();
		this.carregaVeterinario( vresp, c.getVeterinario() );
		
		resp.setId( c.getId() );
		resp.setDataConsulta( dataUtil.formataDataHora( c.getDataConsulta() ) );
		resp.setStatus( c.getStatus() );
		resp.setAnimal( aresp );
		resp.setVeterinario( vresp );
	}
	
	public void carregaVeterinario( VeterinarioResponse resp, Veterinario v ) {
		PessoaResponse presp = new PessoaResponse();
		this.carregaPessoa( presp, v.getPessoa() ); 
		
		resp.setId( v.getId() );
		resp.setPessoa( presp ); 
	}
	
	public void carregaTutor( TutorResponse resp, Tutor t ) {
		PessoaResponse presp = new PessoaResponse();
		this.carregaPessoa( presp, t.getPessoa() ); 
		
		resp.setId( t.getId() );
		resp.setPessoa( presp ); 
	}
	
	public void carregaAnimal( AnimalResponse resp, Animal a ) {
		resp.setId( a.getId() );
		resp.setDataNascimento( dataUtil.formataData( a.getDataNascimento() ) );
		resp.setEspecie( a.getEspecie() );
		resp.setNome( a.getNome() );
		resp.setRaca( a.getRaca() );
		
		List<TutorResponse> tlista = new ArrayList<>();			
		for( Tutor t : a.getTutores() ) {
			TutorResponse tresp = new TutorResponse();				
			this.carregaTutor( tresp, t );
			
			tlista.add( tresp );
		}			
		resp.setTutores( tlista );
	}
	
	public void carregaPessoa( PessoaResponse resp, Pessoa p ) {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setEmail( p.getEmail() );
		resp.setTelefone( p.getTelefone() ); 
	}
	
}
