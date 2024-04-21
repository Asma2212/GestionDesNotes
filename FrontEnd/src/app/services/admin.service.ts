import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../models/Admin';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  url = 'http://localhost:8080/api/admins/';
  constructor(private http: HttpClient) { }

  getAllAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.url + 'all');
  }

  saveAdmin(admin: Admin): Observable<any> {

    return this.http.post<any>(this.url + 'signup', admin);
  }
  updateAdmin(admin: Admin): Observable<any> {

    return this.http.post<any>(this.url + 'update/' + admin.id, admin);
  }

  deleteAdmin(ida: number): Observable<any> {

    return this.http.delete<any>(this.url + 'delete/' + ida);
  }

  getAdminByEmail(u: string): Observable<Admin> {
    return this.http.get<Admin>(this.url + "get-email/" + u);
  }

  getAdminById(id: number): Observable<Admin> {
    return this.http.get<Admin>(this.url + "get/" + id);
  }

  updateAdminPassword(admin: Admin, anc: string, nouv: string): Observable<any> {

    return this.http.post<any>(this.url + 'updatePassword/' + anc + '/' + nouv, admin);
  }
  forgetPass(admin: Admin): Observable<any> {

    return this.http.post<any>(this.url + 'updatePass', admin);
  }
  envoyerCodeCandidat(admin: Admin): Observable<any> {

    return this.http.post<any>(this.url + 'code', admin);
  }

}