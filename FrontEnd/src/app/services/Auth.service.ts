import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { loginRequestPayload } from "../payload/login-request-payload";
import { Observable } from "rxjs";
import { LoginResponsePayload } from "../payload/login-response.payload";
import { map } from 'rxjs/operators';
import { NbTokenLocalStorage } from "@nebular/auth";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  urlEtudiant = "http://localhost:8080/api/etudiant/";
  urlEnseignant = "http://localhost:8080/api/etudiant/";
  urlAdmin = "http://localhost:8080/api/admins/";
  a: string;
  theUserRole: string;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  private roleFormateur: string;
  private roleCandidat: string;
  private p: void;

  constructor(private http: HttpClient, private router: Router) {

  }

  login(loginRequestPayload: loginRequestPayload): Observable<boolean> {
    console.log("before ....")
    return this.http.post<LoginResponsePayload>(this.urlEtudiant + "signin", loginRequestPayload)
      .pipe(map(data => {

        console.log("heres")
        console.log(data);
        this.a = "ETUDIANT";
        localStorage.setItem('authenticationToken', data.token);
        localStorage.setItem('username', loginRequestPayload.email);
        localStorage.setItem('nomPrenom', data.prenom + " " + data.nom);
        localStorage.setItem('role', this.a);

        return true;
      }),
      );
  }

  loginAdmin(loginRequestPayload: loginRequestPayload): Observable<boolean> {
    console.log("before ....")
    return this.http.post<LoginResponsePayload>(this.urlAdmin + "signin", loginRequestPayload)
      .pipe(map(data => {

        console.log("heres")
        console.log(data);
        this.a = "ADMIN";
        localStorage.setItem('authenticationToken', data.token);
        localStorage.setItem('username', loginRequestPayload.email);
        localStorage.setItem('nomPrenom', data.prenom + " " + data.nom);
        localStorage.setItem('role', this.a);

        return true;
      }),
      );
  }

  loginEnseignant(loginRequestPayload: loginRequestPayload): Observable<boolean> {
    console.log("before ....")
    return this.http.post<LoginResponsePayload>(this.urlEtudiant + "signin", loginRequestPayload)
      .pipe(map(data => {

        console.log("heres")
        console.log(data);
        this.a = "ENSEIGNANT";
        localStorage.setItem('authenticationToken', data.token);
        localStorage.setItem('username', loginRequestPayload.email);
        localStorage.setItem('nomPrenom', data.prenom + " " + data.nom);
        localStorage.setItem('role', this.a);

        return true;
      }),
      );
  }
  getJwtToken() {
    return localStorage.getItem('authenticationToken');
  }
  getUserName() {
    return localStorage.getItem('nomPrenom');
  } res: boolean



  isLoggedInAdmin(): boolean {
    return (!!this.getJwtToken() && localStorage.getItem("role") == "ADMIN")

  }
  isLoggedInEtudiant(): boolean {
    return (!!this.getJwtToken() && localStorage.getItem("role") == "ETUDIANT")

  }
  isLoggedInEnseignant(): boolean {
    return (!!this.getJwtToken() && localStorage.getItem("role") == "ENSEIGNANT")

  }

  signupAdmin(loginRequestPayload: loginRequestPayload): Observable<boolean> {
    return this.http.post<LoginResponsePayload>(this.urlAdmin + "signup", loginRequestPayload)
      .pipe(map(data => {
        this.a = "ADMIN";
        localStorage.setItem('authenticationToken', data.token);
        localStorage.setItem('username', loginRequestPayload.email);
        localStorage.setItem('nomPrenom', data.prenom + " " + data.nom);
        localStorage.setItem('role', this.a);

        return true;
      }),
      );
  }

}
