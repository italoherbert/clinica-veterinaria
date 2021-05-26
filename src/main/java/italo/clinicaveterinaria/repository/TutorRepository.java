package italo.clinicaveterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import italo.clinicaveterinaria.model.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

	@Query(value="select t from Tutor t inner join t.pessoa p where lower(p.nome) like concat(lower(:nomeIni),'%')" )
	public List<Tutor> findByNomeIni( @Param("nomeIni") String nomeIni );	
	
}
