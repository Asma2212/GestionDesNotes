import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../Auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthEtudiantGuard implements CanActivate {
  constructor(private authService : AuthService,private router: Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      var isAuthenticated = this.authService.isLoggedInEtudiant();
      if(!isAuthenticated){
        this.router.navigate(['/etudiant/connect/login'])
      }
    return isAuthenticated;
  }
  
}
