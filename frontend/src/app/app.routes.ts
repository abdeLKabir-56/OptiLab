import { Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LandingComponent } from './pages/landing/landing.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LaboratoireComponent } from './laboratoire/laboratoire/laboratoire.component';
import { AuthGuard } from './core/guards/auth.guard';
import { MainDashboardComponent } from './pages/main-dashboard/main-dashboard.component';
import { LabosComponent } from './pages/labos/labos.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { UserPortalComponent } from './pages/user-portal/user-portal.component';

export const routes: Routes = [
  {
    path: '',
    component: AppComponent, // Main layout
    children: [
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: 'home', component: LandingComponent, title: 'OptiLab - Home' },
      { path: 'laboratoires', component: LaboratoireComponent, title: 'Laboratories Management'},
    ]
  },
  {
    path: 'dashboard',
    component: DashboardComponent, // Separate layout
    canActivate: [AuthGuard],
    title: 'Dashboard',
    children: [
      { path: '', component: MainDashboardComponent, title: 'OptiLab - Home' },
      { path: 'labos', component: LabosComponent, title: 'OptiLab - Home' }    ]
  },
  {
    path: 'user',
    component: UserPortalComponent, // Separate layout
    canActivate: [AuthGuard],
    title: 'user',
    children: [
      { path: '', redirectTo: '/user/profile', pathMatch: 'full' },
     { path: 'profile', component: ProfileComponent, title: 'OptiLab - Home' },
    ]
  },
  { path: '**', redirectTo: '/home' }, // Fallback for unknown routes
];
