package tn.encar.gestnotes.models.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatiere;
	
	private String nomMatiere ;
	private int Coeff ;
	@JsonIgnore
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "matiere")
			private Set<Absence> absences;
	@JsonIgnore
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "matiere")
			private Set<Note> notes;
}
