package tn.encar.gestnotes.models.entities;

import java.sql.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("Examen")
public class Examen extends Note{
	
	@Transient
	private Date dateExamen ;
}
