import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Matiere } from '../models/Matiere';

@Injectable({
  providedIn: 'root'
})
export class MatiereService {

  private url = 'http://localhost:8080/api/matiere/'; 

  constructor(private http: HttpClient) { }

  getAllMatieres(): Observable<Matiere[]> {
    return this.http.get<Matiere[]>(this.url+'all');
  }

  getMatiereById(id: number): Observable<Matiere> {
    return this.http.get<Matiere>(this.url+'get/'+id);
  }

  addNewMatiere(matiere: Matiere): Observable<any> {
    return this.http.post(this.url+'save', matiere);
  }

  updateMatiere(matiere: Matiere): Observable<any> {
    return this.http.put(this.url + 'update/' ,matiere);
  }

  deleteMatiereById(id: number): Observable<any> {
    return this.http.delete(this.url+'delete/'+id);
  }

}
