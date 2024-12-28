import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router, Event, NavigationEnd } from '@angular/router';
import { PrimeNG } from 'primeng/config';
import Aura from '@primeng/themes/aura';




@Component({
    selector: 'app-root',
    imports: [RouterModule],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {
  title = 'OptiLab';
  constructor(private primeng: PrimeNG) {}

    ngOnInit() {
        this.primeng.theme.set({
            preset: Aura,
                options: {
                    cssLayer: {
                        name: 'primeng',
                        order: 'tailwind-base, primeng, tailwind-utilities'
                    }
                }
            })
        }
    }
