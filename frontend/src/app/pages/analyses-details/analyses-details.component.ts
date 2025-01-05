import { ActivatedRoute } from '@angular/router';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { Dialog } from 'primeng/dialog';
import { Ripple } from 'primeng/ripple';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { ConfirmDialog } from 'primeng/confirmdialog';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { CommonModule } from '@angular/common';
import { FileUpload } from 'primeng/fileupload';
import { SelectModule } from 'primeng/select';
import { Tag } from 'primeng/tag';
import { RadioButton } from 'primeng/radiobutton';
import { Rating } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { InputNumber } from 'primeng/inputnumber';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { Table } from 'primeng/table';
import { Analyse } from '../../types/analysis-admin';
import { DropdownModule } from 'primeng/dropdown';
import { AnalysesService } from '../../services/admin/analyses.service';
import { RouterModule } from '@angular/router';
import { Epreuve } from '../../types/analysis';


@Component({
  selector: 'app-analyses-details',
  imports: [TableModule,
    Dialog,
    SelectModule,
    ToastModule,
    ToolbarModule,
    ConfirmDialog,
    InputTextModule,
    TextareaModule,
    CommonModule,
    FileUpload,
    DropdownModule,
    Tag,
    RadioButton,
    Rating,
    InputTextModule,
    FormsModule,
    InputNumber,
    IconFieldModule,
    InputIconModule,
    ButtonModule,
  RouterModule],
  templateUrl: './analyses-details.component.html',
  styleUrl: './analyses-details.component.css',
  providers:[AnalysesService ,MessageService , ConfirmationService]
})
export class AnalysesDetailsComponent {
 id = Number(this.router.snapshot.paramMap.get('id'));
analyse:Analyse={} as Analyse
epreuves: Epreuve[] = [];
  epreuveDialog: boolean = false;
  deleteEpreuveDialog: boolean = false;
  deleteEpreuvesDialog: boolean = false;
  epreuve!: Epreuve ;
  selectedEpreuves: Epreuve[] | null = [];
  submitted: boolean = false;
  cols: any[] = [];
  exportColumns: any[] = [];
  @ViewChild('dt') dt!: Table;
constructor(private router:ActivatedRoute  , private analyseService:AnalysesService , private messageService: MessageService,
  private confirmationService: ConfirmationService){}

ngOnInit() {
  
  this.analyseService.getAnalyseById(this.id).subscribe((data)=>{
    this.analyse=data
    this.epreuves=this.analyse.epreuves
  })
  this.cols = [
    { field: 'nom', header: 'Nom' },
    { field: 'resultat', header: 'Resultat' },
    { field: 'observations', header: 'Observations' }
  ];
  this.exportColumns = this.cols.map((col) => ({ title: col.header, dataKey: col.field }));
}
exportCSV() {
  this.dt.exportCSV();
}
openNew() {
  this.epreuve = { examen: {} } as Epreuve;
  this.submitted = false;
  this.epreuveDialog = true;
}

editEpreuve(epreuve: Epreuve) {
  this.epreuve = { ...epreuve };
  this.epreuveDialog = true;
}

deleteSelectedEpreuves() {
  this.confirmationService.confirm({
    message: 'Are you sure you want to delete the selected analyses?',
    header: 'Confirm',
    icon: 'pi pi-exclamation-triangle',
    accept: () => {
      this.epreuves = this.epreuves.filter((val) => !this.selectedEpreuves?.includes(val));
      this.selectedEpreuves = null;
      this.messageService.add({
        severity: 'success',
        summary: 'Successful',
        detail: 'Analyses Deleted',
        life: 3000
      });
    }
  });
}
applyFilterGlobal($event: any, stringVal: any) {
  this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
}

hideDialog() {
  this.epreuveDialog = false;
  this.submitted = false;
}

saveEpreuve() {
  this.submitted = true;

  if (this.epreuve.nom?.trim()) {
    if (this.epreuve.id) {
      this.epreuves[this.findIndexById(this.epreuve.id)] = this.epreuve;
      this.analyse.epreuves = this.epreuves;
      this.analyseService.updateAnalyse(this.id, this.analyse).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Analyse Updated',
            life: 3000
          });
        },
        error: () => console.log("Cannot update")
      });
    } else {
      this.epreuves.push(this.epreuve);
      this.analyse.epreuves = this.epreuves;
      this.analyseService.updateAnalyse(this.id, this.analyse).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Epreuve Added',
            life: 3000
          });
        },
        error: () => console.log("Cannot add")
      });
    }
    this.epreuves = [...this.epreuves];
    this.epreuveDialog = false;
    this.analyse = {} as Analyse;
  }
}


findIndexById(id: number): number {
  let index = -1;
  for (let i = 0; i < this.epreuves.length; i++) {
    if (this.epreuves[i].id === id) {
      index = i;
      break;
    }
  }

  return index;
}
}
