import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-login',
    imports: [NgIf],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {
  @Input() isOpen!:boolean
  @Output() closeModal=new EventEmitter()

  onClose(){
    this.closeModal.emit()
  }

}
