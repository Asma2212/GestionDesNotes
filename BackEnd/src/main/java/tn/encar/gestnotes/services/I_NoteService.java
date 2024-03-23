package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Note;

public interface I_NoteService {
    Note saveNote(Note note);
    Note getNoteById(Long id);
    List<Note> getAllNote();
    void deleteNoteById(Long id);
}
