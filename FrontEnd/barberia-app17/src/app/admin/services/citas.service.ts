import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CitasResponse, ServiceResponse } from '../interfaces/citas.interfaces';

@Injectable({
    providedIn: 'root'
  })
  export class CitasService {
    private baseUrl = 'http://localhost:8190/api';
  
    constructor(private http: HttpClient) {}
  
    getCitas(): Observable<CitasResponse> {
      return this.http.get<CitasResponse>(`${this.baseUrl}/citas`);
    }
  
    getServicio(id: number): Observable<ServiceResponse> {
      return this.http.get<ServiceResponse>(`${this.baseUrl}/servicios/${id}`);
    }
  
    deleteCita(id: number): Observable<any> {
      return this.http.delete(`${this.baseUrl}/citas/${id}`);
    }
  
    cancelarCita(id: number): Observable<any> {
      return this.http.put(`${this.baseUrl}/citas/cancelar/${id}`, {});
    }
  }