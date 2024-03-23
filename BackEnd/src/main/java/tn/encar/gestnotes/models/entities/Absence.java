package tn.encar.gestnotes.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAbsence;
	@ManyToOne(cascade = CascadeType.ALL
			,fetch = FetchType.EAGER/* fetch =
			FetchType.LAZY*/)
			private Etudiant etudiant;
	@ManyToOne(cascade = CascadeType.ALL
			,fetch = FetchType.EAGER/* fetch =
			FetchType.LAZY*/)
			private Matiere matiere;

}
