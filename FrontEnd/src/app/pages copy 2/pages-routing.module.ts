import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { EnseignantsComponent } from './enseignants/enseignants.component';
import { AuthAdminGuard } from '../services/auth_guard/auth-admin.guard';
import { ProfileComponent } from './profile/profile.component';
import { RegleService } from '../services/regle.service';
import { ReglementComponent } from './reglement/reglement.component';
import { MatiereComponent } from './matiere/matiere.component';
import { ClasseComponent } from './classe/classe.component';
import { DepartementComponent } from './departement/departement.component';
import { ResultatComponent } from './resultat/resultat.component';
import { AuthEtudiantGuard } from '../services/auth_guard/auth-etudiant.guard';
import { NoteComponent } from './note/note.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'home',
      component: HomeComponent,
    },
    {
      path: 'profile',
      component: ProfileComponent,canActivate : [AuthEtudiantGuard]
    },
    {
      path: 'resultat',
      component: ResultatComponent,canActivate : [AuthEtudiantGuard]
    },
    {
      path: 'note',
      component: NoteComponent,canActivate : [AuthEtudiantGuard]
    },
    {
      path: 'reglement',
      component: ReglementComponent,
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
