package tn.encar.gestnotes.models.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("CC")
public class CC extends Note{
	
    @OneToOne
    private Evaluation evaluation;

    @OneToOne
    private DS ds;
}
