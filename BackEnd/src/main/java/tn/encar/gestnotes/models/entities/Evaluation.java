package tn.encar.gestnotes.models.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Evaluation {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idEvaluation;
	 
	 @Transient
	 private Date dateDS;
}
