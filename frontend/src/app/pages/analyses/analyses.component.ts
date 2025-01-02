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

interface Column {
  field: string;
  header: string;
  customExportHeader?: string;
}

interface ExportColumn {
  title: string;
  dataKey: string;
}

@Component({
  selector: 'app-analyses',
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
  templateUrl: './analyses.component.html',
  styleUrl: './analyses.component.css',
  styles: [
    `:host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
    }`],
    providers:[MessageService, ConfirmationService, AnalysesService]
})
export class AnalysesComponent {
  analyseDialog: boolean = false;
  cancel!: boolean;

  analyses!: Analyse[];

  analyse!: Analyse;

  selectedAnalyses!: Analyse[] | null;

  submitted: boolean = false;

  @ViewChild('dt') dt!: Table;

  cols!: Column[];

  exportColumns!: ExportColumn[];

  constructor(
    private analyseService: AnalysesService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private cd: ChangeDetectorRef
  ) {}

  exportCSV() {
    this.dt.exportCSV();
  }

  applyFilterGlobal($event: any, stringVal: any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }

  loadDemoData() {
    this.analyseService.getAnalyses().subscribe((data) => {
      this.analyses = data;
      this.cd.markForCheck();
    });

    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'laboratoire', header: 'Laboratoire' },
      { field: 'nom', header: 'Nom' },
      { field: 'description', header: 'Description' },
      { field: 'status', header: 'Status' },
      { field: 'username', header: 'Username' }
    ];

    this.exportColumns = this.cols.map((col) => ({ title: col.header, dataKey: col.field }));
  }

  ngOnInit(): void {
    this.loadDemoData();
  }

  openNew() {
    this.analyse = {} as Analyse;
    this.submitted = false;
    this.analyseDialog = true;
  }

  editAnalyse(analyse: Analyse) {
    this.analyse = { ...analyse };
    this.analyseDialog = true;
  }

  deleteSelectedAnalyses() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected analyses?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.analyses = this.analyses.filter((val) => !this.selectedAnalyses?.includes(val));
        this.selectedAnalyses = null;
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Analyses Deleted',
          life: 3000
        });
      }
    });
  }

  hideDialog() {
    this.analyseDialog = false;
    this.submitted = false;
  }

  deleteAnalyse(analyse: Analyse) {
    this.analyseService.deleteAnalyse(analyse.id).subscribe({
      next: () => {
        this.confirmationService.confirm({
          message: 'Are you sure you want to delete ' + analyse.nom + '?',
          header: 'Confirm',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
            this.analyses = this.analyses.filter((val) => val.id !== analyse.id);
            this.analyse = {} as Analyse;
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Analyse Deleted',
              life: 3000
            });
          }
        });
      },
      error: () => console.log("Cannot delete")
    });
  }

  findIndexById(id: number): number {
    let index = -1;
    for (let i = 0; i < this.analyses.length; i++) {
      if (this.analyses[i].id === id) {
        index = i;
        break;
      }
    }

    return index;
  }

  saveAnalyse() {
    this.submitted = true;

    if (this.analyse.nom?.trim()) {
      if (this.analyse.id) {
        this.analyses[this.findIndexById(this.analyse.id)] = this.analyse;
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Analyse Updated',
          life: 3000
        });
      } else {
        this.analyseService.addAnalyse(this.analyse).subscribe({
          next: (data) => {
            this.analyses.push(data);
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Analyse Created',
              life: 3000
            });
          },
          error: () => console.log("Cannot create")
        });
      }

      this.analyses = [...this.analyses];
      this.analyseDialog = false;
      this.analyse = {} as Analyse;
    }
  }
}