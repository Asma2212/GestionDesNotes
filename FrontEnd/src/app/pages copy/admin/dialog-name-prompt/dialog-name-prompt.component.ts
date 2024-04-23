import { Component } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Admin } from '../../../models/Admin';
import { AdminRole } from '../../../models/AdminRole';

@Component({
  selector: 'ngx-dialog-name-prompt',
  templateUrl: 'dialog-name-prompt.component.html',
  styleUrls: ['dialog-name-prompt.component.scss'],
})
export class DialogNamePromptComponent {

  constructor(protected ref: NbDialogRef<DialogNamePromptComponent>) {
    
  }
admin:Admin = new Admin();
selectedRole: string;
  cancel() {
    this.ref.close();
  }

  submit(email,nom,prenom) {
    console.log("role"+this.selectedRole);
    this.admin.email = email;
    this.admin.nom = nom;
    this.admin.prenom = prenom;
    if(this.selectedRole == "ADMIN_DE_CONTENU"){
      this.admin.roleAdmin = AdminRole.ADMIN_DE_CONTENU;
    }
    else
      this.admin.roleAdmin = AdminRole.SUPER_ADMIN;
    this.ref.close(this.admin);
  }
}
