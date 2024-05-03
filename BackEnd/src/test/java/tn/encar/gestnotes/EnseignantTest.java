package tn.encar.gestnotes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Note;
import tn.encar.gestnotes.services.impl.EnseignantService;

public class EnseignantTest {
	static Classe c1, c2, c3;
	static Enseignant enseignant = new Enseignant();
	static EnseignantService enseignantService =  new EnseignantService();
	@BeforeAll
	static void init() {
        // Création d'un étudiant
        enseignant = new Enseignant();
        enseignant.setNom("Bey");
        enseignant.setPrenom("Asma ");
        //creation des classes
		c1 = new Classe(1,'A');
		c2 = new Classe(1,'B');
		c3 = new Classe(2,'A');
	}
	
	@Test
	public void testCreationEnseignant() {
		// Création d'un enseignant avec un nom
		Enseignant enseignant = new Enseignant();
		enseignant.setNom("Bey");
		enseignant.setPrenom("Asma ");

		// Vérification que le nom est correctement attribué
		assertEquals("Bey", enseignant.getNom());
	}
	@Test
	@Order(1)
	void associerClasse() {
		enseignant.addClasse(c1);
		enseignant.addClasse(c2);
		enseignant.addClasse(c3);
		assertEquals(3, enseignant.getClassesAffectees().size());
	}
	@Test
	@Order(2)
	void desassocierClasse() {
		enseignant.addClasse(c1);
		enseignant.addClasse(c2);
		enseignant.addClasse(c3);
		enseignant.removeClasse(c1);
		assertEquals(2, enseignant.getClassesAffectees().size());
	}

}