package tn.encar.gestnotes.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	
}
