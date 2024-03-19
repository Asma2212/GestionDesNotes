package tn.encar.gestnotes.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInEtudiantDTO {
	
    private String email;
    private String motDePasse;
}
