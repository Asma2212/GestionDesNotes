import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NbDateService } from '@nebular/theme';
import { AuthService } from '../../../services/Auth.service';
import { Admin } from '../../../models/Admin';
import { Role } from '../../../models/Role';

@Component({
  selector: 'ngx-form-register',
  styleUrls: ['./register.component.scss'],
  templateUrl: './register.component.html',
})
export class FormRegisterComponent {

  starRate = 2;
  heartRate = 4;
  radioGroupValue = 'This is value 2';
  radioGenre
  radioRole
  min: Date;
  max: Date;
  signupForm: FormGroup;
  admin: Admin;

  constructor(private formBuilder: FormBuilder, private adminService: AuthService, protected dateService: NbDateService<Date>) {
   this.min = this.dateService.addDay(this.dateService.today(), -(365*110));
    this.max = this.dateService.addDay(this.dateService.today(), -(365*21));
    this.signupForm = this.formBuilder.group({
      cin: ['', Validators.required],
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      tel: ['', Validators.required],
      dateNaiss: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      genre: [''],
      role: ['']
    });
  }
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password').value;
    const confirmPassword = formGroup.get('confirmPassword').value;
    if (password !== confirmPassword) {
      formGroup.get('confirmPassword').setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword').setErrors(null);
    }
  }

  signup() {
    if(this.signupForm.invalid)
      return ;
      this.admin = {
        id:0,
        cin: this.signupForm.get('cin').value,
        prenom: this.signupForm.get('prenom').value,
        nom: this.signupForm.get('nom').value,
        tel: this.signupForm.get('tel').value,
        dateNaiss: this.signupForm.get('dateNaiss').value,
        email: this.signupForm.get('email').value,
        motDePasse: this.signupForm.get('password').value,
        genre: this.signupForm.get('genre').value,
        role: Role.ADMIN,
        roleAdmin: this.signupForm.get('role').value
      };

      // Appel de la méthode de service pour enregistrer l'administrateur
      this.adminService.signupAdmin(this.admin).subscribe(response => {
          console.log('Admin enregistré avec succès !', response);
          this.signupForm.reset();
        },
        error => {
          console.error('Erreur lors de l\'enregistrement de l\'admin :', error);
        }
      );
  }

}