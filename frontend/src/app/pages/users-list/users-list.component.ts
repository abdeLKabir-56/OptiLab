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
import { User, UserService } from '../../services/admin/users.service';
import { ActivatedRoute } from '@angular/router';

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
  selector: 'app-users-list',
  imports: [TableModule, Dialog, SelectModule, ToastModule, ToolbarModule, ConfirmDialog, InputTextModule, TextareaModule, CommonModule, FileUpload, DropdownModule, Tag, RadioButton, Rating, InputTextModule, FormsModule, InputNumber, IconFieldModule, InputIconModule, ButtonModule],
  providers: [MessageService, ConfirmationService, ApiService],
  styles: [
      `:host ::ng-deep .p-dialog .product-image {
          width: 150px;
          margin: 0 auto 2rem auto;
          display: block;
      }`
  ],
  templateUrl: './users-list.component.html',
  
})
export class UsersListComponent {
  users: User[] = [];
  userDialog: boolean = false;
  user: User = {} as User;
  selectedUsers: User[] = [];
  submitted: boolean = false;
  cols: any[] = [];

  constructor(private userService: UserService, private messageService: MessageService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loadUsers();
    this.cols = [
      { field: 'prenom', header: 'Prenom' },
      { field: 'nom', header: 'Nom' },
      { field: 'email', header: 'Email' },
      { field: 'adress', header: 'Adresse' },
      { field: 'telephone', header: 'Téléphone' }
    ];
  }

  loadUsers() {
    this.userService.getUsers().subscribe((data) => {
      this.users = data;
    });
  }

  openNew() {
    this.user = {} as User;
    this.submitted = false;
    this.userDialog = true;
  }

  editUser(user: User) {
    this.user = { ...user };
    this.userDialog = true;
  }

  saveUser() {
    this.submitted = true;

    if (this.user.prenom?.trim()) {
      if (this.user.id) {
        this.userService.updateUser(this.user.id, this.user).subscribe(() => {
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Updated', life: 3000 });
        });
      } else {
        this.userService.createUser(this.user).subscribe((data) => {
          this.users.push(data);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Created', life: 3000 });
        });
      }
      this.userDialog = false;
    }
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.id).subscribe(() => {
      this.users = this.users.filter((val) => val.id !== user.id);
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Deleted', life: 3000 });
    });
  }
  hideDialog() {
    this.userDialog = false;
    this.submitted = false;
  }

  applyFilterGlobal($event: any, stringVal: any) {
    this.users = this.users.filter((val) => {
      return Object.values(val).some((value) => {
        return value.toString().toLowerCase().includes(stringVal.toLowerCase());
      });
    });
  }

}
