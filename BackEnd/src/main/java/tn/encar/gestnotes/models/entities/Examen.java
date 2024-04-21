package tn.encar.gestnotes.models.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.encar.gestnotes.models.enums.Session;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@DiscriminatorValue("Examen")
public class Examen extends Note{
	@Enumerated(EnumType.STRING)
	private Session session;
}
