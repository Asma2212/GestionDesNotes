import { Component, OnDestroy, OnInit } from '@angular/core';
import { NbMediaBreakpointsService, NbMenuService, NbSidebarService, NbThemeService } from '@nebular/theme';

import { UserData } from '../../../@core/data/users';
import { LayoutService } from '../../../@core/utils';
import { map, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/Auth.service';

@Component({
  selector: 'ngx-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();
  userPictureOnly: boolean = false;
  user: any;
  email : string;
  nomPrenom : string = "Anonyme";
  auth:Boolean=false;
  role : string;

  themes = [
    {
      value: 'default',
      name: 'éclairer',
    },
    {
      value: 'dark',
      name: 'sombre',
    },
    {
      value: 'cosmic',
      name: 'cosmetique',
    },
    {
      value: 'corporate',
      name: 'corporatif',
    },
  ];

  currentTheme = 'default';

  userMenu = [ { title: 'Profile' }, { title: 'Deconnexion' } ];

  constructor(private sidebarService: NbSidebarService,
              private menuService: NbMenuService,
              private themeService: NbThemeService,
              private userService: UserData,
              private layoutService: LayoutService,
              private breakpointService: NbMediaBreakpointsService,
              private router: Router,
              private authService : AuthService) {
  }

  ngOnInit() {
    if(this.authService.isLoggedInAdmin()|| this.authService.isLoggedInEnseignant()||this.authService.isLoggedInEtudiant()) 
      {
        this.auth = true;
        this.role = localStorage.getItem("role");
      }
      if(this.auth){
    this.email = localStorage.getItem("username");
    this.nomPrenom = localStorage.getItem("nomPrenom");
    this.currentTheme = this.themeService.currentTheme;
      }
    this.userService.getUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe((users: any) => this.user = users.nick);

    const { xl } = this.breakpointService.getBreakpointsMap();
    this.themeService.onMediaQueryChange()
      .pipe(
        map(([, currentBreakpoint]) => currentBreakpoint.width < xl),
        takeUntil(this.destroy$),
      )
      .subscribe((isLessThanXl: boolean) => this.userPictureOnly = isLessThanXl);

    this.themeService.onThemeChange()
      .pipe(
        map(({ name }) => name),
        takeUntil(this.destroy$),
      )
      .subscribe(themeName => this.currentTheme = themeName);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  changeTheme(themeName: string) {
    this.themeService.changeTheme(themeName);
  }

  toggleSidebar(): boolean {
    this.sidebarService.toggle(true, 'menu-sidebar');
    this.layoutService.changeLayoutSize();

    return false;
  }

  navigateHome() {
    this.menuService.navigateHome();
    return false;
  }

  connecter(){
    
    this.router.navigate(['/pages/connect/login']);
  }
  redirectContact(){
    if(this.auth && this.role=="ADMIN"){
      this.router.navigate(['/admin/contact']);
    }else
    if(this.auth && this.role=="ENSEIGNANT"){
      this.router.navigate(['/enseignant/contact']);
    }else
    this.router.navigate(['/pages/contact']);
  }
  redirectAccueil(){
    if(this.auth && this.role=="ADMIN"){
      this.router.navigate(['/admin/home']);
    }else
    if(this.auth && this.role=="ENSEIGNANT"){
      this.router.navigate(['/enseignant/home']);
    }else
    this.router.navigate(['/pages/home']);
  }
  redirectNotes(){
    if(this.auth && this.role=="ADMIN"){
      this.router.navigate(['/admin/note']);
    }else
    if(this.auth && this.role=="ENSEIGNANT"){
      this.router.navigate(['/enseignant/note']);
    }else
    this.router.navigate(['/etudiant/note']);
  }
  redirectResultat(){
    
    this.router.navigate(['/etudiant/resultat']);
  }
  redirectReglement(){
    
    this.router.navigate(['/pages/reglement']);
  }
  redirectProfile(){
    if(this.auth && this.role=="ADMIN"){
      this.router.navigate(['/admin/profile']);
    }else
    if(this.auth && this.role=="ENSEIGNANT"){
      this.router.navigate(['/enseignant/profile']);
    }else
    this.router.navigate(['/pages/profile']);
  }

  deconnecter(){
    localStorage.clear();
    location.replace("/pages/profile");
  }

}
