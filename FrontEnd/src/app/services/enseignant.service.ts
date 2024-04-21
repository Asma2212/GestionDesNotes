import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Enseignant } from '../models/Enseignant';
import { Statut } from '../models/Statut';

@Injectable({
  providedIn: 'root'
})
export class EnseignantService {

  url = 'http://localhost:8080/api/enseignants/';
  constructor(private http: HttpClient) { }

  getAllEnseignants(): Observable<Enseignant[]> {
    return this.http.get<Enseignant[]>(this.url + 'all');
  }

  saveEnseignant(enseignant: Enseignant): Observable<any> {
    switch (enseignant.position) {
      case "DIRECTEUR":
        enseignant.position = Statut.DIRECTEUR ;
        break;
        case "DIRECTEUR_DE_DEPARTMENT":
          enseignant.position = Statut.DIRECTEUR_DE_DEPARTMENT ;
        break;
        case "DIRECTEUR_DES_ETUDES":
          enseignant.position = Statut.DIRECTEUR_DES_ETUDES ;
        break;
        case "DIRECTEUR_DES_STAGES":
          enseignant.position = Statut.DIRECTEUR_DES_STAGES ;
        break;
      default:
        enseignant.position = Statut.NONE ;
        break;
    }

    return this.http.post<any>(this.url + 'signup', enseignant);
  }

  saveEnseignantByAdmin(enseignant: Enseignant): Observable<any> {
    switch (enseignant.position) {
      case "DIRECTEUR":
        enseignant.position = Statut.DIRECTEUR ;
        break;
        case "DIRECTEUR_DE_DEPARTMENT":
          enseignant.position = Statut.DIRECTEUR_DE_DEPARTMENT ;
        break;
        case "DIRECTEUR_DES_ETUDES":
          enseignant.position = Statut.DIRECTEUR_DES_ETUDES ;
        break;
        case "DIRECTEUR_DES_STAGES":
          enseignant.position = Statut.DIRECTEUR_DES_STAGES ;
        break;
      default:
        enseignant.position = Statut.NONE ;
        break;
    }
    return this.http.post<any>(this.url + 'saveEnsByAdmin', enseignant);
  }

  updateEnseignant(enseignant: Enseignant): Observable<any> {
    //const enseignantObject: Enseignant = JSON.parse(enseignant) as Enseignant;
    console.log("bbb"+JSON.stringify(enseignant));
    
    return this.http.post<any>(this.url + 'update', JSON.stringify(enseignant)
    );
  }

  deleteEnseignant(ide: number): Observable<any> {

    return this.http.delete<any>(this.url + 'delete/' + ide);
  }

  getEnseignantByEmail(u: string): Observable<Enseignant> {
    return this.http.get<Enseignant>(this.url + "get-email/" + u);
  }

  getEnseignantById(id: number): Observable<Enseignant> {
    return this.http.get<Enseignant>(this.url + "get/" + id);
  }

  updateEnseignantPassword(enseignant: Enseignant, anc: string, nouv: string): Observable<any> {

    return this.http.post<any>(this.url + 'updatePassword/' + anc + '/' + nouv, enseignant);
  }
  forgetPass(enseignant: Enseignant): Observable<any> {

    return this.http.post<any>(this.url + 'updatePass', enseignant);
  }
  envoyerCodeCandidat(enseignant: Enseignant): Observable<any> {

    return this.http.post<any>(this.url + 'code', enseignant);
  }

}