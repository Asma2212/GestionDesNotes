import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Classe } from '../models/Classe';
import { Departement } from '../models/Departement';

@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  url = 'http://localhost:8080/api/classes/';
  constructor(private http: HttpClient) { }

  getAllClasses(): Observable<Classe[]> {
    return this.http.get<Classe[]>(this.url + 'all');
  }

  getClassById(id: number): Observable<Classe> {
    return this.http.get<Classe>(this.url+'get/'+id);
  }

  getClassesByNiveau(niveau: number): Observable<Classe[]> {
    return this.http.get<Classe[]>(this.url+'niveau/'+niveau);
  }

  getClassesByIdEnseignant(id: number): Observable<Classe[]> {
    return this.http.get<Classe[]>(this.url+'enseignant/'+id);
  }

  getDepartementByClasseId(classeId: number): Observable<Departement> {
    return this.http.get<Departement>(this.url+classeId+'/departement');
  }

  saveClasse(classe: Classe): Observable<any> {
    return this.http.post(this.url+'add', classe);
  }

  deleteClasseById(id: number): Observable<any> {
    return this.http.delete(this.url+'delete/'+id);
  }

  countClasseByNiveau(niveau: number): Observable<number> {
    return this.http.get<number>(this.url+'count/niveau/'+niveau);
  }

}