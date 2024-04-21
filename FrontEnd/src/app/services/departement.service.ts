import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Departement } from '../models/Departement';
import { Classe } from '../models/Classe';
import { Regle } from '../models/Regle';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {


  private url = 'http://localhost:8080/api/departements/'; 

  constructor(private http: HttpClient) { }

  getAllDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(this.url+'all');
  }

  getDepartementById(id: number): Observable<Departement> {
    return this.http.get<Departement>(this.url+'get/'+id);
  }

  getDepartementByNom(nom: string): Observable<Departement[]> {
    return this.http.get<Departement[]>(this.url+'get/nom/'+nom);
  }

  getClassesByIdDepartement(id: number): Observable<Classe[]> {
    return this.http.get<Classe[]>(this.url+'get-classes/'+id);
  }

  getRegleByDepartementId(id: number): Observable<Regle> {
    return this.http.get<Regle>(this.url+'get-regle/'+id);
  }

  addNewDepartement(departement: Departement): Observable<any> {
    return this.http.post(this.url+'add', departement);
  }

  updateDepartement(id: number, nom: string): Observable<any> {
    return this.http.put(this.url + 'update/' + id + '?nom=' + nom, {});
  }

  deleteDepartementById(id: number): Observable<any> {
    return this.http.delete(this.url+'delete/'+id);
  }

  countDepartement(): Observable<number> {
    return this.http.get<number>(this.url+'count');
  }

  countClassesOfDepartementById(id: number): Observable<number> {
    return this.http.get<number>(this.url+id+'/count-classes');
  }

  assignClasseToDepartement(departementId: number, classeId: number): Observable<Departement> {
    return this.http.post<Departement>(this.url+departementId+'/classe/'+classeId, {});
  }

  removeClasseFromDepartement(departementId: number, classeId: number): Observable<Departement> {
    return this.http.delete<Departement>(this.url+departementId+'/classe/'+classeId);
  }
}
