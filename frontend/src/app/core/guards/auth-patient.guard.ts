import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthPatientGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: any): boolean {
    const user = this.authService.currentUser;
    const expectedRole = route.data.role;

    if (user && user.role === "PATIENT") {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
