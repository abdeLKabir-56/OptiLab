import { Component } from '@angular/core';
import { AnalysisService } from '../../services/user/analysis.service';
import { Analyse } from '../../types/analysis';
import { TagModule } from 'primeng/tag';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analysis',
  imports: [TableModule, TagModule, ButtonModule, CommonModule],
  templateUrl: './analysis.component.html',
  styleUrl: './analysis.component.css',
  providers:[AnalysisService]
})
export class AnalysisComponent {
  analyses:Analyse[]=[]
  constructor(private analyseService:AnalysisService){

  }
  ngOnInit(): void {
    this.analyseService.getAnalyses().subscribe({
      next:(data)=>{
        this.analyses=data
        console.log(data)
      },
      error:()=>{console.log("something went wrong")}
    })
    
  }
  getSeverity(status: string){
    switch (status) {
        case 'resolved':
            return 'success';
        case 'pending':
            return 'warn';
        default:
          return 'warn'
    }
}
}
