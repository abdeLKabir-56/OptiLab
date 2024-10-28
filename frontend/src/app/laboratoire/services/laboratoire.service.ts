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
  private apiUrl = 'http://localhost:8050/api/v1/laboratory';

  constructor(private http: HttpClient) {}

  getAllLaboratories(): Observable<Laboratoire[]> {
    return this.http.get<Laboratoire[]>(`${this.apiUrl}/all`);
  }

  getLaboratoryById(id: number): Observable<Laboratoire> {
    return this.http.get<Laboratoire>(`${this.apiUrl}/${id}`);
  }

  createLaboratory(item: Laboratoire): Observable<Laboratoire> {
    return this.http.post<Laboratoire>(`${this.apiUrl}/add`, item);
  }

  updateLaboratory(id: number, item: Laboratoire): Observable<Laboratoire> {
    return this.http.put<Laboratoire>(`${this.apiUrl}/update/${id}`, item);
  }

  deleteLaboratory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}