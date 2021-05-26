package italo.clinicaveterinaria.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	
	@Column
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Especie especie;
	
	@Column
	private String raca;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="animal", cascade=CascadeType.REMOVE)	
	private List<Tutor> tutores;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="animal", cascade=CascadeType.REMOVE)
	private List<Consulta> consultas;
	
}
