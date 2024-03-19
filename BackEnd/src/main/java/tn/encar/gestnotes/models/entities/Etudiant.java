package tn.encar.gestnotes.models.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
	private TypeFormation typeFormation ;

}
