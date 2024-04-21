import { Classe } from "./Classe";
import { Personne } from "./Personne";
import { Statut } from "./Statut";

export class Enseignant extends Personne {
    position: Statut;
    classesAffectees: Classe[];
	
}
