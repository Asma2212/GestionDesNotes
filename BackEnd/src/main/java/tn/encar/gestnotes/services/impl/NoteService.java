package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Note;
import tn.encar.gestnotes.repositories.NoteRepository;
import tn.encar.gestnotes.services.I_NoteService;

@Service
public class NoteService implements I_NoteService{
	
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}

	@Override
	public Note getNoteById(Long id) {
		return noteRepository.findById(id).orElse(null);
	}

	@Override
	public List<Note> getAllNote() {
		return noteRepository.findAll();
	}

	@Override
	public void deleteNoteById(Long id) {
		noteRepository.deleteById(id);
	}

}
