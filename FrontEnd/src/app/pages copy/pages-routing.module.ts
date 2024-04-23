import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ECommerceComponent } from './e-commerce/e-commerce.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { EnseignantsComponent } from './enseignants/enseignants.component';
import { EtudiantsComponent } from './etudiants/etudiants.component';
import { AuthAdminGuard } from '../services/auth_guard/auth-admin.guard';
import { ProfileComponent } from './profile/profile.component';
import { RegleService } from '../services/regle.service';
import { ReglementComponent } from './reglement/reglement.component';
import { AdminComponent } from './admin/admin.component';
import { MatiereComponent } from './matiere/matiere.component';
import { ClasseComponent } from './classe/classe.component';
import { DepartementComponent } from './departement/departement.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'iot-dashboard',
      component: DashboardComponent,
    },
    {
      path: 'dashboard',
      component: ECommerceComponent,canActivate : [AuthAdminGuard]
    },
    {
      path: 'home',
      component: HomeComponent,
    },
    {
      path: 'profile',
      component: ProfileComponent,canActivate : [AuthAdminGuard]
    },
    {
      path: 'reglement',
      component: ReglementComponent,
    },
    {
      path: 'admins',
      component: AdminComponent,
    },
    {
      path: 'matieres',
      component: MatiereComponent,
    },
    {
      path: 'classes',
      component: ClasseComponent,
    },
    {
      path: 'departements',
      component: DepartementComponent,
    },
    {
      path: 'enseignants',
      component: EnseignantsComponent,canActivate : [AuthAdminGuard]
    },
    {
      path: 'etudiants',
      component: EtudiantsComponent,canActivate : [AuthAdminGuard]
    },
    {
      path: 'contact',
      component: ContactComponent,
    },
    {
      path: 'layout',
      loadChildren: () => import('./layout/layout.module')
        .then(m => m.LayoutModule),
    },
    {
      path: 'forms',
      loadChildren: () => import('./forms/forms.module')
        .then(m => m.FormsModule),
    },
    {
      path: 'connect',
      loadChildren: () => import('./forms copy/forms.module')
        .then(m => m.FormsModule),
    },
    {
      path: 'ui-features',
      loadChildren: () => import('./ui-features/ui-features.module')
        .then(m => m.UiFeaturesModule),
    },
    {
      path: 'modal-overlays',
      loadChildren: () => import('./modal-overlays/modal-overlays.module')
        .then(m => m.ModalOverlaysModule),
    },
    {
      path: 'extra-components',
      loadChildren: () => import('./extra-components/extra-components.module')
        .then(m => m.ExtraComponentsModule),
    },
    {
      path: 'maps',
      loadChildren: () => import('./maps/maps.module')
        .then(m => m.MapsModule),
    },
    {
      path: 'charts',
      loadChildren: () => import('./charts/charts.module')
        .then(m => m.ChartsModule),
    },
    {
      path: 'tables',
      loadChildren: () => import('./tables/tables.module')
        .then(m => m.TablesModule),
    },
    {
      path: 'miscellaneous',
      loadChildren: () => import('./miscellaneous/miscellaneous.module')
        .then(m => m.MiscellaneousModule),
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [AuthAdminGuard] 
})
export class PagesRoutingModule {
}
