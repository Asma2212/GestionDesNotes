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

  submit(email,ds,orale,examen) {
    console.log("role"+this.selectedRole);
  }
}
