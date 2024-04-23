import { NgModule } from '@angular/core';
import { NbAccordionModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDialogModule, NbIconModule, NbInputModule, NbListModule, NbMenuModule, NbPopoverModule, NbRadioModule, NbRouteTabsetModule, NbSelectModule, NbStepperModule, NbTabsetModule, NbTooltipModule, NbTreeGridModule, NbUserModule, NbWindowModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { ECommerceModule } from './e-commerce/e-commerce.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ContactComponent } from './contact/contact.component';
import { EnseignantsComponent } from './enseignants/enseignants.component';
import { TablesRoutingModule } from './tables/tables-routing.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { EtudiantsComponent} from './etudiants/etudiants.component';
import { ProfileComponent } from './profile/profile.component';
import { ReglementComponent } from './reglement/reglement.component';
import { LayoutRoutingModule } from './layout/layout-routing.module';
import { DepartementComponent } from './departement/departement.component';
import { ClasseComponent } from './classe/classe.component';
import { MatiereComponent } from './matiere/matiere.component';
import { AdminComponent } from './admin/admin.component';
import { DialogNamePromptComponent } from './admin/dialog-name-prompt/dialog-name-prompt.component';
import { DialogDepartementComponent } from './departement/dialog-departement/dialog-departement.component';

@NgModule({
  imports: [
    FormsModule,
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    ECommerceModule,
    MiscellaneousModule,
    NbCardModule,
    NbTreeGridModule,
    NbIconModule,
    NbInputModule,
    TablesRoutingModule,
    Ng2SmartTableModule,
    NbStepperModule,
    ReactiveFormsModule,
    NbTabsetModule,
    NbRouteTabsetModule,
    NbButtonModule,
    NbListModule,
    NbAccordionModule,
    NbUserModule,
    LayoutRoutingModule,
    NbButtonModule,
    NbCardModule,
    NbCheckboxModule,
    NbDialogModule,
    NbInputModule,
    NbPopoverModule,
    NbSelectModule,
    NbTooltipModule,
    NbWindowModule,
    NbRadioModule
    
  ],
  declarations: [
    PagesComponent,
    HomeComponent,
    ContactComponent,
    EnseignantsComponent,
    EtudiantsComponent,
    ProfileComponent,
    ReglementComponent,
    DepartementComponent,
    ClasseComponent,
    MatiereComponent,
    AdminComponent,
    DialogNamePromptComponent,
    DialogDepartementComponent
  ],
})
export class PagesModule {
}
