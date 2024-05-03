package tn.encar.gestnotes.models.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.encar.gestnotes.models.enums.Statut;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ENSEIGNANT")
public class Enseignant extends Personne {

	@Enumerated(EnumType.STRING)
	private Statut position;
	
	@ManyToMany
	@JoinTable(name ="enseignant_classe",
			   joinColumns = @JoinColumn(name = "id_enseignant"),
			   inverseJoinColumns = @JoinColumn(name ="id_classe")
	)
	private Set<Classe> classesAffectees = new HashSet<>();

	public void addClasse(Classe c1) {
		this.classesAffectees.add(c1);
		
	}

	public void removeClasse(Classe c1) {
		this.classesAffectees.remove(c1);
		
	}
	
}
