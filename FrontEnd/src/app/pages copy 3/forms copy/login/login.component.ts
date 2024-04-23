import { Component } from '@angular/core';
import { loginRequestPayload } from '../../../payload/login-request-payload';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/Auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-form-login',
  styleUrls: ['./login.component.scss'],
  templateUrl: './login.component.html',
})
export class FormLoginComponent {
  loginForm: FormGroup;
  loginRequest : loginRequestPayload;
  isError: boolean;

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }
constructor(private formBuilder: FormBuilder,private authService: AuthService,private router: Router){
    this.loginRequest = {
    email : "",
    motDePasse : ""
  };
}

  login(){
    if (this.loginForm.valid) {
      this.loginRequest.email = this.loginForm.get('email').value;
      this.loginRequest.motDePasse = this.loginForm.get('password').value;
      this.authService.loginAdmin(this.loginRequest).subscribe(data => {
        this.isError = false;
       try {
        //this.dialogRef.close()
       } catch (error) {
        true
       }
        
        this.router.navigate(['/admin/dashboard']);
       // this.toast.success({detail:"Bienvenu "+this.signupForm.get('username').value+"!",duration:3000});
    
        }, error => {
          if(error.status===400){      //this.toast.error({summary: 'Verifier vos informations"',detail: "Erreur",duration:3000});
          }
    
        });
    }
  }
}
