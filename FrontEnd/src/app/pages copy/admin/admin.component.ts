import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Admin } from '../../models/Admin';
import { Role } from '../../models/Role';
import { AdminRole } from '../../models/AdminRole';
import { NbDialogService } from '@nebular/theme';
import { DialogNamePromptComponent } from './dialog-name-prompt/dialog-name-prompt.component';

@Component({
  selector: 'ngx-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

    you: boolean = false;
    admins: Admin[] = [];
    admin : Admin = new Admin();
  constructor(private adminService:AdminService,private dialogService: NbDialogService) { }

  ngOnInit(): void {
    this.adminService.getAllAdmins().subscribe(data => {
      this.admins = data;
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
      this.adminService.saveAdminByAdmin(ad).subscribe(data => {
        this.admins.push(data);
        this.admins = [...this.admins, data];
      });
    });
}
}
