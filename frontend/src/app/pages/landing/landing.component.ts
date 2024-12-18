import { LoginComponent } from './../../components/login/login.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [LoginComponent],
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.css'
})
export class LandingComponent {
  isOpenModal=false
  constructor(private router: Router) {}
 toggleModal(){
  this.isOpenModal=!this.isOpenModal
 }
  goToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }
  goToLabo(): void {
    this.router.navigate(['/laboratories']);
  }
}
