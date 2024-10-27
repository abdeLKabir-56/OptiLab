import { Component, OnInit } from '@angular/core';
import { LaboratoireService } from '../services/laboratoire.service';

interface Laboratoire {
  id: number;
  nom: string;
  logo: string;
  nrc: string;
  isActive: boolean;
  dateActivation: boolean;
}

@Component({
  selector: 'app-laboratoire',
  templateUrl: './laboratoire.component.html',
  styleUrls: ['./laboratoire.component.css']
})
export class LaboratoireComponent implements OnInit {
  laboratoires: Laboratoire[] = [];
  newLabo: Laboratoire = { id: 0, nom: '', logo: '', nrc: '', isActive: true, dateActivation: false };
  selectedLabo?: Laboratoire;

  constructor(private laboratoireService: LaboratoireService) {}

  ngOnInit(): void {
    this.loadLaboratories();
  }

  loadLaboratories(): void {
    this.laboratoireService.getAllLaboratories().subscribe(
      laboratoires => this.laboratoires = laboratoires,
      error => console.error('Error loading laboratories:', error)
    );
  }

  addLaboratory(): void {
    this.laboratoireService.createLaboratory(this.newLabo).subscribe(labo => {
      this.laboratoires.push(labo);
      this.newLabo = { id: 0, nom: '', logo: '', nrc: '', isActive: true, dateActivation: false };
    });
  }

  updateLaboratory(): void {
    if (this.selectedLabo) {
      this.laboratoireService.updateLaboratory(this.selectedLabo.id, this.selectedLabo).subscribe(() => {
        this.loadLaboratories();
        this.selectedLabo = undefined;
      });
    }
  }

  deleteLaboratory(id: number): void {
    this.laboratoireService.deleteLaboratory(id).subscribe(() => this.loadLaboratories());
  }

  selectLaboratory(labo: Laboratoire): void {
    this.selectedLabo = { ...labo };
  }
}
