import { NgModule } from '@angular/core';
import { NbCardModule, NbIconModule, NbInputModule, NbMenuModule, NbTreeGridModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { ECommerceModule } from './e-commerce/e-commerce.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { ContactComponent } from './contact/contact.component';
import { EnseignantsComponent } from './enseignants/enseignants.component';
import { TablesRoutingModule } from './tables/tables-routing.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { EtudiantsComponent} from './etudiants/etudiants.component';
import { ProfileComponent } from './profile/profile.component';

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
    Ng2SmartTableModule
    
  ],
  declarations: [
    PagesComponent,
    HomeComponent,
    ContactComponent,
    EnseignantsComponent,
    EtudiantsComponent,
    ProfileComponent
  ],
})
export class PagesModule {
}
