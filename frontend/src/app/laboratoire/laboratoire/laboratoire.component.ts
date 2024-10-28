import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LaboratoireService, Laboratoire } from '../services/laboratoire.service';

@Component({
  selector: 'app-laboratoire',
  templateUrl: './laboratoire.component.html',
  styleUrls: ['./laboratoire.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class LaboratoireComponent implements OnInit {
  laboratoires: Laboratoire[] = [];
  newLabo: Laboratoire = {
    id: 0,
    nom: '',
    logo: '',
    nrc: '',
    isActive: true,
    dateActivation: new Date()
  };
  selectedLabo?: Laboratoire;
  loading: boolean = false;

  constructor(private laboratoireService: LaboratoireService) {}

  ngOnInit(): void {
    this.loadLaboratories();
  }

  loadLaboratories(): void {
    this.loading = true;
    this.laboratoireService.getAllLaboratories().subscribe({
      next: (laboratoires) => {
        this.laboratoires = laboratoires;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading laboratories:', error);
        alert('Failed to load laboratories.');
        this.loading = false;
      }
    });
  }

  addLaboratory(): void {
    this.laboratoireService.createLaboratory(this.newLabo).subscribe({
      next: (labo) => {
        this.laboratoires.push(labo);
        this.newLabo = {
          id: 0,
          nom: '',
          logo: '',
          nrc: '',
          isActive: true,
          dateActivation: new Date()
        };
        this.loadLaboratories();
      },
      error: (error) => {
        console.error('Error creating laboratory:', error);
        alert('Failed to create laboratory.');
      }
    });
  }

  updateLaboratory(): void {
    if (this.selectedLabo) {
      this.laboratoireService.updateLaboratory(this.selectedLabo.id, this.selectedLabo).subscribe({
        next: () => {
          this.loadLaboratories();
          this.selectedLabo = undefined;
        },
        error: (error) => {
          console.error('Error updating laboratory:', error);
          alert('Failed to update laboratory.');
        }
      });
    }
  }

  deleteLaboratory(id: number): void {
    if (confirm('Are you sure you want to delete this laboratory?')) {
      this.laboratoireService.deleteLaboratory(id).subscribe({
        next: () => {
          this.loadLaboratories();
        },
        error: (error) => {
          console.error('Error deleting laboratory:', error);
          alert('Failed to delete laboratory.');
        }
      });
    }
  }

  selectLaboratory(labo: Laboratoire): void {
    this.selectedLabo = { ...labo };
  }
}