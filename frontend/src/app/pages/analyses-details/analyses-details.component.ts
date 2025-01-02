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
analyse:Analyse={} as Analyse
epreuves: Epreuve[] = [];
  epreuveDialog: boolean = false;
  deleteEpreuveDialog: boolean = false;
  deleteEpreuvesDialog: boolean = false;
  epreuve!: Epreuve ;
  selectedEpreuves: Epreuve[] = [];
  submitted: boolean = false;
  cols: any[] = [];
  exportColumns: any[] = [];
  @ViewChild('dt') dt!: Table;
constructor(private router:ActivatedRoute  , private analyseService:AnalysesService , private messageService: MessageService,
  private confirmationService: ConfirmationService){}
ngOnInit() {
  const id = Number(this.router.snapshot.paramMap.get('id'));
  this.analyseService.getAnalyseById(id).subscribe((data)=>{
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
  this.epreuve = {} as Epreuve;
  this.submitted = false;
  this.epreuveDialog = true;
}
editAnalyse(epreuve: Epreuve) {
  this.epreuve = { ...epreuve };
  this.epreuveDialog = true;
}
}
