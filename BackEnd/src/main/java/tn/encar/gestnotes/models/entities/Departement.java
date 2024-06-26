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
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_departement") 
	private int id;
	private String nom;
	
	@OneToMany
	@JoinColumn(name = "fk_depart_id", referencedColumnName = "id_departement")
	private List<Classe> classes =new ArrayList<>();
}
