package italo.clinicaveterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.clinicaveterinaria.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
	
}
