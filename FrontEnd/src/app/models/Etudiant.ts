import { Absence } from "./Absence";
import { Classe } from "./Classe";
import { Note } from "./Note";
import { Personne } from "./Personne";
import { TypeFormation } from "./TypeFormation";

export class Etudiant extends Personne{

    numInscri: number;
    classe: Classe;
    typeFormation: TypeFormation;
    absences: Absence[];
    notes: Note[];

}
