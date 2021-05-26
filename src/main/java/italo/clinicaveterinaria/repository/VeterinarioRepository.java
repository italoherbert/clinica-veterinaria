package italo.clinicaveterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import italo.clinicaveterinaria.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
	
	@Query(value="select v from Veterinario v inner join v.pessoa p where lower(p.nome) like concat(lower(:nomeIni),'%')" )
	public List<Veterinario> findByNomeIni( @Param("nomeIni") String nomeIni );
	
}
