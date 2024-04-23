import { Component } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Admin } from '../../../models/Admin';
import { AdminRole } from '../../../models/AdminRole';

@Component({
  selector: 'ngx-dialog-name-prompt',
  templateUrl: 'dialog-departement.component.html',
  styleUrls: ['dialog-name-prompt.component.scss'],
})
export class DialogDepartementComponent {

  constructor(protected ref: NbDialogRef<DialogDepartementComponent>) {
    
  }
admin:Admin = new Admin();
selectedRole: string;
  cancel() {
    this.ref.close();
  }

  submit(nom) {
    this.ref.close(nom);
  }
}
