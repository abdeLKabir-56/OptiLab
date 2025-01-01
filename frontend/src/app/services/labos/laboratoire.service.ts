import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Laboratoire } from '../../types/laboratoire';

@Injectable({
  providedIn: 'root',
})
export class LaboratoireService {
  private apiUrl = 'http://localhost:3000/laboratoire'; // JSON Server endpoint

  constructor(private http: HttpClient) {}

  /**
   * Get all laboratoires
   */
  getLaboratoires(): Observable<Laboratoire[]> {
    return this.http.get<Laboratoire[]>(this.apiUrl);
  }

  /**
   * Get a single laboratoire by ID
   */
  getLaboratoireById(id: number): Observable<Laboratoire> {
    return this.http.get<Laboratoire>(`${this.apiUrl}/${id}`);
  }

  /**
   * Add a new laboratoire
   */
  addLaboratoire(laboratoire: Laboratoire): Observable<Laboratoire> {
    return this.http.post<Laboratoire>(this.apiUrl, laboratoire);
  }

  /**
   * Update an existing laboratoire
   */
  updateLaboratoire(id: number, laboratoire: Laboratoire): Observable<Laboratoire> {
    return this.http.put<Laboratoire>(`${this.apiUrl}/${id}`, laboratoire);
  }

  /**
   * Delete a laboratoire by ID
   */
  deleteLaboratoire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
