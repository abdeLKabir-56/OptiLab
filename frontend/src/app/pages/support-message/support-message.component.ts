import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { Dialog } from 'primeng/dialog';
import { EditorModule } from 'primeng/editor';


@Component({
  selector: 'app-support-message',
  imports: [Dialog, ButtonModule , EditorModule,FormsModule],
  templateUrl: './support-message.component.html',
  styleUrl: './support-message.component.css'
})
export class SupportMessageComponent {
  visible: boolean = true;
  text:string | undefined
}
