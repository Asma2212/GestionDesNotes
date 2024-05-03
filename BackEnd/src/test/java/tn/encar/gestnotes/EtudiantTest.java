package tn.encar.gestnotes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.models.entities.Note;

class EtudiantTest {
	static Etudiant etudiant ; 
	static Note note ; 
	@BeforeAll
	static void init() {
        // Création d'un étudiant
        etudiant = new Etudiant();
        etudiant.setNom("Belhedi");
        etudiant.setPrenom("Abir ");
        // Création d'une note
        Matiere mathematiques = new Matiere("Mathématiques", 2);
        note = new Note();
        note = new Note(15, "DS");
        // Ajout de la note à l'étudiant
        note.setMatiere(mathematiques);
	}
	    @Test
	    @Order(1)
	    public void testCreationEtudiant() {
	        // Création d'un étudiant avec un nom
	        Etudiant etudiant = new Etudiant();
	        etudiant.setNom("Belhedi");
	        etudiant.setPrenom("Abir ");
	        
	        // Vérification que le nom est correctement attribué
	        assertEquals("Belhedi", etudiant.getNom());
	        
	        // Vérification que l'étudiant n'a pas de note initialement
	        assertTrue(etudiant.getNotes().isEmpty());
	    }
		@Test
		@Order(3)
		void testDeleteNote() {
			etudiant.deleteNote(note);
			// Vérification que la note a été supprimée
	        assertEquals(0, etudiant.getNotes().size());
	        assertTrue(!etudiant.getNotes().contains(note));
		}
		
	    @Test
	    @Order(2)
	    public void testAjoutNote() {
	        etudiant.addNotes(note);
	        // Vérification que la note a été ajoutée
	        assertEquals(1, etudiant.getNotes().size());
	        assertTrue(etudiant.getNotes().contains(note));
	    }
	    
	}

