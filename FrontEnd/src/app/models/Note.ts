import { Etudiant } from "./Etudiant";
import { Matiere } from "./Matiere";

export class Note {
    idNote: number;
    note: number;
    semestre: number;
    date: Date;
    type: string;
    etudiant: Etudiant;
    matiere: Matiere;
}
