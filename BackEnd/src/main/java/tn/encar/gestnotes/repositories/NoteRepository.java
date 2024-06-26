package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}
