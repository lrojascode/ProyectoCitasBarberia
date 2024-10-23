import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private baseUrl = 'http://localhost:8090/api/customers'; // URL del endpoint backend

  constructor(private http: HttpClient) {}

  getPerfilUsuario(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
