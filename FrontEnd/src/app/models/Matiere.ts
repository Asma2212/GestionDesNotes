import { Absence } from "./Absence";
import { Note } from "./Note";

export class Matiere {
    idMatiere: number;
    nomMatiere: string;
    coeff: number;
    absences: Absence[];
    notes: Note[];
}
