import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  NbAuthComponent,
  NbLoginComponent,
  NbLogoutComponent,
  NbRegisterComponent,
  NbRequestPasswordComponent,
  NbResetPasswordComponent,
} from '@nebular/auth';
import { FormLoginComponent } from './pages/forms copy/login/login.component';
import { FormRegisterComponent } from './pages/forms copy/register/register.component';
import { AuthAdminGuard } from './services/auth_guard/auth-admin.guard';
import { AuthEtudiantGuard } from './services/auth_guard/auth-etudiant.guard';
import { AuthEnseignantGuard } from './services/auth_guard/auth-enseignant.guard';

export const routes: Routes = [
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module')
      .then(m => m.PagesModule),
  },
  {
    path: 'admin',
    loadChildren: () => import('./pages copy/pages.module')
      .then(m => m.PagesModule),
  },
  {
    path: 'auth',
    component: NbAuthComponent,
    children: [
      {
        path: '',
        component: NbLoginComponent,
      },
      {
        path: 'login',
        component: FormLoginComponent,
      },
      {
        path: 'register',
        component: FormRegisterComponent,
      },
      {
        path: 'logout',
        component: NbLogoutComponent,
      },
      {
        path: 'request-password',
        component: NbRequestPasswordComponent,
      },
      {
        path: 'reset-password',
        component: NbResetPasswordComponent,
      },
    ],
  },
  { path: '', redirectTo: 'pages', pathMatch: 'full' },
  { path: '**', redirectTo: 'pages' },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
  providers: [AuthAdminGuard,AuthEtudiantGuard,AuthEnseignantGuard] 
})
export class AppRoutingModule {
}
