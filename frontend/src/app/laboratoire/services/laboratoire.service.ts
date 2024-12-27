import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Laboratoire {
  id: number;
  nom: string;
  logo: string;
  nrc: string;
  isActive: boolean;
  dateActivation: Date;
}

@Injectable({
  providedIn: 'root'
})
export class LaboratoireService {
  private apiUrl = 'http://localhost:8080/api/v1/laboratoires';

  constructor(private http: HttpClient) {}

  getAllLaboratories(): Observable<Laboratoire[]> {
    return this.http.get<Laboratoire[]>(`${this.apiUrl}`);
  }

  createLaboratory(item: Laboratoire): Observable<Laboratoire> {
    return this.http.post<Laboratoire>(`${this.apiUrl}`, item);
  }

  updateLaboratory(id: number, item: Laboratoire): Observable<Laboratoire> {
    return this.http.put<Laboratoire>(`${this.apiUrl}/${id}`, item);
  }

  deleteLaboratory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}