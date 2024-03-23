package tn.encar.gestnotes.models.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class DS {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idDS;
	 
	 @Transient
	 private Date dateDS;

}
