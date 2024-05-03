package tn.encar.gestnotes.models.entities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Enumerated(EnumType.STRING)
	private TypeFormation typeFormation ;
	@JsonIgnore
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "etudiant")
			private Set<Absence> absences = new HashSet<Absence>();
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "etudiant")
			private Set<Note> notes = new HashSet<Note>();
	@ManyToOne(cascade = CascadeType.ALL
			,fetch = FetchType.EAGER/* fetch =
			FetchType.LAZY*/)
			private Classe classe;
	public void addNotes(Note note) {
		this.notes.add(note);
		
	}
	public void deleteNote(Note note) {
		this.notes.remove(note);
		
	}

}
