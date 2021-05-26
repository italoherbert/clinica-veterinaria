package italo.clinicaveterinaria.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataConsulta;
	
	@Enumerated(EnumType.STRING)
	private ConsultaStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="veterinario_id")
	private Veterinario veterinario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="animal_id	")
	private Animal animal;
	
}
