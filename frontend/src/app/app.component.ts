import { TuiRoot } from "@taiga-ui/core";
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router, Event, NavigationEnd } from '@angular/router';




import { IStaticMethods } from 'preline/preline';
declare global {
  interface Window {
    HSStaticMethods: IStaticMethods;
  }
}

@Component({
    selector: 'app-root',
    imports: [RouterModule, TuiRoot],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {
  title = 'OptiLab';
  constructor() {
   
    }

  

  
  ngOnInit() {
    
}
}