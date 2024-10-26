import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CitasResponse } from '../interfaces/citas.interfaces';


interface CitaResponse {
  citas: CitaData[];
  mensaje: string;
  status: string;
}

interface CitaDetailResponse {
  mensaje: string;
  cita: CitaData;
  status: string;
}

interface CitaData {
  id: number;
  datetime: string;
  service: string;
  end_time: string;
  cancelled: boolean;
  employee: string;
  customer: string;
}

@Injectable({
  providedIn: 'root'
})
export class CitasService {
  private baseUrl = 'http://localhost:8190/api';

  constructor(private http: HttpClient) {}

  getCitas(): Observable<CitasResponse> {
    return this.http.get<CitasResponse>(`${this.baseUrl}/citas`);
  }

  deleteCita(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/citas/${id}`);
  }

  getAllCitas(headers: HttpHeaders): Observable<CitaResponse> {
    return this.http.get<CitaResponse>(`${this.baseUrl}/citas`, { headers });
  }

  getCitaById(id: number, headers: HttpHeaders): Observable<CitaDetailResponse> {
    return this.http.get<CitaDetailResponse>(`${this.baseUrl}/citas/${id}`, { headers });
  }

  cancelarCita(id: number, headers: HttpHeaders): Observable<any> {
    return this.http.put(`${this.baseUrl}/cancelar/${id}`, {}, { headers });
  }
}