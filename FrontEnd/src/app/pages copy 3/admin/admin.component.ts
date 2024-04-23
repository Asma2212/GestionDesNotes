import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Admin } from '../../models/Admin';
import { Role } from '../../models/Role';
import { AdminRole } from '../../models/AdminRole';
import { NbDialogService } from '@nebular/theme';
import { DialogNamePromptComponent } from './dialog-name-prompt/dialog-name-prompt.component';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/Etudiant';

@Component({
  selector: 'ngx-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

    you: boolean = false;
    etudiants: Etudiant[] = [];
    etudiant : Etudiant = new Etudiant();
  constructor(private etudiantService:EtudiantService,private dialogService: NbDialogService) { }

  ngOnInit(): void {
    this.etudiantService.getAllEtudiants().subscribe(data => {
      this.etudiants = data;
    });
  }
  getRoleStyle(role:string){
    if(role == AdminRole.ADMIN_DE_CONTENU){
      return {
        'background-color': '#BA719C',
      };
  }else{
    return {
      'background-color': '#F2C75B',
    };
  }
}

open3() {
  this.dialogService.open(DialogNamePromptComponent)
    .onClose.subscribe(ad => {    
      this.etudiantService.saveEtudiant(ad).subscribe(data => {
        this.etudiants.push(data);
        this.etudiants = [...this.etudiants, data];
      });
    });
}
}
