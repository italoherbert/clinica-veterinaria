package italo.clinicaveterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.clinicaveterinaria.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
	
}
