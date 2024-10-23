import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { firstValueFrom } from 'rxjs';
import { Service } from '../models/service.interface';

@Injectable({
  providedIn: 'root',
})
export class BarberServicesService {
  private readonly _http = inject(HttpClient);
  private readonly _urlBase = environment.apiUrl;

  public async getAll() {
    const url = `${this._urlBase}/servicios`;

    const response = await firstValueFrom(
      this._http.get<{ services: Service[] }>(url),
    );

    return response.services;
  }
}
