import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Etudiant } from '../models/Etudiant';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  url = 'http://localhost:8080/api/etudiant/';
  constructor(private http: HttpClient) { }

  getAllEtudiants(): Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(this.url + 'all');
  }

  getAllEtudiantsByClasse(id: number): Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(this.url + 'allByClasse/' + id);
  }

  saveEtudiant(etudiant: Etudiant): Observable<any> {

    return this.http.post<any>(this.url + 'signup', etudiant);
  }
  updateEtudiant(etudiant: Etudiant): Observable<any> {

    return this.http.post<any>(this.url + 'update/' + etudiant.id, etudiant);
  }

  deleteEtudiant(ida: number): Observable<any> {

    return this.http.delete<any>(this.url + 'delete/' + ida);
  }

  getEtudiantByEmail(u: string): Observable<Etudiant> {
    return this.http.get<Etudiant>(this.url + "get-email/" + u);
  }

  getEtudiantById(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(this.url + "get/" + id);
  }

  updateEtudiantPassword(etudiant: Etudiant, anc: string, nouv: string): Observable<any> {

    return this.http.post<any>(this.url + 'updatePassword/' + anc + '/' + nouv, etudiant);
  }
  forgetPass(etudiant: Etudiant): Observable<any> {

    return this.http.post<any>(this.url + 'updatePass', etudiant);
  }
  envoyerCodeCandidat(etudiant: Etudiant): Observable<any> {

    return this.http.post<any>(this.url + 'code', etudiant);
  }

}