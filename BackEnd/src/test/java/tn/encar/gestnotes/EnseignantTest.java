package tn.encar.gestnotes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Note;

public class EnseignantTest {
static Note note1,note2,note3;
static Etudiant etd = new Etudiant();
	@Test
    void testCalculerPrixTotal() {
        Etudiant etd = new Etudiant();

        Note note1 = new Note();
        Note note2 = new Note();
        Note note3 = new Note();
        
        //etd.(produit1);
        //magasin.addProduct(produit2);
        //magasin.addProduct(produit3);

    }
	
	@BeforeAll
	static void init() {

		Note note1 = new Note();
        Note note2 = new Note();
        Note note3 = new Note();
	}
	@Test
	void testAddProduct() {
		etd.getNotes().add(note1);
		//etd.setNotes();
		//assertTrue(etd.getNotes().contains(note2));
	}
	@Test
	void testDeleteNote() {
		
	}
}