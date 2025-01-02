import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Analyse } from '../../types/analysis-admin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalysesService {
 private apiUrl = 'http://localhost:3000/analyses'; // JSON Server endpoint

  constructor(private http: HttpClient) {}

  /**
   * Get all laboratoires
   */
  getAnalyses(): Observable<Analyse[]> {
    return this.http.get<Analyse[]>(this.apiUrl);
  }

  /**
   * Get a single laboratoire by ID
   */
  getAnalyseById(id: number): Observable<Analyse> {
    return this.http.get<Analyse>(`${this.apiUrl}/${id}`);
  }

  /**
   * Add a new laboratoire
   */
  addAnalyse(analyse: Analyse): Observable<Analyse> {
    return this.http.post<Analyse>(this.apiUrl, analyse);
  }

  /**
   * Update an existing laboratoire
   */
  updateAnalyse(id: number, analyse: Analyse): Observable<Analyse> {
    return this.http.put<Analyse>(`${this.apiUrl}/${id}`, analyse);
  }

  /**
   * Delete a laboratoire by ID
   */
  deleteAnalyse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
