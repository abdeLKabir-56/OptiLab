import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from '../../services/auth/auth-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {  Router } from '@angular/router';

@Component({
    selector: 'app-login',
    imports: [NgIf ,ReactiveFormsModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
    providers:[AuthService]
})
export class LoginComponent {
  @Input() isOpen!:boolean
  @Output() closeModal=new EventEmitter()

  loginForm:FormGroup
  constructor(private fb:FormBuilder , private auth: AuthService,private router: Router){
    this.loginForm=this.fb.group({
      email:['',[Validators.required , Validators.email]],
      password:['',[Validators.required ,Validators.minLength(6)]],
    }) //validators must be in an array if theres more than one
  }

  onClose(){
    this.closeModal.emit()
  }

  
  onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
  
      // Call the AuthService login method
      this.auth.login(email, password).subscribe({
        next: (user) => {
          if (user) {
            // Close the modal and perform necessary actions (e.g., navigate or show a success message)
            this.closeModal.emit();
            console.log(`Welcome, ${user.username}!`);
            // Redirect to the user's dashboard or relevant page based on their role
            if (user.role === 'PATIENT') {
              // Redirect to patient dashboard (e.g., using a Router)
              this.router.navigate(['/user'])
            } else if (user.role === 'TECHNICIEN') {
              this.router.navigate(['/dashboard'])
            } else if (user.role === 'ADMIN') {
              this.router.navigate(['/dashboard'])
            }
          } else {
            console.error('Invalid credentials!');
          }
        },
        error: (err) => {
          console.error('Error during login:', err);
        },
      });
    } else {
      // Show validation errors
      console.error('Form is invalid! Please fill in all required fields correctly.');
    }
  }

}
