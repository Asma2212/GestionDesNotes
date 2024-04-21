import { Role } from "./Role";

export class Personne{
	id: number;
    cin: number;
    nom: string;
    prenom: string;
    genre:string;
    tel: number;
    dateNaiss: Date;
    email: string;
    motDePasse: string;
    role: Role;

}
