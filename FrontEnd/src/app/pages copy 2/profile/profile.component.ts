import { Component, OnInit } from '@angular/core';
import { Admin } from '../../models/Admin';
import { AdminService } from '../../services/admin.service';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/Etudiant';

@Component({
  selector: 'ngx-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  etudiant : Etudiant = new Etudiant();
  email : string;
  oldPassword : string = "";
  accPassword : string = "";
  confirmePassword  : string = "";
  constructor(private etudiantService : EtudiantService){}

  ngOnInit(): void {
    console.log("profile");
    
    this.email = localStorage.getItem("username");
    console.log(this.email);
    this.etudiantService.getEtudiantByEmail(this.email).subscribe(data => {
      console.log("data"+data);
      this.etudiant = data;
    });
  }

  deconnexion(){
    localStorage.clear();
    location.replace("/etudiant/connect/login");
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
    this.etudiantService.updateEtudiantPassword(this.etudiant,this.oldPassword,this.accPassword).subscribe(result => {
      console.log('etudiant updated successfully', result);
      
    }, error => {
      console.log(error);
      this.passwordError(error.error.message);
      
    });
  }
  console.log("etudiant"+this.etudiant);
  this.etudiantService.updateEtudiant(this.etudiant).subscribe(result => {
    console.log('etudiant updated successfully', result);
    location.replace("/etudiant/profile"); 
  });
  }
}

