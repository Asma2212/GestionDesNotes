import { Component, OnInit } from '@angular/core';
import { Admin } from '../../models/Admin';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'ngx-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  admin : Admin = new Admin();
  email : string;
  oldPassword : string = "";
  accPassword : string = "";
  confirmePassword  : string = "";
  constructor(private adminService : AdminService){}

  ngOnInit(): void {
    console.log("profile");
    
    this.email = localStorage.getItem("username");
    console.log(this.email);
    this.adminService.getAdminByEmail(this.email).subscribe(data => {
      console.log("data"+data);
      this.admin = data;
    });
  }

  deconnexion(){
    localStorage.clear();
    location.replace("/admin/connect/login");
  }

  passwordError(msg : string): void {
    // Cette méthode est appelée si le mot de passe actuel est incorrect
    // Vous pouvez afficher un toast ou une alerte ici pour informer l'utilisateur
  }

  onSubmit(): void {
    if (this.oldPassword !== this.accPassword) {
      this.passwordError("nouvelle mot de passe identique à l'ancienne"); 
      return; 
    }
    if (this.accPassword !== this.confirmePassword) {
      this.passwordError("Cofirmation de mot de passe incorrecte"); 
      return; 
    }
    if(this.accPassword!="" && this.oldPassword!="" && this.confirmePassword!=""){
    this.adminService.updateAdminPassword(this.admin,this.oldPassword,this.accPassword).subscribe(result => {
      console.log('Admin updated successfully', result);
      
    }, error => {
      console.log(error);
      this.passwordError(error.error.message);
      
    });
  }
  console.log("admin"+this.admin);
  this.adminService.updateAdmin(this.admin).subscribe(result => {
    console.log('Admin updated successfully', result);
    location.replace("/admin/profile"); 
  });
  }
}

