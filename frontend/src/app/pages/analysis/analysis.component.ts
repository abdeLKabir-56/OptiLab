import { jsPDF } from 'jspdf';
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

generateAnalysisPdf(analyse:Analyse) {
  const doc = new jsPDF();

  // Add double logos
  const logoLeft = '../../../assets/logo.jpg'; // Replace with your left logo Base64
  const logoRight = 'https://cdn.shopify.com/s/files/1/0276/6910/2657/files/LABO_logo_Gros_Prune_RVB.png?v=1657216825'; // Replace with your right logo Base64

  doc.addImage(logoLeft, 'PNG', 10, 10, 40, 20); // Left logo
  doc.addImage(logoRight, 'PNG', 160, 10, 40, 20); // Right logo

  // Add title and subtitle
  doc.setFont('Helvetica', 'bold');
  doc.setFontSize(18);
  doc.text(analyse.laboratoire, 105, 40, { align: 'center' });

  doc.setFont('Helvetica', 'normal');
  doc.setFontSize(14);
  doc.text(analyse.nom, 105, 50, { align: 'center' });

  // Add informational text
  doc.setFontSize(12);
  doc.text('This is your analysis result for : '+analyse.description, 10, 70);

  // Add table
  const tableData = [
    ['Epreuve', 'Resultat', 'Observation'], // Table header
  ];
  analyse.epreuves.forEach((element)=>{
    tableData.push([element.nom , element.examen.resultat!,element.examen.observations!])
  })

  const startX = 10;
  const startY = 90;
  const cellWidth = 70;
  const cellHeight = 10;
  let currentY = startY; // Keep track of the current Y position

  // Draw headers
  tableData.forEach((row, rowIndex) => {
    let maxRowHeight = 0; // Keep track of the maximum height in the row

    row.forEach((cell, cellIndex) => {
      const x = startX + cellIndex * cellWidth;

      // Adjust font size for header row
      if (rowIndex === 0) {
        doc.setFontSize(10); // Smaller font for header
        doc.setFont('Helvetica', 'bold');
      } else {
        doc.setFontSize(12); // Normal font for rows
        doc.setFont('Helvetica', 'normal');
      }

      // Calculate cell height based on text content
      const textDimensions = doc.getTextDimensions(cell);
      const lineHeight = textDimensions.h; // Single-line height
      const cellHeight = Math.max(lineHeight * (cell.split('\n').length || 1), 10); // At least 10

      maxRowHeight = Math.max(maxRowHeight, cellHeight); // Update row height if larger

      // Draw cell border
      doc.rect(x, currentY, cellWidth, cellHeight);

      // Add text inside cell
      const textX = x + 2; // Add padding
      const textY = currentY + 7; // Adjust Y padding
      doc.text(cell, textX, textY);
    });

    currentY += maxRowHeight; // Move to the next row
  });
  // Save or open the PDF
  doc.save('analysis.pdf');
}

  

}
