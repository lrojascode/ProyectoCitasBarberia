import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { firstValueFrom } from 'rxjs';
import { Profesional } from '../models/profesional.interface';

@Injectable({
  providedIn: 'root',
})
export class ProfesionalsService {
  private readonly _http = inject(HttpClient);
  private readonly _urlBase = environment.apiUrl;

  public async getAll() {
    const url = `${this._urlBase}/employees/enable`;

    const response = await firstValueFrom(
      this._http.get<{ empleados: Profesional[] }>(url),
    );

    return response.empleados;
  }
}
