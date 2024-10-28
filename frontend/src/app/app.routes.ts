import { Routes } from '@angular/router';
import { LaboratoireComponent } from '../app/laboratoire/laboratoire/laboratoire.component';

export const routes: Routes = [
  { 
    path: 'laboratories', 
    component: LaboratoireComponent,
    title: 'Laboratories Management' 
  },
  { 
    path: '', 
    redirectTo: '/', 
    pathMatch: 'full',
    title: 'OptiLab - Home'
  },
  { 
    path: '**', 
    redirectTo: '/' 
  }
];