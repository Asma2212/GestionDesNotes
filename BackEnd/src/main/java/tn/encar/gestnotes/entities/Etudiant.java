package tn.encar.gestnotes.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Etudiant extends Personne{

	private int numInscri;
	private int niveau;
	private TypeFormation typeFormation ;

}
