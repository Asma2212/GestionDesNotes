import { Component, OnInit } from '@angular/core';
import { Admin } from '../../models/Admin';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'ngx-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  admin : Admin;
  email : string
  constructor(private adminService : AdminService){}

  ngOnInit(): void {
    this.email = localStorage.getItem("username");
    this.adminService.getAdminByEmail(this.email).subscribe(data => {
      console.log(data);
      this.admin = data;
    });
  }

  deconnexion(){
    localStorage.clear();
    location.replace("/admin/connect/login");
  }

}
