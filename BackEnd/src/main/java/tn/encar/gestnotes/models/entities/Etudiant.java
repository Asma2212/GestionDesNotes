package tn.encar.gestnotes.models.entities;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.encar.gestnotes.models.enums.TypeFormation;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("ETUDIANT")
public class Etudiant extends Personne{

	private int numInscri;
	private int niveau;
	@Enumerated(EnumType.STRING)
	private TypeFormation typeFormation ;
	
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "etudiant")
			private Set<Absence> absences;
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "etudiant")
			private Set<Note> notes;

}
