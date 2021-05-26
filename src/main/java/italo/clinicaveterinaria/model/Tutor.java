package italo.clinicaveterinaria.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter		
@Entity
@Table(name="tutor")
public class Tutor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="animal_id")
	private Animal animal;
	
}
