import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { SiderComponent } from '../../components/sider/sider.component';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-dashboard',
    imports: [RouterModule, HeaderComponent, SiderComponent],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
