import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';


@Component({
  selector: 'app-medical-folder',
  imports: [CardModule, ButtonModule,NgFor],
  templateUrl: './medical-folder.component.html',
  styleUrl: './medical-folder.component.css'
})
export class MedicalFolderComponent {
  numbers = Array.from({ length: 11 }, (_, i) => i);
}
