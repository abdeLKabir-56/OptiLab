import { Component, ElementRef, Output, EventEmitter } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { SiderComponent } from '../../components/sider/sider.component';
import { RouterModule } from '@angular/router';
import { ProfileAccordingComponent } from '../../components/profile-according/profile-according.component';
import { ClickOutsideDirective } from '../../directives/click-outside.directive';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule, HeaderComponent, SiderComponent, ProfileAccordingComponent,NgIf,ClickOutsideDirective ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  collapsed = false;
  isUser = false;
  isVisible: boolean = false; 

  // Toggle the side menu visibility
  toggleMenu() {
    this.collapsed = !this.collapsed;
  }

  // Toggle the profile visibility
  
}
