package tn.encar.gestnotes.models.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Matiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatiere;
	
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "matiere")
			private Set<Absence> absences;
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "matiere")
			private Set<Note> notes;
}
