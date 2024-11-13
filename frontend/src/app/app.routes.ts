import { Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LandingComponent } from './pages/landing/landing.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LaboratoireComponent } from './laboratoire/laboratoire/laboratoire.component';
import { AuthGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: LandingComponent, title: 'OptiLab - Home' },
  { path: 'laboratoires', component: LaboratoireComponent, title: 'Laboratories Management' },
  { path: 'dashboard', component: DashboardComponent , canActivate: [AuthGuard]},
  
];
