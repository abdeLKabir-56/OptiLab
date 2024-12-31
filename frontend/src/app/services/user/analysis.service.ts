import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Analyse } from '../../types/analysis';

@Injectable({
  providedIn: 'root'
})
export class AnalysisService {
  private apiUrl = 'http://localhost:3000/analysis'; // JSON Server endpoint

  constructor(private http:HttpClient) { }

  getAnalyses():Observable<Analyse[]>{
    return this.http.get<Analyse[]>(this.apiUrl)
  }
}
