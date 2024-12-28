import { Component } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { ChangeDetectorRef, effect, inject, OnInit, PLATFORM_ID } from '@angular/core';
import { ChartModule } from 'primeng/chart';

@Component({
  selector: 'app-chart-actif-labo',
  imports: [ChartModule],
  templateUrl: './chart-actif-labo.component.html',
  styleUrl: './chart-actif-labo.component.css'
})
export class ChartActifLaboComponent implements OnInit {
  data: any;

  options: any;

  platformId = inject(PLATFORM_ID);



  constructor(private cd: ChangeDetectorRef) {}

  

  ngOnInit() {
      this.initChart();
  }

  initChart() {
      if (isPlatformBrowser(this.platformId)) {
          const documentStyle = getComputedStyle(document.documentElement);
          const textColor = documentStyle.getPropertyValue('--p-text-color');
          const textColorSecondary = documentStyle.getPropertyValue('--p-text-muted-color');
          const surfaceBorder = documentStyle.getPropertyValue('--p-content-border-color');

          this.data = {
              labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
              datasets: [
                  {
                      label: 'Actif Labos',
                      backgroundColor: '#64C8FF',
                      borderColor: documentStyle.getPropertyValue('--p-cyan-500'),
                      data: [65, 59, 80, 81, 56, 55, 40],
                      borderRadius: 10,
                      barThickness: 10, 
                  },
                  {
                      label: 'Inactif labos',
                      backgroundColor: '#5d87ff',
                      borderColor: documentStyle.getPropertyValue('--p-gray-500'),
                      data: [28, 48, 40, 19, 86, 27, 90],
                      borderRadius: 10,
                      barThickness: 10,
                  }
              ]
          };

          this.options = {
              maintainAspectRatio: false,
              aspectRatio: 0.8,
              plugins: {
                  legend: {
                      labels: {
                          color: textColor
                      }
                  }
              },
              scales: {
                  x: {
                      ticks: {
                          color: textColorSecondary,
                          font: {
                              weight: 500
                          }
                      },
                      grid: {
                          color: surfaceBorder,
                          drawBorder: false
                      }
                  },
                  y: {
                      ticks: {
                          color: textColorSecondary
                      },
                      grid: {
                          color: surfaceBorder,
                          drawBorder: false
                      }
                  }
              }
          };
          this.cd.markForCheck()
      }
  }
}
