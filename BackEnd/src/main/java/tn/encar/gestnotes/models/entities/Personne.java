package tn.encar.gestnotes.models.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ROLE")
public abstract class Personne {

	@Id
	@SequenceGenerator(
			name= "sequence_personne",
			sequenceName = "sequence_personne",
			allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator ="sequence_personne")
	private int id;
	private int cin;
	private String nom;
	private String prenom;
	private int tel;
	private Date dateNaiss;
	private String email;
	private String motDePasse;
	@Column(name = "ROLE", insertable = false, updatable = false)
    private String role;
}
