import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { SiderComponent } from '../../components/sider/sider.component';
import { ProfileAccordingComponent } from '../../components/profile-according/profile-according.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-user-portal',
  imports: [RouterModule, HeaderComponent, SiderComponent, ProfileAccordingComponent,NgIf],
  templateUrl: './user-portal.component.html',
  styleUrl: './user-portal.component.css'
})
export class UserPortalComponent {
  collapsed = false;
  isUser = true;
  isVisible: boolean = false; 
  toggleVisibility(): void {
    this.isVisible = !this.isVisible;
    console.log('Toggling visibility. Current state:', this.isVisible);
  }

  // Hide the profile component
  hide(): void {
    this.isVisible = false;
  }
  toggleMenu() {
    this.collapsed = !this.collapsed;
  }
}
