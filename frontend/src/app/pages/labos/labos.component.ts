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
import { Laboratoire } from '../../types/laboratoire';
import { ApiService } from '../../services/api.service';
import { DropdownModule } from 'primeng/dropdown';
import { LaboratoireService } from '../../services/labos/laboratoire.service';

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
    selector: 'app-labos',
    templateUrl: './labos.component.html',
    standalone: true,
    imports: [TableModule, Dialog, SelectModule, ToastModule, ToolbarModule, ConfirmDialog, InputTextModule, TextareaModule, CommonModule, FileUpload, DropdownModule, Tag, RadioButton, Rating, InputTextModule, FormsModule, InputNumber, IconFieldModule, InputIconModule, ButtonModule],
    providers: [MessageService, ConfirmationService, ApiService],
    styles: [
        `:host ::ng-deep .p-dialog .product-image {
            width: 150px;
            margin: 0 auto 2rem auto;
            display: block;
        }`
    ]
})
export class LabosComponent implements OnInit {
    laboratoireDialog: boolean = false;
    cancel!: boolean

    laboratoires!: Laboratoire[];

    laboratoire!: Laboratoire;

    selectedLaboratoires!: Laboratoire[] | null;

    submitted: boolean = false;

    @ViewChild('dt') dt!: Table;

    cols!: Column[];

    exportColumns!: ExportColumn[];

    constructor(
        private laboratoireService: LaboratoireService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private cd: ChangeDetectorRef
    ) { }

    exportCSV() {
        this.dt.exportCSV();
    }

    applyFilterGlobal($event: any, stringVal: any) {
        this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
    }

    loadDemoData() {
        this.laboratoireService.getLaboratoires().subscribe((data) => {
            this.laboratoires = data;
            this.cd.markForCheck();
        });

        this.cols = [
            { field: 'id', header: 'ID' },
            { field: 'nom', header: 'Nom' },
            { field: 'logo', header: 'Logo' },
            { field: 'nrc', header: 'NRC' },
            { field: 'isActive', header: 'Active' },
            { field: 'dateActivation', header: 'Date Activation' }
        ];

        this.exportColumns = this.cols.map((col) => ({ title: col.header, dataKey: col.field }));
    }

    ngOnInit(): void {
        this.loadDemoData()
    }

    openNew() {
        this.laboratoire = {} as Laboratoire;
        this.submitted = false;
        this.laboratoireDialog = true;
    }

    editLaboratoire(laboratoire: Laboratoire) {
        this.laboratoire = { ...laboratoire };
        this.laboratoireDialog = true;
    }

    deleteSelectedLaboratoires() {
        this.confirmationService.confirm({
            message: 'Are you sure you want to delete the selected laboratoires?',
            header: 'Confirm',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.laboratoires = this.laboratoires.filter((val) => !this.selectedLaboratoires?.includes(val));
                this.selectedLaboratoires = null;
                this.messageService.add({
                    severity: 'success',
                    summary: 'Successful',
                    detail: 'Laboratoires Deleted',
                    life: 3000
                  });
                }
            });
        }
    
        hideDialog() {
            this.laboratoireDialog = false;
            this.submitted = false;
        }
    
        deleteLaboratoire(laboratoire: Laboratoire) {
            this.laboratoireService.deleteLaboratoire(laboratoire.id).subscribe({
                next: () => {
                    this.confirmationService.confirm({
                        message: 'Are you sure you want to delete ' + laboratoire.nom + '?',
                        header: 'Confirm',
                        icon: 'pi pi-exclamation-triangle',
                        accept: () => {
                            this.laboratoires = this.laboratoires.filter((val) => val.id !== laboratoire.id);
                            this.laboratoire = {} as Laboratoire;
                            this.messageService.add({
                                severity: 'success',
                                summary: 'Successful',
                                detail: 'Laboratoire Deleted',
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
            for (let i = 0; i < this.laboratoires.length; i++) {
                if (this.laboratoires[i].id === id) {
                    index = i;
                    break;
                }
            }
    
            return index;
        }
    
        saveLaboratoire() {
            this.submitted = true;
    
            if (this.laboratoire.nom?.trim()) {
                if (this.laboratoire.id) {
                    this.laboratoires[this.findIndexById(this.laboratoire.id)] = this.laboratoire;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Successful',
                        detail: 'Laboratoire Updated',
                        life: 3000
                    });
                } else {
                    this.laboratoireService.addLaboratoire(this.laboratoire).subscribe({
                        next: (data) => {
                            this.laboratoires.push(data);
                            this.messageService.add({
                                severity: 'success',
                                summary: 'Successful',
                                detail: 'Laboratoire Created',
                                life: 3000
                            });
                        },
                        error: () => console.log("Cannot create")
                    });
                }
    
                this.laboratoires = [...this.laboratoires];
                this.laboratoireDialog = false;
                this.laboratoire = {} as Laboratoire;
            }
        }
    }