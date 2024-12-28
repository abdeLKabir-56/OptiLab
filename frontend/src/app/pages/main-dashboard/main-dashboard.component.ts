import { Component } from '@angular/core';
import { CardDashComponent } from "../../components/card-dash/card-dash.component";
import { ChartActifLaboComponent } from '../../components/chart-actif-labo/chart-actif-labo.component';

@Component({
    selector: 'app-main-dashboard',
    imports: [CardDashComponent  , ChartActifLaboComponent  ],
    templateUrl: './main-dashboard.component.html',
    styleUrl: './main-dashboard.component.css'
})
export class MainDashboardComponent {

}
