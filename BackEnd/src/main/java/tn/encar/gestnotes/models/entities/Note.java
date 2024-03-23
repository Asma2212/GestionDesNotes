package tn.encar.gestnotes.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Type")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNote ;
	private double note ;
	@ManyToOne(cascade = CascadeType.ALL
			,fetch = FetchType.EAGER/* fetch =
			FetchType.LAZY*/)
			private Etudiant etudiant;
	@ManyToOne(cascade = CascadeType.ALL
			,fetch = FetchType.EAGER/* fetch =
			FetchType.LAZY*/)
			private Matiere matiere;
}
