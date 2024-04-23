package tn.encar.gestnotes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.models.entities.Note;

class EtudiantTest {

	    @Test
	    public void testCreationEtudiant() {
	        // Création d'un étudiant avec un nom
	        Etudiant etudiant = new Etudiant();
	        etudiant.setNom("Belhedi");
	        etudiant.setPrenom("Abir ");
	        
	        // Vérification que le nom est correctement attribué
	        assertEquals("John Doe", etudiant.getNom());
	        
	        // Vérification que l'étudiant n'a pas de note initialement
	        assertTrue(etudiant.getNotes().isEmpty());
	    }
	    
	    @Test
	    public void testAjoutNote() {
	        // Création d'un étudiant
	        Etudiant etudiant = new Etudiant();
	        etudiant.setNom("Bey");
	        etudiant.setPrenom("Asma ");
	        // Création d'une note
	        Matiere mathematiques = new Matiere("Mathématiques", 2);
	        Note note = new Note();//= new Note(15, "DS", mathematiques);
	        
	        // Ajout de la note à l'étudiant
	        //etudiant.ajouterNote(note);
	        
	        // Vérification que la note a été ajoutée
	        assertEquals(1, etudiant.getNotes().size());
	        assertTrue(etudiant.getNotes().contains(note));
	    }
	}

