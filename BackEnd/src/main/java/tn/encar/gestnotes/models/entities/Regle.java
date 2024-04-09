package tn.encar.gestnotes.models.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Regle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_regle") 
	private int id;
	private double minMoyGen;
	private double minMoyModule;
	private int minCredit;
	
	@OneToMany
	@JoinColumn(name = "fk_regle_id", referencedColumnName = "id_regle")
	private List<Departement> departements = new ArrayList<>();

}
