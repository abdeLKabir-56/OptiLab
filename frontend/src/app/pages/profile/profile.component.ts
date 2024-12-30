import { ButtonModule } from 'primeng/button';
import { Component } from '@angular/core';
import { Dialog } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { AvatarModule } from 'primeng/avatar';

@Component({
  selector: 'app-profile',
  imports: [Dialog, ButtonModule, InputTextModule, AvatarModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  visible: boolean = false;

  showDialog() {
      this.visible = true;
  }
}
