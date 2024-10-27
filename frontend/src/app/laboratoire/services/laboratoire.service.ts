import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Laboratoire {
  id: number;
  nom: string;
  logo: string;
  nrc: string;
  isActive: boolean;
  dateActivation: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class LaboratoireService {
  private apiUrl = 'http://localhost:8050/api/v1/laboratory';

  constructor(private http: HttpClient) { }

  // Fetch all laboratories
  getAllLaboratories(): Observable<Laboratoire[]> {
    return this.http.get<Laboratoire[]>(this.apiUrl);
  }

  // Get a specific laboratory by ID
  getLaboratoryById(id: number): Observable<Laboratoire> {
    return this.http.get<Laboratoire>(`${this.apiUrl}/${id}`);
  }

  // Create a new laboratory
  createLaboratory(item: Laboratoire): Observable<Laboratoire> {
    return this.http.post<Laboratoire>(this.apiUrl, item);
  }

  // Update an existing laboratory by ID
  updateLaboratory(id: number, item: Laboratoire): Observable<Laboratoire> {
    return this.http.put<Laboratoire>(`${this.apiUrl}/${id}`, item);
  }

  // Delete a laboratory by ID
  deleteLaboratory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
