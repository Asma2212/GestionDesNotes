package tn.encar.gestnotes.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int niveau;
	private char groupe;
	@JsonIgnore
	@ManyToMany(mappedBy = "classesAffectees")
	private Set<Enseignant> enseignants= new HashSet<>();
	@JsonIgnore 
	@OneToMany( cascade =
			CascadeType.ALL, mappedBy = "classe")
			private Set<Etudiant> etudiants;
	@Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", niveau=" + niveau +
                ", groupe=" + groupe +
                '}';
    }
	
	public Classe(int niveau , char groupe) {
		this.niveau = niveau ;
		this.groupe = groupe;
	}
	
}
